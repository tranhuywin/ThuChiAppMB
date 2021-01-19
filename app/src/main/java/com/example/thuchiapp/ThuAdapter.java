package com.example.thuchiapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thuchiapp.data.model.ThuUser;

import java.util.List;

public class ThuAdapter extends BaseAdapter {
    Context myContext;
    int Layout;
    List<ThuUser> thuUserList;
    public ThuAdapter(Context context, int Layout, List<ThuUser> thuUserList){
        this.myContext = context;
        this.Layout = Layout;
        this.thuUserList = thuUserList;
    }
    @Override
    public int getCount() {
        return thuUserList.size();
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
        TextView txtNgay = (TextView) convertView.findViewById(R.id.textView6);
        txtNgay.setText(String.valueOf(thuUserList.get(position).getDated()));
        TextView txtThang = (TextView) convertView.findViewById(R.id.textView3);
        txtThang.setText(String.valueOf(thuUserList.get(position).getMonth()));
        TextView txtNam = (TextView) convertView.findViewById(R.id.textView4);
        txtNam.setText(String.valueOf(thuUserList.get(position).getYear()));
        TextView txtSoTien = (TextView) convertView.findViewById(R.id.textView5);
        txtSoTien.setText(String.valueOf(thuUserList.get(position).getPrice()));
        TextView txtloai = (TextView) convertView.findViewById(R.id.textView7);
        txtloai.setText(String.valueOf(thuUserList.get(position).getType()));
        TextView txtghichu = (TextView) convertView.findViewById(R.id.textView8);
        txtghichu.setText(String.valueOf(thuUserList.get(position).getNote()));
        return convertView;
    }

}
