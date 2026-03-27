package com.example.todo_mgmt.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Execute before Spring Security Filters
//Validate the JWT token and provides user details to Spring Security for Authentication.

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	private JwtTokenProvider jwtTokenProvider;
	
	private UserDetailsService userDetailsService;
	
	
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
					
									FilterChain filterChain) throws ServletException, IOException {
		
		logger.info("JWT Filter triggered for request: {}", request.getRequestURI());
		
		logger.info("Extracting token from Authorization header");
		//Get JWT token from HTTP request
		String token = getTokenFromRequest(request);
		
		//Validate Token
		if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
			
			logger.info("JWT token found");
			
			//Get Username from token
			String username = jwtTokenProvider.extractUsername(token);
			
			logger.info("Username from token: {}", username);
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					userDetails, 
					null,
					userDetails.getAuthorities());
				
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			logger.info("Setting authentication in SecurityContext for user: {}", username);
			
			SecurityContextHolder.getContext().setAuthentication(authToken);
			
		}
		
		filterChain.doFilter(request, response);
		
		
	}
	
	private String getTokenFromRequest(HttpServletRequest request) {
		
		String bearerToken = request.getHeader("Authorization");
		
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			
			return bearerToken.substring(7, bearerToken.length());
		}
		
		return null;
	}

}
