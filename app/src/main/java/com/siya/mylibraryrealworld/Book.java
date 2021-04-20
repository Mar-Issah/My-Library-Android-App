package com.siya.mylibraryrealworld;

public class Book {                     //In java this class is called ; POJO: Plain Old Java Object. it has some field such as a constructor, getters and setters and string method
    private int id;
    private String name;
    private String author;
    private int pages;
    private String imageUrl;
    private String shortDesc;
    private String longDecs;
    private boolean isExpanded;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public Book(int id, String name, String author, int pages, String imageUrl, String shortDesc, String longDecs) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.imageUrl = imageUrl;
        this.shortDesc = shortDesc;
        this.longDecs = longDecs;
        isExpanded =false;                      //not going to receive any info but making that anything you create a new book the relative layout is collapsed
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDecs() {
        return longDecs;
    }

    public void setLongDecs(String longDecs) {
        this.longDecs = longDecs;
    }

    @Override
    public String toString() {                                  //take all the variables and return a string with a new format
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", imageUrl='" + imageUrl + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDecs='" + longDecs + '\'' +
                '}';
    }
}
