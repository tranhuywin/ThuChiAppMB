package com.example.thuchiapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thuchiapp.data.model.ChiUser;
import com.example.thuchiapp.data.model.ThuUser;
import com.example.thuchiapp.data.model.User;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ChiTietThongKe extends AppCompatActivity {

    private BarChart thongKeBarChart;
    String TypeSoTienMinThongke = "", TypeSoTienMaxThongke = "";
    String TypeThoiGianThongke, TypeLoaiThongke;
    int Thang = 1, Nam = 2020;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thong_ke);
        Intent intent = getIntent();
        TypeThoiGianThongke = intent.getStringExtra("TypeThoiGianThongke");
        TypeLoaiThongke = intent.getStringExtra("TypeLoaiThongke");
        TypeSoTienMinThongke = intent.getStringExtra("TypeSoTienMinThongke");
        TypeSoTienMaxThongke = intent.getStringExtra("TypeSoTienMaxThongke");
        Thang = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("ThangThongke")));
        Nam = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("NamThongke")));
        thongKeBarChart = (BarChart) findViewById(R.id.BarChartThongKe);

        switch (TypeThoiGianThongke) {
            case "Ngày":
                ThongKeNgay(Thang, Nam, TypeLoaiThongke);
                break;
            case "Tháng":
                ThongKeThang(Nam, TypeLoaiThongke);
                break;
            case "Năm":
                ThongKeNam(2020, 2030, TypeLoaiThongke);
                break;
        }


    }
    private void ThongKeThang(int Year, String TypeLoaiThongke){
        User UserBarData = User.getInstance();
        List<ThuUser> thuUserList = UserBarData.getListThu();
        List<ChiUser> chiUserList = UserBarData.getListChi();
        int UserListSize;

        if(TypeLoaiThongke.equals("Thu"))
        {
            thuUserList = UserBarData.getListThu();
            UserListSize = thuUserList.size();
        }
        else
        {
            chiUserList = UserBarData.getListChi();
            UserListSize = chiUserList.size();
        }
        ArrayList<BarEntry> visitors = new ArrayList<>();

        if(TypeLoaiThongke.equals("Thu"))
        {
            for(int i = 0; i < 12; i++){
                int TongTien = 0;
                for(int j = 0 ; j < UserListSize; j++){
                    if(thuUserList.get(j).getMonth() == i && thuUserList.get(i).getYear() == Year )
                    {
                        TongTien += thuUserList.get(j).getPrice();
                    }
                }
                if(TongTien > 0)
                    visitors.add(new BarEntry(i, TongTien));
            }
        }
        else
        {
            for(int i = 0; i < 12; i++){
                int TongTien = 0;
                for(int j = 0 ; j < UserListSize; j++){
                    if(chiUserList.get(j).getMonth() == i && chiUserList.get(i).getYear() == Year )
                    {
                        TongTien += chiUserList.get(j).getPrice();
                    }
                }
                if(TongTien > 0)
                    visitors.add(new BarEntry(i, TongTien));
            }
        }


        BarDataSet barDataSet = new BarDataSet(visitors,"Số tiền VND");
        barDataSet.setColors(Color.parseColor("#fa5246"));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        thongKeBarChart.setFitBars(true);
        thongKeBarChart.setData(barData);
        thongKeBarChart.getDescription().setText("");
        thongKeBarChart.animateY(2000);
    }
    private void ThongKeNgay(int Thang,int Nam, String TypeLoaiThongke){
        User UserBarData = User.getInstance();
        List<ThuUser> thuUserList = null;
        List<ChiUser> chiUserList = null;
        int UserListSize = 0;
        if(TypeLoaiThongke.equals("Thu"))
        {
            thuUserList = UserBarData.getListThu();
            UserListSize = thuUserList.size();
        }
        else
        {
            chiUserList = UserBarData.getListChi();
            UserListSize = chiUserList.size();
        }

        ArrayList<BarEntry> visitors = new ArrayList<>();

        if(TypeLoaiThongke.equals("Thu"))
        {
            for(int i = 0; i < 31; i++){
                int TongTien = 0;
                for(int j = 0 ; j < UserListSize; j++){
                    if(thuUserList.get(j).getDated() == i && thuUserList.get(j).getMonth() == Thang && thuUserList.get(j).getYear() == Nam)
                    {
                        if(!TypeSoTienMinThongke.equals("") && !TypeSoTienMaxThongke.equals("")) {
                            if (thuUserList.get(j).getPrice() >= Integer.parseInt(TypeSoTienMinThongke) && thuUserList.get(j).getPrice() <= Integer.parseInt(TypeSoTienMaxThongke))
                                TongTien += thuUserList.get(j).getPrice();
                        }
                        else
                            TongTien += thuUserList.get(j).getPrice();
                    }
                }

                if(TongTien > 0)
                    visitors.add(new BarEntry(i, TongTien));
            }
        }
        else
        {
            for(int i = 0; i < 31; i++){
                int TongTien = 0;
                for(int j = 0 ; j < UserListSize; j++){
                    if(chiUserList.get(j).getDated() == i && chiUserList.get(j).getMonth() == Thang && thuUserList.get(j).getYear() == Nam)
                    {
                        if(!TypeSoTienMinThongke.equals("") && !TypeSoTienMaxThongke.equals("")) {
                            if (chiUserList.get(j).getPrice() >= Integer.parseInt(TypeSoTienMinThongke) && chiUserList.get(j).getPrice() <= Integer.parseInt(TypeSoTienMaxThongke))
                                TongTien += chiUserList.get(j).getPrice();
                        }
                        else
                            TongTien += chiUserList.get(j).getPrice();
                    }
                }

                if(TongTien > 0)
                    visitors.add(new BarEntry(i, TongTien));
            }
        }

        BarDataSet barDataSet = new BarDataSet(visitors,"Số tiền VND");
        barDataSet.setColors(Color.parseColor("#fa5246"));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        thongKeBarChart.setFitBars(true);
        thongKeBarChart.setData(barData);
        thongKeBarChart.getDescription().setText("");
        thongKeBarChart.animateY(2000);
    }
    private void ThongKeNam(int min, int max, String TypeLoaiThongke){
        User UserBarData = User.getInstance();
        List<ThuUser> thuUserList = null;
        List<ChiUser> chiUserList = null;
        int UserListSize = 0;
        if(TypeLoaiThongke.equals("Thu"))
        {
            thuUserList = UserBarData.getListThu();
            UserListSize = thuUserList.size();
        }
        else
        {
            chiUserList = UserBarData.getListChi();
            UserListSize = chiUserList.size();
        }

        ArrayList<BarEntry> visitors = new ArrayList<>();

        if(TypeLoaiThongke.equals("Thu"))
        {
            for(int i = min; i < max; i++){
                int TongTien = 0;
                for(int j = 0 ; j < UserListSize; j++){
                    if(thuUserList.get(j).getYear() == i)
                    {
                        TongTien += thuUserList.get(j).getPrice();
                    }
                }
                if(TongTien > 0)
                    visitors.add(new BarEntry(i, TongTien));
            }
        }
        else
        {
            for(int i = min; i < max; i++){
                int TongTien = 0;
                for(int j = 0 ; j < UserListSize; j++){
                    if(chiUserList.get(j).getYear() == i)
                    {
                        TongTien += chiUserList.get(j).getPrice();
                    }
                }
                if(TongTien > 0)
                    visitors.add(new BarEntry(i, TongTien));
            }
        }

        BarDataSet barDataSet = new BarDataSet(visitors,"Năm");
        barDataSet.setColors(Color.parseColor("#fa5246"));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        thongKeBarChart.setFitBars(true);
        thongKeBarChart.setData(barData);
        thongKeBarChart.getDescription().setText("");
        thongKeBarChart.animateY(2000);
    }
    private void ThongKeLoai(String Loai){
        User UserBarData = User.getInstance();
        //List<ThuUser> thuUserList = UserBarData.getListThu();
        List<ChiUser> chiUserList = UserBarData.getListChi();
        int chiUserListSize = chiUserList.size();

        ArrayList<BarEntry> visitors = new ArrayList<>();
        //ArrayList<String> labels = new ArrayList<>();
        //labels.add("tét");
        List<String> Lines = Arrays.asList(getResources().getStringArray(R.array.loai_thong_ke));
        for(int i = 0; i < Lines.size(); i++){
            int TongTien = 0;
            for(int j = 0 ; j < chiUserListSize; j++){
                if(chiUserList.get(j).getType().equals(Lines.get(i)))
                {
                    TongTien += chiUserList.get(j).getPrice();
                }
                System.out.println(Lines.get(i));
            }
            if(TongTien > 0)
            {
                visitors.add(new BarEntry(i, TongTien));
            }
        }


        BarDataSet barDataSet = new BarDataSet(visitors,Loai);
        barDataSet.setColors(Color.parseColor("#fa5246"));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData( barDataSet);

        thongKeBarChart.setFitBars(true);
        thongKeBarChart.setData(barData);
        thongKeBarChart.getDescription().setText("");
        thongKeBarChart.animateY(2000);
    }
}
