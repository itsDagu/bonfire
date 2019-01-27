package com.example.bonfire;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;


public class WaitingScreen extends AppCompatActivity {

    private JSONObject jsonMessage;
    public RestaurantData restaurantData = new RestaurantData();
    //private JSONObject jsonResponse = new JSONObject();
    public static final String EXTRA_MESSAGE = "com.example.WaitingScreen.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_screen);

        GetDataTask getDataTask = new GetDataTask();
        getDataTask.execute();
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

<<<<<<< HEAD

        //jsonResponse = SheetJSONParser.getDataFromWeb();



     /*  String url = "www.example.com";
=======
        //jsonResponse = SheetJSONParser.getDataFromWeb();

        /*SheetJSONParser sheetJSONParser = new SheetJSONParser();
        jsonResponse = sheetJSONParser.getDataFromWeb();
*/
     /*   String url = "www.example.com";
>>>>>>> 283227884cfa8286248e5357ceeb91f8b523c166
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
<<<<<<< HEAD
     /*
=======

>>>>>>> 283227884cfa8286248e5357ceeb91f8b523c166
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
        }*/

        ( new Handler()).postDelayed(this::startReturnRestaurant, 1500);
    }

    public class GetDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            restaurantData.initData();

            return null;
        }
    }


    public void startReturnRestaurant(){
        Intent intent = new Intent(this, ReturnRestaurant.class);
        intent.putExtra("name", restaurantData.getName());
        intent.putExtra("address", restaurantData.getAddress());
        intent.putExtra("photo_url", restaurantData.getPhoto_url());
        intent.putExtra("menu_url", restaurantData.getMenu_url());
        intent.putExtra("phone_number", restaurantData.getPhone_number());
        intent.putExtra("price_range", restaurantData.getPrice_range());
        intent.putExtra("rating", restaurantData.getRating());
        intent.putExtra("cuisine", restaurantData.getCuisines());

        startActivity(intent);
    }
    // Sends aggregated optimized restaurant




}
