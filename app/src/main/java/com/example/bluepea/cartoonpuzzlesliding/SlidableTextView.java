package com.example.bluepea.cartoonpuzzlesliding;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.ArrayList;


public class SlidableTextView extends android.support.v7.widget.AppCompatTextView{
    private float beforeX;
    private float beforeY;
    private int currentIndexLevel =0;
    private ArrayList<LevelGame> listLevel;

    public SlidableTextView(Context context) {
        super(context);

    }

    public SlidableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ;
    }

    public SlidableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public boolean isContainCoorY(float y){
        if(y<0 || y>this.getHeight())
            return false;
        else
            return true;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.beforeX = event.getX();
                this.beforeY = event.getY();
                return true;
            case MotionEvent.ACTION_UP:
                if((this.beforeX -event.getX()> 50) && isContainCoorY(event.getY()) )
                    updateNextView();
                else if ((this.beforeX -  event.getX()< -50) && isContainCoorY(event.getY()))
                    updatePreviousView();
                return true;
        }
        return false;
    }

    private void updateNextView() {
        if(currentIndexLevel ==(listLevel.size()-1))
            return;
        else
            this.setText(listLevel.get(++currentIndexLevel).toString());
    }

    private void updatePreviousView() {
        if(currentIndexLevel ==0)
            return;
        else
            this.setText(listLevel.get(--currentIndexLevel).toString());
    }



    public void setListLevel(ArrayList<LevelGame> listLevel) {
        this.listLevel = listLevel;
    }
    public int getCurrentIndexLevel(){
        return currentIndexLevel;
    }
}
