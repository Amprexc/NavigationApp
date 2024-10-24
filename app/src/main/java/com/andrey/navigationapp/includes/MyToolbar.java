package com.andrey.navigationapp.includes;

import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.andrey.navigationapp.R;

public class MyToolbar {
    static Toolbar toolbar;
    public static void show(AppCompatActivity activity, String title, boolean upButton){
        toolbar = activity.findViewById(R.id.toolbar);
        //activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(title);
        //activity.getSupportActionBar().setDisplayHomeAsUpEnabled(boolean);
    }
}
