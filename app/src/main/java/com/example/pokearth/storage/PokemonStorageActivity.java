package com.example.pokearth.storage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.DB.Party;
import com.example.pokearth.DB.PokemonStorage;
import com.example.pokearth.DB.PokemonStorageDataSource;
import com.example.pokearth.MainActivity;
import com.example.pokearth.PartyActivity;
import com.example.pokearth.PlayActivity;
import com.example.pokearth.R;
import static com.example.pokearth.MusicPlayer.StopSound;

import java.util.ArrayList;
import java.util.List;

public class PokemonStorageActivity extends AppCompatActivity
{
    private Button backButton;
    GridView simpleGrid;
    private PokemonStorageDataSource dataSource;
    private int partySlot=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_page);
        dataSource = new PokemonStorageDataSource(this);

        Intent intent = getIntent();
        partySlot=intent.getIntExtra("party_slot", -1);

        GenerateSaved runnable = new GenerateSaved();
        new Thread(runnable).start();

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(back);
            }
        });
    }

    private class GenerateSaved implements Runnable {

        @Override
        public void run() {

            List<PokemonStorage> storage = dataSource.getAllPokemon();
            ArrayList<Bitmap> maps = new ArrayList<Bitmap>(storage.size());
            for(int x = 0; x<storage.size(); x++)
            {
                maps.add(BitmapFactory.decodeByteArray(storage.get(x).getBitmapString(), 0, storage.get(x).getBitmapString().length));
            }
            runOnUiThread(new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                    GridView gridView = (GridView) findViewById(R.id.gridview);
                    gridView.setAdapter(new StorageAdapter(getApplicationContext(), maps));

                    gridView.setOnItemClickListener(new OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(), PartyActivity.class);
                            intent.putExtra("storage_slot", position);
                            intent.putExtra("party_slot", partySlot);
                            startActivity(intent);
                        }
                    });
                }
            });
        }
    }
}
