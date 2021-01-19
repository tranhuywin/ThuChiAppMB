package com.example.thuchiapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thuchiapp.data.model.ChiUser;
import com.example.thuchiapp.data.model.ThuUser;

import java.util.List;

public class ChiAdapter extends BaseAdapter {
    Context myContext;
    int Layout;
    List<ChiUser> chiUserList;
    public ChiAdapter(Context context, int Layout, List<ChiUser> chiUserList){
        this.myContext = context;
        this.Layout = Layout;
        this.chiUserList = chiUserList;
    }
    @Override
    public int getCount() {
        return chiUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(this.Layout, null);
        TextView txtNgay = (TextView) convertView.findViewById(R.id.textViewChi6);
        txtNgay.setText(String.valueOf(chiUserList.get(position).getDated()));
        TextView txtThang = (TextView) convertView.findViewById(R.id.textViewChi3);
        txtThang.setText(String.valueOf(chiUserList.get(position).getMonth()));
        TextView txtNam = (TextView) convertView.findViewById(R.id.textViewChi4);
        txtNam.setText(String.valueOf(chiUserList.get(position).getYear()));
        TextView txtSoTien = (TextView) convertView.findViewById(R.id.textViewChi5);
        txtSoTien.setText(String.valueOf(chiUserList.get(position).getPrice()));
        TextView txtloai = (TextView) convertView.findViewById(R.id.textViewChi7);
        txtloai.setText(String.valueOf(chiUserList.get(position).getType()));
        TextView txtghichu = (TextView) convertView.findViewById(R.id.textViewChi8);
        txtghichu.setText(String.valueOf(chiUserList.get(position).getNote()));
        return convertView;
    }
}
