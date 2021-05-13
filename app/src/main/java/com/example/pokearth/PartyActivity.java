package com.example.pokearth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokearth.DB.Party;
import com.example.pokearth.DB.PartyDataSource;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import java.util.List;
import java.util.Random;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;

public class PartyActivity extends AppCompatActivity {
    PokeApi pokeApi = new PokeApiClient();
    final PokemonObject[] po = {null, null, null, null, null, null};
    private PartyDataSource dataSource;
    private Party tempPoke = new Party(0, 0);

    private Button backButton;
    private Button statButton1, statButton2, statButton3, statButton4, statButton5, statButton6;
    private Button removeButton1, removeButton2, removeButton3, removeButton4, removeButton5, removeButton6;
    private Button assignButton1, assignButton2, assignButton3, assignButton4, assignButton5, assignButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.party_list);

        dataSource = new PartyDataSource(this);

        GenerateSaved runnable = new GenerateSaved();
        new Thread(runnable).start();

        removeButton1 = findViewById(R.id.remove1);
        removeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempPoke.setId(0);

                GenerateRemoveFromParty runnable = new GenerateRemoveFromParty();
                new Thread(runnable).start();
            }
        });

        removeButton2 = findViewById(R.id.remove2);
        removeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempPoke.setId(1);

                GenerateRemoveFromParty runnable = new GenerateRemoveFromParty();
                new Thread(runnable).start();
            }
        });

        removeButton3 = findViewById(R.id.remove3);
        removeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempPoke.setId(2);

                GenerateRemoveFromParty runnable = new GenerateRemoveFromParty();
                new Thread(runnable).start();
            }
        });

        removeButton4 = findViewById(R.id.remove4);
        removeButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempPoke.setId(3);

                GenerateRemoveFromParty runnable = new GenerateRemoveFromParty();
                new Thread(runnable).start();
            }
        });

        removeButton5 = findViewById(R.id.remove5);
        removeButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempPoke.setId(4);

                GenerateRemoveFromParty runnable = new GenerateRemoveFromParty();
                new Thread(runnable).start();
            }
        });

        removeButton6 = findViewById(R.id.remove6);
        removeButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempPoke.setId(5);

                GenerateRemoveFromParty runnable = new GenerateRemoveFromParty();
                new Thread(runnable).start();
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    class GenerateRemoveFromParty implements Runnable {
        @Override
        public void run() {
            if (dataSource != null) {
                Log.d("onRemoveFromPartyClick", "deleting saved pokemon");
                dataSource.createPokemon(tempPoke);
            } else
                Log.d("onRemoveFromPartyClick", " db is null ");

            List<Party> partyPokemon = dataSource.getAllPokemon();
            Looper.prepare();
            for (int x = 0; x < 6; x++) {
                if (partyPokemon.get(x).getPokemonId() > 0)
                    po[x] = new PokemonObject(partyPokemon.get(x).getPokemonId(), partyPokemon.get(x).getPokeObject(), partyPokemon.get(x).getPokeSpecies(), partyPokemon.get(x).getBitmapString());
                else
                    po[x] = null;
            }

            runOnUiThread(new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                    TextView pokeName1 = (TextView) findViewById(R.id.pokeName1);
                    ImageView pokemonImageView1 = (ImageView) findViewById(R.id.pokemonImageView1);


                    if (po[0] != null) {
                        pokeName1.setText(po[0].getName());
                        pokemonImageView1.setImageBitmap(po[0].getBitmap());
                    } else {
                        pokeName1.setText("Empty");
                        pokemonImageView1.setImageBitmap(null);
                    }

                    TextView pokeName2 = (TextView) findViewById(R.id.pokeName2);
                    ImageView pokemonImageView2 = (ImageView) findViewById(R.id.pokemonImageView2);
                    if (po[1] != null) {
                        pokeName2.setText(po[1].getName());
                        pokemonImageView2.setImageBitmap(po[1].getBitmap());
                    } else {
                        pokeName2.setText("Empty");
                        pokemonImageView2.setImageBitmap(null);
                    }

                    TextView pokeName3 = (TextView) findViewById(R.id.pokeName3);
                    ImageView pokemonImageView3 = (ImageView) findViewById(R.id.pokemonImageView3);
                    if (po[2] != null) {
                        pokeName3.setText(po[2].getName());
                        pokemonImageView3.setImageBitmap(po[2].getBitmap());
                    } else {
                        pokeName3.setText("Empty");
                        pokemonImageView3.setImageBitmap(null);
                    }

                    TextView pokeName4 = (TextView) findViewById(R.id.pokeName4);
                    ImageView pokemonImageView4 = (ImageView) findViewById(R.id.pokemonImageView4);
                    if (po[3] != null) {
                        pokeName4.setText(po[3].getName());
                        pokemonImageView4.setImageBitmap(po[3].getBitmap());
                    } else {
                        pokeName4.setText("Empty");
                        pokemonImageView4.setImageBitmap(null);
                    }

                    TextView pokeName5 = (TextView) findViewById(R.id.pokeName5);
                    ImageView pokemonImageView5 = (ImageView) findViewById(R.id.pokemonImageView5);
                    if (po[4] != null) {
                        pokeName5.setText(po[4].getName());
                        pokemonImageView5.setImageBitmap(po[4].getBitmap());
                    } else {
                        pokeName5.setText("Empty");
                        pokemonImageView5.setImageBitmap(null);
                    }

                    TextView pokeName6 = (TextView) findViewById(R.id.pokeName6);
                    ImageView pokemonImageView6 = (ImageView) findViewById(R.id.pokemonImageView6);
                    if (po[5] != null) {
                        pokeName6.setText(po[5].getName());
                        pokemonImageView6.setImageBitmap(po[5].getBitmap());
                    } else {
                        pokeName6.setText("Empty");
                        pokemonImageView6.setImageBitmap(null);
                    }
                }
            });
        }
    }

    class GenerateSaved implements Runnable {
        @Override
        public void run() {
            List<Party> partyPokemon = dataSource.getAllPokemon();
            Looper.prepare();
            int nullCounter = 0;
            for (int x = 0; x < 6; x++) {
                if (partyPokemon.size() > 0 && partyPokemon.get(x).getPokemonId() > 0)
                    po[x] = new PokemonObject(partyPokemon.get(x).getPokemonId(), partyPokemon.get(x).getPokeObject(), partyPokemon.get(x).getPokeSpecies(), partyPokemon.get(x).getBitmapString());
                else {
                    po[x] = null;
                    nullCounter++;
                }
            }
            if (nullCounter != 6) {
                runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        TextView pokeName1 = (TextView) findViewById(R.id.pokeName1);
                        ImageView pokemonImageView1 = (ImageView) findViewById(R.id.pokemonImageView1);

                        if (po[0] != null) {
                            pokeName1.setText(po[0].getName());
                            pokemonImageView1.setImageBitmap(po[0].getBitmap());
                        } else {
                            pokeName1.setText("Empty");
                            pokemonImageView1.setImageBitmap(null);
                        }

                        TextView pokeName2 = (TextView) findViewById(R.id.pokeName2);
                        ImageView pokemonImageView2 = (ImageView) findViewById(R.id.pokemonImageView2);
                        if (po[1] != null) {
                            pokeName2.setText(po[1].getName());
                            pokemonImageView2.setImageBitmap(po[1].getBitmap());
                        } else {
                            pokeName2.setText("Empty");
                            pokemonImageView2.setImageBitmap(null);
                        }

                        TextView pokeName3 = (TextView) findViewById(R.id.pokeName3);
                        ImageView pokemonImageView3 = (ImageView) findViewById(R.id.pokemonImageView3);
                        if (po[2] != null) {
                            pokeName3.setText(po[2].getName());
                            pokemonImageView3.setImageBitmap(po[2].getBitmap());
                        } else {
                            pokeName3.setText("Empty");
                            pokemonImageView3.setImageBitmap(null);
                        }

                        TextView pokeName4 = (TextView) findViewById(R.id.pokeName4);
                        ImageView pokemonImageView4 = (ImageView) findViewById(R.id.pokemonImageView4);
                        if (po[3] != null) {
                            pokeName4.setText(po[3].getName());
                            pokemonImageView4.setImageBitmap(po[3].getBitmap());
                        } else {
                            pokeName4.setText("Empty");
                            pokemonImageView4.setImageBitmap(null);
                        }

                        TextView pokeName5 = (TextView) findViewById(R.id.pokeName5);
                        ImageView pokemonImageView5 = (ImageView) findViewById(R.id.pokemonImageView5);
                        if (po[4] != null) {
                            pokeName5.setText(po[4].getName());
                            pokemonImageView5.setImageBitmap(po[4].getBitmap());
                        } else {
                            pokeName5.setText("Empty");
                            pokemonImageView5.setImageBitmap(null);
                        }

                        TextView pokeName6 = (TextView) findViewById(R.id.pokeName6);
                        ImageView pokemonImageView6 = (ImageView) findViewById(R.id.pokemonImageView6);
                        if (po[5] != null) {
                            pokeName6.setText(po[5].getName());
                            pokemonImageView6.setImageBitmap(po[5].getBitmap());
                        } else {
                            pokeName6.setText("Empty");
                            pokemonImageView6.setImageBitmap(null);
                        }
                    }
                });
            } else {
                GenerateParty runnable = new GenerateParty();
                new Thread(runnable).start();
            }
        }
    }

    class GenerateParty implements Runnable {
        Random rand = new Random();

        @Override
        public void run() {
            if (dataSource != null) {
                Looper.prepare();
                for (int x = 1; x <= 7; x+=3) {
                    po[x/3] = new PokemonObject(x);
                }
                for (int x=3; x<6; x++)
                {
                    po[x] = null;
                }

                for (int i = 0; i < 3; i++) {
                    byte[] img = new byte[0];
                    String myPS = "";
                    String myP = "";
                    Gson gson = new Gson();

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    po[i].getBitmap().compress(Bitmap.CompressFormat.PNG, 100, bos);
                    img = bos.toByteArray();

                    myP = gson.toJson(po[i].myPoke, Pokemon.class);
                    myPS = gson.toJson(po[i].myPokeSpecies, PokemonSpecies.class);
                    Party tempPokemon = new Party(i, po[i].getId(), myP, myPS, img);
                    dataSource.createPokemon(tempPokemon);
                }
                for(int i=3; i<6; i++)
                {
                    Party tempPokemon = new Party(i, 0);
                    dataSource.createPokemon(tempPokemon);
                }
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView pokeName1 = (TextView) findViewById(R.id.pokeName1);
                    pokeName1.setText(po[0].getName());
                    ImageView pokemonImageView1 = (ImageView) findViewById(R.id.pokemonImageView1);
                    pokemonImageView1.setImageBitmap(po[0].getBitmap());


                    TextView pokeName2 = (TextView) findViewById(R.id.pokeName2);
                    pokeName2.setText(po[1].getName());
                    ImageView pokemonImageView2 = (ImageView) findViewById(R.id.pokemonImageView2);
                    pokemonImageView2.setImageBitmap(po[1].getBitmap());


                    TextView pokeName3 = (TextView) findViewById(R.id.pokeName3);
                    pokeName3.setText(po[2].getName());
                    ImageView pokemonImageView3 = (ImageView) findViewById(R.id.pokemonImageView3);
                    pokemonImageView3.setImageBitmap(po[2].getBitmap());

                    TextView pokeName4 = (TextView) findViewById(R.id.pokeName4);
                    pokeName4.setText("Empty");
                    ImageView pokemonImageView4 = (ImageView) findViewById(R.id.pokemonImageView4);
                    pokemonImageView4.setImageBitmap(null);


                    TextView pokeName5 = (TextView) findViewById(R.id.pokeName5);
                    pokeName5.setText("Empty");
                    ImageView pokemonImageView5 = (ImageView) findViewById(R.id.pokemonImageView5);
                    pokemonImageView5.setImageBitmap(null);


                    TextView pokeName6 = (TextView) findViewById(R.id.pokeName6);
                    pokeName6.setText("Empty");
                    ImageView pokemonImageView6 = (ImageView) findViewById(R.id.pokemonImageView6);
                    pokemonImageView6.setImageBitmap(null);

                }
            });
        }
    }
}
