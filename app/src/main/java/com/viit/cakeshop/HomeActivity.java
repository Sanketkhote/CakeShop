package com.viit.cakeshop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.viit.cakeshop.R;


public class HomeActivity extends Activity {

    private Button mOrderButton;
    private Button mNavButton;
    private Button mPhoneButton;
    private Button mEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mOrderButton = (Button) findViewById(R.id.orderButton);
        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOrderActivity();
            }
        });

        mEmailButton = (Button) findViewById(R.id.emailButton);
        mNavButton = (Button) findViewById(R.id.navButton);
        mPhoneButton = (Button) findViewById(R.id.callButton);
        mEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"mayur.21810704@viit.ac.in"});
                email.putExtra(Intent.EXTRA_SUBJECT, "MyCakeShop Support");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client:"));
            }
        });

        mPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calls the phone number
                Intent intent = new Intent (Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+918412028750"));
                startActivity(intent);
            }
        });

        mNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // navigate to address (random for now)
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q=VIIT College"));
                startActivity(intent);
            }
        });
    }

    private void startOrderActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
