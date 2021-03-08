package com.example.pokearth;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FightActivity extends AppCompatActivity {

    TextView textView, textView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_page);


        textView = findViewById(R.id.textView2);
        ColorDrawable leftBorder1 = new ColorDrawable(Color.BLACK);
        ColorDrawable topBorder1 = new ColorDrawable(Color.BLACK);
        ColorDrawable rightBorder1 = new ColorDrawable(Color.WHITE);
        ColorDrawable bottomBorder1 = new ColorDrawable(Color.WHITE);
        ColorDrawable background1 = new ColorDrawable(Color.WHITE);

        Drawable[] layers1 = new Drawable[] {
                leftBorder1,
                topBorder1,
                rightBorder1,
                bottomBorder1,
                background1
        };

        LayerDrawable layerDrawable1 = new LayerDrawable(layers1);

        // Left Border
        layerDrawable1.setLayerInset(0, 0, 0, 15, 0);
        // Top Border
        layerDrawable1.setLayerInset(1, 15, 0, 15, 0);
        // Right Border
        layerDrawable1.setLayerInset(2, 15, 15, 0, 0);
        // Bottom Border
        layerDrawable1.setLayerInset(3, 15, 15, 15, 0);
        // Background
        layerDrawable1.setLayerInset(4, 15, 15, 15, 15);

        textView.setBackground(layerDrawable1);

        textView2 = findViewById(R.id.textView3);
        ColorDrawable leftBorder2 = new ColorDrawable(Color.WHITE);
        ColorDrawable topBorder2 = new ColorDrawable(Color.WHITE);
        ColorDrawable rightBorder2 = new ColorDrawable(Color.BLACK);
        ColorDrawable bottomBorder2 = new ColorDrawable(Color.BLACK);
        ColorDrawable background2 = new ColorDrawable(Color.WHITE);

        Drawable[] layers2 = new Drawable[] {
                leftBorder2,
                topBorder2,
                rightBorder2,
                bottomBorder2,
                background2
        };

        LayerDrawable layerDrawable2 = new LayerDrawable(layers2);

        // Left Border
        layerDrawable2.setLayerInset(0, 0, 0, 15, 0);
        // Top Border
        layerDrawable2.setLayerInset(1, 15, 0, 15, 0);
        // Right Border
        layerDrawable2.setLayerInset(2, 15, 15, 0, 0);
        // Bottom Border
        layerDrawable2.setLayerInset(3, 15, 15, 15, 0);
        // Background
        layerDrawable2.setLayerInset(4, 15, 15, 15, 15);

        textView2.setBackground(layerDrawable2);
    }
}
