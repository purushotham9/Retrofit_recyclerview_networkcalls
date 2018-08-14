package com.example.kvanamacair4.calenderevents.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class HelveticaEditTextView extends AppCompatEditText {
    public HelveticaEditTextView(Context context) {
        super(context);
        setFaceType();
    }

    public HelveticaEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFaceType();
    }

    public HelveticaEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFaceType();
    }

    private void setFaceType() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "HelveticaNeue-LightExt.otf");
        setTypeface(typeface);
    }

}
