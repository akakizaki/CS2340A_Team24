package com.example.team24dungeoncrawler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EndingScreen extends AppCompatActivity {

    private String name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending_screen);

        name = getIntent().getStringExtra("name");
        //Testing to see if this prints
        System.out.println("test: " + name);

    }
}
