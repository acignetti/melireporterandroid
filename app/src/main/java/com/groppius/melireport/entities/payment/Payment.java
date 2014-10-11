package com.groppius.melireport.entities.payment;

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
}
