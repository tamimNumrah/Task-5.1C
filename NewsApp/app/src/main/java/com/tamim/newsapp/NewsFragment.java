package com.tamim.newsapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {

    private static final String ARG_PARAM1 = "news";
    private static final String ARG_PARAM2 = "relatedNews";
    private News news;
    private List<News> relatedNews;
    private Context context;

    ImageView newsImageView;
    TextView newsTitleTextView;
    TextView newsDetailsTextView;
    RecyclerView relatedStoriesRecyclerView;
    LinearLayoutManager relatedStoriesLayoutManager;
    NewsFragment.RelatedNewsAdapter relatedStoriesAdapter;
    Button backButton;
    public NewsFragment() {
        // Required empty public constructor
    }
    public static NewsFragment newInstance(News news, List<News> relatedNews, Context context) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, news);
        args.putSerializable(ARG_PARAM2, (Serializable) relatedNews);
        fragment.setArguments(args);
        fragment.context = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            news = (News)getArguments().getSerializable(ARG_PARAM1);
            relatedNews = (List<News>) getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        newsImageView = view.findViewById(R.id.newsImageView);
        newsTitleTextView = view.findViewById(R.id.newsTitleTextView);
        newsDetailsTextView = view.findViewById(R.id.newsDetailsTextView);
        relatedStoriesRecyclerView = view.findViewById(R.id.relatedStoriesRecyclerView);
        backButton = view.findViewById(R.id.button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, NewsListFragment.newInstance(context), null)
                        .addToBackStack(null)
                        .commit();
            }
        });
        newsTitleTextView.setText(news.getTitle());
        newsDetailsTextView.setText(news.getDetails());
        Picasso.get().load(news.getImageUrl()).into(newsImageView);

        relatedStoriesLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        relatedStoriesAdapter = new RelatedNewsAdapter(relatedNews, new OnItemClickListener() {
            @Override public void onItemClick(News news, List<News> relatedNews) {
                System.out.println("story "+news.getTitle()+" clicked!");
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, NewsFragment.newInstance(news, relatedNews, context), null)
                        .commit();
            }
        });
        relatedStoriesRecyclerView.setLayoutManager(relatedStoriesLayoutManager);
        relatedStoriesRecyclerView.setAdapter(relatedStoriesAdapter);

        return view;
    }

    public class RelatedNewsAdapter extends RecyclerView.Adapter<NewsFragment.RelatedNewsAdapter.MyView> {
        private List<News> newsList;
        private final OnItemClickListener listener;

        public class MyView
                extends RecyclerView.ViewHolder {

            TextView relatedNewsTitle;
            TextView relatedNewsDescription;
            ImageView relatedNewsItemImageview;

            public MyView(View view) {
                super(view);
                relatedNewsItemImageview = (ImageView) view.findViewById(R.id.relatedNewsItemImageview);
                relatedNewsTitle = (TextView) view.findViewById(R.id.relatedNewsTitle);
                relatedNewsDescription = (TextView) view.findViewById(R.id.relatedNewsDescription);
            }
            public void bind(final News news, final OnItemClickListener listener) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        listener.onItemClick(news, newsList);
                    }
                });
            }
        }
        public RelatedNewsAdapter(List<News> newsList, OnItemClickListener listener) {
            this.newsList = newsList;
            this.listener = listener;
        }
        @NonNull
        @Override
        public NewsFragment.RelatedNewsAdapter.MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.related_stories_item, parent, false);
            return new NewsFragment.RelatedNewsAdapter.MyView(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsFragment.RelatedNewsAdapter.MyView holder, int position) {
            holder.relatedNewsTitle.setText(newsList.get(position).getTitle());
            holder.relatedNewsDescription.setText(newsList.get(position).getDetails());
            Picasso.get().load(newsList.get(position).getImageUrl()).into(holder.relatedNewsItemImageview);
            holder.bind(newsList.get(position), listener);
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }
    }
}