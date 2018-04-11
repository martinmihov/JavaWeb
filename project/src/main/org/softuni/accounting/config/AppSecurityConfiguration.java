package org.softuni.accounting.config;

import org.softuni.accounting.areas.users.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userDetailsService;

    @Autowired
    public AppSecurityConfiguration(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/users/login", "/users/register","/blog/").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/users/login").permitAll()
                .loginProcessingUrl("/users/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
//                    .failureUrl("/error")
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/users/login")
                .permitAll()
                .and()
//                .rememberMe()
//                .rememberMeParameter("remember")
//                .rememberMeCookieName("rememberMeCookie")
//                .key("48433e39-e610-4a2c-926c-f86d46f5360a")
//                .tokenValiditySeconds(100)
                .userDetailsService(userDetailsService)
                .csrf().disable();

    }
}
