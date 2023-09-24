package com.example.team24dungeoncrawler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class EndingScreen extends AppCompatActivity {

    private String name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending_screen);

        name = getIntent().getStringExtra("name");
    }
}
