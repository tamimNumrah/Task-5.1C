package com.tamim.newsapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class News implements Serializable {
    private String title;
    private String details;
    private String imageUrl;

    public News(String title, String details, String imageUrl) {
        this.title = title;
        this.details = details;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static List<News> loadStory() {
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String title = "Story Title " + (i+1);
            String details = "This is a sample description for news item number " + (i+1);
            String imageURL = "https://dummyimage.com/400x400/ffbb86fc&text=Top+Story+" + (i+1);
            News news = new News(title, details, imageURL);
            newsList.add(news);
        }
        return newsList;
    }

    public static List<News> loadNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String title = "News Title " + (i+1);
            String details = "This is a sample description for news item number " + (i+1);
            String imageURL = "https://dummyimage.com/600x400/03dac5&text=News+Image+" + (i+1);
            News news = new News(title, details, imageURL);
            newsList.add(news);
        }
        return newsList;
    }
}
