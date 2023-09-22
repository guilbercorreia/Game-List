package com.project.Game.list.config;

import com.project.Game.list.services.SecurityDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
   @Autowired
   private SecurityDBService securityService;

   @Bean
   public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder(12);
   }
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf(csfr -> csfr.disable())
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(new AntPathRequestMatcher("/games/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("h2-console")).hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .headers(headers -> headers.disable())
            .httpBasic(Customizer.withDefaults()).build();
  }

}
