package com.example.pokearth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.databinding.ActivityBallshopBinding;

import java.util.ArrayList;

public class BallShopActivity extends AppCompatActivity {

    ActivityBallshopBinding ui;
    private final Activity context = this;
    ItemAdapter adapterArray;
    ArrayList<Item> itemsList = new ArrayList<>();

    int position;
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityBallshopBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        setTitle("Pokeballs");
        position = getIntent().getIntExtra("position", 0);
        name = getIntent().getStringExtra("name");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Pokeball, Great Ball, Ultra Ball
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
                                Intent intent = new Intent(BallShopActivity.this, ItemActivity.class);
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
