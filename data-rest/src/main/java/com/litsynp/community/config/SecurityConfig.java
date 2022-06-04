package com.litsynp.community.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(CorsConfiguration.ALL);
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        http
                    .httpBasic()
                .and()
                    .authorizeRequests()
                    .anyRequest().permitAll()
                .and()
                    .cors().configurationSource(source)
                .and()
                    .csrf().disable();
    }

    @Bean
    InMemoryUserDetailsManager userDetailsManager() {
        User.UserBuilder commonUser = User
                .withUsername("commonUser")
                .password("{noop}common")  // encoding 방식 지정하지 않기 위해 {noop} 지정
                .roles("USER");

        User.UserBuilder litsynp = User
                .withUsername("litsynp")
                .password("{noop}test")
                .roles("ADMIN");

        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(commonUser.build());
        userDetailsList.add(litsynp.build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }
}
