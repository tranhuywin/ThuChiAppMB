package com.example.thuchiapp.data.model;

import com.example.thuchiapp.ui.Components.Chi;
import com.example.thuchiapp.ui.Components.Thu;

import java.util.List;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class User {

    private String password;
    private String email;
    private int moneyNotification;
    private String hoVaTen;
    private List<ThuUser> ListThu;
    private List<ChiUser> ListChi;

    public List<ThuUser> getListThu() {
        return ListThu;
    }

    public void setListThu(List<ThuUser> listThu) {
        ListThu = listThu;
    }

    public List<ChiUser> getListChi() {
        return ListChi;
    }

    public void setListChi(List<ChiUser> listChi) {
        ListChi = listChi;
    }

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
    public User(){};
    public User(String Email, String Password, int MoneyNotification, String HoVaTen) {
        this.email = Email;
        this.password = Password;
        this.moneyNotification = MoneyNotification;
        this.hoVaTen = HoVaTen;
    }
}