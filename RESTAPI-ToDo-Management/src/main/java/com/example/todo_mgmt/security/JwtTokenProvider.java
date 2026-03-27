package com.example.todo_mgmt.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.todo_mgmt.controller.AuthController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	
	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private long jwtExpirtaionMilliseconds;
	
	// 🔐 Generate JWT Token
	public String generateToken(Authentication authentication) {
		
		logger.info("Generating token for user: {}", authentication.getDetails());
		
		String username=authentication.getName();
		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + jwtExpirtaionMilliseconds);
		
        return Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expirationDate)
                .signWith(key())
                .compact();
		
	}
	
	// 🔑 Secret key
	public Key key() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}
	
	// 🔍 Extract username from token
	public String extractUsername(String token) {
		
		logger.info("Parsing JWT token");
		
        String username= Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        
        logger.info("Extracted username from token: {}", username);
        
        return username;
	}
	  
	    // ✅ Validate token
	public boolean validateToken(String token){
		
		logger.info("Validating JWT token");
		
        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
        return true;
    }
}
