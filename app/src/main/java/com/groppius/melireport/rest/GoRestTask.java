package com.groppius.melireport.rest;

import android.os.AsyncTask;

import com.groppius.melireport.R;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by julio on 11/10/14.
 */
public class GoRestTask extends AsyncTask<String, Void, JSONObject> {

    private String HttpMethod;

    public static final String HTTPGet = "get";
    public static final String HTTPPost = "post";
    public static final String HTTPPut = "put";
    public static final String HTTPDelete = "delete";

    private HttpClient httpclient = new DefaultHttpClient();
    private HttpResponse response;
    private JSONObject meliResponse;


    public GoRestTask(String HTTPMethod) {
        HttpMethod = HTTPMethod;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        String uri = strings[0];
        JSONObject reportObject = null;
        try{
            if(HttpMethod == HTTPGet) {
                response = httpclient.execute(new HttpGet(uri));
            }
            if(HttpMethod == HTTPPost) {
                response = httpclient.execute(new HttpPost(uri));
            }
            if(HttpMethod == HTTPPut) {
                response = httpclient.execute(new HttpPut(uri));
            }
            if(HttpMethod == HTTPDelete) {
                response = httpclient.execute(new HttpDelete(uri));
            }

            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                reportObject = new JSONObject(out.toString());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return reportObject;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {

    }
}
