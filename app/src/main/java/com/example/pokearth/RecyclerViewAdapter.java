package com.example.pokearth;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<Drawable> mImages = new ArrayList<>();
    private Context mContext;
    private int selectedItemPosition = 0;

    public RecyclerViewAdapter(ArrayList<String> imageNames, ArrayList<Drawable> images, Context context) {
        this.mImageNames = imageNames;
        this.mImages = images;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.biome_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.imageName.setText(mImageNames.get(position));
        if (position < mImages.size())
            holder.image.setImageDrawable(mImages.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));
                selectedItemPosition = position;
                BiomeActivity.setChosenBiome(selectedItemPosition);
                notifyDataSetChanged();
            }
        });

        if (selectedItemPosition == position)
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        else
            holder.itemView.setBackgroundColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.biomeImage);
            imageName = itemView.findViewById(R.id.biomeImageName);
            parentLayout = itemView.findViewById(R.id.biomeMenuParentLayout);
        }
    }
}
