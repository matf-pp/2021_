// Generated by view binder compiler. Do not edit!
package com.example.mypa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.example.mypa.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class OneactivityitemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView tvAct;

  @NonNull
  public final TextView tvTime;

  private OneactivityitemBinding(@NonNull CardView rootView, @NonNull TextView tvAct,
      @NonNull TextView tvTime) {
    this.rootView = rootView;
    this.tvAct = tvAct;
    this.tvTime = tvTime;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static OneactivityitemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static OneactivityitemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.oneactivityitem, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static OneactivityitemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.tvAct;
      TextView tvAct = rootView.findViewById(id);
      if (tvAct == null) {
        break missingId;
      }

      id = R.id.tvTime;
      TextView tvTime = rootView.findViewById(id);
      if (tvTime == null) {
        break missingId;
      }

      return new OneactivityitemBinding((CardView) rootView, tvAct, tvTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
