package com.spring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.prefs.Preferences;

@Configuration
public class CommonConfiguration {

    @Bean
    public SpringApplicationContext springApplicationContext() {
        return new SpringApplicationContext();
    }

    @Bean
    public Preferences systemCache() {
        return Preferences.systemRoot();
    }

    @Bean
    public Preferences userCache() {
        return Preferences.userRoot();
    }
}
