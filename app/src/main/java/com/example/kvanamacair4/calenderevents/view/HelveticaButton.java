package com.example.kvanamacair4.calenderevents.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class HelveticaButton extends AppCompatButton {
    public HelveticaButton(Context context) {
        super(context);
        setFaceType();
    }

    public HelveticaButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFaceType();
    }

    public HelveticaButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFaceType();
    }
    private void setFaceType() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "HelveticaNeue-BoldExt.otf");
        setTypeface(typeface);
    }
}
