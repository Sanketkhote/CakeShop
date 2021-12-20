package com.example.cakeshop;

import static com.example.cakeshop.Constant.emailText;
import static com.example.cakeshop.Constant.listItem;
import static com.example.cakeshop.adapter.adapter_cake.sendBuyOrder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class SummaryActivity extends Activity {

    private String mDateString;
    private String mTimeString;
    private TextView mDateText;
    private TextView  mShopText;
    private TextView mCakeTitleText;
    private TextView mNumberText;
    private TextView mEmailText;
    private TextView mCostText;
    private TextView mThemeText;
    private TextView mAddressText;

    private TextView mWeightText;
    private Button mChangeButton;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
//        setDateAndTime();
        setLayout();
        mIntent = getIntent();

        Button button = findViewById(R.id.finalButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendBuyOrder(getApplicationContext(),Constant.endpoint+"/order",listItem,Constant.shopName);
//

//                    sendEmail(Constant.endpoint+"/send-email/"+emailText.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void sendEmail(String url) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(),"Email Sent Successfully",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("oder error", String.valueOf(error));
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("Field", "Value"); //Add the data you'd like to send to the server.
                return MyData;
            }
        };
        MyRequestQueue.add(jsonObjectRequest);
    }


    private void setLayout() {
        int cost = 0;
        try{
            cost = listItem.getPrice();
        }catch (NullPointerException e){
            Log.e("PriceNull", "price not present");
        }
        //flavours ops

        mNumberText = (TextView) findViewById(R.id.numberLabel);
        mEmailText = (TextView) findViewById(R.id.emailLabel);
        mAddressText  = (TextView)findViewById(R.id.addressLabel);
        mThemeText = (TextView) findViewById(R.id.themeLabel);
        mCakeTitleText = (TextView) findViewById(R.id.cakeTitalLabel);
        mShopText = (TextView) findViewById(R.id.shopLabel);



        mNumberText.setText(Constant.numberText);
        mEmailText.setText(Constant.emailText);
        mAddressText.setText(Constant.address);
        mCakeTitleText.setText(Constant.cakeText);
        mShopText.setText(Constant.shopName);

        String theme = Constant.cakeTheme;

//        int flavourPos = Data.baseIndex;
//        mFlavourText.setText("Base Flavour: " +
//                Pages.cakePage.getObjects()[flavourPos].getDisplayName());
//        mFlavourText.setVisibility(View.VISIBLE);
//
//        //toppings ops
//
//        mToppingText = (TextView) findViewById(R.id.toppingLabel);
//        int toppingPos = Data.topIndex;
//        mToppingText.setText("Topping: " +
//                Pages.creamPage.getObjects()[toppingPos].getDisplayName());
//        mToppingText.setVisibility(View.VISIBLE);

        //weight ops

//        mWeightText = (TextView) findViewById(R.id.weightLabel);
//        int weightPos = Data.sizeIndex;
//        mWeightText.setText(Pages.weights.getObjects()[weightPos].getDisplayName());
//        cost = Pages.weights.getObjects()[weightPos].getPrice();

        //theme ops


        if (!theme.isEmpty()){
            mThemeText.setText("Personal Theme: " + theme + " (add Rs. 200)");
            cost+=200;
        }
        else{
            mThemeText.setVisibility(View.GONE);
        }
        //sugarfree settings
//        mSugarFreeText = (TextView) findViewById(R.id.sugarFreeLabel);
//        int sugarIndex = Data.sugarIndex;
//        mSugarFreeText.setText("Sugarfree: " +
//                Pages.sugarPage.getObjects()[sugarIndex].getDisplayName());
//        if (sugarIndex == 0){
//            cost+=100;
//        }


        mCostText = (TextView) findViewById(R.id.finalCostLabel);
        mCostText.setText("Total Cost: " + cost);

//        mChangeButton = (Button) findViewById(R.id.changeDateButton);
//        mChangeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setDateAndTime();
//            }
//        });
    }

//    private void setDateAndTime() {
//        Calendar now = Calendar.getInstance();
//        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePickerDialog, int year, int month, int day) {
//                GregorianCalendar date = new GregorianCalendar(year, month, day);
//                SimpleDateFormat formatter = new SimpleDateFormat("d MMMM y");
//                mDateString = formatter.format(date.getTime());
//                //date operation
//                mDateText = (TextView) findViewById(R.id.dateLabel);
//                mDateText.setText("Delivery Date: " + mDateString);
//                mDateText.setVisibility(View.VISIBLE);
//                setTime();
//            }
//        };
//        DatePicker dpd = DatePickerDialog.newInstance(
//                listener,
//                now.get(Calendar.YEAR),
//                now.get(Calendar.MONTH),
//                now.get(Calendar.DAY_OF_MONTH)
//        );
//        dpd.setThemeDark(true);
//        dpd.show(getFragmentManager(), "Select You Delivery Date");
//    }

//    private void setTime() {
//        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(RadialPickerLayout radialPickerLayout, int hour, int minute) {
//                String dep;
//                if (hour>13){
//                    hour-=12;
//                    dep = " PM";
//                }
//                else dep = " AM";
//                String minString = String.valueOf(minute);
//                if (minString.length() < 2){
//                    minString = " " + minString;
//                }
//                mTimeString = "" + hour + " : " + minString + dep;
//                //time operations
//                mTimeText = (TextView) findViewById(R.id.timeLabel);
//                mTimeText.setText("Delivery Time: " + mTimeString);
//                mTimeText.setVisibility(View.VISIBLE);
//                setLayout();
//            }
//        };
//        Calendar now = Calendar.getInstance();
//        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
//                listener,
//                now.get(Calendar.HOUR_OF_DAY),
//                now.get(Calendar.MINUTE),
//                false
//        );
//        timePickerDialog.setThemeDark(true);
//        timePickerDialog.show(getFragmentManager(), "Select Delivery Time");
//    }
}
