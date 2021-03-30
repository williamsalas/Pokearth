package com.example.pokearth.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pokearth.PokemonObject;
import com.example.pokearth.databinding.PokemonObjectLayoutBinding;


import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends ArrayAdapter<PokemonObject> {

    PokemonObjectLayoutBinding ui;
    private Context context;
    private ArrayList<PokemonObject> pokemons;
    private int resource;

    public PokemonAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PokemonObject> objects) {

        super(context, resource, objects);

        this.context = context;
        this.pokemons = objects;
        this.resource = resource;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        }

        PokemonObject pokemon = pokemons.get(position);
        ui = PokemonObjectLayoutBinding.bind(convertView);

        ui.pokeName.setText(pokemon.getPokemonName());
        ui.pokemonImageView.setImageBitmap(pokemon.getBitmap());

        return convertView;
    }
}
