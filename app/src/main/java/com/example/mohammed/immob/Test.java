package com.example.mohammed.immob;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Adapter.SliderAdapter;

public class Test extends AppCompatActivity {
    ViewPager viewPagertest;
    SliderAdapter sliderAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        viewPagertest= (ViewPager)findViewById(R.id.myfirstviewpager);



    }
}
