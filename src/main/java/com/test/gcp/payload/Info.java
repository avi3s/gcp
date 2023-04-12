package com.test.gcp.payload;

import org.springframework.stereotype.Component;

@Component
public class Info {

    private String key;
    private String linkText;
    private String url;

    @Override
    public String toString() {
        return "Info{" +
                "key='" + key + '\'' +
                ", linkText='" + linkText + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
