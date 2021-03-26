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

        fightButton = findViewById(R.id.fight_button);
        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFightActivity();
            }
        });

        pokedexButton = findViewById(R.id.pokemon_select_button);
        pokedexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPokedex();
            }
        });
    }

    public void openFightActivity(){
        Intent intent = new Intent(PlayActivity.this, FightActivity.class);
        startActivity(intent);
    }

    public void openPokedex(){
        Intent intent = new Intent(PlayActivity.this, PokedexActivity.class);
        startActivity(intent);
    }
}
