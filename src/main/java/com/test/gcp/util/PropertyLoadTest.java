package com.test.gcp.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.Multimap;

public class PropertyLoadTest {

    public static void main(String[] args) throws JsonMappingException, StreamReadException, DatabindException, JsonProcessingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new GuavaModule());

        String jsonString = "{\"SOME-KEY\":[{\"key\":\"lul-java-examples\",\"linkText\":\"MyExamples\",\"url\":\"/java/somenewurl/\"},{\"key\":\"lul-java-exercises\",\"linkText\":\"MyExercises\",\"url\":\"/java/exercises/\",}]}";
        Multimap<String, NavItem> navs = objectMapper.readValue(
                objectMapper.treeAsTokens(objectMapper.readTree(jsonString)),
                objectMapper.getTypeFactory().constructMapLikeType(
                        Multimap.class, String.class, NavItem.class));
        System.out.println("Test ==>> "+navs.get("SOME-KEY"));
    }

}

class NavItem {
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    
}