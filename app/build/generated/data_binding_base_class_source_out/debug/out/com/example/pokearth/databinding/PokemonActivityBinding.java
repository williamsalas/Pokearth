// Generated by view binder compiler. Do not edit!
package com.example.pokearth.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.pokearth.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class PokemonActivityBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imageItem;

  @NonNull
  public final TextView pokemonDescriptionView;

  @NonNull
  public final TextView pokemonNameView2;

  @NonNull
  public final Button returnButton;

  @NonNull
  public final Button selectButton;

  private PokemonActivityBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView imageItem,
      @NonNull TextView pokemonDescriptionView, @NonNull TextView pokemonNameView2,
      @NonNull Button returnButton, @NonNull Button selectButton) {
    this.rootView = rootView;
    this.imageItem = imageItem;
    this.pokemonDescriptionView = pokemonDescriptionView;
    this.pokemonNameView2 = pokemonNameView2;
    this.returnButton = returnButton;
    this.selectButton = selectButton;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PokemonActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PokemonActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.pokemon_activity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PokemonActivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageItem;
      ImageView imageItem = rootView.findViewById(id);
      if (imageItem == null) {
        break missingId;
      }

      id = R.id.pokemonDescriptionView;
      TextView pokemonDescriptionView = rootView.findViewById(id);
      if (pokemonDescriptionView == null) {
        break missingId;
      }

      id = R.id.pokemonNameView2;
      TextView pokemonNameView2 = rootView.findViewById(id);
      if (pokemonNameView2 == null) {
        break missingId;
      }

      id = R.id.returnButton;
      Button returnButton = rootView.findViewById(id);
      if (returnButton == null) {
        break missingId;
      }

      id = R.id.selectButton;
      Button selectButton = rootView.findViewById(id);
      if (selectButton == null) {
        break missingId;
      }

      return new PokemonActivityBinding((ConstraintLayout) rootView, imageItem,
          pokemonDescriptionView, pokemonNameView2, returnButton, selectButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
