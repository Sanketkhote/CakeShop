package com.viit.cakeshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viit.cakeshop.Cakes;
import com.viit.cakeshop.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter_shop  extends RecyclerView.Adapter<adapter_shop.ViewHolder>{
    private List<Listitem> listItems;
    private Context context;
    public adapter_shop(List<Listitem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }
    @NonNull
    @Override
    public adapter_shop.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_shop.ViewHolder holder, int position) {
        final Listitem listItem = listItems.get(position);
        holder.heading.setText(listItem.getHeading());
        holder.subHeading.setText("Address: " + listItem.getSubHeading());
        Picasso.with(context).load(listItem.getImage()).into(holder.letter);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, Cakes.class);
                intent.putExtra("shop_name",listItem.getHeading());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
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

        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            heading=itemView.findViewById(R.id.headingTextView);
            subHeading = (TextView) itemView.findViewById(R.id.subHeadingTextView);
            letter=itemView.findViewById(R.id.letterimage);
            linearLayout = itemView.findViewById(R.id.linear_click);
        }
    }
}
