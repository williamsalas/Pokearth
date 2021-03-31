package com.example.pokearth.pokedex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.PokemonPokedexObject;
import com.example.pokearth.R;
import com.example.pokearth.databinding.PokemonListBinding;

import java.util.ArrayList;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

public class PokedexActivity extends AppCompatActivity {

    // binds the data from pokemon_list.xml to implement list view
    PokemonListBinding ui;

    //
    PokemonAdapter adapterList;
    ArrayList<PokemonPokedexObject> pokemonList = new ArrayList<>();
    ListView listViewPokedex;
    PokeApi pokeApi = new PokeApiClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = PokemonListBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        listViewPokedex = findViewById(R.id.pokemonList);


        PokedexThreads thread1 = new PokedexThreads(0, 5);
        PokedexThreads thread2 = new PokedexThreads(5, 10);
        PokedexThreads thread3 = new PokedexThreads(10, 15);
        PokedexThreads thread4 = new PokedexThreads(15, 20);
        PokedexThreads thread5 = new PokedexThreads(20, 25);



        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();



        // wait for thread to finish before continuing
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
           
        } catch (InterruptedException e) {
            // oops
        }

        adapterList = new PokemonAdapter(PokedexActivity.this, R.layout.pokemon_object_layout, pokemonList);

        listViewPokedex.setAdapter(adapterList);
        listViewPokedex.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PokedexActivity.this, PokemonActivity.class);
                PokemonPokedexObject existingPokemon = pokemonList.get(position);
                intent.putExtra("position", position);
                intent.putExtra("name", existingPokemon.getPokemonName());
                intent.putExtra("image", existingPokemon.getBitmap());
                startActivityForResult(intent, 1);
            }
        });

        /*
        GenerateRunnable runnable = new GenerateRunnable(0, 40);
        new Thread(runnable).start();
        */

    }

    class PokedexThreads extends Thread {

        int start;
        int end;

        PokedexThreads(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for(int i = start; i < end; i++)
            {
                pokemonList.add(new PokemonPokedexObject(i+1));
            }
        }
    }

    class GenerateRunnable implements Runnable {

        int start;
        int end;

        GenerateRunnable(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            Looper.prepare();
            for(int i = start; i < end; i++)
            {
                pokemonList.add(new PokemonPokedexObject(i+1));
            }

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // grab reference to relevant data fields
                    adapterList = new PokemonAdapter(PokedexActivity.this, R.layout.pokemon_object_layout, pokemonList);

                    listViewPokedex.setAdapter(adapterList);
                    listViewPokedex.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(PokedexActivity.this, PokemonActivity.class);
                            PokemonPokedexObject existingPokemon = pokemonList.get(position);
                            intent.putExtra("position", position);
                            intent.putExtra("name", existingPokemon.getPokemonName());
                            intent.putExtra("image", existingPokemon.getBitmap());
                            startActivityForResult(intent, 1);
                        }
                    });
                } // end run
            }); // end run
        }
    } // end GenerateRunnable
}
