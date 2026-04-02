package com.example.security.config;


import com.example.security.filter.JwtAuthFilter;
import com.example.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter;
    private final CustomUserDetailsService customUserDetailsService;
    @Bean
  public   SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
       http.csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(auth->
                       auth
                               .requestMatchers("/auth/**").permitAll()
                               .anyRequest().authenticated());
       http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

       return http.build();

    }


//                      InMemory user create
//    @Bean
//    UserDetailsService userDetailsService(){
//        UserDetails user1= User.withUsername("admin")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user2=User.withUsername("user")
//                .password(passwordEncoder().encode("12345"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);


        provider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(provider);
    }
}
