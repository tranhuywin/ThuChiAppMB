package com.example.thuchiapp;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.thuchiapp.data.model.ChiUser;
import com.example.thuchiapp.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SignUp extends AppCompatActivity {

    Button SingUp_btn;
    EditText Email_et;
    EditText FullName_et;
    EditText Password_et;
    EditText RePassword_et;
    ProgressBar Progress_SignUp;
    FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth =FirebaseAuth.getInstance();

        SingUp_btn = (Button)findViewById(R.id.StartSignUp);
        Email_et = (EditText)findViewById(R.id.EmailSignUp);
        Password_et = (EditText)findViewById(R.id.PasswordSignUp);
        RePassword_et = (EditText)findViewById(R.id.RePasswordSignUp);
        FullName_et = (EditText)findViewById(R.id.FullNameSignUp);
        Progress_SignUp = (ProgressBar)findViewById(R.id.ProgressSignUp);

        SingUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Password = Password_et.getText().toString();
                String RePassword = RePassword_et.getText().toString();
                if(Password.length() <6)
                {
                    Password_et.setError("Mật khẩu phải ít nhất 6 kí tự");
                    return;
                }
                if(RePassword.length() <6)
                {
                    RePassword_et.setError("Mật khẩu phải ít nhất 6 kí tự");
                    return;
                }
                if(!Password.equals(RePassword))
                {
                    RePassword_et.setError("Mật khẩu không khớp");
                    return;
                }

                Progress_SignUp.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(Email_et.getText().toString(), Password_et.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            int money = 100000;
                            String FullName = FullName_et.getText().toString();
                            User user = new User(Email_et.getText().toString(),Password_et.getText().toString(),money,FullName);

                            //khoi tao chi
                            ChiUser chiUser = new ChiUser("Thức ăn", 20000,1,1,2021);
                            ChiUser chiUser2 = new ChiUser("Nước", 10000,1,1,2021);
                            ChiUser chiUser3 = new ChiUser("Xăng", 50000,1,1,2021);
                            //tao list va them cac chi tiet chi vao list chi
                            List<ChiUser> listChiUser = new ArrayList<>();
                            listChiUser.add(chiUser);
                            listChiUser.add(chiUser2);
                            listChiUser.add(chiUser3);
                            //them vao user
                            user.setListChi(listChiUser);

                            databaseReference = firebaseDatabase.getReference("Users/" + Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
                            databaseReference.setValue(user);

                            Toast.makeText(getApplicationContext(),"Tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            Progress_SignUp.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
    }
}