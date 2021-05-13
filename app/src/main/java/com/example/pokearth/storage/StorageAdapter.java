package com.example.pokearth.storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.pokearth.R;

import java.util.ArrayList;

public class StorageAdapter extends BaseAdapter
{
    Context mContext;
    ArrayList<Bitmap> maps;
    LayoutInflater inflter;

    public StorageAdapter(Context applicationContext, ArrayList<Bitmap> maps) {
        this.mContext = applicationContext;
        this.maps = maps;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public StorageAdapter(Context applicationContext) {
        this.mContext = applicationContext;
    }

    public int getCount() {
        return maps.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(300,300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageBitmap(maps.get(position));
        return imageView;
    }
}
