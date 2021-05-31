package com.rejowan.cutebutton;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;


public class CuteButton extends LinearLayout {


    /*
     CuteButton Library for Android
     Created by K M Rejowan Ahmmed (ahmmedrejowan)
     Min Api 21
     Target Api 30
     Java Version 1.8
     */


    /*
     CopyEND 2021 K M Rejowan Ahmmed (ahmmedrejowan)

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
     */


    public static final int POSITION_START = 1;
    public static final int POSITION_END = 2;
    public static final int POSITION_TOP = 3;
    public static final int POSITION_BOTTOM = 4;


    public static final int GRAVITY_CENTER = 0;
    public static final int GRAVITY_START = 1;
    public static final int GRAVITY_END = 2;
    public static final int GRAVITY_TOP = 3;
    public static final int GRAVITY_BOTTOM = 4;


    public static final int TEXT_STYLE_BOLD = 1;
    public static final int TEXT_STYLE_ITALIC = 2;


    private final Context context;
    private final int gravity = Gravity.CENTER;
    private int borderColor = Color.TRANSPARENT;
    private int borderWidth = 0;
    private float radius = 0;
    private boolean isEnabled = true;
    private int backgroundColor = Color.parseColor("#D6D7D7");
    private int focusColor = Color.parseColor("#B0B0B0");
    private int disableColor = Color.parseColor("#D6D7D7");
    private int textSize = 37;
    private int textColor = Color.parseColor("#1C1C1C");
    private int disabledTextColor = Color.parseColor("#A0A0A0");
    private boolean textAllCaps = false;
    private int textStyle = 0;
    private int padding = 30;
    private int paddingSTART = 30;
    private int paddingTop = 30;
    private int paddingEND = 30;
    private int paddingBottom = 30;
    private String text = "";
    private int icon = 0;
    private Drawable drawable = null;
    private int iconPosition = POSITION_START;
    private int iconSize = 37;
    private int iconPadding = 10;
    private int lGravity = 0;
    private ImageView imageView;
    private TextView textView;
    public CuteButton(Context context) {
        super(context);
        this.context = context;

        initializeView();
    }

    public CuteButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        processAttributes(context, attrs);

        initializeView();
    }

    public CuteButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        processAttributes(context, attrs);

        initializeView();
    }

    @SuppressWarnings("unused")
    public CuteButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;

        processAttributes(context, attrs);

        initializeView();
    }

    private static int pxToSp(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity);
    }

    private void initializeView() {

        if (iconPosition == POSITION_TOP || iconPosition == POSITION_BOTTOM) {
            this.setOrientation(LinearLayout.VERTICAL);
        } else {
            this.setOrientation(LinearLayout.HORIZONTAL);
        }


        if (this.getLayoutParams() == null) {
            LayoutParams containerParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            this.setLayoutParams(containerParams);
        }


        super.setGravity(gravity);
        super.setEnabled(isEnabled);
        this.setClickable(isEnabled);
        this.setFocusable(true);

        setupTextView();
        setupImageView();
        setupBackground();

        super.setPadding(paddingSTART, paddingTop, paddingEND, paddingBottom);

        this.removeAllViews();


        if (iconPosition == POSITION_END || iconPosition == POSITION_BOTTOM) {
            if (textView != null) this.addView(textView);
            if (imageView != null) this.addView(imageView);
        } else {
            if (imageView != null) this.addView(imageView);
            if (textView != null) this.addView(textView);

        }

        updateGravity();
        updateIconPadding();

    }

    private void processAttributes(final Context context, final AttributeSet attrs) {
        initDefaultAttributes(attrs);
        TypedArray attrsArray = context.obtainStyledAttributes(attrs, R.styleable.CuteButton, 0, 0);
        initAttributes(attrsArray);
        attrsArray.recycle();
    }

    private void initDefaultAttributes(AttributeSet attrs) {
        int[] defAttr = new int[]{
                android.R.attr.gravity,
                android.R.attr.padding,
                android.R.attr.paddingStart,
                android.R.attr.paddingTop,
                android.R.attr.paddingEnd,
                android.R.attr.paddingBottom,
                android.R.attr.paddingStart,
                android.R.attr.paddingEnd
        };

        TypedArray defAttrsArray = context.obtainStyledAttributes(attrs, defAttr);
        padding = defAttrsArray.getDimensionPixelSize(1, padding);
        if (padding != 0) {
            paddingSTART = paddingTop = paddingEND = paddingBottom = padding;
        }
        paddingSTART = defAttrsArray.getDimensionPixelSize(2, paddingSTART);
        paddingTop = defAttrsArray.getDimensionPixelSize(3, paddingTop);
        paddingEND = defAttrsArray.getDimensionPixelSize(4, paddingEND);
        paddingBottom = defAttrsArray.getDimensionPixelSize(5, paddingBottom);
        paddingSTART = defAttrsArray.getDimensionPixelSize(6, paddingSTART);
        paddingEND = defAttrsArray.getDimensionPixelSize(7, paddingEND);

        defAttrsArray.recycle();

    }

    private void initAttributes(TypedArray attrs) {

        radius = attrs.getDimension(R.styleable.CuteButton_cb_borderRadius, radius);
        borderColor = attrs.getColor(R.styleable.CuteButton_cb_borderColor, borderColor);
        borderWidth = (int) attrs.getDimension(R.styleable.CuteButton_cb_borderWidth, borderWidth);
        backgroundColor = attrs.getColor(R.styleable.CuteButton_cb_bgColor, backgroundColor);
        disableColor = attrs.getColor(R.styleable.CuteButton_cb_bgColorDisabled, disableColor);

        focusColor = attrs.getColor(R.styleable.CuteButton_cb_bgColorFocus, focusColor);

        text = attrs.getString(R.styleable.CuteButton_cb_text);
        textColor = attrs.getColor(R.styleable.CuteButton_cb_textColor, textColor);
        disabledTextColor = attrs.getColor(R.styleable.CuteButton_cb_textColorDisabled, disabledTextColor);
        textSize = attrs.getDimensionPixelSize(R.styleable.CuteButton_cb_textSize, textSize);

        textStyle = attrs.getInt(R.styleable.CuteButton_cb_textStyle, textStyle);

        textAllCaps = attrs.getBoolean(R.styleable.CuteButton_cb_textAllCaps, textAllCaps);


        iconSize = attrs.getDimensionPixelSize(R.styleable.CuteButton_cb_iconSize, iconSize);
        iconPosition = attrs.getInt(R.styleable.CuteButton_cb_iconPosition, iconPosition);

        icon = attrs.getResourceId(R.styleable.CuteButton_cb_icon, icon);
        iconPadding = attrs.getDimensionPixelSize(R.styleable.CuteButton_cb_iconPadding, iconPadding);

        lGravity = attrs.getInt(R.styleable.CuteButton_cb_gravity, lGravity);
        isEnabled = attrs.getBoolean(R.styleable.CuteButton_cb_enabled, isEnabled);

    }

    private void setupBackground() {

        GradientDrawable defaultDrawable = new GradientDrawable();
        defaultDrawable.setCornerRadius(radius);
        defaultDrawable.setColor(backgroundColor);

        GradientDrawable focusDrawable = new GradientDrawable();
        focusDrawable.setCornerRadius(radius);
        focusDrawable.setColor(focusColor);

        GradientDrawable disabledDrawable = new GradientDrawable();
        disabledDrawable.setCornerRadius(radius);
        disabledDrawable.setColor(disableColor);

        if (borderColor != 0 && borderWidth > 0) {
            defaultDrawable.setStroke(borderWidth, borderColor);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            setBackground(getRippleDrawable(defaultDrawable, focusDrawable, disabledDrawable));

        } else {

            StateListDrawable states = new StateListDrawable();

            GradientDrawable drawable2 = new GradientDrawable();
            drawable2.setCornerRadius(radius);
            drawable2.setColor(focusColor);

            if (focusColor != 0) {
                states.addState(new int[]{android.R.attr.state_pressed}, drawable2);
                states.addState(new int[]{android.R.attr.state_focused}, drawable2);
                states.addState(new int[]{-android.R.attr.state_enabled}, disabledDrawable);
            }

            states.addState(new int[]{}, defaultDrawable);

            this.setBackground(states);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Drawable getRippleDrawable(Drawable defaultDrawable, Drawable focusDrawable, Drawable disabledDrawable) {
        if (!isEnabled()) {
            return disabledDrawable;
        } else {
            return new RippleDrawable(ColorStateList.valueOf(focusColor), defaultDrawable, focusDrawable);
        }

    }

    private void setupTextView() {
        textView = new TextView(context);
        LayoutParams textViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textViewParams);


        textView.setText(text);
        textView.setTextColor(isEnabled ? textColor : disabledTextColor);
        textView.setTextSize(pxToSp(context, textSize));
        textView.setAllCaps(textAllCaps);

        if (textStyle == 2) {
            textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
        } else if (textStyle == 1) {
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        } else {
            textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
        }

        textView.setGravity(gravity);
    }

    private void setupImageView() {
        imageView = new ImageView(context);
        if (icon == 0) {
            iconSize = 0;
        }

        if (icon != 0) {
            int width = iconSize;
            int height = iconSize;
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width, height);
            imageView.setLayoutParams(parms);
            imageView.setImageResource(icon);
        }

        if (drawable != null) {

            imageView.setImageDrawable(drawable);
        }
        updateIconPadding();

    }

    private void updateGravity() {
        if (lGravity == GRAVITY_CENTER) {
            super.setGravity(Gravity.CENTER);
        } else if (lGravity == GRAVITY_START) {
            super.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        } else if (lGravity == GRAVITY_END) {
            super.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        } else if (lGravity == GRAVITY_TOP) {
            super.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        } else if (lGravity == GRAVITY_BOTTOM) {
            super.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        }
    }

    private void updateIconPadding() {
        if (icon == 0) {
            iconSize = 0;
        }
        LayoutParams imageViewParams = new LayoutParams(iconSize, iconSize);

        if (iconPosition == POSITION_START) {
            imageViewParams.setMargins(0, 0, getDrawablePadding(), 0);
        } else if (iconPosition == POSITION_TOP) {
            imageViewParams.setMargins(0, 0, 0, getDrawablePadding());
        } else if (iconPosition == POSITION_END) {
            imageViewParams.setMargins(getDrawablePadding(), 0, 0, 0);
        } else if (iconPosition == POSITION_BOTTOM) {
            imageViewParams.setMargins(0, getDrawablePadding(), 0, 0);
        }


        imageView.setLayoutParams(imageViewParams);
    }

    private int getDrawablePadding() {
        if (icon == 0) {
            iconPadding = 0;
        }
        return iconPadding;

    }

    @SuppressWarnings("unused")
    public boolean getAllCaps() {
        return textAllCaps;
    }

    @SuppressWarnings("unused")
    public void setAllCaps(boolean allCaps) {
        this.textAllCaps = allCaps;
        textView.setAllCaps(allCaps);
    }

    @SuppressWarnings("unused")
    public String getText() {
        return text;
    }

    @SuppressWarnings("unused")
    public void setText(String text) {
        this.text = text;
        if (textView != null) textView.setText(text);
        else setupTextView();
    }

    @SuppressWarnings("unused")
    public int getTextStyle() {
        return textStyle;
    }

    @SuppressWarnings("unused")
    public void setTextStyle(int style) {
        this.textStyle = style;
        if (textStyle == TEXT_STYLE_ITALIC) {
            textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
        } else if (textStyle == TEXT_STYLE_BOLD) {
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        } else {
            textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
        }
    }

    @SuppressWarnings("unused")
    public float getTextSize() {
        return textSize;
    }

    @SuppressWarnings("unused")
    public void setTextSize(int size) {
        this.textSize = size;
        textView.setTextSize(size);
    }

    @SuppressWarnings("unused")
    public int getTextColor() {
        return this.textColor;
    }

    @SuppressWarnings("unused")
    public void setTextColor(int color) {
        this.textColor = color;
        textView.setTextColor(isEnabled ? textColor : disabledTextColor);
    }

    @SuppressWarnings("unused")
    public int getBorderWidth() {
        return borderWidth;
    }

    @SuppressWarnings("unused")
    public void setBorderWidth(int size) {
        this.borderWidth = size;
        setupBackground();
    }

    @SuppressWarnings("unused")
    public int getBorderColor() {
        return this.borderColor;
    }

    @SuppressWarnings("unused")
    public void setBorderColor(int color) {
        this.borderColor = color;
        setupBackground();
    }

    @SuppressWarnings("unused")
    public float getRadius() {
        return radius;
    }

    @SuppressWarnings("unused")
    public void setRadius(float size) {
        this.radius = size;
        setupBackground();
    }

    @SuppressWarnings("unused")
    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(@ColorInt int color) {
        this.backgroundColor = color;
        setupBackground();
    }

    @SuppressWarnings("unused")
    public int getFocusColor() {
        return this.focusColor;
    }

    @SuppressWarnings("unused")
    public void setFocusColor(@ColorInt int color) {
        this.focusColor = color;
        setupBackground();
    }

    @SuppressWarnings("unused")
    public int getDisableColor() {
        return disableColor;
    }

    @SuppressWarnings("unused")
    public void setDisableColor(@ColorInt int color) {
        this.disableColor = color;
        setupBackground();
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
        initializeView();
    }

    @SuppressWarnings("unused")
    public int getDisabledTextColor() {
        return disabledTextColor;
    }

    @SuppressWarnings("unused")
    public void setDisabledTextColor(int color) {
        this.disabledTextColor = color;
        initializeView();
    }

    @SuppressWarnings("unused")
    public int getDisabledColor() {
        return disableColor;
    }

    @SuppressWarnings("unused")
    public void setDisabledColor(int color) {
        this.disableColor = color;
        setupBackground();
    }

    @SuppressWarnings("unused")
    public int getIconSize() {

        return iconSize;

    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
    }

    @SuppressWarnings("unused")
    public void setIconPosition(int position) {
        this.iconPosition = position;
        initializeView();
    }

    @SuppressWarnings("unused")
    public void setIcon(@DrawableRes int resource) {
        this.icon = resource;
        imageView.setImageResource(resource);
    }

    @SuppressWarnings("unused")
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        initializeView();
    }

    @SuppressWarnings("unused")
    public int getIconPadding() {
        return this.iconPadding;
    }

    @SuppressWarnings("unused")
    public void setIconPadding(int padding) {
        this.iconPadding = padding;
        initializeView();
    }

    @SuppressWarnings("unused")
    public void setTextGravity(int gravity) {
        this.lGravity = gravity;
        initializeView();

    }

}
