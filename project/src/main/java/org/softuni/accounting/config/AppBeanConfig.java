package org.softuni.accounting.config;

import org.modelmapper.ModelMapper;
import org.softuni.accounting.utils.parser.ModelParserImpl;
import org.softuni.accounting.utils.parser.interfaces.ModelParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppBeanConfig {

    @Bean
    public ModelParser modelParser() {
        return new ModelParserImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
