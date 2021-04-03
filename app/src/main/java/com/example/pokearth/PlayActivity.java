package com.example.pokearth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    private Button fightButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_page);

//        fightButton = findViewById(R.id.fight_button);
//        fightButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openFightActivity();
//            }
//        });
    }

    public void openFightActivity(View v){
        Intent intent = new Intent(PlayActivity.this, FightActivity.class);
        startActivity(intent);
    }

    public void openBiomeActivity(View v){
        Intent intent = new Intent(PlayActivity.this, BiomeActivity.class);
        startActivity(intent);
    }
}
