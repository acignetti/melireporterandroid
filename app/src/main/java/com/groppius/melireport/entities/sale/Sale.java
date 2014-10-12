package com.groppius.melireport.entities.sale;

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
    private int sale_status;

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

    public int getSale_status() {
        return sale_status;
    }

    public void setSale_status(int sale_status) {
        this.sale_status = sale_status;
    }

    public static Sale parser(JSONObject jsonObject) {
        Sale sale = new Sale();
        try {
            if(jsonObject != null) {

            }

        }catch (Exception e) {

        }
        return sale;
    }
}
