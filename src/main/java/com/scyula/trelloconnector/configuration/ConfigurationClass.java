package com.scyula.trelloconnector.configuration;

import org.springframework.beans.factory.annotation.Value;

public class ConfigurationClass {
    @Value("${app.value1}")
    private String value1;

    @Value("${app.value2}")
    private String value2;

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

}
