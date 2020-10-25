package com.applaudo.restapi.security;

import static com.applaudo.restapi.security.SecurityConstants.APP_ITEM;
import static com.applaudo.restapi.security.SecurityConstants.H2_DB_CONSOLE;
import static com.applaudo.restapi.security.SecurityConstants.SIGN_UP_URL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.applaudo.restapi.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private UserDetailsServiceImpl userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
				.antMatchers(HttpMethod.GET, APP_ITEM).permitAll().antMatchers(H2_DB_CONSOLE).permitAll().and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService)).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		List<String> list = new ArrayList<>();
		list.add("*");
		configuration.setAllowedOrigins(list);
		list = getHttpMethods();
		configuration.setAllowedMethods(list);
		configuration.setAllowCredentials(true);
		list = new ArrayList<>();
		list.add("*");
		configuration.setAllowedHeaders(list);
		list = getExposedHeaders();
		configuration.setExposedHeaders(list);
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	private List<String> getExposedHeaders() {
		List<String> list = new ArrayList<>();
		list.add("X-Auth-Token");
		list.add("Authorization");
		list.add("Access-Control-Allow-Origin");
		list.add("Access-Control-Alow-Credenttials");
		return list;
	}

	private List<String> getHttpMethods() {
		List<String> list = new ArrayList<>();
		list.add("GET");
		list.add("POST");
		list.add("PUT");
		list.add("DELETE");
		list.add("PATCH");
		list.add("OPTIONS");
		list.add("GET");
		return list;
	}
}
