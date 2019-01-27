
package com.example.bonfire;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;


public class SheetJSONParser {

    private static final String MAIN_URL = "https://script.google.com/macros/s/AKfycbxoxj4l5mIxKdFXkYBik0sZMpknfsattCXrBFds7q35qvt5_wo/exec";

    public static final String TAG = "SheetJSONParser";

    private static final String KEY_USER_ID = "user_id";

    private static Response response;

    public static JSONObject getDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());

        }catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "FUCK " + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject getDataById(int userId) {

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormEncodingBuilder()
                    .add(KEY_USER_ID, Integer.toString(userId))
                    .build();

            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .post(formBody)
                    .build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());

        } catch (IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
}

