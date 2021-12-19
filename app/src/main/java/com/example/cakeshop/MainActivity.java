package com.example.cakeshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cakeshop.adapter.Listitem;
import com.example.cakeshop.adapter.adapter_shop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView shop_recycler;
    RecyclerView.Adapter adapterShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shop_recycler=findViewById(R.id.shop_recycler);
        showShopDetails(Constant.endpoint +"/shops",shop_recycler);
    }
    public void showShopDetails(String url,final RecyclerView recyclerView){
        List<Listitem> listitems_shop;
        listitems_shop=new ArrayList<>();

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(
                Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("error",String.valueOf(response));
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject o= response.getJSONObject(i);
                                Listitem item = new Listitem(o.getString("image"),
                                        o.getString("shop_name"),
                                        o.getString("address"));
                                listitems_shop.add(item);
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                            }

                        }
                        LinearLayoutManager verticalLayoutManagaer
                                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                        shop_recycler.setLayoutManager(verticalLayoutManagaer);
                        adapterShop =new adapter_shop(listitems_shop,getApplicationContext());
                        shop_recycler.setAdapter(adapterShop);


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("error",String.valueOf(error));


            }
        }) ;
        requestQueue.add(jsonArrayReq);

    }

    public void getShopDetails(){

    }
}