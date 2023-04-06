package com.test.gcp.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test {

    private static List<String> maskedFieldNames = new ArrayList<>();
    static {
        maskedFieldNames.add("email");
        maskedFieldNames.add("name");
    }

    public static void main(String[] args) {

        String jsonString1 = "{\"employeeId\":\"1\",\"name\":\"AvirupPal\",\"email\":\"avirup.pal@gmail.com\",\"address\":\"1442Nayabad.Kolkata-700099\"}";
        String jsonString2 = "[{\"employeeId\":\"1\",\"name\":\"AvirupPal\",\"email\":\"avirup.pal@gmail.com\",\"address\":\"1442Nayabad.Kolkata-700099\"},{\"employeeId\":\"2\",\"name\":\"Abhishek\",\"email\":\"abhishek@gmail.com\",\"address\":\"1442Nayabad.Kolkata-700099\"}]";
        try {
            JSONObject jsonObject = new JSONObject(jsonString1);
            JSONObject jsonObject2 = parseObject(jsonObject);
            System.out.println("Masked jsonString ==>> " + jsonObject2.toString());
        } catch (JSONException e) {
            System.out.println("Not An Object");
            JSONArray jsonArray = new JSONArray(jsonString2);
            JSONArray jsonArray2 = parseArray(jsonArray);
            System.out.println("Masked jsonString ==>> " + jsonArray2.toString());
        }

    }

    private static JSONObject parseObject(JSONObject jsonObject) {
        System.out.println("Coming in parseObject");
        maskedFieldNames.stream().forEach(fn -> {
            String value = (String) jsonObject.get(fn);
            System.out.println("Key ==>> " + fn);
            System.out.println("Value Before ==>> " + value);
            jsonObject.put(fn, "****");
            value = (String) jsonObject.get(fn);
            System.out.println("Value After ==>> " + value);
        });
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
