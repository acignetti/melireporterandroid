package com.groppius.melireport.rest;

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
public class GoRestApi {

    private HttpClient httpclient = new DefaultHttpClient();
    private HttpResponse response;
    private JSONObject meliResponse;

    public GoRestApi() {
    }

    public JSONObject get(String uri) throws IOException {
        return executeMethod(httpclient.execute(new HttpGet(uri)));
    }

    public JSONObject post(String uri) throws IOException {
        return executeMethod(httpclient.execute(new HttpPost(uri)));
    }

    public JSONObject put(String uri) throws IOException {
        return executeMethod(httpclient.execute(new HttpPut(uri)));
    }

    public JSONObject delete(String uri) throws IOException {
        return executeMethod(httpclient.execute(new HttpDelete(uri)));
    }

    private JSONObject executeMethod(HttpResponse response) {
        JSONObject responseObject = null;
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                response.getEntity().writeTo(out);
                out.close();
                responseObject = new JSONObject(out.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
