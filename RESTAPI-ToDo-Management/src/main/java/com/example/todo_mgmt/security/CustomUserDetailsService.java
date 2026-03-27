package com.example.todo_mgmt.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.todo_mgmt.entity.User;
import com.example.todo_mgmt.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String usernameorEmail) throws UsernameNotFoundException {

		System.out.println("DEBUG: username = " + usernameorEmail);

		User user = userRepo.findByUsernameOrEmail(usernameorEmail, usernameorEmail)
				.orElseThrow(() -> new UsernameNotFoundException("Username or email not found in DB"));

		// Converting roles from DB into authorities that Spring understands.
		Set<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

		return new org.springframework.security.core.userdetails.User(usernameorEmail, user.getPassword(), authorities);
	}

}
