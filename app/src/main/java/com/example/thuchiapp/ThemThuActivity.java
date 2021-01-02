package com.example.thuchiapp;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ThemThuActivity extends AppCompatActivity {

    private TextView mTextView;
    DatePicker datepicker_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thu);

        mTextView = (TextView) findViewById(R.id.text);

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        datepicker_btn = (DatePicker) findViewById(R.id.ChonNgay_ThemThu);
        datepicker_btn.init(mYear,mMonth,mDay,null);

    }
}