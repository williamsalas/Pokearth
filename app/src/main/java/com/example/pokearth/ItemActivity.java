package com.example.pokearth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokearth.databinding.ActivityItemBinding;

public class ItemActivity extends AppCompatActivity {

    ActivityItemBinding ui;

    int position;
    String name = "";
    Bitmap image;
    String description = "";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ui = ActivityItemBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());


        position = getIntent().getIntExtra("position", 0);
        name = getIntent().getStringExtra("name");
        image = getIntent().getParcelableExtra("image");
        description = getIntent().getStringExtra("description");

        setTitle(name);
        ui.itemNameView.setText(name);
        ui.imageItem.setImageBitmap(image);
        ui.itemDescriptionView.setText(description);

    }

    public void onReturnClicked(View view){
        Intent i = new Intent();
        i.putExtra("position", position);
        i.putExtra("name", name);
        setResult(RESULT_OK,i);
        finish();
    }

    public void onBuyClicked(View view){
        Intent intent = new Intent();
        intent.putExtra("position", position);
        intent.putExtra("name", name);

        String quantity = String.valueOf(Integer.parseInt(ItemList.getInstance().getItems().get(position).getQuantityInBag()) + 1);
        ItemList.getInstance().getItems().get(position).setQuantityInBag(quantity);

        setResult(RESULT_OK,intent);
        finish();

    }

}