package com.example.pokearth;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.pokedex.LoadingPageDialog;
import com.example.pokearth.pokedex.PokedexActivity;

public class PlayActivity extends AppCompatActivity {

    private Button pokedexButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_page);

        LoadingPageDialog loadingPageDialog = new LoadingPageDialog(PlayActivity.this);

        pokedexButton = findViewById(R.id.pokemon_select_button);
        pokedexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPageDialog.startLoadingDialog();
                Intent intent = new Intent(PlayActivity.this, PokedexActivity.class);
                startActivity(intent);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingPageDialog.dismissDialog();
                    }
                }, 10000);
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

    public void openPokedex(){

    }
}
