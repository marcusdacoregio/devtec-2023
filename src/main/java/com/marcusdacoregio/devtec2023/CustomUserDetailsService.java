package com.marcusdacoregio.devtec2023;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final Map<String, CustomUserDetails> users = Map.of(
			"marcus", new CustomUserDetails("marcus", "{bcrypt}$2a$10$xSXdNAhXn70nbeSBfrjg0ekxMdFBZvjjz3PhGrYed7LsKTbqWguuy"), // password
			"john", new CustomUserDetails("john", "{bcrypt}$2a$10$TGOu2N8wzP/1cMBjKFkN2uUl/DfMXnwTR5.Zw3HNMyXJTuoHHMMQ.") // password
	);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserDetails user = this.users.get(username);
		if (user != null) {
			return user;
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}

}
