package com.example.thuchiapp.data.model;

public class ThuUser {
    String type;
    int price;
    int dated, month, year;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDated() {
        return dated;
    }

    public void setDated(int dated) {
        this.dated = dated;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }



    public ThuUser() {
    }

    public ThuUser(String type, int price, int date, int month, int year) {
        this.type = type;
        this.price = price;
        this.dated = date;
        this.month = month;
        this.year = year;
    }




}