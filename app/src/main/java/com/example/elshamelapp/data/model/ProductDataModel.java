package com.example.elshamelapp.data.model;

public class ProductDataModel {




    private String name;
    private String cost;
    private int photo;

    public ProductDataModel(String name, int photo) {
        this.name = name;
        this.photo = photo;
    }

    public ProductDataModel(String name, String cost, int photo) {
        this.name = name;
        this.cost = cost;
        this.photo = photo;
    }

    public ProductDataModel(String name) {
        this.name = name;
    }
    public ProductDataModel() {
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}