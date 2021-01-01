package com.example.thuchiapp.ui.Components;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thuchiapp.R;
import com.example.thuchiapp.data.model.LoggedInUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

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
    private Button Save_btn;
    private EditText FullName_et;
    private EditText Email_et;
    private EditText Password_et;
    private EditText Money_et;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cai_dat, container, false);

        Save_btn = (Button) view.findViewById(R.id.SaveSetting);
        Email_et = (EditText) view.findViewById(R.id.EmailSetting);
        Password_et = (EditText) view.findViewById(R.id.passwordSetting);
        Money_et = (EditText) view.findViewById(R.id.MoneySetting);
        FullName_et= (EditText) view.findViewById(R.id.FullNameSetting);

        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoggedInUser loggedInUser = new LoggedInUser(Email_et.getText().toString(),Password_et.getText().toString(),Integer.valueOf(Money_et.getText().toString()),FullName_et.getText().toString());
                databaseReference = firebaseDatabase.getReference("Users/" +firebaseAuth.getCurrentUser().getUid());
                databaseReference.setValue(loggedInUser);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                user.updateEmail(Email_et.getText().toString());
                user.updatePassword(Password_et.getText().toString());
                FirebaseAuth.getInstance().updateCurrentUser(user);

                Toast.makeText(getActivity(), "Cập nhập thành công",Toast.LENGTH_SHORT).show();
        }
        });
        firebaseAuth =FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assert user != null;
        Email_et.setText(user.getEmail());

        databaseReference = firebaseDatabase.getReference("Users/"+user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LoggedInUser loggedInUser = new LoggedInUser();
                loggedInUser = dataSnapshot.getValue(LoggedInUser.class);
                FullName_et.setText(loggedInUser.getHoVaTen());
                Money_et.setText(String.valueOf(loggedInUser.getMoneyNotification()));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getActivity(), error.toString(),Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
}