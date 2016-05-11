package com.example.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class AConfiguration {

    @Autowired
    private CommonAppProperties commonAppProperties;

    @PostConstruct
    public void init() {
        System.out.println("Registering configuration");
        System.out.println("Sending logs to '" + commonAppProperties.getLogging().getLogstash().getHost() + "'");
    }

}