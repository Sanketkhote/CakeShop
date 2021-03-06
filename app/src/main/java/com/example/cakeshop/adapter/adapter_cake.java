package com.example.cakeshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cakeshop.Cakes;
import com.example.cakeshop.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class adapter_cake extends RecyclerView.Adapter<adapter_cake.ViewHolder> {
    private List<Listitem_cake> listItems;
    private Context context;
    private String ShopName=new String();
    public adapter_cake(List<Listitem_cake> listItems, Context context,String shopName) {
        this.listItems = listItems;
        this.context = context;
        this.ShopName=shopName;
    }
    @NonNull
    @Override
    public adapter_cake.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cake, parent, false);
        return new adapter_cake.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_cake.ViewHolder holder, int position) {
        final Listitem_cake listItem = listItems.get(position);
        holder.heading.setText(listItem.getHeading());
        holder.subHeading.setText("Address: " + listItem.getSubHeading());
        Picasso.with(context).load(listItem.getImage()).into(holder.letter);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendBuyOrder(context,"http://192.168.225.181:8080/order",listItem,ShopName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView heading, subHeading;
        public ImageView letter;
        public Button buy;

        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            buy=itemView.findViewById(R.id.buyButton);
            heading=itemView.findViewById(R.id.headingTextView);
            subHeading = (TextView) itemView.findViewById(R.id.subHeadingTextView);
            letter=itemView.findViewById(R.id.letterimage);
            linearLayout = itemView.findViewById(R.id.linear_click);
        }
    }
    public void sendBuyOrder(Context context,String url,Listitem_cake listitem,String shopName) throws JSONException {
        JSONObject jsonRequest=new JSONObject();
        jsonRequest.put("shop_name",shopName);
        jsonRequest.put("cake_name",listitem.getHeading());
        jsonRequest.put("price",listitem.getPrice());
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,jsonRequest,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context,"Order placed Successfully",Toast.LENGTH_LONG).show();
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

}
