package com.groppius.melireport.synchro;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.groppius.melireport.entities.buyer.Buyer;
import com.groppius.melireport.entities.buyer.BuyerRepository;
import com.groppius.melireport.entities.category.CategoryRepository;
import com.groppius.melireport.entities.item.ItemRepository;
import com.groppius.melireport.entities.payment.PaymentRepository;
import com.groppius.melireport.entities.sale.SaleRepository;
import com.groppius.melireport.entities.user.User;
import com.groppius.melireport.entities.user.UserRepository;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by julio on 11/10/14.
 */
public class SyncTask extends AsyncTask<Void, Void, Boolean> {

    private static final String SYNC_TASK_TAG = "SYNC_TASK";

    private Context context;

    public SyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return null;
    }


    private Boolean synchronize() {
        BuyerRepository buyerRepository = new BuyerRepository(context);
        CategoryRepository categoryRepository = new CategoryRepository(context);
        ItemRepository itemRepository = new ItemRepository(context);
        PaymentRepository paymentRepository = new PaymentRepository(context);
        SaleRepository saleRepository = new SaleRepository(context);
        UserRepository userRepository = new UserRepository(context);

        Log.d(SYNC_TASK_TAG, "Synchronize process started...");

        Log.d(SYNC_TASK_TAG, "Synchronizing Buyer Repo.");


        return null;
    }
}
