// Generated by view binder compiler. Do not edit!
package com.example.mypa.databinding;

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
import com.example.mypa.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MainPageBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnCalendar;

  @NonNull
  public final Button btnGrades;

  @NonNull
  public final Button btnNotes;

  @NonNull
  public final Button btnSchedule;

  @NonNull
  public final Button btnToDo;

  @NonNull
  public final MaterialCardView card;

  @NonNull
  public final TextView name;

  @NonNull
  public final ImageView pic;

  private MainPageBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnCalendar,
      @NonNull Button btnGrades, @NonNull Button btnNotes, @NonNull Button btnSchedule,
      @NonNull Button btnToDo, @NonNull MaterialCardView card, @NonNull TextView name,
      @NonNull ImageView pic) {
    this.rootView = rootView;
    this.btnCalendar = btnCalendar;
    this.btnGrades = btnGrades;
    this.btnNotes = btnNotes;
    this.btnSchedule = btnSchedule;
    this.btnToDo = btnToDo;
    this.card = card;
    this.name = name;
    this.pic = pic;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MainPageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MainPageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.main_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MainPageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnCalendar;
      Button btnCalendar = rootView.findViewById(id);
      if (btnCalendar == null) {
        break missingId;
      }

      id = R.id.btnGrades;
      Button btnGrades = rootView.findViewById(id);
      if (btnGrades == null) {
        break missingId;
      }

      id = R.id.btnNotes;
      Button btnNotes = rootView.findViewById(id);
      if (btnNotes == null) {
        break missingId;
      }

      id = R.id.btnSchedule;
      Button btnSchedule = rootView.findViewById(id);
      if (btnSchedule == null) {
        break missingId;
      }

      id = R.id.btnToDo;
      Button btnToDo = rootView.findViewById(id);
      if (btnToDo == null) {
        break missingId;
      }

      id = R.id.card;
      MaterialCardView card = rootView.findViewById(id);
      if (card == null) {
        break missingId;
      }

      id = R.id.name;
      TextView name = rootView.findViewById(id);
      if (name == null) {
        break missingId;
      }

      id = R.id.pic;
      ImageView pic = rootView.findViewById(id);
      if (pic == null) {
        break missingId;
      }

      return new MainPageBinding((ConstraintLayout) rootView, btnCalendar, btnGrades, btnNotes,
          btnSchedule, btnToDo, card, name, pic);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}