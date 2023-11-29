package com.example.team24dungeoncrawler.views;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.app.Activity;

import com.example.team24dungeoncrawler.R;
import com.example.team24dungeoncrawler.viewmodels.InstructionsActivity;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }

    public void exitGame(View view) {
        finishAffinity();
    }

}
