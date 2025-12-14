package org.example.HCDD_412_Project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @SuppressWarnings("Convert2MethodRef")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                    // permit all users to have access to the URLs needed for registration
                    .antMatchers("/", "/games/**", "/users/user_list", "/users/showNewUserForm", "/users/saveUser", "/css/**",
                                  "/js/**").permitAll()
                    // users will need to be logged in to access other pages
                    .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                     // let all users have access to the log in page
                     .permitAll()
                )
                // all users have the ability to log out
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
}
