package com.example.team24dungeoncrawler.model;

import android.content.Context;
import android.widget.GridLayout;

public class EnemyView extends androidx.appcompat.widget.AppCompatImageView {
    public EnemyView(Context context) {
        super(context);
    }

    public void updatePosition(int row, int col) {
        // Set the position of the enemy view on the grid
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(row);
        params.columnSpec = GridLayout.spec(col);
        setLayoutParams(params);
    }
}