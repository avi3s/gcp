package com.test.gcp.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.gcp.payload.ErrorPojo;
import com.test.gcp.payload.Info;

@Configuration
public class ErrorCodeMapping {
  
  @Value("classpath:error.json")
  private Resource resource;

  @Value("classpath:info.json")
  private Resource resource1;
  
  @Bean
  List<ErrorPojo> setUpList() throws IOException {
    Gson gson = new Gson();
    String jsonString = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
    Type type = new TypeToken<List<ErrorPojo>>(){}.getType();
    List<ErrorPojo> errors = gson.fromJson(jsonString, type);
    for(ErrorPojo error : errors) {
    System.out.println("Error" + error.toString());
    }
    return errors;
  }
  
  @Bean
  Map<String, Info> setUpMap() throws IOException {
    Gson gson = new Gson();
    String jsonString1 = IOUtils.toString(resource1.getInputStream(), StandardCharsets.UTF_8);
    Type type1 = new TypeToken<Map<String, Info>>(){}.getType();
    Map<String, Info> info = gson.fromJson(jsonString1, type1);
    info.entrySet().stream().forEach(x->System.out.println(x));
    return info;
  }
}
