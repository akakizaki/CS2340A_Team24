package com.example.cs2340fr;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.app.Activity;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }



    public void startGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
       startActivity(intent);
    }

    public void exitGame(View view) {
        finishAffinity();

    }

}
