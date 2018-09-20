package com.mapbox.services.android.navigation.ui.v5.instruction;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

import com.mapbox.services.android.navigation.ui.v5.NavigationButton;
import com.mapbox.services.android.navigation.ui.v5.R;

import java.util.ArrayList;
import java.util.List;

public class FeedbackButton extends ConstraintLayout implements NavigationButton {
  private FloatingActionButton feedbackFab;
  private List<OnClickListener> onClickListeners;

  public FeedbackButton(Context context) {
    this(context, null);
  }

  public FeedbackButton(Context context, AttributeSet attrs) {
    this(context, attrs, -1);
  }

  public FeedbackButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initialize(context);
  }

  /**
   * Adds an onClickListener to the button
   *
   * @param onClickListener to add
   */
  @Override
  public void addOnClickListener(OnClickListener onClickListener) {
    if (!onClickListeners.contains(onClickListener)) {
      onClickListeners.add(onClickListener);
    }
  }

  /**
   * Hides the widget
   */
  @Override
  public void hide() {
    setVisibility(GONE);
  }

  /**
   * Shows the widget
   */
  @Override
  public void show() {
    setVisibility(VISIBLE);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    bind();
    setupOnClickListeners();
  }

  private void initialize(Context context) {
    inflate(context, R.layout.feedback_button_layout, this);
  }

  private void bind() {
    feedbackFab = findViewById(R.id.feedbackFab);
  }

  private void setupOnClickListeners() {
    onClickListeners = new ArrayList<>();

    feedbackFab.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        for (OnClickListener onClickListener : onClickListeners) {
          onClickListener.onClick(view);
        }
      }
    });
  }
}
