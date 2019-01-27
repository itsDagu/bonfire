/*
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

            client.newCall(request).enqueue(new Handler.Callback() {

                @Override
                public boolean handleMessage(Message msg) {
                    return false;
                }

                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }


                public void onResponse(Call call, final Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    // you code to handle response
                }
            )

        } catch (@NonNull IOException | JSONException e) {
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

*/
/*
// Sheets API
    implementation('com.google.apis:google-api-services-sheets:v4-rev553-1.25.0') {
        exclude group: 'org.apache.httpcomponents'
    }

    implementation('com.google.api-client:google-api-client-android:1.25.0'){
        exclude group: 'org.apache.httpcomponents'
    }

    implementation 'com.google.android.gms:play-services-auth:16.0.1'

    implementation 'pub.devrel:easypermissions:0.3.0'

    implementation fileTree(dir: 'libs', include: ['*.jar'])

// okhttp
    implementation("com.squareup.okhttp3:okhttp:3.12.1")
    implementation("com.squareup.okhttp:okhttp-urlconnection:2.2.0")
*/
