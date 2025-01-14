package com.example.pokearth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

import static com.example.pokearth.MusicPlayer.SoundPlayer;
import static com.example.pokearth.MusicPlayer.StopSound;
import static com.example.pokearth.MusicPlayer.player;

public class MainActivity extends AppCompatActivity {


    private Button playButton, inventoryButton, shopButton;


    PokeApi pokeApi = new PokeApiClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        if (player != null)
            StopSound();
        SoundPlayer(this, R.raw.mainmusic);

        playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayActivity();
            }
        });
        inventoryButton = findViewById(R.id.inventory_button);
        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInventoryActivity();
            }
        });
        shopButton = findViewById(R.id.shop_button);
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShopActivity();
            }
        });
    }

    public void openPlayActivity() {
        Intent intent = new Intent(MainActivity.this, PlayActivity.class);
        startActivity(intent);
    }

    public void openInventoryActivity() {
        Intent intent = new Intent(MainActivity.this, InventoryActivity.class);
        startActivity(intent);
    }
    public void openShopActivity() {
        Intent intent = new Intent(MainActivity.this, PokeMartActivity.class);
        startActivity(intent);
    }
}