package com.example.jude.newsapp;

public class Article {

    private String title;

    private String nameSection;

    private String dataPublished;

    private String author;

    private String url;

    public Article(String title, String nameSection, String author, String dataPublished, String url) {
        this.title = title;
        this.nameSection = nameSection;
        this.author = author;
        this.dataPublished = dataPublished;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getNameSection() {
        return nameSection;
    }

    public String getAuthor() {
        return author;
    }

    public String getDataPublished() {
        return dataPublished;
    }

    public String getUrl() {
        return url;
    }
}
