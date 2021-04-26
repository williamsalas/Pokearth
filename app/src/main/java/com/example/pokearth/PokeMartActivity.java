package com.example.pokearth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.databinding.ActivityPokemartBinding;
import com.example.pokearth.databinding.ActivityShopBinding;

public class PokeMartActivity extends AppCompatActivity {

    ActivityPokemartBinding ui;
    int position;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityPokemartBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        position = getIntent().getIntExtra("position", 0);
        name = getIntent().getStringExtra("name");
        setTitle("Poke Mart");



        ui.ballIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PokeMartActivity.this, BallShopActivity.class);
                startActivity(intent);
            }
        });


        ui.potionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PokeMartActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });



    }

    public void onReturnClicked(View view){
        Intent i = new Intent();
        i.putExtra("position", position);
        i.putExtra("name", name);
        setResult(RESULT_OK,i);
        finish();
    }
}
