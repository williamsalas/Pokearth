package com.example.pokearth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pokearth.databinding.ItemlistObjectLayoutBinding;
import com.example.pokearth.databinding.ItemlistObjectLayoutWithQuantityBinding;

import java.util.ArrayList;

public class InventoryAdapter extends ArrayAdapter<Item> {

    ItemlistObjectLayoutWithQuantityBinding ui;
    private Context context;
    private ArrayList<Item> items;
    private int resource;

    public InventoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> objects) {
        super(context, resource, objects);

        this.context = context;
        this.items = objects;
        this.resource = resource;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource,parent,false);
        }

        Item item = items.get(position);
        ui = ItemlistObjectLayoutWithQuantityBinding.bind(convertView);

        ui.itemName.setText(item.getName());
        ui.imageView.setImageBitmap(item.getBitmap());
        ui.itemQuantity.setText(item.getQuantityInBag());
        return convertView;
    }
}