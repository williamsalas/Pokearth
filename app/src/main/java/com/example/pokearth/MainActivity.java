package com.example.pokearth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

public class MainActivity extends AppCompatActivity {


    private Button playButton, inventoryButton, shopButton, partyButton;


    PokeApi pokeApi = new PokeApiClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

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
        partyButton = findViewById(R.id.party_button);
        partyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openPartyActivity();}
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

    public void openPartyActivity() {
        Intent intent = new Intent(MainActivity.this, PartyActivity.class);
        startActivity(intent);
    }
}