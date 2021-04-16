package com.example.pokearth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.DB.Party;
import com.example.pokearth.DB.PartyDataSource;

import java.util.List;
import java.util.Random;

public class FightActivity extends AppCompatActivity {

    private PartyDataSource dataSource;
    final PokemonObject[] po = {null, null};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_page);
        dataSource = new PartyDataSource(this);

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

            } else if (po[0].health.getCurrentHP() == 0) {
                battleText.setText("You can't do that! " + po[0].getName() + " is fainted!");
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
            //List<Party> partyPokemon = dataSource.getAllPokemon();
            Looper.prepare();
            po[0] = new PokemonObject(dataSource.getFirstPokemon().getPokemonId());
            // po[0] = new PokemonObject(rand.nextInt(151) + 1);
            po[1] = new PokemonObject(rand.nextInt(151) + 1);

//            for (int i = 0; i < 2; i++) {
//                //po[i] = new PokemonObject(rand.nextInt(151) + 1);
//                po[i] = new PokemonObject(partyPokemon.get(i).getPokemonId());
            //}

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

    @SuppressLint("SetTextI18n")
    public void catchAttempt(View v) {
        TextView battleText = (TextView) findViewById(R.id.battleTextView);
        ImageView opponentSprite = (ImageView) findViewById(R.id.opponentPokemonSprite);

        if (Math.random() > 0.5) {
            battleText.setText("Gotcha! " + this.po[1].getName() + " was caught!");
            opponentSprite.getLayoutParams().height = 250;
            opponentSprite.getLayoutParams().width = 250;
            opponentSprite.setImageResource(R.drawable.pokeballsprite);
            CapturePokemon runnable = new CapturePokemon();
            new Thread(runnable).start();
        } else {
            battleText.setText("Oh no! The Pokemon broke free!");
        }
    }

    private class CapturePokemon implements Runnable {
        @Override
        public void run() {
            if (dataSource != null) {
                Looper.prepare();
                int partyIDToFill = dataSource.getFirstEmptySlot();
                if (partyIDToFill != -1) {
                    Party caughtPokemon = new Party(partyIDToFill, po[1].getId());
                    dataSource.createPokemon(caughtPokemon);
                }else {
                    Log.d(FightActivity.class.getSimpleName(),"failed to find an index to fill into party.");
                }
            }
        }
    }

} // end fight activity
