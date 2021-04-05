package com.example.pokearth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class BiomeActivity extends AppCompatActivity {

    private static final String TAG = "BiomeActivity";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImgUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biome_page);
        Log.d(TAG, "onCreate: started.");

        mNames.add("Grassland");
        mNames.add("Volcano");
        for (int i = 2; i < 10; i++)
        {
            mNames.add("Biome: " + i);
        }


        initRecyclerView();
    }

//    public void initImageBitmaps(){
//        Log.d(TAG,)
//    }

    public void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, mImgUrls, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}