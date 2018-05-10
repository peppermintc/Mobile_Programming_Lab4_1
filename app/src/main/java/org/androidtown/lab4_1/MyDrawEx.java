package org.androidtown.lab4_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MyDrawEx extends View{

    //Vertex class
    public class Vertex {
        float x;
        float y;
        boolean draw;

        public Vertex(float x, float y, boolean draw){
            this.x=x;
            this.y=y;
            this.draw=draw;
        }
    }

    //values & Vertex array
    private float x = 0.0f;
    private float y = 0.0f;
    ArrayList<Vertex> arrVertex = new ArrayList<Vertex>();

    //Constructor
    public MyDrawEx(Context c) {
        super(c);
    }
    public MyDrawEx(Context c, AttributeSet a) {
        super(c,a);
    }

    //When motion happens x,y value will be stored in array of vertex.
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                arrVertex.add(new Vertex(event.getX(), event.getY(), false));
                break;
            case MotionEvent.ACTION_MOVE :
                arrVertex.add(new Vertex(event.getX(), event.getY(), true));
        }

        invalidate();

        return true;
    }

    //draw method which performs drawing lines and points.
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);

        for(int i=0; i<arrVertex.size(); i++) {
            if(arrVertex.get(i).draw){
                canvas.drawLine(arrVertex.get(i-1).x,arrVertex.get(i-1).y,arrVertex.get(i).x,arrVertex.get(i).y,paint);
            }
            else {
                canvas.drawPoint(arrVertex.get(i).x,arrVertex.get(i).y,paint);
            }
        }
    }
}
