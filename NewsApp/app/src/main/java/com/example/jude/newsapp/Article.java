package com.example.jude.newsapp;

public class Article {

    private String title;

    private String nameSection;

    private String dataPublished;

    private String url;

    public Article(String title, String nameSection, String dataPublished, String url) {
        this.title = title;
        this.nameSection = nameSection;
        this.dataPublished = dataPublished;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getNameSection() {
        return nameSection;
    }

    public String getDataPublished() {
        return dataPublished;
    }

    public String getUrl() {
        return url;
    }
}
