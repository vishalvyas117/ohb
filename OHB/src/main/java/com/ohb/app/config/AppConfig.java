package com.ohb.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/application.properties")
@EnableAspectJAutoProxy
public class AppConfig {
    @Autowired
    Environment env;
    public String getValueOfKey(String key) {
        return env.getProperty(key);
    }
}
