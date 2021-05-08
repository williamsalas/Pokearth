package com.example.pokearth;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BiomeActivity extends AppCompatActivity {

    public static int chosenBiome = 0;
    private static final String TAG = "BiomeActivity";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Drawable> mImages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biome_page);
        Log.d(TAG, "onCreate: started.");
        setChosenBiome(0); // default

        Resources res = this.getResources();
        mNames.add("Grassland"); // 0
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_grassland, null));
        mNames.add("Volcano"); // 1
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_volcano, null));
        mNames.add("Graveyard");
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_graveyard, null));

        for (int i = 3; i < 10; i++) {
            mNames.add("Biome: " + i);
        }


        initRecyclerView();
    }

    public void openPlayActivity(View v) {
        Intent intent = new Intent(BiomeActivity.this, PlayActivity.class);
        startActivity(intent);
    }

    public void openFightActivity(View v) {
        Intent intent = new Intent(BiomeActivity.this, FightActivity.class);
        intent.putExtra("Chosen Biome", chosenBiome);
        startActivity(intent);
    }

    public void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, mImages, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static void setChosenBiome(int i) {
        chosenBiome = i;
    }
}