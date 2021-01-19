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

import com.example.thuchiapp.ChiAdapter;
import com.example.thuchiapp.R;
import com.example.thuchiapp.ThemChiActivity;
import com.example.thuchiapp.data.model.ChiUser;
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
public class Chi extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button NewChi_btn;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ChiAdapter adapter;
    List<ChiUser> chiUserList;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    User userDTO;
    public Chi() {
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
    public static Chi newInstance(String param1, String param2) {
        Chi fragment = new Chi();
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
        View view = inflater.inflate(R.layout.fragment_chi, container, false);
        NewChi_btn = (Button) view.findViewById(R.id.CreateChi);
        final ProgressBar progressBarChi = (ProgressBar)view.findViewById(R.id.ProgressChi);
        final ListView listViewChi = (ListView)view.findViewById(R.id.ListViewChi);

        NewChi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ThemChiActivity.class);
                startActivity(intent);
            }
        });
        listViewChi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog ad = new AlertDialog.Builder(getActivity())
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("xoa");
                                chiUserList.remove(position);
                                adapter.notifyDataSetChanged();

                                userDTO.setListChi(chiUserList);
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
        progressBarChi.setVisibility(View.VISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assert user != null;

        databaseReference = firebaseDatabase.getReference("Users/"+user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userDTO = dataSnapshot.getValue(User.class);
                progressBarChi.setVisibility(View.GONE);
                // lay du lieu user
                chiUserList = userDTO.getListChi();
                adapter = new ChiAdapter(getActivity(), R.layout.list_chi, chiUserList);
                listViewChi.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
    void DeleteItem(int position){

    }
}