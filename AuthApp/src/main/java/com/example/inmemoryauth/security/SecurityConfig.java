package com.example.inmemoryauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( // which URLs can be accessed by whom.
                registry->{
                    registry.requestMatchers("/home","/login","/register/**").permitAll(); // accessed by anyone
                    registry.requestMatchers("/user/**").hasRole("USER"); // accessed by users only
                    registry.requestMatchers("/admin/**").hasRole("ADMIN"); // accessed by admins only
                    registry.anyRequest().authenticated(); // must be authenticated to access what is not above
                }
                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails normalUser = User.builder()
//                .username("user")
//                .password("{noop}12345")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}admin")
//                .roles("ADMIN","USER")
//                .build();
//        return new InMemoryUserDetailsManager(normalUser,admin);
//
//    }

    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }








}
