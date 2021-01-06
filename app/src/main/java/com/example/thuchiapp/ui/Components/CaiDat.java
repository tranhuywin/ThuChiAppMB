package com.example.thuchiapp.ui.Components;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.thuchiapp.R;
import com.example.thuchiapp.data.model.User;
import com.example.thuchiapp.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaiDat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaiDat extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText FullName_et;
    private EditText Email_et;
    private EditText Password_et;
    private EditText Money_et;
    private ProgressBar ProgressSetting_pb;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();;
    DatabaseReference databaseReference;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseAuth firebaseAuth;
    public CaiDat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaiDat.
     */
    // TODO: Rename and change types and number of parameters
    public static CaiDat newInstance(String param1, String param2) {
        CaiDat fragment = new CaiDat();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cai_dat, container, false);

        Button save_btn = (Button) view.findViewById(R.id.saveSetting);
        Button logOut_btn = (Button) view.findViewById(R.id.LogOutSetting);
        Button editEmail_btn = (Button)view.findViewById(R.id.EmailEditSetting);
        Button editPassword_btn = (Button)view.findViewById(R.id.PasswordEditSetting);
        Button editFullName_btn = (Button)view.findViewById(R.id.FullNameEditSetting);
        Button editMoney_btn = (Button)view.findViewById(R.id.MoneyEditSetting);
        //Button showPassword_btn = (Button)view.findViewById(R.id.ShowPasswordSetting);
        Email_et = (EditText) view.findViewById(R.id.EmailSetting);
        Password_et = (EditText) view.findViewById(R.id.passwordSetting);
        Money_et = (EditText) view.findViewById(R.id.MoneySetting);
        FullName_et= (EditText) view.findViewById(R.id.FullNameSetting);
        ProgressSetting_pb = (ProgressBar)view.findViewById(R.id.ProgressSetting);

        editEmail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Email_et.isEnabled())
                    Email_et.setEnabled(false);
                else
                    Email_et.setEnabled(true);
            }
        });
        editPassword_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Password_et.isEnabled())
                    Password_et.setEnabled(false);
                else
                    Password_et.setEnabled(true);
            }
        });
        editFullName_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FullName_et.isEnabled())
                    FullName_et.setEnabled(false);
                else
                    FullName_et.setEnabled(true);
            }
        });
        editMoney_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Money_et.isEnabled())
                    Money_et.setEnabled(false);
                else
                    Money_et.setEnabled(true);
            }
        });

        /*showPassword_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });*/
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User loggedInUser = new User(Email_et.getText().toString(),Password_et.getText().toString(),Integer.parseInt(Money_et.getText().toString()),FullName_et.getText().toString());
                databaseReference = firebaseDatabase.getReference("Users/" + Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
                databaseReference.setValue(loggedInUser);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                user.updateEmail(Email_et.getText().toString());
                user.updatePassword(Password_et.getText().toString());
                FirebaseAuth.getInstance().updateCurrentUser(user);

                Toast.makeText(getActivity(), "Đã cập nhập thành công",Toast.LENGTH_SHORT).show();
        }
        });

        logOut_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        ProgressSetting_pb.setVisibility(View.VISIBLE);

        firebaseAuth =FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assert user != null;
        Email_et.setText(user.getEmail());

        databaseReference = firebaseDatabase.getReference("Users/"+user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user;
                user = dataSnapshot.getValue(User.class);

                assert user != null;
                FullName_et.setText(user.getHoVaTen());
                Money_et.setText(String.valueOf(user.getMoneyNotification()));
                Password_et.setText(user.getPassword());
                ProgressSetting_pb.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.toString(),Toast.LENGTH_SHORT).show();
                ProgressSetting_pb.setVisibility(View.GONE);
            }
        });
        return view;
    }
}