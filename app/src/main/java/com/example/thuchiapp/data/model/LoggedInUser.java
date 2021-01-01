package com.example.thuchiapp.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String password;
    private String email;
    private int moneyNotification;
    private String hoVaTen;

    public int getMoneyNotification() {
        return moneyNotification;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword(){return password; }
    public LoggedInUser(){};
    public LoggedInUser(String Email, String Password, int MoneyNotification, String HoVaTen) {
        this.email = Email;
        this.password = Password;
        this.moneyNotification = MoneyNotification;
        this.hoVaTen = HoVaTen;
    }
}