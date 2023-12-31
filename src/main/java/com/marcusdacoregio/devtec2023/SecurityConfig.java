package com.marcusdacoregio.devtec2023;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(authorize -> authorize
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults())
				.formLogin(form -> form
						.loginPage("/login")
						.permitAll()
				)
				.logout(Customizer.withDefaults());
		return http.build();
	}

//	@Bean
//	UserDetailsService userDetailsService() {
//		UserDetails marcus = User.withDefaultPasswordEncoder().username("marcus").password("password").roles("USER").build();
//		UserDetails john = User.withDefaultPasswordEncoder().username("john").password("password").roles("USER").build();
//		return new InMemoryUserDetailsManager(marcus, john);
//	}

}
