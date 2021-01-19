package com.example.thuchiapp.ui.Components;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.thuchiapp.R;
import com.example.thuchiapp.ThemThuActivity;
import com.example.thuchiapp.ThuAdapter;
import com.example.thuchiapp.data.model.ThuUser;
import com.example.thuchiapp.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Thu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Thu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button NewThu_btn;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<ThuUser> thuUserList;
    ThuAdapter adapter;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    User userDTO;
    public Thu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Thu.
     */
    // TODO: Rename and change types and number of parameters
    public static Thu newInstance(String param1, String param2) {
        Thu fragment = new Thu();
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
        View view = inflater.inflate(R.layout.fragment_thu, container, false);
        NewThu_btn = (Button) view.findViewById(R.id.newThu);
        final ProgressBar progressBarThu = (ProgressBar)view.findViewById(R.id.ProgressThu);
        final ListView listViewThu = (ListView)view.findViewById(R.id.ListViewThu);
        NewThu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ThemThuActivity.class);
                startActivity(intent);
            }
        });

        listViewThu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog ad = new AlertDialog.Builder(getActivity())
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                thuUserList.remove(position);
                                adapter.notifyDataSetChanged();

                                userDTO.setListThu(thuUserList);
                                databaseReference = firebaseDatabase.getReference("Users/" + Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
                                databaseReference.setValue(userDTO);
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                            }
                        })
                        .setNegativeButton("Canncel", null)
                        .create();
                ad.setCancelable(false);
                ad.setTitle("Xóa thông tin chi");
                ad.setMessage("Bạn có muốn xóa");
                ad.show();
                return false;
            }
        });
        progressBarThu.setVisibility(View.VISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assert user != null;

        databaseReference = firebaseDatabase.getReference("Users/"+user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userDTO = dataSnapshot.getValue(User.class);

                progressBarThu.setVisibility(View.GONE);
                // lay du lieu user
                thuUserList = userDTO.getListThu();

                adapter = new ThuAdapter(getActivity(), R.layout.list_thu, thuUserList);
                 listViewThu.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}