package com.example.thuchiapp.ui.Components;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.thuchiapp.ChiTietThongKe;
import com.example.thuchiapp.R;
import com.example.thuchiapp.ThemThuActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKe extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String ThoiGianThongke;
    String LoaiThongke;
    String SoTienMin, SoTienMax;
    BarChart thongKeBarChart;
    public ThongKe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThongKe.
     */
    // TODO: Rename and change types and number of parameters
    public static ThongKe newInstance(String param1, String param2) {
        ThongKe fragment = new ThongKe();
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
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);

        final Spinner spinnerThoiGianThongke = (Spinner) view.findViewById(R.id.SpinnerThoiGianThongKe);
        Spinner spinnerloaiThongke = (Spinner) view.findViewById(R.id.SpinnerLoaiThongKe);
        LoaiThongke = spinnerloaiThongke.getSelectedItem().toString();
        final EditText soTienMinet = (EditText) view.findViewById(R.id.SoTienMinThongKe);
        final EditText soTienMaxet = (EditText) view.findViewById(R.id.SoTienMaxThongKe);
        Button WatchBarChartThongKe_btn = (Button) view.findViewById(R.id.WatchBarChartThongke);

        WatchBarChartThongKe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChiTietThongKe.class);
                intent.putExtra("TypeThoiGianThongke", spinnerThoiGianThongke.getSelectedItem().toString());
                intent.putExtra("TypeLoaiThongke", LoaiThongke);
                intent.putExtra("TypeSoTienMinThongke", soTienMinet.getText().toString());
                intent.putExtra("TypeSoTienMaxThongke", soTienMaxet.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }
}