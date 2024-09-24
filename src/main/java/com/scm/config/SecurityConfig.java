package com.scm.config;

import com.scm.services.SecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
//   user create and login using java code with in memory service
    /*
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user= User
                .withDefaultPasswordEncoder()
                .username("shreya")
                .password("shreya")
                .roles("ADMIN","USER")
                .build();
        return new InMemoryUserDetailsManager(user);}*/

    @Autowired
    private SecurityCustomUserDetailService userDetailsService;

//    configuration of authentication provider for spring security

   @Bean
   public DaoAuthenticationProvider authenticationProvider(){
       DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
       //userDetails service ka object
       daoAuthenticationProvider.setUserDetailsService(userDetailsService);
       //password encoder ka object
       daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
       return daoAuthenticationProvider;
   }
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http, HttpSecurity httpSecurity) throws Exception {

//       configuration
//       it is a configuration to determine which site is going to be public and which is going to be private
       httpSecurity.authorizeHttpRequests(authorize->{
//           authorize.requestMatchers("/home","/register").permitAll();
           authorize.requestMatchers("/user/**").authenticated();
           authorize.anyRequest().permitAll();
       });

       //default login
       //agar hume kuch change krna hua related to form login we will come here
       httpSecurity.formLogin(Customizer.withDefaults());

       return httpSecurity.build();
   }

   @Bean
   public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }


}
