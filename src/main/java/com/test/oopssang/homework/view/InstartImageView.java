package com.test.oopssang.homework.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.WindowManager;

/**
 * Created by sang on 2017-04-18.
 * Viewer에 사용될 ImageView
 * onMeasure() 를 통해 width size에 비례하도록 height size 조정
 */

public class InstartImageView extends android.support.v7.widget.AppCompatImageView {

    public int mWidth = 0;
    public int mHeight = 0;
    public int mPosition = 0;

    public InstartImageView(Context context) {
        super(context);
    }

    public InstartImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InstartImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int heightC = 0;

        width = MeasureSpec.getSize(widthMeasureSpec);

        if (width == 0) {
            width = ((WindowManager)this.getRootView().getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        }

        heightC = width * mHeight / mWidth;

        setMeasuredDimension(width, heightC);
    }
}
