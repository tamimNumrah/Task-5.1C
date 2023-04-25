package com.tamim.propertyapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager verticalLayoutManager;
    PropertyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new PropertyAdapter(Property.loadProperties());
        recyclerView.setLayoutManager(verticalLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.MyView> {
        private List<Property> propertyList;
        public class MyView
                extends RecyclerView.ViewHolder {

            TextView locationTextView;
            TextView rentTextView;
            TextView bedroomTextView;
            TextView carParkTextView;
            TextView bathroomTextView;
            ImageView imageView;

            public MyView(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.imageView);
                locationTextView = (TextView) view.findViewById(R.id.locationTextView);
                rentTextView = (TextView) view.findViewById(R.id.rentTextView);
                bedroomTextView = (TextView) view.findViewById(R.id.bedroomTextView);
                carParkTextView = (TextView) view.findViewById(R.id.carParkTextView);
                bathroomTextView = (TextView) view.findViewById(R.id.bathroomTextView);
            }
        }
        public PropertyAdapter(List<Property> propertyList) {
            this.propertyList = propertyList;
        }
        @NonNull
        @Override
        public PropertyAdapter.MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_item, parent, false);
            return new PropertyAdapter.MyView(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PropertyAdapter.MyView holder, int position) {
            holder.locationTextView.setText(propertyList.get(position).getLocation());
            holder.rentTextView.setText(propertyList.get(position).getRent() + " PW");
            holder.bedroomTextView.setText(propertyList.get(position).getBedroomCount() + " Bedroom");
            holder.carParkTextView.setText(propertyList.get(position).getCarParkCount() + " Car park");
            holder.bathroomTextView.setText(propertyList.get(position).getBathroomCount() + " Bathroom");
            Picasso.get().load(propertyList.get(position).getImageUrl()).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return propertyList.size();
        }
    }
}