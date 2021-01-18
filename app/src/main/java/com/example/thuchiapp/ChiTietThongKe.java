package com.example.thuchiapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ChiTietThongKe extends AppCompatActivity {

    private BarChart thongKeBarChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thong_ke);
        Intent intent = getIntent();
        String TypeThoiGianThongke = intent.getStringExtra("TypeThoiGianThongke");
        String TypeLoaiThongke = intent.getStringExtra("TypeLoaiThongke");
        String TypeSoTienMinThongke = intent.getStringExtra("TypeSoTienMinThongke");
        String TypeSoTienMaxThongke = intent.getStringExtra("TypeSoTienMaxThongke");

        thongKeBarChart = (BarChart) findViewById(R.id.BarChartThongKe);
        ThongKe(TypeThoiGianThongke);

    }
    private void ThongKe(String Type){
        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(2017f,320));
        visitors.add(new BarEntry(2018,280));
        visitors.add(new BarEntry(2019,420));
        visitors.add(new BarEntry(2020,520));

        BarDataSet barDataSet = new BarDataSet(visitors,Type);
        barDataSet.setColors(Color.parseColor("#fa5246"));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        thongKeBarChart.setFitBars(true);
        thongKeBarChart.setData(barData);
        thongKeBarChart.getDescription().setText("");
        thongKeBarChart.animateY(2000);
    }
}