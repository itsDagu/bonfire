package com.example.bonfire;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.net.URI;
import java.net.URISyntaxException;


public class ReturnRestaurant extends AppCompatActivity {

    private RestaurantData restaurantData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_restaurant);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        String photoUrl = getIntent().getStringExtra("photo_url");
        String menuUrl = getIntent().getStringExtra("menu_url");
        String phoneNumber = getIntent().getStringExtra("phone_number");
        String priceRange = "Price Range "+getIntent().getStringExtra("price_range");
        String rating = "Ratings "+getIntent().getStringExtra("rating");
        String cuisines = "Cuisines "+getIntent().getStringExtra("cuisines");

        TextView nameView = (TextView) findViewById (R.id.nameView);
        TextView addressView = (TextView) findViewById (R.id.addressView);
        TextView priceRangeView = (TextView) findViewById (R.id.priceRangeView);
        TextView ratingView = (TextView) findViewById (R.id.ratingView);
        TextView cuisinesView = (TextView) findViewById (R.id.cuisinesView);

        nameView.setText(name);
        addressView.setText(address);
        priceRangeView.setText(priceRange);
        ratingView.setText(rating);
        cuisinesView.setText(cuisines);


    }

    public class MakePhoneCall {
        // Find your Account Sid and Token at twilio.com/console
        /*public static final String ACCOUNT_SID = "AC1c84046f962d7338a0d7f531684ab7fa";
        public static final String AUTH_TOKEN = "271e1456e2d155a15c2a7ef82c3df037";
        Twilio.InitListener
        public static void main(String[] args) throws URISyntaxException {

            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            String from = "+16043730631";
            String to = "+12483138557";
            Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from),
                    new URI("http://demo.twilio.com/docs/voice.xml")).create();
            System.out.println(call.getSid());
        }*/
    }


}
