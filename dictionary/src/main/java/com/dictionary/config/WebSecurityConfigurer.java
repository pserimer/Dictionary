// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    public AuthenticationConverter authenticationConverter() {
        return new BasicAuthenticationConverter();
    }

    public AuthenticationManagerResolver<HttpServletRequest> resolver() {
        return request -> authenticationManager();
    }

    public AuthenticationManager authenticationManager() {
        return authentication -> new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(),
                authentication.getCredentials(),
                Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
    }

    private AuthenticationFilter authenticationFilter() {
        AuthenticationFilter filter = new AuthenticationFilter(
                resolver(), authenticationConverter());
        filter.setSuccessHandler((request, response, auth) -> {});

        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) {
        http.addFilterBefore(
                authenticationFilter(),
                BasicAuthenticationFilter.class
        );
    }
}
