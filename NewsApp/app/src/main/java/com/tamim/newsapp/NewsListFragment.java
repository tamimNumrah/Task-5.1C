package com.tamim.newsapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsListFragment extends Fragment {
    RecyclerView horizontalRecyclerView;
    LinearLayoutManager horizontalLayoutManager;
    HorizontalRecyclerViewAdapter horizontalAdapter;
    RecyclerView verticalRecyclerView;
    VerticalRecyclerViewAdapter verticalAdapter;
    private Context context;

    public NewsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsListFragment newInstance(Context context) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.context = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView);
        verticalRecyclerView = view.findViewById(R.id.verticalRecyclerView);

        horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalAdapter = new HorizontalRecyclerViewAdapter(News.loadStory(), new OnItemClickListener() {
            @Override public void onItemClick(News news, List<News> relatedNews) {
                System.out.println("story "+news.getTitle()+" clicked!");
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, NewsFragment.newInstance(news, relatedNews, context), null)
                        .commit();
            }
        });
        horizontalRecyclerView.setLayoutManager(horizontalLayoutManager);
        horizontalRecyclerView.setAdapter(horizontalAdapter);

        verticalAdapter = new VerticalRecyclerViewAdapter(News.loadNews(), new OnItemClickListener() {
            @Override public void onItemClick(News news, List<News> relatedNews) {
                System.out.println("news "+news.getTitle()+" clicked!");
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, NewsFragment.newInstance(news, relatedNews, context), null)
                        .commit();
            }
        });
        verticalRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        verticalRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
        verticalRecyclerView.setAdapter(verticalAdapter);
        return view;
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
                        listener.onItemClick(news, newsList);
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
            View view = LayoutInflater.from(getContext()).inflate(R.layout.story_item, parent, false);
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
                        listener.onItemClick(news, newsList);
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
            View view = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
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