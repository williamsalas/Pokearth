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

public final class FightPageBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button button;

  @NonNull
  public final Button button2;

  @NonNull
  public final Button button3;

  @NonNull
  public final Button button4;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView3;

  private FightPageBinding(@NonNull ConstraintLayout rootView, @NonNull Button button,
      @NonNull Button button2, @NonNull Button button3, @NonNull Button button4,
      @NonNull ImageView imageView2, @NonNull ImageView imageView3, @NonNull TextView textView,
      @NonNull TextView textView2, @NonNull TextView textView3) {
    this.rootView = rootView;
    this.button = button;
    this.button2 = button2;
    this.button3 = button3;
    this.button4 = button4;
    this.imageView2 = imageView2;
    this.imageView3 = imageView3;
    this.textView = textView;
    this.textView2 = textView2;
    this.textView3 = textView3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FightPageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FightPageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fight_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FightPageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button;
      Button button = rootView.findViewById(id);
      if (button == null) {
        break missingId;
      }

      id = R.id.button2;
      Button button2 = rootView.findViewById(id);
      if (button2 == null) {
        break missingId;
      }

      id = R.id.button3;
      Button button3 = rootView.findViewById(id);
      if (button3 == null) {
        break missingId;
      }

      id = R.id.button4;
      Button button4 = rootView.findViewById(id);
      if (button4 == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = rootView.findViewById(id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = rootView.findViewById(id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = rootView.findViewById(id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = rootView.findViewById(id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = rootView.findViewById(id);
      if (textView3 == null) {
        break missingId;
      }

      return new FightPageBinding((ConstraintLayout) rootView, button, button2, button3, button4,
          imageView2, imageView3, textView, textView2, textView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
