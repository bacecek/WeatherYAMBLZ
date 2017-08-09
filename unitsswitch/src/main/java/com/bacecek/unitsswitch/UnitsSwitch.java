package com.bacecek.unitsswitch;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

public class UnitsSwitch extends FrameLayout {
    private TextView txtLeft;
    private TextView txtRight;

    public UnitsSwitch(@NonNull Context context) {
        this(context, null);
    }

    public UnitsSwitch(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnitsSwitch(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_switch, this, true);
        TextView txtTitle = findViewById(R.id.txt_title);
        txtLeft = findViewById(R.id.txt_left);
        txtRight = findViewById(R.id.txt_right);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UnitsSwitch);

        String title = a.getString(R.styleable.UnitsSwitch_textTitle);
        txtTitle.setText(title != null ? title : context.getString(R.string.default_title));
        String leftValue = a.getString(R.styleable.UnitsSwitch_textLeft);
        txtLeft.setText(leftValue != null ? leftValue : context.getString(R.string.default_left_value));
        String rightValue = a.getString(R.styleable.UnitsSwitch_textRight);
        txtRight.setText(rightValue != null ? rightValue : context.getString(R.string.default_right_value));

        a.recycle();

        txtLeft.setEnabled(true);
        txtRight.setEnabled(false);
    }

    public void setActiveLeft() {
        txtLeft.setEnabled(true);
        txtRight.setEnabled(false);
    }

    public void setActiveRight() {
        txtLeft.setEnabled(false);
        txtRight.setEnabled(true);
    }

    public boolean isLeftActive() {
        return txtLeft.isEnabled();
    }

    public boolean isRightActive() {
        return txtRight.isEnabled();
    }
}
