package com.hanselandpetal.catalog.parser;


import com.hanselandpetal.catalog.model.Flower;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static List<Flower> parse(String xml) throws IOException {
        try {


            JSONArray array = new JSONArray(xml);
            List<Flower> flowerList = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Flower flower = new Flower();
                flower.setProductId(object.getInt("productId"));
                flower.setName(object.getString("name"));
                flower.setCategory(object.getString("category"));
                flower.setInstructions(object.getString("instructions"));
                flower.setPhoto(object.getString("photo"));
                flower.setPrice(object.getDouble("price"));

                flowerList.add(flower);
            }
            return flowerList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
