package com.scm.config;

import com.scm.services.SecurityCustomUserDetailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

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
       httpSecurity.formLogin(formLogin->{
           formLogin.loginPage("/login");
           formLogin.loginProcessingUrl("/authenticate"); //when page is going to submit it is going to submit on authenticate
           formLogin.defaultSuccessUrl("/user/dashboard");
//           formLogin.failureForwardUrl("/login?error=true");
           formLogin.usernameParameter("email");
           formLogin.passwordParameter("password");
           /*
           formLogin.failureHandler(new AuthenticationFailureHandler() {
               @Override
               public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

               }
           });
           formLogin.successHandler(new AuthenticationSuccessHandler() {
               @Override
               public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

               }
           });*/
       });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
       httpSecurity.logout(logout->{
           logout.logoutUrl("/logout");
           logout.logoutSuccessUrl("/login?logout=true");
       });

       return httpSecurity.build();
   }

   @Bean
   public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }


}
