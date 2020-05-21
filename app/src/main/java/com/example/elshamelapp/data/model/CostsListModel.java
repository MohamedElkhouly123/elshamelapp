package com.example.elshamelapp.data.model;

import androidx.room.PrimaryKey;

public class CostsListModel {

    private String name;
    private String cost;
    private String quantity;

    public CostsListModel(String name, String cost, String quantity, Integer id) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    public CostsListModel(String name, String cost, String quantity) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public CostsListModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}
