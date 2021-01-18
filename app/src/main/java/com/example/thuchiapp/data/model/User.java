package com.example.thuchiapp.data.model;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class User {
    private static User instance;

    private User() {
    }
    private User(String Email, String Password, int MoneyNotification, String HoVaTen) {
        this.email = Email;
        this.password = Password;
        this.moneyNotification = MoneyNotification;
        this.hoVaTen = HoVaTen;
    }
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }
    private String password;
    private String email;
    private int moneyNotification;
    private String hoVaTen;
    private List<ThuUser> listThu = new ArrayList<>();
    private List<ChiUser> listChi = new ArrayList<>();

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();;
    DatabaseReference databaseReference;
    private User userSnapShot;
    FirebaseAuth firebaseAuth;
    public List<ThuUser> getListThu() {
        return listThu;
    }

    public void setListThu(List<ThuUser> listThu) {
        this.listThu = listThu;
    }

    public List<ChiUser> getListChi() {
        return listChi;
    }

    public void setListChi(List<ChiUser> listChi) {
        this.listChi = listChi;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMoneyNotification(int moneyNotification) {
        this.moneyNotification = moneyNotification;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public void LoadUser(){
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assert user != null;

        databaseReference = firebaseDatabase.getReference("Users/"+user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                userSnapShot = Objects.requireNonNull(dataSnapshot).getValue(User.class);

                assert userSnapShot != null;
                setEmail(userSnapShot.getEmail());
                setHoVaTen(userSnapShot.getHoVaTen());
                setPassword(userSnapShot.getPassword());
                setMoneyNotification(userSnapShot.getMoneyNotification());
                setListChi(userSnapShot.getListChi());
                setListThu(userSnapShot.getListThu());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}