package com.example.pokearth.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.PokemonObject;
import com.example.pokearth.R;
import com.example.pokearth.databinding.PokemonListBinding;

import java.util.ArrayList;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

public class PokedexActivity extends AppCompatActivity {

    PokemonListBinding ui;

    PokemonAdapter adapterList;
    ArrayList<PokemonObject> pokemonList = new ArrayList<>();
    ListView listViewPokedex;
    PokeApi pokeApi = new PokeApiClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = PokemonListBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        listViewPokedex = findViewById(R.id.pokemonList);

        /*
        PokemonObject first = new PokemonObject("A", "");
        PokemonObject second = new PokemonObject("B", "");
        PokemonObject third = new PokemonObject("C", "");
        PokemonObject fourth = new PokemonObject("D", "");
        PokemonObject fifth = new PokemonObject("E", "");

        pokemonList.add(first);
        pokemonList.add(second);
        pokemonList.add(third);
        pokemonList.add(fourth);
        pokemonList.add(fifth);
         */

        PokedexThreads thread1 = new PokedexThreads(0, 20);
        PokedexThreads thread2 = new PokedexThreads(20, 40);

        thread1.start(); // start the thread
        thread2.start();
        // wait for thread to finish before continuing
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            // oops
        }

        adapterList = new PokemonAdapter(this, R.layout.pokemon_object_layout, pokemonList);

        listViewPokedex.setAdapter(adapterList);
        listViewPokedex.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PokedexActivity.this, PokemonActivity.class);
                PokemonObject existingPokemon = pokemonList.get(position);
                intent.putExtra("position", position);
                intent.putExtra("name", existingPokemon.getPokemonName());
                intent.putExtra("image", existingPokemon.getBitmap());
                startActivityForResult(intent, 1);
            }
        });
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
                pokemonList.add(new PokemonObject(i+1));
            }
        }
    }
}
