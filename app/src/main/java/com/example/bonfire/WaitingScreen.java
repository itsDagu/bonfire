package com.example.bonfire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;


public class WaitingScreen extends AppCompatActivity {

    private JSONObject jsonMessage;
    private JSONObject jsonResponse;
    public static final String EXTRA_MESSAGE = "com.example.WaitingScreen.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_screen);

        Intent intent = getIntent();
        LatLng latLng = intent.getExtras().getParcelable(EnterLocation.EXTRA_MESSAGE);

        jsonMessage = new JSONObject();
        try {
            jsonMessage.put("name", "Bob Zhou");
            jsonMessage.put("latlng", latLng.toString());
            jsonMessage.put("preference", "chinese");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*SheetJSONParser sheetJSONParser = new SheetJSONParser();
        jsonResponse = sheetJSONParser.getDataFromWeb();*/

     /*   String url = "www.example.com";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (com.android.volley.Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                    public void onResponse(JSONObject response) {
                        jsonResponse = response;
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        */


    }

    // Sends aggregated optimized restaurant

    public void sendMessage(View view) {
        Intent intent = new Intent(this, ReturnRestaurant.class);

        intent.putExtra(EXTRA_MESSAGE, jsonResponse.toString());
        startActivity(intent);
    }



}
