package com.example.pokearth.storage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.pokearth.R;

public class IndividualStorageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_storage_page);

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        StorageAdapter imageAdapter = new StorageAdapter(getApplicationContext());

        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        imageView.setImageBitmap(imageAdapter.maps.get(position));
    }
}
