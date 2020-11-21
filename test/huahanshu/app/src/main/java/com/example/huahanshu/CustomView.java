package com.example.huahanshu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    String strFunction;
    float a,b=1;
    float m,n=0;
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public String getStrFunction() {
        return strFunction;
    }

    public void setStrFunction(String strFunction) {
        this.strFunction = strFunction;
    }

    void drawSomething(){
        Bitmap bufferBm = Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
        Canvas bufferCanvas = new Canvas(bufferBm);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect=new Rect();
        rect.left=0;
        rect.top=0;
        rect.right = getWidth();
        rect.bottom=getHeight();
        canvas.scale(a, b, getWidth()/2, getHeight()/2);
        canvas.translate(m, n);
        Axis axis=new Axis(rect);
        axis.draw(canvas);
        if(strFunction==null || strFunction.length()==0)
            return;
        Plot plot=new Plot(axis,strFunction,"x");
        plot.draw(canvas);
    }
    protected void big(){
        a = a +0.5f;
        b = b +0.5f;
    }
    protected void small(){
        a = a -0.5f;
        b = b -0.5f;
    }
    protected void original(){
        a = 2.0f;
        b = 2.0f;
    }
    protected void up(){
        n=n+100;
    }
    protected void down(){
        n=n-100;
    }
    protected void right(){
        m=m-100;
    }
    protected void left(){
        m=m+100;
    }
}
