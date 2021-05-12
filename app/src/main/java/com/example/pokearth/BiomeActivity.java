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
        setChosenBiome(0); // set top of biome list as default selection

        initBiomeData();

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

    private void initBiomeData() {
        Resources res = this.getResources();
        mNames.add("Grassland"); // 0
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_grassland, null));
        mNames.add("Forest"); // 1
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_forest, null));
        mNames.add("Lake");
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_lake, null));
        mNames.add("City");
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_city, null));
        mNames.add("Ocean");
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_ocean, null));
        mNames.add("Cave");
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_cave, null));
        mNames.add("Graveyard");
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_graveyard, null));
        mNames.add("Jungle");
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_jungle, null));
        mNames.add("Safari Zone");
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_safarizone, null));
        mNames.add("Volcano");
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_volcano, null));
        mNames.add("Power Plant");
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_powerplant, null));
        mNames.add("Ice Cave");
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_icecave, null));
        mNames.add("Space");
        mImages.add(ResourcesCompat.getDrawable(res, R.drawable.pokearth_space, null));
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