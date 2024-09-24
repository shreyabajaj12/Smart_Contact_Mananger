package com.scm.config;

import com.scm.services.SecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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

   @Bean
   public AuthenticationProvider authenticationProvider(){
       DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
       //userDetails service ka object
       daoAuthenticationProvider.setUserDetailsService(userDetailsService);
       //password encoder ka object
       daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
       return daoAuthenticationProvider;
   }
   @Bean
   public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }


}
