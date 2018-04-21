package org.softuni.accounting.config;

import org.softuni.accounting.interceptors.FirstToRegisterHasAllRolesInterceptor;
import org.softuni.accounting.interceptors.OnAppStartSeedRolesInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppMvcConfig implements WebMvcConfigurer {

    private final OnAppStartSeedRolesInterceptor onAppStartSeedRolesInterceptor;
    private final FirstToRegisterHasAllRolesInterceptor firstToRegisterHasAllRolesInterceptor;

    @Autowired
    public AppMvcConfig(OnAppStartSeedRolesInterceptor onAppStartSeedRolesInterceptor,
                        FirstToRegisterHasAllRolesInterceptor firstToRegisterHasAllRolesInterceptor) {

        this.onAppStartSeedRolesInterceptor = onAppStartSeedRolesInterceptor;
        this.firstToRegisterHasAllRolesInterceptor = firstToRegisterHasAllRolesInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(this.onAppStartSeedRolesInterceptor)
                .addPathPatterns("/","/users/register","/users/login");

        registry.addInterceptor(this.firstToRegisterHasAllRolesInterceptor)
                .addPathPatterns("/users/login");
    }
}
