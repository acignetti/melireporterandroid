package com.groppius.melireport.entities.item;

import com.groppius.melireport.misc.Helper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by julio on 11/10/14.
 */
public class Item {

    private long item_id;
    private String item_name;
    private String item_description;
    private Double item_price;
    private Double item_cost;
    private Date item_published_on;
    private Date item_published_ended_on;
    private long item_category_id;
    private String item_thumbnail;

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public Double getItem_price() {
        return item_price;
    }

    public void setItem_price(Double item_price) {
        this.item_price = item_price;
    }

    public Double getItem_cost() {
        return item_cost;
    }

    public void setItem_cost(Double item_cost) {
        this.item_cost = item_cost;
    }

    public Date getItem_published_on() {
        return item_published_on;
    }

    public void setItem_published_on(Date item_published_on) {
        this.item_published_on = item_published_on;
    }

    public Date getItem_published_ended_on() {
        return item_published_ended_on;
    }

    public void setItem_published_ended_on(Date item_published_ended_on) {
        this.item_published_ended_on = item_published_ended_on;
    }

    public long getItem_category_id() {
        return item_category_id;
    }

    public void setItem_category_id(long item_category_id) {
        this.item_category_id = item_category_id;
    }

    public String getItem_thumbnail() {
        return item_thumbnail;
    }

    public void setItem_thumbnail(String item_thumbnail) {
        this.item_thumbnail = item_thumbnail;
    }

    public static Item parser(JSONObject jsonObject) {
        //{"item_id":"1","item_name":"(item De Prueba) Motorola Moto X","item_description":"\nEste es un item de prueba, no ofertar","item_thumbnail":"","item_price":"3500","item_cost":"0","item_published_on":"2014-10-12 05:57:41","item_ended_on":"2014-10-12 05:38:33","item_category_id":"9"}
        Item item = new Item();
        try {
            if(jsonObject != null) {
                item.setItem_id(jsonObject.getLong("item_id"));
                item.setItem_category_id(jsonObject.getLong("item_category_id"));
                item.setItem_cost(jsonObject.getDouble("item_cost"));
                item.setItem_description(jsonObject.getString("item_description"));
                item.setItem_price(jsonObject.getDouble("item_price"));
                item.setItem_published_on(Helper.dateFormatter(jsonObject.getString("item_published_on")));
                item.setItem_name(jsonObject.getString("item_name"));
                item.setItem_thumbnail(jsonObject.getString("item_thumbnail"));
                item.setItem_published_ended_on(Helper.dateFormatter(jsonObject.getString("item_ended_on")));
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }


}
