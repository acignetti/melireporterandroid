package com.groppius.melireport.synchro;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.groppius.melireport.database.MeliReportDbGlobalsInterface;
import com.groppius.melireport.entities.buyer.Buyer;
import com.groppius.melireport.entities.buyer.BuyerRepository;
import com.groppius.melireport.entities.category.Category;
import com.groppius.melireport.entities.category.CategoryRepository;
import com.groppius.melireport.entities.item.ItemRepository;
import com.groppius.melireport.entities.payment.Payment;
import com.groppius.melireport.entities.payment.PaymentRepository;
import com.groppius.melireport.entities.sale.SaleRepository;
import com.groppius.melireport.entities.user.User;
import com.groppius.melireport.entities.user.UserRepository;
import com.groppius.melireport.rest.GoRestApi;
import com.groppius.melireport.rest.MeliReportGlobalUriInterface;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by julio on 11/10/14.
 */
public class SyncTask extends AsyncTask<Void, Void, Boolean> implements MeliReportGlobalUriInterface{

    private static final String SYNC_TASK_TAG = "SYNC_TASK";
    private GoRestApi goRest;
    private Context context;

    public SyncTask(Context context) {
        this.context = context;
        goRest = new GoRestApi();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return null;
    }


    private Boolean synchronize(String userToken) {
        BuyerRepository buyerRepository = new BuyerRepository(context);

        ItemRepository itemRepository = new ItemRepository(context);

        SaleRepository saleRepository = new SaleRepository(context);


        Log.d(SYNC_TASK_TAG, "Synchronize process started...");

        Log.d(SYNC_TASK_TAG, "Synchronizing Buyer Repo.");



        return null;
    }

    private void synchronizePayment() {
        JSONObject response = null;
        Log.d(SYNC_TASK_TAG, "Synchronizing Payment Repo.");
        PaymentRepository paymentRepository = new PaymentRepository(context);
        try {
            response = goRest.get(URL+GET_PAYMENT_TYPES);
            if(response != null) {
                //{ "success" : true, "message" : "Mensaje informativo", "optional" : "Datos extras que usa la aplicación" }
                if(response.getBoolean("success")) {
                    JSONObject optional = response.getJSONObject("optional");
                    if(optional != null) {
                        JSONArray paymentTypes = optional.getJSONArray("payment-type");
                        if(paymentTypes != null) {
                            for (int i = 0; i < paymentTypes.length(); i++) {
                                Payment payment = Payment.parser(paymentTypes.getJSONObject(i));
                                paymentRepository.insert(payment);
                            }
                        }
                    }
                } else {
                    Log.d(SYNC_TASK_TAG, response.getString("message"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(SYNC_TASK_TAG, "Synchronizing Payment Repo failed - " + e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(SYNC_TASK_TAG, "Synchronizing Payment Repo failed - " + e.getMessage());
        }
        Log.d(SYNC_TASK_TAG, "Payment Repo Synchronized correctly." );
    }

    private void synchronizeCategory() {
        JSONObject response = null;
        Log.d(SYNC_TASK_TAG, "Synchronizing Category Repo.");
        CategoryRepository categoryRepository = new CategoryRepository(context);
        try {
            response = goRest.get(URL+GET_PAYMENT_TYPES);
            if(response != null) {
                //{ "success" : true, "message" : "Mensaje informativo", "optional" : "Datos extras que usa la aplicación" }
                if(response.getBoolean("success")) {
                    JSONObject optional = response.getJSONObject("optional");
                    if(optional != null) {
                        JSONArray categories = optional.getJSONArray("category");
                        if(categories != null) {
                            for (int i = 0; i < categories.length(); i++) {
                                Category category = Category.parser(categories.getJSONObject(i));
                                categoryRepository.insert(category);
                            }
                        }
                    }
                } else {
                    Log.d(SYNC_TASK_TAG, response.getString("message"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(SYNC_TASK_TAG, "Synchronizing Category Repo failed - " + e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(SYNC_TASK_TAG, "Synchronizing Category Repo failed - " + e.getMessage());
        }
        Log.d(SYNC_TASK_TAG, "Category Repo Synchronized correctly." );
    }

    private void synchronizeUser() {
        JSONObject response = null;
        Log.d(SYNC_TASK_TAG, "Synchronizing User Repo.");
        UserRepository userRepository = new UserRepository(context);
        try {
            response = goRest.get(URL+GET_PAYMENT_TYPES);
            if(response != null) {
                //{ "success" : true, "message" : "Mensaje informativo", "optional" : "Datos extras que usa la aplicación" }
                if(response.getBoolean("success")) {
                    JSONObject optional = response.getJSONObject("optional");
                    if(optional != null) {
                        JSONArray users = optional.getJSONArray("user");
                        if(users != null) {
                            for (int i = 0; i < users.length(); i++) {
                                User user = User.parser(users.getJSONObject(i));
                                userRepository.insert(user);
                            }
                        }
                    }
                } else {
                    Log.d(SYNC_TASK_TAG, response.getString("message"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(SYNC_TASK_TAG, "Synchronizing User Repo failed - " + e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(SYNC_TASK_TAG, "Synchronizing User Repo failed - " + e.getMessage());
        }
        Log.d(SYNC_TASK_TAG, "User Repo Synchronized correctly." );
    }
}
