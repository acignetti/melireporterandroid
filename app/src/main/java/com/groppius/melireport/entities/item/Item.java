package com.groppius.melireport.entities.item;

import android.media.Image;

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
    private Date getItem_published_ended_on;
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

    public Date getGetItem_published_ended_on() {
        return getItem_published_ended_on;
    }

    public void setGetItem_published_ended_on(Date getItem_published_ended_on) {
        this.getItem_published_ended_on = getItem_published_ended_on;
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
        Item item = new Item();
        try {
            if(jsonObject != null) {

            }

        }catch (Exception e) {

        }
        return item;
    }


}
