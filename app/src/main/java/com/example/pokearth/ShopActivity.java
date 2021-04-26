package com.example.pokearth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.databinding.ActivityShopBinding;

import java.util.ArrayList;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

public class ShopActivity extends AppCompatActivity {


    ActivityShopBinding ui;
    private final Activity context = this;
    ItemAdapter adapterArray;
    ArrayList<Item> itemsList = new ArrayList<>();
    PokeApi pokeApi = new PokeApiClient();
    int position;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityShopBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        position = getIntent().getIntExtra("position", 0);
        name = getIntent().getStringExtra("name");
        setTitle("Poke Mart");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Potions
                Item first = new Item(17);
                Item second = new Item(26);
                Item third = new Item(25);
                //X Items
                Item fourth = new Item(57);
                //Item fifth = new Item(58);
                Item sixth = new Item(61);
                //Item seventh = new Item(62);
                Item eighth = new Item(59);



                itemsList.add(first);
                itemsList.add(second);
                itemsList.add(third);
                itemsList.add(fourth);
                //itemsList.add(fifth);
                itemsList.add(sixth);
                //itemsList.add(seventh);
                itemsList.add(eighth);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterArray = new ItemAdapter(context, R.layout.itemlist_object_layout, itemsList);


                        ui.listObjects.setAdapter(adapterArray);

                        ui.listObjects.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(ShopActivity.this, ItemActivity.class);
                                Item existingItem = itemsList.get(position);
                                intent.putExtra("position",position);
                                intent.putExtra("name",existingItem.getName());
                                intent.putExtra("image",existingItem.getBitmap());
                                intent.putExtra("description",existingItem.getDescription());
                                startActivityForResult(intent,1);
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
        setResult(RESULT_OK,i);
        finish();
    }

}
