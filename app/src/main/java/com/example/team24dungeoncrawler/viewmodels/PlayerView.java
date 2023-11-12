package com.example.team24dungeoncrawler.viewmodels;

import android.content.Context;
import android.widget.GridLayout;

import com.example.team24dungeoncrawler.R;

public class PlayerView extends androidx.appcompat.widget.AppCompatImageView {
    public PlayerView(Context context) {
        super(context);
        //default player sprite
        setImageResource(R.drawable.sprite1);
    }

    public void updatePosition(int row, int col) {
        // Set the position of the player view on the grid
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(row);
        params.columnSpec = GridLayout.spec(col);
        setLayoutParams(params);
    }
}