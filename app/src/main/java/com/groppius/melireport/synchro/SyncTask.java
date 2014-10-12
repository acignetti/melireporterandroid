package com.groppius.melireport.synchro;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.groppius.melireport.entities.buyer.Buyer;
import com.groppius.melireport.entities.buyer.BuyerRepository;
import com.groppius.melireport.entities.category.Category;
import com.groppius.melireport.entities.category.CategoryRepository;
import com.groppius.melireport.entities.item.Item;
import com.groppius.melireport.entities.item.ItemRepository;
import com.groppius.melireport.entities.payment.Payment;
import com.groppius.melireport.entities.payment.PaymentRepository;
import com.groppius.melireport.entities.sale.Sale;
import com.groppius.melireport.entities.sale.SaleRepository;
import com.groppius.melireport.entities.user.User;
import com.groppius.melireport.entities.user.UserRepository;
import com.groppius.melireport.rest.GoRestApi;
import com.groppius.melireport.rest.MeliReportGlobalUriInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        return synchronize("/TT726719/0497eafa43804d8728d41d91d1c862cd");
    }


    private Boolean synchronize(String userToken) {
        Log.d(SYNC_TASK_TAG, "Synchronize process started...");
        //synchronizeUser();
        synchronizeCategory();
        synchronizePayment();
        synchronizeBuyer(userToken);
        synchronizeItem(userToken);
        synchronizeSale(userToken);
        Log.d(SYNC_TASK_TAG, "Synchronizing process finished.");
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
                        JSONArray paymentTypes = optional.getJSONArray("payment_type");
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
            response = goRest.get(URL+GET_CATEGORIES);
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

    private void synchronizeUser(String token) {
        JSONObject response = null;
        Log.d(SYNC_TASK_TAG, "Synchronizing User Repo.");
        UserRepository userRepository = new UserRepository(context);
        try {
            response = goRest.get(URL+GET_USER+token);
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

    private void synchronizeBuyer(String token) {
        JSONObject response = null;
        Log.d(SYNC_TASK_TAG, "Synchronizing Buyer Repo.");
        BuyerRepository buyerRepository = new BuyerRepository(context);
        try {
            response = goRest.get(URL+GET_BUYER+token);
            if(response != null) {
                //{ "success" : true, "message" : "Mensaje informativo", "optional" : "Datos extras que usa la aplicación" }
                if(response.getBoolean("success")) {
                    JSONObject optional = response.getJSONObject("optional");
                    if(optional != null) {
                        JSONArray buyers = optional.getJSONArray("buyer");
                        if(buyers != null) {
                            for (int i = 0; i < buyers.length(); i++) {
                                Buyer buyer = Buyer.parser(buyers.getJSONObject(i));
                                buyerRepository.insert(buyer);
                            }
                        }
                    }
                } else {
                    Log.d(SYNC_TASK_TAG, response.getString("message"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(SYNC_TASK_TAG, "Synchronizing Buyer Repo failed - " + e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(SYNC_TASK_TAG, "Synchronizing Buyer Repo failed - " + e.getMessage());
        }
        Log.d(SYNC_TASK_TAG, "Buyer Repo Synchronized correctly." );
    }

    private void synchronizeItem(String token) {
        JSONObject response = null;
        Log.d(SYNC_TASK_TAG, "Synchronizing Item Repo.");
        ItemRepository itemRepository = new ItemRepository(context);
        try {
            response = goRest.get(URL+GET_ITEM+token);
            if(response != null) {
                //{ "success" : true, "message" : "Mensaje informativo", "optional" : "Datos extras que usa la aplicación" }
                if(response.getBoolean("success")) {
                    JSONObject optional = response.getJSONObject("optional");
                    if(optional != null) {
                        JSONArray items = optional.getJSONArray("item");
                        if(items != null) {
                            for (int i = 0; i < items.length(); i++) {
                                Item item = Item.parser(items.getJSONObject(i));
                                itemRepository.insert(item);
                            }
                        }
                    }
                } else {
                    Log.d(SYNC_TASK_TAG, response.getString("message"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(SYNC_TASK_TAG, "Synchronizing Item Repo failed - " + e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(SYNC_TASK_TAG, "Synchronizing Item Repo failed - " + e.getMessage());
        }
        Log.d(SYNC_TASK_TAG, "Item Repo Synchronized correctly." );
    }

    private void synchronizeSale(String token) {
        JSONObject response = null;
        Log.d(SYNC_TASK_TAG, "Synchronizing Sale Repo.");
        SaleRepository saleRepository = new SaleRepository(context);
        try {
            response = goRest.get(URL+GET_SALE+token);
            if(response != null) {
                //{ "success" : true, "message" : "Mensaje informativo", "optional" : "Datos extras que usa la aplicación" }
                if(response.getBoolean("success")) {
                    JSONObject optional = response.getJSONObject("optional");
                    if(optional != null) {
                        JSONArray sales = optional.getJSONArray("sale");
                        if(sales != null) {
                            for (int i = 0; i < sales.length(); i++) {
                                Sale sale = Sale.parser(sales.getJSONObject(i));
                                saleRepository.insert(sale);
                            }
                        }
                    }
                } else {
                    Log.d(SYNC_TASK_TAG, response.getString("message"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(SYNC_TASK_TAG, "Synchronizing Sale Repo failed - " + e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(SYNC_TASK_TAG, "Synchronizing Sale Repo failed - " + e.getMessage());
        }
        Log.d(SYNC_TASK_TAG, "Sale Repo Synchronized correctly." );
    }

}
