package com.example.pokearth;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.Biome.Biome;
import com.example.pokearth.Biome.CaveBiome;
import com.example.pokearth.Biome.CityBiome;
import com.example.pokearth.Biome.ForestBiome;
import com.example.pokearth.Biome.GrasslandBiome;
import com.example.pokearth.Biome.GraveyardBiome;
import com.example.pokearth.Biome.IceBiome;
import com.example.pokearth.Biome.JungleBiome;
import com.example.pokearth.Biome.LakeBiome;
import com.example.pokearth.Biome.OceanBiome;
import com.example.pokearth.Biome.PowerPlantBiome;
import com.example.pokearth.Biome.SafariBiome;
import com.example.pokearth.Biome.SpaceBiome;
import com.example.pokearth.Biome.VolcanoBiome;
import com.example.pokearth.DB.Party;
import com.example.pokearth.DB.PartyDataSource;
import com.example.pokearth.DB.PokemonStorage;
import com.example.pokearth.DB.PokemonStorageDataSource;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Random;

import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;

import static com.example.pokearth.MusicPlayer.SoundPlayer;
import static com.example.pokearth.MusicPlayer.StopSound;


public class FightActivity extends AppCompatActivity {


    private PartyDataSource dataSource;
    private PokemonStorageDataSource storageDataSource;
    final PokemonObject[] battlingPokemon = {null, null};
    final PokemonObject[] teamPokemon = {null, null, null, null, null, null};
    private int pokemonToSwapIn;
    final PokemonObject[] po = {null, null};
    Biome biome = null;
    List<Party> partyPokemon = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_page);
        dataSource = new PartyDataSource(this);
        storageDataSource = new PokemonStorageDataSource(this);

        int chosenBiome = -1;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            chosenBiome = extras.getInt("Chosen Biome");
        }

        setFightActivity(chosenBiome);

        Log.d(FightActivity.class.getSimpleName(), "Starting FightActivity with a chosen biome of " + chosenBiome);

        // biome = new GrasslandBiome();
        GenerateNewEncounterRunnable runnable = new GenerateNewEncounterRunnable();
        new Thread(runnable).start();

        // paint all the buttons appropriately
        TeamButtonsRunnable runnable1 = new TeamButtonsRunnable();
        new Thread(runnable1).start();

    }

    public void setFightActivity(int chosenBiome) {
        Log.d(FightActivity.class.getSimpleName(), "Inside setFightActivity with chosenBiome " + chosenBiome);
        RelativeLayout relativeLayout = findViewById(R.id.fightScreen);
        switch (chosenBiome) {
            case 0:
            default:
                biome = new GrasslandBiome();
                break;
            case 1:
                biome = new ForestBiome();
                relativeLayout.setBackgroundResource(R.drawable.pokearth_forest);
                break;
            case 2:
                biome = new LakeBiome();
                relativeLayout.setBackgroundResource(R.drawable.pokearth_lake);
                break;
            case 3:
                biome = new CityBiome();
                relativeLayout.setBackgroundResource(R.drawable.pokearth_city);
                break;
            case 4:
                biome = new OceanBiome();
                relativeLayout.setBackgroundResource(R.drawable.pokearth_ocean);
                break;
            case 5:
                biome = new CaveBiome();
                relativeLayout.setBackgroundResource(R.drawable.pokearth_cave);
                break;
            case 6:
                biome = new GraveyardBiome();
                relativeLayout.setBackgroundResource(R.drawable.pokearth_graveyard);
                break;
            case 7:
                biome = new JungleBiome();
                relativeLayout.setBackgroundResource(R.drawable.pokearth_jungle);
                break;
            case 8:
                biome = new SafariBiome();
                relativeLayout.setBackgroundResource(R.drawable.pokearth_safarizone);
                break;
            case 9:
                biome = new VolcanoBiome();
                relativeLayout.setBackgroundResource(R.drawable.pokearth_volcano);
                break;
            case 10:
                biome = new PowerPlantBiome();
                relativeLayout.setBackgroundResource(R.drawable.pokearth_powerplant);
                break;
            case 11:
                biome = new IceBiome();
                relativeLayout.setBackgroundResource(R.drawable.pokearth_icecave);
                break;
            case 12:
                biome = new SpaceBiome();
                relativeLayout.setBackgroundResource(R.drawable.pokearth_space);
                break;


        }
    }

    // click on the run button to go back to the Play Activity screen
    public void openBiomeActivity(View v) {
//        Intent intent = new Intent(FightActivity.this, BiomeActivity.class);
//        if (player != null)
//            StopSound();
//        startActivity(intent);
        StopSound();
        SoundPlayer(this, R.raw.mainmusic);
        super.finish();

    }

    @SuppressLint("SetTextI18n")
    public void playerAttackButtonClick(View v) {
        if (battlingPokemon[0] != null && battlingPokemon[1] != null) {

            TextView battleText = (TextView) findViewById(R.id.battleTextView);
            LinearLayout fightButtonsLinearLayout = (LinearLayout) findViewById(R.id.fightButtonsLinearLayout);

            fightButtonsLinearLayout.setVisibility(View.INVISIBLE);
            battleText.setVisibility(View.VISIBLE);

            if (battlingPokemon[0].health.getCurrentHP() != 0 && battlingPokemon[1].health.getCurrentHP() != 0) {

                battlingPokemon[0].attack(battlingPokemon[1], battleText);
                TextView opponentPokemonHPTextView = (TextView) findViewById(R.id.opponentPokemonHPTextView);
                opponentPokemonHPTextView.setText(battlingPokemon[1].health.getCurrentHP() + " HP");

                Runnable opponentMove = new Runnable() {
                    @Override
                    public void run() {
                        battlingPokemon[1].attack(battlingPokemon[0], battleText);
                        TextView playerPokemonHPTextView = (TextView) findViewById(R.id.playerPokemonHPTextView);
                        playerPokemonHPTextView.setText(battlingPokemon[0].health.getCurrentHP() + " HP");
                    }
                };


                if (battlingPokemon[1].health.getCurrentHP() != 0) {
                    Handler h = new Handler();
                    h.postDelayed(opponentMove, 3000); // simulate enemy thinking
                }

            } else if (battlingPokemon[0].health.getCurrentHP() == 0) {
                battleText.setText("You can't do that! " + battlingPokemon[0].getName() + " is fainted!");
            } else {
                battleText.setText("You can't do that! " + battlingPokemon[1].getName() + " is already fainted!");
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

    public void displayTeamSelectMenu(View v) {
        // if toggled off, toggle the ui that has team selection buttons
        // if toggled on, turn off
        LinearLayout teamSelectButtonsOddsLinearLayout = (LinearLayout) findViewById(R.id.teamSelectButtonsOddsLinearLayout);
        LinearLayout teamSelectButtonsEvensLinearLayout = (LinearLayout) findViewById(R.id.teamSelectButtonsEvensLinearLayout);

//        // paint all the buttons appropriately
//        SetTeamColors runnable = new SetTeamColors();
//        new Thread(runnable).start();

        if (teamSelectButtonsOddsLinearLayout.getVisibility() == View.INVISIBLE) {
            teamSelectButtonsOddsLinearLayout.setVisibility(View.VISIBLE);

        } else {
            teamSelectButtonsOddsLinearLayout.setVisibility(View.INVISIBLE);
        }

        if (teamSelectButtonsEvensLinearLayout.getVisibility() == View.INVISIBLE) {
            teamSelectButtonsEvensLinearLayout.setVisibility(View.VISIBLE);

        } else {
            teamSelectButtonsEvensLinearLayout.setVisibility(View.INVISIBLE);
        }

    }

    class TeamButtonsRunnable implements Runnable {

        public void run() {
            // pokeAttackButton1.setBackgroundColor(Color.parseColor(po[0].getTypeColorString(0)));
            Looper.prepare();
            //PokemonObject[] team = new PokemonObject[6];
            partyPokemon = dataSource.getAllPokemon();
            for (int x = 0; x < 6; x++) {
                if (partyPokemon.get(x).getPokemonId() > 0)
                    teamPokemon[x] = new PokemonObject(partyPokemon.get(x).getPokemonId());
                else
                    teamPokemon[x] = null;
            }
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // Stuff that updates the UI
                    Button teamMemberButton;
                    for (int i = 0; i < teamPokemon.length; i++) {
                        switch (i) {
                            case 0:
                                teamMemberButton = findViewById(R.id.selectPokemon1Button);
                                break;
                            case 1:
                                teamMemberButton = findViewById(R.id.selectPokemon2Button);
                                break;
                            case 2:
                                teamMemberButton = findViewById(R.id.selectPokemon3Button);
                                break;
                            case 3:
                                teamMemberButton = findViewById(R.id.selectPokemon4Button);
                                break;
                            case 4:
                                teamMemberButton = findViewById(R.id.selectPokemon5Button);
                                break;
                            case 5:
                                teamMemberButton = findViewById(R.id.selectPokemon6Button);
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + i);
                        }
                        if (teamPokemon[i] != null) {
                            teamMemberButton.setBackgroundColor(Color.parseColor(teamPokemon[i].getTypeColorString(0)));
                            teamMemberButton.setText(teamPokemon[i].getName());
                        } else {
                            teamMemberButton.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            });
        }
    }

    @SuppressLint("SetTextI18n")
    public void catchAttempt(View v) {
        TextView battleText = (TextView) findViewById(R.id.battleTextView);
        ImageView opponentSprite = (ImageView) findViewById(R.id.opponentPokemonSprite);

        if (Math.random() > 0.5) {
            battleText.setText("Gotcha! " + this.battlingPokemon[1].getName() + " was caught!");
            opponentSprite.getLayoutParams().height = 250;
            opponentSprite.getLayoutParams().width = 250;
            opponentSprite.setImageResource(R.drawable.pokeballsprite);
            CapturePokemon runnable = new CapturePokemon();
            new Thread(runnable).start();
        } else {
            battleText.setText("Oh no! The Pokemon broke free!");
        }
    }

    private class GenerateNewEncounterRunnable implements Runnable {
        Random rand = new Random();

        @Override
        public void run() {
            Looper.prepare();
//            battlingPokemon[0] = new PokemonObject(dataSource.getFirstPokemon().getPokemonId());
            Party firstPartyPokemon = dataSource.getFirstPokemon();
            battlingPokemon[0] = new PokemonObject(firstPartyPokemon.getPokemonId(), firstPartyPokemon.getPokeObject(), firstPartyPokemon.getPokeSpecies(), firstPartyPokemon.getBitmapString());
            //battlingPokemon[0] = new PokemonObject(rand.nextInt(151) + 1);
//            battlingPokemon[1] = new PokemonObject(rand.nextInt(151) + 1);
            battlingPokemon[1] = new PokemonObject(biome.spawnWildPokemon());


            runOnUiThread(new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                    StopSound();
                    SoundPlayer(FightActivity.this, R.raw.battlemusic);

                    // grab reference to relevant data fields
                    TextView pokeName1 = (TextView) findViewById(R.id.playerPokemonNameTextView);
                    TextView pokeName2 = (TextView) findViewById(R.id.opponentPokemonNameTextView);

                    pokeName1.setText(battlingPokemon[0].getName());
                    pokeName2.setText(battlingPokemon[1].getName());

                    ImageView image1 = (ImageView) findViewById(R.id.playerPokemonSprite);
                    ImageView image2 = (ImageView) findViewById(R.id.opponentPokemonSprite);

                    // set the image accordingly
                    image1.setImageBitmap(battlingPokemon[0].getBitmap());
                    image2.setImageBitmap(battlingPokemon[1].getBitmap());

                    image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.VISIBLE);

                    TextView pokeHP1 = (TextView) findViewById(R.id.playerPokemonHPTextView);
                    TextView pokeHP2 = (TextView) findViewById(R.id.opponentPokemonHPTextView);

                    pokeHP1.setText(battlingPokemon[0].health.getCurrentHP() + " HP");
                    pokeHP2.setText(battlingPokemon[1].health.getCurrentHP() + " HP");

                    TextView battleText = (TextView) findViewById(R.id.battleTextView);
                    battleText.setText("A wild " + battlingPokemon[1].getName() + " appeared!");

                    View pokeAttackButton1 = findViewById(R.id.move1Button);
                    View pokeAttackButton2 = findViewById(R.id.move2Button);

                    pokeAttackButton1.setBackgroundColor(Color.parseColor(battlingPokemon[0].getTypeColorString(0)));

                    if (battlingPokemon[0].isDualType()) {
                        pokeAttackButton2.setBackgroundColor(Color.parseColor(battlingPokemon[0].getTypeColorString(1)));

                    } else {
                        pokeAttackButton2.setVisibility(View.INVISIBLE);
                    }

                } // end run

            }); // end run

        }
    }

    @SuppressLint("SetTextI18n")
    public void swapPokemonButton(View v) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Looper.prepare();
                //List<Party> partyPokemon = dataSource.getAllPokemon();
                switch (v.getId()) {
                    case R.id.selectPokemon1Button:
                        pokemonToSwapIn = 0;
                        break;
                    case R.id.selectPokemon2Button:
                        pokemonToSwapIn = 1;
                        break;
                    case R.id.selectPokemon3Button:
                        pokemonToSwapIn = 2;
                        break;
                    case R.id.selectPokemon4Button:
                        pokemonToSwapIn = 3;
                        break;
                    case R.id.selectPokemon5Button:
                        pokemonToSwapIn = 4;
                        break;
                    case R.id.selectPokemon6Button:
                        pokemonToSwapIn = 5;
                        break;
                }
                LinearLayout oddTeamButtons = (LinearLayout) findViewById(R.id.teamSelectButtonsOddsLinearLayout);
                LinearLayout evenTeamButtons = (LinearLayout) findViewById(R.id.teamSelectButtonsEvensLinearLayout);
                oddTeamButtons.setVisibility(View.INVISIBLE);
                evenTeamButtons.setVisibility(View.INVISIBLE);

                TextView battleText = (TextView) findViewById(R.id.battleTextView);
                battleText.setText("Sent out " + teamPokemon[pokemonToSwapIn].getName() + "! You can do it!");

                TextView pokeName1 = (TextView) findViewById(R.id.playerPokemonNameTextView);
                pokeName1.setText(teamPokemon[pokemonToSwapIn].getName());

                ImageView image1 = (ImageView) findViewById(R.id.playerPokemonSprite);
                // set the image accordingly
                byte[] byteBitmap = partyPokemon.get(pokemonToSwapIn).getBitmapString();
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteBitmap, 0, byteBitmap.length);
                image1.setImageBitmap(bitmap);


                TextView pokeHP1 = (TextView) findViewById(R.id.playerPokemonHPTextView);
                pokeHP1.setText(teamPokemon[pokemonToSwapIn].health.getCurrentHP() + " HP");
                View pokeAttackButton1 = findViewById(R.id.move1Button);
                View pokeAttackButton2 = findViewById(R.id.move2Button);

                pokeAttackButton1.setBackgroundColor(Color.parseColor(teamPokemon[pokemonToSwapIn].getTypeColorString(0)));

                if (teamPokemon[pokemonToSwapIn].isDualType()) {
                    pokeAttackButton2.setBackgroundColor(Color.parseColor(teamPokemon[pokemonToSwapIn].getTypeColorString(1)));

                } else {
                    pokeAttackButton2.setVisibility(View.INVISIBLE);
                }
            }
        });

        battlingPokemon[0] = teamPokemon[pokemonToSwapIn];

    }

    private class CapturePokemon implements Runnable {
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            TextView battleText = (TextView) findViewById(R.id.battleTextView);
            if (dataSource != null) {
//                        Looper.prepare();
                int partyIDToFill = dataSource.getFirstEmpty();

                byte[] caughtPokemonImg = new byte[0];
                String caughtPokemonSpecies = "";
                String caughtPokemon = "";
                Gson gson = new Gson();

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                battlingPokemon[1].getBitmap().compress(Bitmap.CompressFormat.PNG, 100, bos);
                caughtPokemonImg = bos.toByteArray();

                caughtPokemon = gson.toJson(battlingPokemon[1].myPoke, Pokemon.class);
                caughtPokemonSpecies = gson.toJson(battlingPokemon[1].myPokeSpecies, PokemonSpecies.class);

                int hasSpace = dataSource.checkEmpty();
                if (hasSpace == 1) {
                    Party capturedPokemon = new Party(partyIDToFill, battlingPokemon[1].getId(), caughtPokemon, caughtPokemonSpecies, caughtPokemonImg);
                    dataSource.createPokemon(capturedPokemon);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            battleText.setText("Gotcha! " + battlingPokemon[1].getName() + " was caught and sent to party!");
                        }
                    });
                } else {
                    PokemonStorage captdPokemon = new PokemonStorage(partyIDToFill, battlingPokemon[1].getId(), caughtPokemon, caughtPokemonSpecies, caughtPokemonImg);
                    storageDataSource.createPokemon(captdPokemon);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            battleText.setText("Gotcha! " + battlingPokemon[1].getName() + " was caught and sent to storage!");
                        }
                    });
                }
            }
        }
    }

} // end fight activity
