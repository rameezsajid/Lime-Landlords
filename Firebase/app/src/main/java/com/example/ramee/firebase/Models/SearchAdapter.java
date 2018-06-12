package com.example.ramee.firebase.Models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramee.firebase.R;

import java.util.ArrayList;

/**
 * Created by ramee on 10/04/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> propertyLocationList;
    ArrayList<String> propertyPriceList;

    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView property_location, property_price;

        public SearchViewHolder(View itemView) {
            super(itemView);
            property_location = (TextView) itemView.findViewById(R.id.property_location);
            property_price = (TextView) itemView.findViewById(R.id.property_price);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> fullNameList, ArrayList<String> userNameList) {
        this.context = context;
        this.propertyLocationList = fullNameList;
        this.propertyPriceList = userNameList;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.property_location.setText(propertyLocationList.get(position));
        holder.property_price.setText(propertyPriceList.get(position));

        holder.property_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Full Name Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return propertyLocationList.size();
    }
}