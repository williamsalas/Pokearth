package com.example.pokearth.pokedex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pokearth.databinding.PokemonActivityBinding;

import me.sargunvohra.lib.pokekotlin.model.PokemonType;

// this activity populates the pokemon_activity.xml when a pokemon is clicked
// in the list view of pokemon_list.xml
public class PokemonActivity extends AppCompatActivity {

    PokemonActivityBinding ui;

    int position;
    String name = "";
    Bitmap image;
    int height;
    int weight;
    String pokemonType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = PokemonActivityBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        position = getIntent().getIntExtra("position",0);
        name = getIntent().getStringExtra("name");
        image = getIntent().getParcelableExtra("image");
        height = getIntent().getIntExtra("height", 0);
        weight = getIntent().getIntExtra("weight", 0);
        pokemonType = getIntent().getStringExtra("type");
        setTitle(name);
        ui.pokemonNameView2.setText(name.toUpperCase());
        ui.pokemonImage.setImageBitmap(image);
        ui.pokemonNumber.setText("No. " + (position+1));
        // ui.pokemonType.setText("TYPE: " + pokemonType);
        ui.pokemonHeight.setText("HEIGHT:     " + String.format("%d", height) + "  ft");
        ui.pokemonWeight.setText("WEIGHT:     " + String.format("%d", weight) + "  lb");
    }

    public void onReturnClicked(View view){
        Intent i = new Intent();
        i.putExtra("position", position);
        i.putExtra("name", name);
        setResult(RESULT_OK,i);
        finish();
    }

}
