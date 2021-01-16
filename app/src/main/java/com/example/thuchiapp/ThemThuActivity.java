package com.example.thuchiapp;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thuchiapp.data.model.ThuUser;
import com.example.thuchiapp.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ThemThuActivity extends AppCompatActivity {

    private TextView mTextView;
    DatePicker datepicker_btn;
    Button Save_btn;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();;
    DatabaseReference databaseReference;
    private User userSnapShot;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thu);

        mTextView = (TextView) findViewById(R.id.text);
        Save_btn = (Button) findViewById(R.id.SaveThu);

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        datepicker_btn = (DatePicker) findViewById(R.id.ChonNgay_ThemThu);
        datepicker_btn.init(mYear,mMonth,mDay,null);
        //add user data to database
        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User loggedInUser = userSnapShot;
                List<ThuUser> thuUserList = userSnapShot.getListThu();
                //ToDo: them du lieu tu view vao
                ThuUser thuUser = new ThuUser("xang", 1000, 1,1,2021);
                thuUserList.add(thuUser);
                databaseReference.setValue(loggedInUser);
                Toast.makeText(getApplicationContext(), "Thêm thu thành công",Toast.LENGTH_SHORT).show();
            }
        });
        //get user data from firebase
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assert user != null;

        databaseReference = firebaseDatabase.getReference("Users/"+user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userSnapShot = dataSnapshot.getValue(User.class);
                //ProgressSetting_pb.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
                //ProgressSetting_pb.setVisibility(View.GONE);
            }
        });
    }
}