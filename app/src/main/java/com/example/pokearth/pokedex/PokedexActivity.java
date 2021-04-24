package com.example.pokearth.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.PlayActivity;
import com.example.pokearth.R;
import com.example.pokearth.databinding.PokemonListBinding;

import java.util.ArrayList;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

public class PokedexActivity extends AppCompatActivity {

    // binds the data from pokemon_list.xml to implement list view
    PokemonListBinding ui;

    PokemonAdapter adapterList;
    ArrayList<PokemonPokedexObject> pokemonList = new ArrayList<>();
    ListView listViewPokedex;
    PokeApi pokeApi = new PokeApiClient();
    boolean wasClicked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ui = PokemonListBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        listViewPokedex = findViewById(R.id.pokemonList);



        PokedexThreads thread1 = new PokedexThreads(1, 5);
        PokedexThreads thread2 = new PokedexThreads(5, 10);
        PokedexThreads thread3 = new PokedexThreads(10, 15);
        PokedexThreads thread4 = new PokedexThreads(15, 20);


        thread1.start();
        // wait for thread to finish before continuing
        try {
            thread1.join();

        } catch (InterruptedException e) {
            // oops
        }

        thread2.start();
        // wait for thread to finish before continuing
        try {
            thread2.join();

        } catch (InterruptedException e) {
            // oops
        }

        thread3.start();
        // wait for thread to finish before continuing
        try {
            thread3.join();

        } catch (InterruptedException e) {
            // oops
        }

        thread4.start();
        // wait for thread to finish before continuing
        try {
            thread4.join();

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
                // intent.putExtra("type", existingPokemon.getPokemonType());
                intent.putExtra("height", existingPokemon.getHeight());
                intent.putExtra("weight", existingPokemon.getWeight());
                startActivityForResult(intent, 1);
            }
        });

    }

    public void onMoreButtonClicked(View view) {

        // LoadingPageDialog loadingPageDialog = new LoadingPageDialog(PokedexActivity.this);
        // loadingPageDialog.startLoadingDialog();

        if(wasClicked){
            return;
        }

        wasClicked = true;

        PokedexThreads thread5 = new PokedexThreads(20, 25);
        PokedexThreads thread6 = new PokedexThreads(25, 30);
        PokedexThreads thread7 = new PokedexThreads(20, 35);
        PokedexThreads thread8 = new PokedexThreads(35, 40);

        thread5.start();
        // wait for thread to finish before continuing
        try {
            thread5.join();

        } catch (InterruptedException e) {
            // oops
        }

        thread6.start();
        // wait for thread to finish before continuing
        try {
            thread6.join();

        } catch (InterruptedException e) {
            // oops
        }

        thread7.start();
        // wait for thread to finish before continuing
        try {
            thread7.join();

        } catch (InterruptedException e) {
            // oops
        }

        thread8.start();
        // wait for thread to finish before continuing
        try {
            thread8.join();

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
                // intent.putExtra("type", existingPokemon.getPokemonType());
                intent.putExtra("height", existingPokemon.getHeight());
                intent.putExtra("weight", existingPokemon.getWeight());
                startActivityForResult(intent, 1);
            }
        });

        /*
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingPageDialog.dismissDialog();
            }
        }, 5000);
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
                pokemonList.add(new PokemonPokedexObject(i));
            }
        }
    }

}
