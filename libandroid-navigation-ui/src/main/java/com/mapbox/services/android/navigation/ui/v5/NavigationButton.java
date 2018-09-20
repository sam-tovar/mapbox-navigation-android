package com.mapbox.services.android.navigation.ui.v5;

import android.view.View;

public interface NavigationButton {

  /**
   * Adds an onClickListener to the button
   *
   * @param onClickListener to add
   */
  void addOnClickListener(View.OnClickListener onClickListener);

  /**
   * Hides the widget
   */
  void hide();

  /**
   * Shows the widget
   */
  void show();
}
