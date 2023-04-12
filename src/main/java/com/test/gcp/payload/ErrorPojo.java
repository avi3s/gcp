package com.test.gcp.payload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@PropertySource(value = {"classpath:error.json"})
//@ConfigurationProperties
public class ErrorPojo {
    //@Value("${error_type}")
    private String error_type;


   // @Value("${error_code}")
    private String error_code;
  // @Value("${error_message}")
    private String error_message;

    @Override
    public String toString() {
        return "ErrorPojo{" +
                "error_type='" + error_type + '\'' +
                ", error_code='" + error_code + '\'' +
                ", error_message='" + error_message + '\'' +
                '}';
    }
}
