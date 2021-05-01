// Generated by view binder compiler. Do not edit!
package com.example.mypa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.example.mypa.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDayBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton btnBack;

  @NonNull
  public final EditText etAkivnost;

  @NonNull
  public final EditText etVreme;

  @NonNull
  public final RecyclerView rvAktivnosti;

  private ActivityDayBinding(@NonNull ConstraintLayout rootView, @NonNull ImageButton btnBack,
      @NonNull EditText etAkivnost, @NonNull EditText etVreme, @NonNull RecyclerView rvAktivnosti) {
    this.rootView = rootView;
    this.btnBack = btnBack;
    this.etAkivnost = etAkivnost;
    this.etVreme = etVreme;
    this.rvAktivnosti = rvAktivnosti;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDayBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDayBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_day, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDayBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBack;
      ImageButton btnBack = rootView.findViewById(id);
      if (btnBack == null) {
        break missingId;
      }

      id = R.id.etAkivnost;
      EditText etAkivnost = rootView.findViewById(id);
      if (etAkivnost == null) {
        break missingId;
      }

      id = R.id.etVreme;
      EditText etVreme = rootView.findViewById(id);
      if (etVreme == null) {
        break missingId;
      }

      id = R.id.rvAktivnosti;
      RecyclerView rvAktivnosti = rootView.findViewById(id);
      if (rvAktivnosti == null) {
        break missingId;
      }

      return new ActivityDayBinding((ConstraintLayout) rootView, btnBack, etAkivnost, etVreme,
          rvAktivnosti);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
