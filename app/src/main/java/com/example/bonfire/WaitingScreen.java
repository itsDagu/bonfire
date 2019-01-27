package com.example.bonfire;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;


public class WaitingScreen extends AppCompatActivity {

    private JSONObject jsonMessage;
    private JSONObject jsonResponse = new JSONObject();
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

        //jsonResponse = SheetJSONParser.getDataFromWeb();

        /*SheetJSONParser sheetJSONParser = new SheetJSONParser();
        jsonResponse = sheetJSONParser.getDataFromWeb();
*/
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

        try {
            jsonResponse.put("name","Truffles Fine Foods Cafe");
            jsonResponse.put("address","VanDusen Botanical Gardens, 5151 Oak Street, Vancouver V6H2L8");
            jsonResponse.put("photo_url", "https://www.zomato.com/vancouver/truffles-fine-foods-cafe-shaughnessy-vancouver/photos?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1#tabtop");
            jsonResponse.put("menu_url", "https://www.zomato.com/vancouver/truffles-fine-foods-cafe-shaughnessy-vancouver/menu?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1&openSwipeBox=menu&showMinimal=1#tabtop")
;           jsonResponse.put("phone_number","1 248-313-8557");
            jsonResponse.put("price_range", 1);
            jsonResponse.put("rating", 3.4);
            jsonResponse.put("cuisines", "Cafe");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        (new Handler()).postDelayed(this::startReturnRestaurant, 1500);

    }

    public void startReturnRestaurant(){
        Intent intent = new Intent(this, ReturnRestaurant.class);

        intent.putExtra(EXTRA_MESSAGE, jsonResponse.toString());
        startActivity(intent);
    }
    // Sends aggregated optimized restaurant




}
