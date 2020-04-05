package com.example.mapwithmarker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatSeekBar;

public class MySeekbar extends AppCompatSeekBar {
    public MySeekbar (Context context) {
        super(context);
    }

    public MySeekbar (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MySeekbar (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
//        int thumb_x = (int) (( (double)this.getProgress()/this.getMax() ) * (double)this.getWidth());
        int thumb_x =  (this.getWidth())/2;

        float middle = (float) (this.getHeight()-200);

        Paint text = new Paint();
        text.setColor(Color.WHITE);
        text.setTextSize(50);
        c.drawText(""+this.getProgress(), thumb_x, middle, text);
    }
}