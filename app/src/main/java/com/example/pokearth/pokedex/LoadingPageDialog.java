package com.example.pokearth.pokedex;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.pokearth.R;

public class LoadingPageDialog {

    private Activity activity;
    private AlertDialog dialog;

    public LoadingPageDialog(Activity myActivity) {
        activity = myActivity;
    }

    public void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_screen_pokedex, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog() {
        dialog.dismiss();
    }
}
