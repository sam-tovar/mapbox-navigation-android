package com.mapbox.services.android.navigation.ui.v5;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * Button used to re-activate following user location during navigation.
 * <p>
 * If a user scrolls the map while navigating, the
 * {@link com.mapbox.services.android.navigation.ui.v5.summary.SummaryBottomSheet}
 * is set to hidden and this button is shown.
 * <p>
 * This button uses a custom {@link TranslateAnimation} with {@link OvershootInterpolator}
 * to be shown.
 *
 * @since 0.6.0
 */
public class RecenterButton extends CardView implements NavigationButton {

  private List<OnClickListener> onClickListeners;
  private Animation slideUpBottom;

  public RecenterButton(Context context) {
    this(context, null);
  }

  public RecenterButton(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, -1);
  }

  public RecenterButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  /**
   * Sets visibility to VISIBLE and starts custom animation.
   *
   * @since 0.6.0
   */
  @Override
  public void show() {
    if (getVisibility() == INVISIBLE) {
      setVisibility(VISIBLE);
      startAnimation(slideUpBottom);
    }
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
   * Sets visibility to INVISIBLE.
   *
   * @since 0.6.0
   */
  @Override
  public void hide() {
    if (getVisibility() == VISIBLE) {
      setVisibility(INVISIBLE);
    }
  }

  /**
   * Once inflation of the view has finished,
   * create the custom animation.
   */
  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    setupOnClickListeners();
    initAnimation();
  }

  private void setupOnClickListeners() {
    onClickListeners = new ArrayList<>();

    setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        for (OnClickListener onClickListener : onClickListeners) {
          onClickListener.onClick(view);
        }
      }
    });
  }

  /**
   * Inflates the layout.
   */
  private void init() {
    inflate(getContext(), R.layout.recenter_btn_layout, this);
  }

  /**
   * Creates the custom animation used to show this button.
   */
  private void initAnimation() {
    slideUpBottom = new TranslateAnimation(0f, 0f, 125f, 0f);
    slideUpBottom.setDuration(300);
    slideUpBottom.setInterpolator(new OvershootInterpolator(2.0f));
  }
}
