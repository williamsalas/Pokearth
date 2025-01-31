package com.example.pokearth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    Biome biome = null;
    List<Party> partyPokemon = null;
    final private String redHPHex = "#e84545";
    final private String greenHPHex = "#29bb89";
    private boolean fightOver = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_page);
        dataSource = new PartyDataSource(this);
        storageDataSource = new PokemonStorageDataSource(this);

        // assigns the chosen biome based on what was chosen in BiomeActivity
        assignChosenBiome();

        GenerateNewEncounterRunnable newEncounterRunnable = new GenerateNewEncounterRunnable();
        new Thread(newEncounterRunnable).start();

        // paint all the buttons appropriately
        TeamButtonsRunnable teamButtonsRunnable = new TeamButtonsRunnable();
        new Thread(teamButtonsRunnable).start();
    }

    // assigns the chosen biome based on what was chosen in BiomeActivity
    public void assignChosenBiome() {
        int chosenBiome = -1;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            chosenBiome = extras.getInt("Chosen Biome");
        }
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
        Log.d(FightActivity.class.getSimpleName(), "Starting FightActivity with a chosen biome of " + chosenBiome);
    }

    // click on the run button to go back to the Play Activity screen
    public void openBiomeActivity(View v) {
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

            playerAttack();
        }
    }

    private void playerAttack() {
        TextView battleText = (TextView) findViewById(R.id.battleTextView);
        if (bothPokemonAlive() && !this.fightOver) {
            // attack the opponent
            battlingPokemon[0].attack(battlingPokemon[1], battleText);

            setOpponentHP();

            if (battlingPokemon[1].health.isFainted()) {
                this.fightOver = true;
            } else // opponent counter attacks
            {
                opponentAttack();
            }
        } else if (battlingPokemon[0].health.isFainted()) {
            battleText.setText("You can't do that! " + battlingPokemon[0].getName() + " is fainted!");
        } else {
            battleText.setText("You can't do that! " + battlingPokemon[1].getName() + " is already fainted!");
        }
    }

    private void opponentAttack() {
        TextView battleText = (TextView) findViewById(R.id.battleTextView);
        TextView playerPokemonHP = (TextView) findViewById(R.id.playerPokemonHPTextView);

        Runnable opponentMove = new Runnable() {
            @Override
            public void run() {
                battlingPokemon[1].attack(battlingPokemon[0], battleText);
                TextView playerPokemonHPTextView = (TextView) findViewById(R.id.playerPokemonHPTextView);
                playerPokemonHPTextView.setText(battlingPokemon[0].health.getCurrentHP() + " HP");

                if (battlingPokemon[0].health.getCurrentHP() <= 20) {
                    Log.d("FightActivity", "Opponent attack set player health to below 20");
                    playerPokemonHP.setBackgroundColor(Color.parseColor(redHPHex));
                }
            }
        };


        if (bothPokemonAlive()) {
            Handler h = new Handler();
            h.postDelayed(opponentMove, 3000); // simulate enemy thinking
        }
    }

    // on 'Fight' button click
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

    // on 'PKMN' button click
    public void displayTeamSelectMenu(View v) {
        // if toggled off, toggle the ui that has team selection buttons
        // if toggled on, turn off
        LinearLayout teamSelectButtonsOddsLinearLayout = (LinearLayout) findViewById(R.id.teamSelectButtonsOddsLinearLayout);
        LinearLayout teamSelectButtonsEvensLinearLayout = (LinearLayout) findViewById(R.id.teamSelectButtonsEvensLinearLayout);

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

    // on 'Items' button click, to be changed to on pokeball item use
    @SuppressLint("SetTextI18n")
    public void catchAttempt(View v) {
        if (!this.fightOver) {
            TextView battleText = (TextView) findViewById(R.id.battleTextView);
            ImageView opponentSprite = (ImageView) findViewById(R.id.opponentPokemonSprite);

            // if success
            if (Math.random() > 0.5) {
                battleText.setText("Gotcha! " + this.battlingPokemon[1].getName() + " was caught!");
                opponentSprite.getLayoutParams().height = 250;
                opponentSprite.getLayoutParams().width = 250;
                opponentSprite.setImageResource(R.drawable.pokeballsprite);
                CapturePokemon runnable = new CapturePokemon();
                new Thread(runnable).start();
                this.fightOver = true;
            } else {
                battleText.setText("Oh no! The Pokemon broke free!");
                opponentAttack();
            }
        } // end if
    }

    // sets the Pokemon move names appropriately
    public void setAttackButton(Button attackButton, String typeString) {
        switch (typeString) {
            case "normal":
                attackButton.setText("Take Down");
                break;
            case "fighting":
                attackButton.setText("Brick Break");
                break;
            case "flying":
                attackButton.setText("Brave Bird");
                break;
            case "poison":
                attackButton.setText("Sludge Bomb");
                break;
            case "ground":
                attackButton.setText("Dig");
                break;
            case "rock":
                attackButton.setText("Rock Slide");
                break;
            case "bug":
                attackButton.setText("Bug Buzz");
                break;
            case "ghost":
                attackButton.setText("Shadow Ball");
                break;
            case "steel":
                attackButton.setText("Heavy Slam");
                break;
            case "fire":
                attackButton.setText("Fire Blast");
                break;
            case "water":
                attackButton.setText("Surf");
                break;
            case "grass":
                attackButton.setText("Razor Leaf");
                break;
            case "electric":
                attackButton.setText("Thunder");
                break;
            case "psychic":
                attackButton.setText("Psychic");
                break;
            case "ice":
                attackButton.setText("Blizzard");
                break;
            case "dragon":
                attackButton.setText("Dragon Rage");
                break;
            case "dark":
                attackButton.setText("Crunch");
                break;
            case "fairy":
                attackButton.setText("Dazzling Gleam");
                break;
            default:
                attackButton.setText("Tackle");
                break;
        }
    }

    // swaps the active fighting pokemon with a party pokemon
    @SuppressLint("SetTextI18n")
    public void swapPokemonButton(View v) {
        if (!this.fightOver) {
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

                    int playerPokeColor1 = Color.parseColor(teamPokemon[pokemonToSwapIn].getTypeColorString(0));
                    pokeName1.setBackgroundColor(playerPokeColor1);

                    ImageView image1 = (ImageView) findViewById(R.id.playerPokemonSprite);
                    // set the image accordingly
                    byte[] byteBitmap = partyPokemon.get(pokemonToSwapIn).getBitmapString();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(byteBitmap, 0, byteBitmap.length);
                    image1.setImageBitmap(bitmap);


                    TextView pokeHP1 = (TextView) findViewById(R.id.playerPokemonHPTextView);
                    pokeHP1.setText(teamPokemon[pokemonToSwapIn].health.getCurrentHP() + " HP");
                    if (teamPokemon[pokemonToSwapIn].health.getCurrentHP() > 20) {
                        pokeHP1.setBackgroundColor(Color.parseColor(greenHPHex));
                    } else {
                        pokeHP1.setBackgroundColor(Color.parseColor(redHPHex));
                    }
                    Button pokeAttackButton1 = findViewById(R.id.move1Button);
                    Button pokeAttackButton2 = findViewById(R.id.move2Button);

                    pokeAttackButton1.setBackgroundColor(Color.parseColor(teamPokemon[pokemonToSwapIn].getTypeColorString(0)));
                    setAttackButton(pokeAttackButton1, teamPokemon[pokemonToSwapIn].getTypeString(0));

                    if (teamPokemon[pokemonToSwapIn].isDualType()) {
                        pokeAttackButton2.setBackgroundColor(Color.parseColor(teamPokemon[pokemonToSwapIn].getTypeColorString(1)));
                        setAttackButton(pokeAttackButton2, teamPokemon[pokemonToSwapIn].getTypeString(1));
                        pokeAttackButton2.setVisibility(View.VISIBLE);
                    } else {
                        pokeAttackButton2.setVisibility(View.INVISIBLE);
                    }
                }

            });
        }
    }
    public void onPotionBagClick(View view){
        LinearLayout itemSelectMenuLayout = (LinearLayout) findViewById(R.id.ItemBagButtons);
        itemSelectMenuLayout.setVisibility(View.INVISIBLE);

        Intent i = new Intent(FightActivity.this, PotionBagActivity.class);
        startActivityForResult(i,1);
    }

    public void onPokeBallBagClick(View view){
        LinearLayout itemSelectMenuLayout = (LinearLayout) findViewById(R.id.ItemBagButtons);
        itemSelectMenuLayout.setVisibility(View.INVISIBLE);

        Intent i = new Intent(FightActivity.this, PokeBallBagActivity.class);
        startActivityForResult(i,2);

    }

    public void displayItemMenu(View view){
        LinearLayout itemSelectMenuLayout = (LinearLayout) findViewById(R.id.ItemBagButtons);

        if(itemSelectMenuLayout.getVisibility() == View.INVISIBLE){
            itemSelectMenuLayout.setVisibility(View.VISIBLE);
        }else {
            itemSelectMenuLayout.setVisibility(View.INVISIBLE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        int position = data.getIntExtra("position",0);
        String itemUsed = "";

        //If PotionBagClick

        if(requestCode == 1){
            if(resultCode == RESULT_OK){

                if(position == 0){
                    itemUsed = "potion";
                }else if(position == 1){
                    itemUsed = "superpotion";
                }else if(position == 2){
                    itemUsed = "hyperpotion";
                }
                heal(itemUsed);
            } else if(resultCode == RESULT_CANCELED){

            }

        } else if(requestCode == 2){
            if(resultCode == RESULT_OK){
                if(position == 0){
                    itemUsed = "pokeball";
                }else if(position == 1){
                    itemUsed = "greatball";
                }else if(position == 2){
                    itemUsed = "ultraball";
                }
                catchAttempt(itemUsed);

            } else if(resultCode == RESULT_CANCELED){

            }
        }
    }

    public void heal(String potionType){
        TextView pokeHP1 = (TextView) findViewById(R.id.playerPokemonHPTextView);
        int healingAmount = 0;
        if(potionType == "potion"){
            healingAmount = 20;
        }else if(potionType == "superpotion"){
            healingAmount = 60;
        }else if(potionType == "hyperpotion"){
            healingAmount = 200;
        }

        battlingPokemon[0].heal(battlingPokemon[0],healingAmount);
        pokeHP1.setText(Integer.toString(battlingPokemon[0].health.getCurrentHP()) + " HP");
        Toast.makeText(this,"You healed your pokemon!", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    public void catchAttempt(String ballUsed) {
        TextView battleText = (TextView) findViewById(R.id.battleTextView);
        ImageView opponentSprite = (ImageView) findViewById(R.id.opponentPokemonSprite);

        if (Math.random() > 0.5) {
            battleText.setText("Gotcha! " + this.battlingPokemon[1].getName() + " was caught!");
            opponentSprite.getLayoutParams().height = 250;
            opponentSprite.getLayoutParams().width = 250;

            if(ballUsed == "pokeball"){
                opponentSprite.setImageResource(R.drawable.pokeballsprite);
            }else if(ballUsed == "greatball"){
                opponentSprite.setImageResource(R.drawable.greatballsprite);
            }else if(ballUsed == "ultraball"){
                opponentSprite.setImageResource(R.drawable.ultraballsprite);
            }

            CapturePokemon runnable = new CapturePokemon();
            new Thread(runnable).start();
        } else {
            battleText.setText("Oh no! The Pokemon broke free!");
          opponentAttack();
        }

    }

    // generates a new wild pokemon encounter
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

                    TextView pokeHP1 = (TextView) findViewById(R.id.playerPokemonHPTextView);
                    TextView pokeHP2 = (TextView) findViewById(R.id.opponentPokemonHPTextView);

                    pokeHP1.setText(battlingPokemon[0].health.getCurrentHP() + " HP");
                    pokeHP2.setText(battlingPokemon[1].health.getCurrentHP() + " HP");

                    TextView battleText = (TextView) findViewById(R.id.battleTextView);
                    battleText.setText("A wild " + battlingPokemon[1].getName() + " appeared!");

                    Button pokeAttackButton1 = findViewById(R.id.move1Button);
                    Button pokeAttackButton2 = findViewById(R.id.move2Button);

                    int playerPokeColor1 = Color.parseColor(battlingPokemon[0].getTypeColorString(0));
                    int opponentPokeColor1 = Color.parseColor(battlingPokemon[1].getTypeColorString(0));

                    pokeName1.setBackgroundColor(playerPokeColor1);
                    pokeName2.setBackgroundColor(opponentPokeColor1);

                    pokeAttackButton1.setBackgroundColor(playerPokeColor1);
                    setAttackButton(pokeAttackButton1, battlingPokemon[0].getTypeString(0));

                    Button fightButton = findViewById(R.id.fightButton);
                    Button itemsButton = findViewById(R.id.itemsButton);
                    Button pkmnButton = findViewById(R.id.pokemonTeamButton);
                    Button runButton = findViewById(R.id.runButton);

                    fightButton.setVisibility(View.VISIBLE);
                    itemsButton.setVisibility(View.VISIBLE);
                    pkmnButton.setVisibility(View.VISIBLE);
                    runButton.setVisibility(View.VISIBLE);

                    pokeName1.setVisibility(View.VISIBLE);
                    pokeName2.setVisibility(View.VISIBLE);
                    image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.VISIBLE);
                    pokeHP1.setVisibility(View.VISIBLE);
                    pokeHP2.setVisibility(View.VISIBLE);
                    battleText.setVisibility(View.VISIBLE);

                    if (battlingPokemon[0].isDualType()) {
                        int playerColor2 = Color.parseColor(battlingPokemon[0].getTypeColorString(1));
                        pokeAttackButton2.setBackgroundColor(playerColor2);
                        setAttackButton(pokeAttackButton2, battlingPokemon[0].getTypeString(1));
                    } else {
                        pokeAttackButton2.setVisibility(View.INVISIBLE);
                    }

                } // end run

            }); // end run

        }
    }

    // creates the appropriate party pokemon buttons
    private class TeamButtonsRunnable implements Runnable {

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

    // capture pokemon event
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

    public boolean bothPokemonAlive() {
        return battlingPokemon[0].health.getCurrentHP() != 0 && battlingPokemon[1].health.getCurrentHP() != 0;
    }

    public void setOpponentHP() {
        int opponentCurrentHP = battlingPokemon[1].health.getCurrentHP();
        TextView opponentPokemonHPTextView = (TextView) findViewById(R.id.opponentPokemonHPTextView);
        opponentPokemonHPTextView.setText(opponentCurrentHP + " HP");

        if (opponentCurrentHP <= 20) {
            Log.d("FightActivity", "Player attack set opponent health to below 20");
            opponentPokemonHPTextView.setBackgroundColor(Color.parseColor(redHPHex));
        }
    }

} // end fight activity
