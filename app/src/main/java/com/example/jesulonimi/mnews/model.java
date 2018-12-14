package com.example.jesulonimi.mnews;

public class model {
    String title;
    String imageUrl;
String detailsUrl;
String publishedAt;
    public model() {
    }

    public model(String title, String imageUrl,String detailsUrl,String publishedAt) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.detailsUrl=detailsUrl;
        this.publishedAt=publishedAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
