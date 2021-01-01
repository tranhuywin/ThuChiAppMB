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

import com.example.thuchiapp.DAL.DatabaseHelper;
import com.example.thuchiapp.data.model.LoggedInUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    final DatabaseHelper db = new DatabaseHelper(this);

    Button SingUp_btn;
    EditText Email_et;
    EditText Password_et;
    EditText RePassword_et;
    ProgressBar Progress_SignUp;
    FirebaseAuth firebaseAuth;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth =FirebaseAuth.getInstance();

       /* if(firebaseAuth.getCurrentUser() != null)
        {
            Toast.makeText(getApplicationContext(),firebaseAuth.getCurrentUser().getDisplayName(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }*/



        SingUp_btn = (Button)findViewById(R.id.StartSignUp);
        Email_et = (EditText)findViewById(R.id.EmailSignUp);
        Password_et = (EditText)findViewById(R.id.PasswordSignUp);
        RePassword_et = (EditText)findViewById(R.id.RePasswordSignUp);
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
                            String FullName = "Admin";
                            LoggedInUser loggedInUser = new LoggedInUser(Email_et.getText().toString(),Password_et.getText().toString(),money,FullName);
                            databaseReference = firebaseDatabase.getReference("Users/" +firebaseAuth.getCurrentUser().getUid());
                            databaseReference.setValue(loggedInUser);

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
                //Email_et.setText(databaseReference.toString());
                //Toast.makeText(getApplicationContext(),databaseReference.getKey(),Toast.LENGTH_SHORT).show();
                /*databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            Email_et.setText(ds.getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/

            }
        });
    }
}