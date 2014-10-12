package com.groppius.melireport.entities.payment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by julio on 11/10/14.
 */
public class Payment {

    private long payment_id;
    private String payment_name;
    private String payment_description;

    public long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(long payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }

    public String getPayment_description() {
        return payment_description;
    }

    public void setPayment_description(String payment_description) {
        this.payment_description = payment_description;
    }

    public static Payment parser(JSONObject jsonObject) {
        //{"payment_id":"2","payment_name":"master","payment_description":"","payment_category":null,"payment_status":"1"}
        Payment payment = new Payment();
            if(jsonObject != null) {
                try {
                    payment.setPayment_id(jsonObject.getLong("payment_id"));
                    payment.setPayment_name(jsonObject.getString("payment_name"));
                    payment.setPayment_description(jsonObject.getString("payment_description"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        return payment;
    }
}
