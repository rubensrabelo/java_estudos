package com.course.greeting_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties("greeting-service")
public class GreetingConfig {

    private String greeting;
    private String defaultValue;

    public GreetingConfig() {}

    public GreetingConfig(String greeting, String defaultValue) {
        this.greeting = greeting;
        this.defaultValue = defaultValue;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
