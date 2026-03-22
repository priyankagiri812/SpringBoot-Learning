package com.example.todo_mgmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		/*
		 * CSRF (Cross-Site Request Forgery) is a security attack where a malicious
		 * website tricks a user’s browser into making an unwanted request to another
		 * site where the user is already authenticated.
		 */

		// Disable CSRF for simplicity (useful for APIs / Postman testing)
		http.csrf(csrf -> csrf.disable())

				// Authorization rules
				.authorizeHttpRequests((auth) -> {
					// auth.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
					// auth.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
					// auth.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
					// auth.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
					// auth.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN",
					// "USER");
					auth.anyRequest().authenticated();
				})

				// Enable Basic Authentication
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {

		UserDetails priyanka = User.builder().username("priyanka").password(passwordEncoder().encode("password"))
				.roles("USER").build();

		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("password"))
				.roles("ADMIN").build();

		return new InMemoryUserDetailsManager(priyanka, admin);

	}
}
