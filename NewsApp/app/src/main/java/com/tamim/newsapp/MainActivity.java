package com.tamim.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
    LinearLayoutManager horizontalLayoutManager;
    HorizontalRecyclerViewAdapter horizontalAdapter;
    RecyclerView verticalRecyclerView;
    VerticalRecyclerViewAdapter verticalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horizontalRecyclerView = findViewById(R.id.horizontalRecyclerView);
        verticalRecyclerView = findViewById(R.id.verticalRecyclerView);

        horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontalAdapter = new HorizontalRecyclerViewAdapter(News.loadStory(), new OnItemClickListener() {
            @Override public void onItemClick(News news) {
                System.out.println("story "+news.getTitle()+" clicked!");
            }
        });
        horizontalRecyclerView.setLayoutManager(horizontalLayoutManager);
        horizontalRecyclerView.setAdapter(horizontalAdapter);

        verticalAdapter = new VerticalRecyclerViewAdapter(News.loadNews(), new OnItemClickListener() {
            @Override public void onItemClick(News news) {
                System.out.println("news "+news.getTitle()+" clicked!");
            }
        });
        verticalRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        verticalRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
        verticalRecyclerView.setAdapter(verticalAdapter);
    }

    public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.MyView> {
        private List<News> newsList;
        private final OnItemClickListener listener;

        public class MyView
                extends RecyclerView.ViewHolder {

            TextView storyTitle;
            ImageView storyImageView;
            public MyView(View view) {
                super(view);
                storyImageView = (ImageView) view.findViewById(R.id.storyImageView);
                storyTitle = (TextView) view.findViewById(R.id.storyTitle);
            }
            public void bind(final News news, final OnItemClickListener listener) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        listener.onItemClick(news);
                    }
                });
            }
        }
        public HorizontalRecyclerViewAdapter(List<News> newsList, OnItemClickListener listener) {
            this.newsList = newsList;
            this.listener = listener;
        }
        @NonNull
        @Override
        public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.story_item, parent, false);
            return new MyView(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyView holder, int position) {
            holder.storyTitle.setText(newsList.get(position).getTitle());
            Picasso.get().load(newsList.get(position).getImageUrl()).into(holder.storyImageView);
            holder.bind(newsList.get(position), listener);
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }
    }

    public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.MyView> {
        private List<News> newsList;
        private final OnItemClickListener listener;

        public class MyView
                extends RecyclerView.ViewHolder {

            TextView newsTitle;
            ImageView imageView;
            public MyView(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.imageView);
                newsTitle = (TextView) view.findViewById(R.id.newsTitle);
            }
            public void bind(final News news, final OnItemClickListener listener) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        listener.onItemClick(news);
                    }
                });
            }
        }
        public VerticalRecyclerViewAdapter(List<News> newsList, OnItemClickListener listener) {
            this.newsList = newsList;
            this.listener = listener;
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
            holder.bind(newsList.get(position), listener);
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }
    }
}