package com.viit.cakeshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.viit.cakeshop.R;
import com.viit.cakeshop.adapter.Listitem_cake;
import com.viit.cakeshop.adapter.adapter_cake;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Cakes extends AppCompatActivity {
    RecyclerView cake_recycler;
    RecyclerView.Adapter adapterCake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cakes);
        cake_recycler=findViewById(R.id.cake_recycler);
        Bundle bundle = getIntent().getExtras();
        String shopName = bundle.getString("shop_name");
        showCakeDetails(Constant.endpoint+"/shops/"+shopName,cake_recycler,shopName);
    }
    public void showCakeDetails(String url,final RecyclerView recyclerView,String shopName){
        List<Listitem_cake> listitems_shop;
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
                                Listitem_cake item = new Listitem_cake(o.getString("image"),
                                        o.getString("cake_name"),
                                        o.getString("price"),
                                        o.getInt("price"));
                                listitems_shop.add(item);
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                            }

                        }
                        LinearLayoutManager verticalLayoutManagaer
                                = new LinearLayoutManager(Cakes.this, LinearLayoutManager.VERTICAL, false);
                        cake_recycler.setLayoutManager(verticalLayoutManagaer);
                        adapterCake =new adapter_cake(listitems_shop,getApplicationContext(),shopName);
                        cake_recycler.setAdapter(adapterCake);


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("error",String.valueOf(error));


            }
        }) ;
        requestQueue.add(jsonArrayReq);

    }
}