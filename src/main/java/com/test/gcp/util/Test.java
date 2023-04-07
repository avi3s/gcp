package com.test.gcp.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test {

    private static List<String> maskedFieldNames = new ArrayList<>();
    static {
        maskedFieldNames.add("email");
        maskedFieldNames.add("name");
        maskedFieldNames.add("password");
    }

    public static void main(String[] args) {

        String jsonString = "{\"name\":\"AvirupPal\",\"password\":\"@password#\",\"email\":\"avirup.pal@gmail.com\",\"address\":\"1442Nayabad.Kolkata-700099\",\"roles\":[{\"id\":1,\"name\":\"ROLE_ADMIN\"}]}";
        String jsonString1 = "{\"employeeId\":\"1\",\"name\":\"AvirupPal\",\"email\":\"avirup.pal@gmail.com\",\"address\":\"1442Nayabad.Kolkata-700099\"}";
        String jsonString2 = "[{\"employeeId\":\"1\",\"name\":\"AvirupPal\",\"email\":\"avirup.pal@gmail.com\",\"address\":\"1442Nayabad.Kolkata-700099\"},{\"employeeId\":\"2\",\"name\":\"Abhishek\",\"email\":\"abhishek@gmail.com\",\"address\":\"1442Nayabad.Kolkata-700099\"}]";
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject jsonObject2 = parseObject(jsonObject);
            System.out.println("Masked jsonString ==>> " + jsonObject2.toString());
        } catch (JSONException e) {
            System.out.println("JSONException Not An Object" + e.getMessage());
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonString1);
            JSONObject jsonObject2 = parseObject(jsonObject);
            System.out.println("Masked jsonString1 ==>> " + jsonObject2.toString());
        } catch (JSONException e) {
            System.out.println("JSONException Not An Object");
            JSONArray jsonArray = new JSONArray(jsonString2);
            JSONArray jsonArray2 = parseArray(jsonArray);
            System.out.println("Masked jsonString2 ==>> " + jsonArray2.toString());
        }

        try {
            JSONArray jsonArray = new JSONArray(jsonString2);
            JSONArray jsonArray2 = parseArray(jsonArray);
            System.out.println("Masked jsonString2 ==>> " + jsonArray2.toString());
        } catch (JSONException e) {
            System.out.println("JSONException Not An Array");
        }
    }

    private static JSONObject parseObject(JSONObject jsonObject) {
        System.out.println("Coming in parseObject");
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            System.out.println("Key ==>> " + key);
            if (jsonObject.get(key) instanceof JSONObject) {
                System.out.println("JSONObject");
                JSONObject jsonObject2 = (JSONObject) jsonObject.get(key);
                jsonObject.put(key, jsonObject2.toString());
            } else if (jsonObject.get(key) instanceof JSONArray) {
                System.out.println("JSONArray");
                JSONArray jsonArray2 = parseArray((JSONArray) jsonObject.get(key));
                jsonObject.put(key, jsonArray2.toString());
            } else {
                System.out.println("Normal Key");
                if (maskedFieldNames.contains(key)) {
                    String value = (String) jsonObject.get(key);
                    System.out.println("Key ==>> " + key + "Value Before ==>> " + value);
                    jsonObject.put(key, "****");
                    value = (String) jsonObject.get(key);
                    System.out.println("Key ==>> " + key + "Value After ==>> " + value);
                }
            }
        }
        return jsonObject;

    }

    private static JSONArray parseArray(JSONArray jsonArray) {
        System.out.println("Coming in parseArray");
        JSONArray jsonArray2 = new JSONArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = parseObject(jsonArray.getJSONObject(i));
            jsonArray2.put(jsonObject.toString());
        }
        return jsonArray;
    }
}
