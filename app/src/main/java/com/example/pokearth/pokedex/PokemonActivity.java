package com.example.pokearth.pokedex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pokearth.databinding.PokemonActivityBinding;

public class PokemonActivity extends AppCompatActivity {

    PokemonActivityBinding ui;

    int position;
    String name = "";
    Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = PokemonActivityBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        position = getIntent().getIntExtra("position",0);
        name = getIntent().getStringExtra("name");
        setTitle(name);
        ui.pokemonNameView2.setText(name);
    }

    public void onReturnClicked(View view){
        Intent i = new Intent();
        i.putExtra("position", position);
        i.putExtra("name", name);
        setResult(RESULT_OK,i);
        finish();
    }

    public void onSelectClicked(View view){
        Intent intent = new Intent();
        intent.putExtra("position", position);
        intent.putExtra("name", name);
        setResult(RESULT_OK,intent);
        finish();
    }
}
