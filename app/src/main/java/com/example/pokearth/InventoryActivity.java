package com.example.pokearth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.databinding.ActivityInventoryBinding;
import com.example.pokearth.databinding.ActivityShopBinding;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {

    ActivityInventoryBinding ui;
    private final Activity context = this;
    InventoryAdapter adapterArray;
    ArrayList<Item> itemsList = new ArrayList<>();
    int position;
    String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityInventoryBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        
        position = getIntent().getIntExtra("position", 0);
        name = getIntent().getStringExtra("name");
        setTitle("Inventory");


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Potions

                if(ItemList.getInstance().getItems().isEmpty()){
                    Item first = new Item(17);
                    Item second = new Item(26);
                    Item third = new Item(25);

                    Item fourth = new Item(4);
                    Item fifth = new Item(3);
                    Item sixth = new Item(2);



                    ItemList.getInstance().addToArray(first);
                    ItemList.getInstance().addToArray(second);
                    ItemList.getInstance().addToArray(third);
                    ItemList.getInstance().addToArray(fourth);
                    ItemList.getInstance().addToArray(fifth);
                    ItemList.getInstance().addToArray(sixth);


                }

//
//
//
//
//
//                itemsList.add(first);
//                itemsList.add(second);
//                itemsList.add(third);
//                itemsList.add(fourth);
//                //itemsList.add(fifth);
//                itemsList.add(sixth);
//                //itemsList.add(seventh);
//                itemsList.add(eighth);




                //itemsList.get(0).setQuantityInBag("5");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapterArray = new InventoryAdapter(context, R.layout.itemlist_object_layout_with_quantity, ItemList.getInstance().getItems());


                        ui.listObjects.setAdapter(adapterArray);

                    }
                });



            }
        });

        thread.start();


    }
}
