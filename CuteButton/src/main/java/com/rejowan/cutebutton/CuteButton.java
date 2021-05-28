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
import androidx.annotation.RequiresApi;

//import android.support.annotation.RequiresApi;

public class CuteButton extends LinearLayout {

    /**
     * icon position
     */
    public static final int POSITION_LEFT = 1;
    public static final int POSITION_RIGHT = 2;
    public static final int POSITION_TOP = 3;
    public static final int POSITION_BOTTOM = 4;


    public static final int GRAVITY_CENTER = 0;
    public static final int GRAVITY_LEFT = 1;
    public static final int GRAVITY_RIGHT = 2;
    public static final int GRAVITY_TOP = 3;
    public static final int GRAVITY_BOTTOM = 4;


    public static final int TEXT_STYLE_BOLD = 1;
    public static final int TEXT_STYLE_ITALIC = 2;


    private static final String FONT_AWESOME = "fonts/fontawesome-webfont.ttf";


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
    private int padding = 20;
    private int paddingLeft = 20;
    private int paddingTop = 20;
    private int paddingRight = 20;
    private int paddingBottom = 20;
    private String text = "";
    private int drawableResource = 0;
    private Drawable drawable = null;
    private int iconPosition = POSITION_LEFT;
    private int iconSize = 0;
    private int iconPadding = 20;
    private int lGravity = 0;

    private Typeface awesomeIconTypeFace = null;
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

        //TypedArray attrsArray = context.obtainStyledAttributes(attrs, R.styleable.CButton, 0, 0);
		/*TypedArray attrsArray = context.obtainStyledAttributes(attrs, R.styleable.CButton, 0, 0);
		initAttributes(attrsArray);
		attrsArray.recycle();*/

        processAttributes(context, attrs);

        initializeView();
    }

    public CuteButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        processAttributes(context, attrs);

        initializeView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CuteButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;

        processAttributes(context, attrs);

        initializeView();
    }

    private static int pxToSp(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static Typeface getAwesomeTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), FONT_AWESOME);
    }

    private void initializeView() {

        if (!isInEditMode()) {
            awesomeIconTypeFace = getAwesomeTypeface(context);
            //Log.e("TAG", "awesomeIconTypeFace");
        }


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
        //if (imageView != null) this.addView(imageView);

        setupBackground();

        super.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

        //super.setPadding(0, 0, 0, 0);


        this.removeAllViews();

        if (iconPosition == POSITION_RIGHT || iconPosition == POSITION_BOTTOM) {
            if (textView != null) this.addView(textView);
            if (imageView != null) this.addView(imageView);
        } else {
            if (imageView != null) this.addView(imageView);
            if (textView != null) this.addView(textView);

        }


        updateGravity();


        updateIconPadding();
    }

    private void setupSize() {
        int width = iconSize;
        int height = iconSize;
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width, height);
        imageView.setLayoutParams(parms);

    }

    private void processAttributes(final Context context, final AttributeSet attrs) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)
            initDefaultAttributes(attrs);
        else
            initDefaultAttributes17(attrs);

        TypedArray attrsArray = context.obtainStyledAttributes(attrs, R.styleable.CuteButton, 0, 0);
        initAttributes(attrsArray);
        attrsArray.recycle();
    }

    private void initDefaultAttributes(AttributeSet attrs) {
        int[] defAttr = new int[]{
                android.R.attr.gravity,
                android.R.attr.padding,
                android.R.attr.paddingLeft,
                android.R.attr.paddingTop,
                android.R.attr.paddingRight,
                android.R.attr.paddingBottom
        };

        TypedArray defAttrsArray = context.obtainStyledAttributes(attrs, defAttr);
        //gravity = defAttrsArray.getInt(0, gravity);
        padding = defAttrsArray.getDimensionPixelSize(1, padding);

        // initialize padding to all
        if (padding != 0) {
            paddingLeft = paddingTop = paddingRight = paddingBottom = padding;
        }

        paddingLeft = defAttrsArray.getDimensionPixelSize(2, paddingLeft);
        paddingTop = defAttrsArray.getDimensionPixelSize(3, paddingTop);
        paddingRight = defAttrsArray.getDimensionPixelSize(4, paddingRight);
        paddingBottom = defAttrsArray.getDimensionPixelSize(5, paddingBottom);
        paddingLeft = defAttrsArray.getDimensionPixelSize(6, paddingLeft);
        paddingRight = defAttrsArray.getDimensionPixelSize(7, paddingRight);


        defAttrsArray.recycle();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initDefaultAttributes17(AttributeSet attrs) {
        int[] defAttr = new int[]{
                android.R.attr.gravity,
                android.R.attr.padding,
                android.R.attr.paddingLeft,
                android.R.attr.paddingTop,
                android.R.attr.paddingRight,
                android.R.attr.paddingBottom,
                android.R.attr.paddingStart,
                android.R.attr.paddingEnd
        };

        TypedArray defAttrsArray = context.obtainStyledAttributes(attrs, defAttr);
        //gravity = defAttrsArray.getInt(0, gravity);
        padding = defAttrsArray.getDimensionPixelSize(1, padding);
        paddingLeft = paddingTop = paddingRight = paddingBottom = padding;

        paddingLeft = defAttrsArray.getDimensionPixelSize(2, paddingLeft);
        paddingTop = defAttrsArray.getDimensionPixelSize(3, paddingTop);
        paddingRight = defAttrsArray.getDimensionPixelSize(4, paddingRight);
        paddingBottom = defAttrsArray.getDimensionPixelSize(5, paddingBottom);
        paddingLeft = defAttrsArray.getDimensionPixelSize(6, paddingLeft);
        paddingRight = defAttrsArray.getDimensionPixelSize(7, paddingRight);


        defAttrsArray.recycle();

    }

    /**
     * Initialize Attributes arrays
     *
     * @param attrs : Attributes array
     */
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

		/*Log.e("TAG", "Dimension "+attrs.getDimension(R.styleable.CuteButton_textSize, textSize));
		Log.e("TAG", "DimensionPixelSize "+attrs.getDimensionPixelSize(R.styleable.CuteButton_textSize, textSize));*/

        textAllCaps = attrs.getBoolean(R.styleable.CuteButton_cb_textAllCaps, textAllCaps);


        iconSize = attrs.getDimensionPixelSize(R.styleable.CuteButton_cb_iconSize, iconSize);
        iconPosition = attrs.getInt(R.styleable.CuteButton_cb_iconPosition, iconPosition);

        drawableResource = attrs.getResourceId(R.styleable.CuteButton_cb_icon, drawableResource);
        iconPadding = attrs.getDimensionPixelSize(R.styleable.CuteButton_cb_iconPadding, iconPadding);

        lGravity = attrs.getInt(R.styleable.CuteButton_cb_gravity, lGravity);
        isEnabled = attrs.getBoolean(R.styleable.CuteButton_cb_enabled, isEnabled);

    }

    private void setupBackground() {


        // Default Drawable
        GradientDrawable defaultDrawable = new GradientDrawable();
        defaultDrawable.setCornerRadius(radius);
        defaultDrawable.setColor(backgroundColor);

        //Focus Drawable
        GradientDrawable focusDrawable = new GradientDrawable();
        focusDrawable.setCornerRadius(radius);
        focusDrawable.setColor(focusColor);

        // Disabled Drawable
        GradientDrawable disabledDrawable = new GradientDrawable();
        disabledDrawable.setCornerRadius(radius);
        disabledDrawable.setColor(disableColor);
        //disabledDrawable.setStroke(mBorderWidth, mDisabledBorderColor);


        if (borderColor != 0 && borderWidth > 0) {
            defaultDrawable.setStroke(borderWidth, borderColor);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            setBackground(getRippleDrawable(defaultDrawable, focusDrawable, disabledDrawable));

        } else {

            StateListDrawable states = new StateListDrawable();

            // Focus/Pressed Drawable
            GradientDrawable drawable2 = new GradientDrawable();
            drawable2.setCornerRadius(radius);
            drawable2.setColor(focusColor);

            if (focusColor != 0) {
                states.addState(new int[]{android.R.attr.state_pressed}, drawable2);
                states.addState(new int[]{android.R.attr.state_focused}, drawable2);
                states.addState(new int[]{-android.R.attr.state_enabled}, disabledDrawable);
            }

            states.addState(new int[]{}, defaultDrawable);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                this.setBackgroundDrawable(states);
            } else {
                this.setBackground(states);
            }
        }


		/*DrawableHelper helper = new DrawableHelper.Builder()
			  .setBackgroundColor(backgroundColor)
			  .setFocusColor(focusColor)
			  .setDisabledColor(disabledColor)
			  .setBorderWidth(borderWidth)
			  .setBorderColor(borderColor)
			  .setRadius((int) radius)
			  .build();

		helper.setBackground(this, isEnabled);*/

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
        //textView.setBackgroundColor(Color.BLUE);
        LayoutParams textViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textViewParams);

		/*if (text == null || text.isEmpty()) {
			text = "";
			return;
		}*/

        textView.setText(text);
        textView.setTextColor(isEnabled ? textColor : disabledTextColor);
        textView.setTextSize(pxToSp(context, textSize));
        textView.setAllCaps(textAllCaps);

        // font style normal, bold, italic
        if (textStyle == 2) {
            textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
        } else if (textStyle == 1) {
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        } else {
            textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
        }

        //textView.setEnabled(isEnabled());

        textView.setGravity(gravity);
    }

	/*public static int spToPx(final Context context, final float sp) {
		return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
	}

	public static int pxToDp(final Context context, final float px) {
		return Math.round(px / context.getResources().getDisplayMetrics().density);
	}

	public static int dpToPx(final Context context, final float dp) {
		return Math.round(dp * context.getResources().getDisplayMetrics().density);
	}*/

    private void setupImageView() {
        imageView = new ImageView(context);

		/*if (drawableResource == 0 && (fontIcon == null || fontIcon.isEmpty())) {
			return;

		}*/

        //imageView = new ImageView(context);

        // add font_awesome icon to imageView

        // add drawable icon to imageview
        if (drawableResource != 0) {
            int width = iconSize;
            int height = iconSize;
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width, height);
            imageView.setLayoutParams(parms);
            imageView.setImageResource(drawableResource);
        }

        if (drawable != null) {

            imageView.setImageDrawable(drawable);
        }


        updateIconPadding();

        // icon padding
		/*LayoutParams imageViewParams;
		if (iconPosition == POSITION_TOP || iconPosition == POSITION_BOTTOM) {
			imageViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		} else {
			imageViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		}*/

		/*if (textView != null && imageView != null) {
			if (iconPosition == POSITION_LEFT) {
				imageViewParams.setMargins(0, 0, getDrawablePadding(), 0);
			} else if (iconPosition == POSITION_TOP) {
				imageViewParams.setMargins(0, 0, 0, getDrawablePadding());
			} else if (iconPosition == POSITION_RIGHT) {
				imageViewParams.setMargins(getDrawablePadding(), 0, 0, 0);
			} else if (iconPosition == POSITION_BOTTOM) {
				imageViewParams.setMargins(0, getDrawablePadding(), 0, 0);
			}
		}*/


        //imageView.setEnabled(isEnabled());

        //imageView.setLayoutParams(imageViewParams);
        //imageView.setBackgroundColor(Color.RED);
    }

    private void updateGravity() {
        if (lGravity == GRAVITY_CENTER) {
            // center
            super.setGravity(Gravity.CENTER);
        } else if (lGravity == GRAVITY_LEFT) {
            // left
            super.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        } else if (lGravity == GRAVITY_RIGHT) {
            // right
            super.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        } else if (lGravity == GRAVITY_TOP) {
            // top
            super.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        } else if (lGravity == GRAVITY_BOTTOM) {
            // bottom
            super.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        }
    }

    private void updateIconPadding() {
        LayoutParams imageViewParams = new LayoutParams(iconSize, iconSize);

        if (iconPosition == POSITION_LEFT) {
            imageViewParams.setMargins(0, 0, getDrawablePadding(), 0);
        } else if (iconPosition == POSITION_TOP) {
            imageViewParams.setMargins(0, 0, 0, getDrawablePadding());
        } else if (iconPosition == POSITION_RIGHT) {
            imageViewParams.setMargins(getDrawablePadding(), 0, 0, 0);
        } else if (iconPosition == POSITION_BOTTOM) {
            imageViewParams.setMargins(0, getDrawablePadding(), 0, 0);
        }


        imageView.setLayoutParams(imageViewParams);
    }

    private int getDrawablePadding() {
        if (iconPadding != 0) {
            return iconPadding;
        }
        int fixedIconPadding = 5;
        return fixedIconPadding;
    }


    public boolean getAllCaps() {
        return textAllCaps;
    }

    public void setAllCaps(boolean allCaps) {
        this.textAllCaps = allCaps;
        textView.setAllCaps(allCaps);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        if (textView != null) textView.setText(text);
        else setupTextView();
    }

    public int getTextStyle() {
        return textStyle;
    }

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

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(int size) {
        this.textSize = size;
        textView.setTextSize(size);
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int color) {
        this.textColor = color;
        textView.setTextColor(isEnabled ? textColor : disabledTextColor);
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int size) {
        this.borderWidth = size;
        setupBackground();
    }

    public int getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(int color) {
        this.borderColor = color;
        setupBackground();
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float size) {
        this.radius = size;
        setupBackground();
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(@ColorInt int color) {
        this.backgroundColor = color;
        setupBackground();
    }

    public int getFocusColor() {
        return this.focusColor;
    }

    public void setFocusColor(@ColorInt int color) {
        this.focusColor = color;
        setupBackground();
    }

    public int getDisableColor() {
        return disableColor;
    }

    public void setDisableColor(@ColorInt int color) {
        this.disableColor = color;
        setupBackground();
    }

    @Override
    public void setEnabled(boolean enabled) {
        //super.setEnabled(enabled);
        this.isEnabled = enabled;
        initializeView();
    }

    public int getDisabledTextColor() {
        return disabledTextColor;
    }

    public void setDisabledTextColor(int color) {
        this.disabledTextColor = color;
        initializeView();
    }

    public int getDisabledColor() {
        return disableColor;
    }

    public void setDisabledColor(int color) {
        this.disableColor = color;
        setupBackground();
    }


    public int getIconSize() {
        return iconSize;
    }

    public void setIconPosition(int position) {
        this.iconPosition = position;
        initializeView();
    }

	/*public int getIconPosition() {
		return iconPosition;
	}*/

    public void setDrawableResource(@DrawableRes int resource) {
        this.drawableResource = resource;
        //initializeView();
        imageView.setImageResource(resource);
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        initializeView();
    }

    public int getIconPadding() {
        return this.iconPadding;
    }

    public void setIconPadding(int padding) {
        this.iconPadding = padding;
        //updateIconPadding();
        initializeView();
    }

    public void setTextGravity(int gravity) {
        this.lGravity = gravity;
        initializeView();
        //if (textView != null)
        //updateGravity();
        //updateIconPadding();
		/*else
			initializeView();*/
    }


}
