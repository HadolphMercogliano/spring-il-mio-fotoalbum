package com.learning.java.springIlMioFotoalbum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

//  UserDetailsService
  @Bean
  DatabaseUserDetailsService userDetailsService() {
    return new DatabaseUserDetailsService();
  }
  
//  PasswordEncoder(prende l'algoritmo di encoding da una stringa nella password stessa)
  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
  
  @Bean
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(userDetailsService());
    return provider;
  }
  
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
      .requestMatchers("/photos").hasAuthority("ADMIN")
      .requestMatchers("/photos/*").hasAuthority("ADMIN")
      .requestMatchers("/photos/create").hasAuthority("ADMIN")
      .requestMatchers("/photos/edit/**").hasAuthority("ADMIN")
      .requestMatchers("/photos/delete/*").hasAuthority("ADMIN")
      .requestMatchers(HttpMethod.POST, "/photos/**").hasAuthority("ADMIN")
      .requestMatchers("/categories/**").hasAuthority("ADMIN")
      .requestMatchers("/discounts/**").hasAuthority("ADMIN")
      .requestMatchers("/api/**").permitAll()
      .requestMatchers("/**").permitAll()
      .and().formLogin()
      .and().logout();
    http.csrf().disable();
    return http.build();
  }
}
