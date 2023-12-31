package com.project.Game.list.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

   @Bean
   public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder(12);
   }
   
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf(csfr -> csfr.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(AntPathRequestMatcher.antMatcher (HttpMethod.DELETE , "/games/**")).hasRole("ADMIN")
            .requestMatchers(AntPathRequestMatcher.antMatcher (HttpMethod.POST , "/games/**")).hasRole("ADMIN")
            .requestMatchers(AntPathRequestMatcher.antMatcher ( HttpMethod.PUT,"/games/**" )).hasRole("ADMIN")
            .requestMatchers(AntPathRequestMatcher.antMatcher (HttpMethod.DELETE ,"/lists/**")).hasRole("ADMIN")
            .requestMatchers(AntPathRequestMatcher.antMatcher (HttpMethod.POST, "/lists/**")).hasRole("ADMIN")
            .requestMatchers(AntPathRequestMatcher.antMatcher (HttpMethod.PUT, "/lists/**")).hasRole("ADMIN")
            .requestMatchers(AntPathRequestMatcher.antMatcher (HttpMethod.POST, "/users/**")).permitAll()
            .requestMatchers(AntPathRequestMatcher.antMatcher (HttpMethod.DELETE, "/users/**")).hasAnyRole("ADMIN", "USER")
            .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
            .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll()
            .requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**")).permitAll()
            .anyRequest().authenticated()
        )
        .headers(headers -> headers.disable())
        .httpBasic(Customizer.withDefaults()).build();
  }
}
