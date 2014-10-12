package com.groppius.melireport.entities.buyer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by julio on 11/10/14.
 */
public class Buyer {

    private long buyer_id;
    private String buyer_name;
    private String buyer_last_name;
    private String buyer_email;
    private String buyer_address;
    private String buyer_real_name;

    public long getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(long buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getBuyer_last_name() {
        return buyer_last_name;
    }

    public void setBuyer_last_name(String buyer_last_name) {
        this.buyer_last_name = buyer_last_name;
    }

    public String getBuyer_email() {
        return buyer_email;
    }

    public void setBuyer_email(String buyer_email) {
        this.buyer_email = buyer_email;
    }

    public String getBuyer_address() {
        return buyer_address;
    }

    public void setBuyer_address(String buyer_address) {
        this.buyer_address = buyer_address;
    }

    public String getBuyer_real_name() {
        return buyer_real_name;
    }

    public void setBuyer_real_name(String buyer_real_name) {
        this.buyer_real_name = buyer_real_name;
    }

    public static Buyer parser(JSONObject jsonObject) {
        //{"buyer_id":"1","buyer_name":"TETE253223","buyer_real_name":"Test","buyer_last_name":"Test","buyer_email":"test_user_9412195@testuser.com","buyer_address":""}
        Buyer buyer = new Buyer();
        try {
            if(jsonObject != null) {
                buyer.setBuyer_id(jsonObject.getLong("buyer_id"));
                buyer.setBuyer_name(jsonObject.getString("buyer_name"));
                buyer.setBuyer_real_name(jsonObject.getString("buyer_real_name"));
                buyer.setBuyer_last_name(jsonObject.getString("buyer_last_name"));
                buyer.setBuyer_email(jsonObject.getString("buyer_email"));
                buyer.setBuyer_address(jsonObject.getString("buyer_address"));
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return buyer;
    }
}
