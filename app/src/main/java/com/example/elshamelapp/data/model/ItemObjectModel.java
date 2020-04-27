package com.example.elshamelapp.data.model;

public class ItemObjectModel {
    private String name;
    private int photo;

    public ItemObjectModel(String name, int photo) {
        this.name = name;
        this.photo = photo;
    }

    public ItemObjectModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}