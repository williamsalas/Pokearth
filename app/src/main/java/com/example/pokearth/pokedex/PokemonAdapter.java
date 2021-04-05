package com.example.pokearth.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pokearth.PokemonPokedexObject;
import com.example.pokearth.R;
import com.example.pokearth.databinding.PokemonObjectLayoutBinding;


import java.util.ArrayList;

public class PokemonAdapter extends ArrayAdapter<PokemonPokedexObject> {

    // binds the pokemon_object_layout.xml to display and image and name of the
    // pokemon for the pokedex in list view
    PokemonObjectLayoutBinding ui;
    private Context context;
    private ArrayList<PokemonPokedexObject> pokemons;
    private int resource;

    public PokemonAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PokemonPokedexObject> objects) {

        super(context, resource, objects);

        this.context = context; // takes the activity class that is using the adapter and sets it
        this.pokemons = objects; // takes a list of pokemon and sets it
        this.resource = resource; // takes the layout of the internal part of the list view

    }

    // passes the internal part to list view to the list view itself
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        }


        PokemonPokedexObject pokemon = pokemons.get(position);
        ui = PokemonObjectLayoutBinding.bind(convertView);


        ui.pokeName.setText(pokemon.getPokemonName());
        ui.pokemonImageView.setImageBitmap(pokemon.getBitmap());


        // blacks out the pokemon if not collected otherwise pokemon is displayed
        if(!pokemon.isCollected()){
            convertView.setBackgroundResource(R.color.black);
            ui.pokemonImageView.setImageBitmap(null);
        }
        else
        {
            ui.pokeName.setText(pokemon.getPokemonName());
            ui.pokemonImageView.setImageBitmap(pokemon.getBitmap());
        }


        return convertView;
    }
}
