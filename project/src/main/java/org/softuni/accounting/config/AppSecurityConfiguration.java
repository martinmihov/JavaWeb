package org.softuni.accounting.config;

import org.softuni.accounting.areas.users.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userDetailsService;
    private final SessionRegistry sessionRegistry;

    @Autowired
    public AppSecurityConfiguration(UserServiceImpl userDetailsService, SessionRegistry sessionRegistry) {
        this.userDetailsService = userDetailsService;
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/images/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/users/login", "/users/register", "/blog/").permitAll()
                .anyRequest().authenticated()
                    .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/users/login").permitAll()
                .loginProcessingUrl("/users/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                    .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/users/login").permitAll()
                    .and()
                .userDetailsService(userDetailsService)
                .csrf().disable()
                .sessionManagement()
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry);

    }
}