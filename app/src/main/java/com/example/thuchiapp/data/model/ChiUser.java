package com.example.thuchiapp.data.model;

public class ChiUser {
    String Type;
    int Price;
    int Date, Month, Year;

    public ChiUser(){}
    public ChiUser(String type, int price, int date, int month, int year) {
        Type = type;
        Price = price;
        Date = date;
        Month = month;
        Year = year;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

}
