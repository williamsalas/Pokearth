package com.example.pokearth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class FightActivity extends AppCompatActivity {


    final PokeObject[] po = {null, null};
    TextView textView, textView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_page);


        textView = findViewById(R.id.playerPokemonNameTextView);
        ColorDrawable leftBorder1 = new ColorDrawable(Color.BLACK);
        ColorDrawable topBorder1 = new ColorDrawable(Color.BLACK);
        ColorDrawable rightBorder1 = new ColorDrawable(Color.WHITE);
        ColorDrawable bottomBorder1 = new ColorDrawable(Color.WHITE);
        ColorDrawable background1 = new ColorDrawable(Color.WHITE);

        Drawable[] layers1 = new Drawable[]{
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

        textView2 = findViewById(R.id.opponentPokemonNameTextView);
        ColorDrawable leftBorder2 = new ColorDrawable(Color.WHITE);
        ColorDrawable topBorder2 = new ColorDrawable(Color.WHITE);
        ColorDrawable rightBorder2 = new ColorDrawable(Color.BLACK);
        ColorDrawable bottomBorder2 = new ColorDrawable(Color.BLACK);
        ColorDrawable background2 = new ColorDrawable(Color.WHITE);

        Drawable[] layers2 = new Drawable[]{
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

        GenerateRunnable runnable = new GenerateRunnable();
        new Thread(runnable).start();

    }

    // click on the run button to go back to the Play Activity screen
    public void openPlayActivity(View v) {
        Intent intent = new Intent(FightActivity.this, PlayActivity.class);
        startActivity(intent);
    }

    class GenerateRunnable implements Runnable {
        Random rand = new Random();

        @Override
        public void run() {
            Looper.prepare();
            for (int i = 0; i < 2; i++) {
                po[i] = new PokeObject(rand.nextInt(151) + 1);
            }

            runOnUiThread(new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                    // grab reference to relevant data fields
                    TextView pokeName1 = (TextView) findViewById(R.id.playerPokemonNameTextView);
                    TextView pokeName2 = (TextView) findViewById(R.id.opponentPokemonNameTextView);

                    pokeName1.setText(po[0].getName());
                    pokeName2.setText(po[1].getName());

                    ImageView image1 = (ImageView) findViewById(R.id.playerPokemonSprite);
                    ImageView image2 = (ImageView) findViewById(R.id.opponentPokemonSprite);

                    // set the image accordingly
                    image1.setImageBitmap(po[0].getBitmap());
                    image2.setImageBitmap(po[1].getBitmap());

                    image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.VISIBLE);

                    TextView pokeHP1 = (TextView) findViewById(R.id.playerPokemonHPTextView);
                    TextView pokeHP2 = (TextView) findViewById(R.id.opponentPokemonHPTextView);

                    pokeHP1.setText(po[0].health.getCurrentHP() + " HP");
                    pokeHP2.setText(po[1].health.getCurrentHP() + " HP");

                    TextView battleText = (TextView) findViewById(R.id.battleTextView);
                    battleText.setText("A wild " + po[1].getName() + " appeared!");

                    View pokeAttackButton1 = findViewById(R.id.fightButton);
                    //View pokeAttackButton2 = findViewById(R.id.button2);

                    pokeAttackButton1.setBackgroundColor(Color.parseColor(po[0].getTypeColorString(0)));
                    // pokeAttackButton2.setBackgroundColor(Color.parseColor(po[1].getTypeColorString(0)));


                } // end run


            }); // end run

        }
    }

} // end fight activity
