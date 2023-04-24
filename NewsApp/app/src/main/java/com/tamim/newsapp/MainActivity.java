package com.tamim.newsapp;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView horizontalRecyclerView;
    LinearLayoutManager linearLayoutManager;
    HorizontalRecyclerViewAdapter horizontalAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horizontalRecyclerView = findViewById(R.id.horizontalRecyclerView);

        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontalAdapter = new HorizontalRecyclerViewAdapter(News.loadNews());
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);
        horizontalRecyclerView.setAdapter(horizontalAdapter);
    }

    public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.MyView> {
        private List<News> newsList;

        public class MyView
                extends RecyclerView.ViewHolder {

            TextView newsTitle;
            ImageView imageView;
            public MyView(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.imageView);
                newsTitle = (TextView) view.findViewById(R.id.newsTitle);
            }
        }
        public HorizontalRecyclerViewAdapter(List<News> newsList) {
            this.newsList = newsList;
        }
        @NonNull
        @Override
        public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.news_item, parent, false);
            return new MyView(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyView holder, int position) {
            holder.newsTitle.setText(newsList.get(position).getTitle());
            Picasso.get().load(newsList.get(position).getImageUrl()).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }
    }

}