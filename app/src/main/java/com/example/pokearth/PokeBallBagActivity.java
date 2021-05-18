package com.example.pokearth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.databinding.ActivityPokeballbagBinding;
import com.example.pokearth.databinding.ActivityShopBinding;

import java.util.ArrayList;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

public class PokeBallBagActivity extends AppCompatActivity {

    ActivityPokeballbagBinding ui;
    private final Activity context = this;
    ItemAdapter adapterArray;
    ArrayList<Item> itemsList = new ArrayList<>();
    int position;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityPokeballbagBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        position = getIntent().getIntExtra("position", 0);
        name = getIntent().getStringExtra("name");
        setTitle("Pokeballs");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Item first = new Item(4);
                Item second = new Item(3);
                Item third = new Item(2);

                itemsList.add(first);
                itemsList.add(second);
                itemsList.add(third);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterArray = new ItemAdapter(context, R.layout.itemlist_object_layout, itemsList);


                        ui.listObjects.setAdapter(adapterArray);

                        ui.listObjects.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("position",position);
                                setResult(Activity.RESULT_OK,returnIntent);
                                finish();
                            }
                        });

                    }
                });



            }
        });

        thread.start();

    }

    public void onReturnClicked(View view){
        Intent i = new Intent();
        i.putExtra("position", position);
        i.putExtra("name", name);
        setResult(RESULT_CANCELED,i);
        finish();
    }

}
