package com.groppius.melireport.entities.sale;

import com.groppius.melireport.misc.Helper;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by julio on 11/10/14.
 */
public class Sale {

    private long sale_id;
    private long sale_user_id;
    private long sale_item_id;
    private long sale_payment_id;
    private int sale_quantity;
    private Date sale_bought_on;
    private Date sale_payed_on;
    private String sale_status;
    private Double sale_total;

    public long getSale_id() {
        return sale_id;
    }

    public void setSale_id(long sale_id) {
        this.sale_id = sale_id;
    }

    public long getSale_user_id() {
        return sale_user_id;
    }

    public void setSale_user_id(long sale_user_id) {
        this.sale_user_id = sale_user_id;
    }

    public long getSale_item_id() {
        return sale_item_id;
    }

    public void setSale_item_id(long sale_item_id) {
        this.sale_item_id = sale_item_id;
    }

    public long getSale_payment_id() {
        return sale_payment_id;
    }

    public void setSale_payment_id(long sale_payment_id) {
        this.sale_payment_id = sale_payment_id;
    }

    public int getSale_quantity() {
        return sale_quantity;
    }

    public void setSale_quantity(int sale_quantity) {
        this.sale_quantity = sale_quantity;
    }

    public Date getSale_bought_on() {
        return sale_bought_on;
    }

    public void setSale_bought_on(Date sale_bought_on) {
        this.sale_bought_on = sale_bought_on;
    }

    public Date getSale_payed_on() {
        return sale_payed_on;
    }

    public void setSale_payed_on(Date sale_payed_on) {
        this.sale_payed_on = sale_payed_on;
    }

    public String getSale_status() {
        return sale_status;
    }

    public void setSale_status(String sale_status) {
        this.sale_status = sale_status;
    }

    public Double getSale_total() {
        return sale_total;
    }

    public void setSale_total(Double sale_total) {
        this.sale_total = sale_total;
    }

    public static Sale parser(JSONObject jsonObject) {
        //{"sale_id":"889527200","sale_user_id":"3","sale_item_id":"1","sale_payment_id":"0","sale_quantity":"1","sale_bought_on":"2014-10-12 01:57:42","sale_paid_on":"2014-10-12 01:57:42","sale_status":"confirmed","sale_total":"3500"}
        Sale sale = new Sale();
        try {
            if(jsonObject != null) {
                sale.setSale_id(jsonObject.getLong("sale_id"));
                sale.setSale_user_id(jsonObject.getLong("sale_user_id"));
                sale.setSale_item_id(jsonObject.getLong("sale_item_id"));
                sale.setSale_payment_id(jsonObject.getLong("sale_payment_id"));
                sale.setSale_status(jsonObject.getString("sale_status"));
                sale.setSale_quantity(jsonObject.getInt("sale_quantity"));
                sale.setSale_bought_on(Helper.dateFormatter(jsonObject.getString("sale_bought_on")));
                sale.setSale_payed_on(Helper.dateFormatter(jsonObject.getString("sale_paid_on")));
                sale.setSale_total(jsonObject.getDouble("sale_total"));
            }

        }catch (Exception e) {

        }
        return sale;
    }
}
