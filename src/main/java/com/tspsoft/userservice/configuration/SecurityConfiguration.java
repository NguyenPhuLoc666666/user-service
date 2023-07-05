package com.tspsoft.userservice.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(
						HttpMethod.OPTIONS, 
						"/api/v0/user/**")
				.permitAll()
				.requestMatchers(HttpMethod.POST, "/api/v0/user/register")
				.permitAll()
				.anyRequest().authenticated()
				)
		.oauth2ResourceServer(oauth2 -> oauth2
				.jwt(Customizer.withDefaults())
				);
		return http.build();
	}

}
