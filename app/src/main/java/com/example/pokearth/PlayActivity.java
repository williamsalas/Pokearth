package com.example.pokearth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.pokedex.PokedexActivity;

public class PlayActivity extends AppCompatActivity {

    private Button fightButton;
    private Button pokedexButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_page);

        pokedexButton = findViewById(R.id.pokemon_select_button);
        pokedexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPokedexActivity(v);
            }
        });

    }

    public void openFightActivity(View v){
        Intent intent = new Intent(PlayActivity.this, FightActivity.class);
        startActivity(intent);
    }


    public void openBiomeActivity(View v){
        Intent intent = new Intent(PlayActivity.this, BiomeActivity.class);
       startActivity(intent);
    }

    public void openPokedexActivity(View v){
        Intent intent = new Intent(PlayActivity.this, PokedexActivity.class);
        startActivity(intent);
    }

    public void openMainActivity(View v){
        Intent intent = new Intent(PlayActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
