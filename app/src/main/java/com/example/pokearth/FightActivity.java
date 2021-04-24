package com.example.pokearth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.pokedex.PokemonPokedexObject;

import java.util.Random;

import static com.example.pokearth.MusicPlayer.SoundPlayer;
import static com.example.pokearth.MusicPlayer.StopSound;

public class FightActivity extends AppCompatActivity {


    final PokemonObject[] po = {null, null};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_page);

        StopSound();

        GenerateRunnable runnable = new GenerateRunnable();
        new Thread(runnable).start();
    }

    // click on the run button to go back to the Play Activity screen
    public void openPlayActivity(View v) {
        Intent intent = new Intent(FightActivity.this, PlayActivity.class);
        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    public void playerAttackButtonClick(View v) {
        if (po[0] != null && po[1] != null) {

            TextView battleText = (TextView) findViewById(R.id.battleTextView);
            LinearLayout fightButtonsLinearLayout = (LinearLayout) findViewById(R.id.fightButtonsLinearLayout);

            fightButtonsLinearLayout.setVisibility(View.INVISIBLE);
            battleText.setVisibility(View.VISIBLE);

            if (po[0].health.getCurrentHP() != 0 && po[1].health.getCurrentHP() != 0) {

                po[0].attack(po[1], battleText);
                TextView opponentPokemonHPTextView = (TextView) findViewById(R.id.opponentPokemonHPTextView);
                opponentPokemonHPTextView.setText(po[1].health.getCurrentHP() + " HP");

                Runnable opponentMove = new Runnable() {
                    @Override
                    public void run() {
                        po[1].attack(po[0], battleText);
                        TextView playerPokemonHPTextView = (TextView) findViewById(R.id.playerPokemonHPTextView);
                        playerPokemonHPTextView.setText(po[0].health.getCurrentHP() + " HP");
                    }
                };


                if (po[1].health.getCurrentHP() != 0) {
                    Handler h = new Handler();
                    h.postDelayed(opponentMove, 3000); // simulate enemy thinking
                }

            } else {
                battleText.setText("You can't do that! " + po[1].getName() + " is already fainted!");
            }
        }
    }

    public void displayAttackMenu(View v) {
        // if toggled off, toggle the ui that has attack1, attack2
        // if toggled on, turn off
        LinearLayout fightButtonsLinearLayout = (LinearLayout) findViewById(R.id.fightButtonsLinearLayout);
        TextView battleTextView = (TextView) findViewById(R.id.battleTextView);

        if (fightButtonsLinearLayout.getVisibility() == View.INVISIBLE) {
            fightButtonsLinearLayout.setVisibility(View.VISIBLE);
            battleTextView.setVisibility(View.INVISIBLE);
        } else {
            fightButtonsLinearLayout.setVisibility(View.INVISIBLE);
            battleTextView.setVisibility(View.VISIBLE);
        }
    }

    class GenerateRunnable implements Runnable {
        Random rand = new Random();

        @Override
        public void run() {
            Looper.prepare();
            for (int i = 0; i < 2; i++) {
                po[i] = new PokemonObject(rand.nextInt(151) + 1);
            }

            runOnUiThread(new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                    SoundPlayer(FightActivity.this, R.raw.battlemusic);

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

                    View pokeAttackButton1 = findViewById(R.id.move1Button);
                    View pokeAttackButton2 = findViewById(R.id.move2Button);

                    pokeAttackButton1.setBackgroundColor(Color.parseColor(po[0].getTypeColorString(0)));

                    if (po[0].isDualType()) {
                        pokeAttackButton2.setBackgroundColor(Color.parseColor(po[0].getTypeColorString(1)));

                    } else {
                        pokeAttackButton2.setVisibility(View.INVISIBLE);
                    }

                } // end run

            }); // end run

        }
    } // end GenerateRunnable

} // end fight activity
