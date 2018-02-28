package com.cl.proficiencytest.net;

import android.net.ParseException;
import android.util.Log;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ExceptionHelper {

    public static String handleException(Throwable e) {
        e.printStackTrace();
        String error;
        if (e instanceof SocketTimeoutException) {//network time out
            Log.e("TAG", "Connection Error: " + e.getMessage());
            error = "Connection Error";
        } else if (e instanceof ConnectException) {
            Log.e("TAG", "Connection Error: " + e.getMessage());

            error = "Connection Exception";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            Log.e("TAG", "Date Parse Exception: " + e.getMessage());
            error = "Date Parse Exception";
        } else if (e instanceof ApiException) {//server ex
            error = e.getCause().getMessage();
        } else if (e instanceof UnknownHostException) {
            Log.e("TAG", "Network Exception: " + e.getMessage());
            error = "Network Exception";
        } else if (e instanceof IllegalArgumentException) {
            Log.e("TAG", "File exists: " + e.getMessage());
            error = "File exists";
        } else {//unknown
            try {
                Log.e("TAG", "Error: " + e.getMessage());
            } catch (Exception e1) {
                Log.e("TAG", "Unknown Debug ");
            }
            error = "Error";
        }
        return error;
    }

}