package com.groppius.melireport.entities.category;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by julio on 11/10/14.
 */
public class Category {

    private long category_id;
    private String category_name;
    private String category_description;
    private long category_father;

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    public long getCategory_father() {
        return category_father;
    }

    public void setCategory_father(long category_father) {
        this.category_father = category_father;
    }

    public static Category parser(JSONObject jsonObject) {
        //{"category":[{"category_id":"1","category_name":"Accesorios para Veh\u00edculos","category_description":null,"category_father":"0","category_status":true}
        Category category = new Category();
        try {
            if(jsonObject != null) {
                category.setCategory_id(jsonObject.getLong("category_id"));
                category.setCategory_name(jsonObject.getString("category_name"));
                category.setCategory_description(jsonObject.getString("category_description"));
                category.setCategory_father(jsonObject.getLong("category_father"));
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return category;
    }
}
