package com.example.pokearth;

import android.widget.Toast;

import java.util.ArrayList;

public final class ItemList {

    private static ItemList instance;
    private ArrayList<Item> items = null;

    private ItemList() {
        items = new ArrayList<Item>();

    }

    public static ItemList getInstance() {
        if(instance == null){
            instance = new ItemList();
        }
        return instance;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void addToArray(Item item){
        items.add(item);
    }

    public void createList(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Item first = new Item(17);
                Item second = new Item(26);
                Item third = new Item(25);
                Item fourth = new Item(57);
                Item fifth = new Item(61);
                Item sixth = new Item(59);

                ItemList.getInstance().addToArray(first);
                ItemList.getInstance().addToArray(second);
                ItemList.getInstance().addToArray(third);
                ItemList.getInstance().addToArray(fourth);
                ItemList.getInstance().addToArray(fifth);
                ItemList.getInstance().addToArray(sixth);


            }
        });
        thread.start();
    }

}
