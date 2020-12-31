package com.example.thuchiapp.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String Password;
    private String Email;
    private int MoneyNotification;
    private String HoVaTen;

    public void setPassword(String password) {
        Password = password;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setMoneyNotification(int moneyNotification) {
        MoneyNotification = moneyNotification;
    }

    public void setHoVaTen(String hoVaTen) {
        HoVaTen = hoVaTen;
    }

    public int getMoneyNotification() {
        return MoneyNotification;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }
    public String getEmail() {
        return Email;
    }
    public String getPassword(){return Password; }

    public LoggedInUser(String Email, String Password, int MoneyNotification, String HoVaTen) {
        this.Email = Email;
        this.Password = Password;
        this.MoneyNotification = MoneyNotification;
        this.HoVaTen = HoVaTen;
    }


}