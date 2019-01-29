package com.example.bonfire;

import android.util.Log;

import com.google.api.services.sheets.v4.model.Sheet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RestaurantData {
    private String name, address, photo_url, menu_url, phone_number, price_range, rating, cuisines;

    public RestaurantData() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoto(String photo) {
        this.photo_url = photo;
    }

    public void setMenu(String menu) {
        this.menu_url = menu;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phone_number = phoneNumber;
    }

    public void setPriceRange(String priceRange) {
        if(priceRange.equals("1")){
            this.price_range = "$";
        }else if(priceRange.equals("2")){
            this.price_range = "$$";

        }else if(priceRange.equals("3")){
            this.price_range = "$$$";
        }else if(priceRange.equals("4")){
            this.price_range = "$$$$";
        }else if(priceRange.equals("5")){
            this.price_range = "$$$$$";
        }

    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getPhoto_url(){
        return photo_url;
    }

    public String getMenu_url(){
        return menu_url;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPrice_range() {
        return price_range;
    }

    public String getRating() {
        return rating;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void initData() {
        JSONObject jsonObject = SheetJSONParser.getDataFromWeb();

        try {
            /**
             * Check Whether Its NULL???
             */
            if (jsonObject != null) {
                /**
                 * Check Length...
                 */
                if(jsonObject.length() > 0) {
                    JSONArray array = jsonObject.getJSONArray("Sheet1");

                    /**
                     * Check Length of Array...
                     */

                    int lenArray = array.length();
                    if(lenArray > 0) {
                        for( int i = 0; i < lenArray; i++) {
                            JSONObject innerObject = array.getJSONObject(0);
                            this.setName(innerObject.getString("name"));
                            this.setAddress(innerObject.getString("address"));
                            this.setPhoto(innerObject.getString("photo_url"));
                            this.setMenu(innerObject.getString("menu_url"));
                            this.setPhoneNumber(innerObject.getString("phone_number"));
                            this.setPriceRange(innerObject.getString("price_range"));
                            this.setRating(innerObject.getString("rating"));
                            this.setCuisines(innerObject.getString("cuisines"));
                        }
                    }
                }
            }
        } catch (JSONException je) {
            Log.i(SheetJSONParser.TAG, "" + je.getLocalizedMessage());
        }
    }

}
