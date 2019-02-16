package com.example.bluepea.cartoonpuzzlesliding;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.util.ArrayList;

public class PieceOfImage extends android.support.v7.widget.AppCompatImageView {
    public final static int LEFT =1;
    public final static int RIGHT =-1;
    public final static int UP =2;
    public final static int DOWN =-2;
    private IndexMatrix correctIndex;
    private PieceOfImage nextPiece;
    private int timeDuration = 300;
    private IndexMatrix currentIndex;
    private IndexMatrix previousIndex;
    private boolean isAnimationEnd=true;

    public boolean isAnimationEnd() {
        return isAnimationEnd;
    }

    public PieceOfImage getNextPiece() {
        return nextPiece;
    }

    public void setNextPiece(PieceOfImage nextPiece) {
        this.nextPiece = nextPiece;
    }

    public IndexMatrix getPreviousIndex() {
        return previousIndex;
    }

    public void setPreviousIndex(IndexMatrix previousIndex) {
        this.previousIndex = previousIndex;
    }

    public IndexMatrix getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(IndexMatrix currentIndex) {
        this.currentIndex = currentIndex;
    }



    public IndexMatrix getCorrectIndex() {
        return correctIndex;
    }

    public void setCorrectIndex(IndexMatrix correctIndex) {
        this.correctIndex = correctIndex;
    }

    public PieceOfImage(Context context) {
        super(context);
    }

    public PieceOfImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieceOfImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void doGo(int direction, final ArrayList<ArrayList<PieceOfImage>> matrix, final IndexMatrix indexEmpty){
        final IndexMatrix previousEmpty =new IndexMatrix(indexEmpty.iRow,indexEmpty.iCol) ;
        TranslateAnimation animation;
        Animation.AnimationListener listener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimationEnd=false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                PieceOfImage emptyPiece = matrix.get(previousEmpty.iRow).get(previousEmpty.iCol);
                emptyPiece.setImageBitmap(((BitmapDrawable)PieceOfImage.this.getDrawable()).getBitmap());
                emptyPiece.setCorrectIndex(PieceOfImage.this.getCorrectIndex());
                PieceOfImage.this.setImageResource(R.drawable.empty);
                isAnimationEnd=true;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        switch (direction){
            case LEFT:
                animation = new TranslateAnimation(0,0-this.getWidth(),
                        0,0);
                animation.setAnimationListener(listener);
                animation.setDuration(timeDuration);
                this.startAnimation(animation);
                indexEmpty.iCol++;
                break;
            case RIGHT:
                animation = new TranslateAnimation(0,0+this.getWidth(),
                        0,0);
                animation.setAnimationListener(listener);
                animation.setDuration(timeDuration);
                this.startAnimation(animation);
                indexEmpty.iCol--;
                break;
            case UP:
                animation = new TranslateAnimation(0,0,
                        0,0-this.getHeight());
                animation.setAnimationListener(listener);
                animation.setDuration(timeDuration);
                this.startAnimation(animation);
                indexEmpty.iRow++;
                break;
            case DOWN:
                animation = new TranslateAnimation(0,0,
                        0,0+this.getHeight());
                animation.setAnimationListener(listener);
                animation.setDuration(timeDuration);
                this.startAnimation(animation);
                indexEmpty.iRow--;
                break;
        }
    }


    public void doShuffle(int direction, final ArrayList<ArrayList<PieceOfImage>> matrix, final IndexMatrix indexEmpty){
        PieceOfImage emptyPiece = matrix.get(indexEmpty.iRow).get(indexEmpty.iCol);
        emptyPiece.setImageBitmap(((BitmapDrawable)PieceOfImage.this.getDrawable()).getBitmap());
        emptyPiece.setCorrectIndex(PieceOfImage.this.getCorrectIndex());
        PieceOfImage.this.setImageResource(R.drawable.empty);
        switch (direction){
            case LEFT:
                indexEmpty.iCol++;
                break;
            case RIGHT:
                indexEmpty.iCol--;
                break;
            case UP:
                indexEmpty.iRow++;
                break;
            case DOWN:
                indexEmpty.iRow--;
                break;
        }

    }

    public void goUp(){
        TranslateAnimation animation = new TranslateAnimation(0,0,
                0,0-this.getHeight());

        animation.setDuration(timeDuration);
        this.startAnimation(animation);

    }
    public void goDown(){
        TranslateAnimation animation = new TranslateAnimation(0,0,
                0,0+this.getHeight());
        animation.setDuration(timeDuration);
        this.startAnimation(animation);
    }
    public void goLeft(){
        TranslateAnimation animation = new TranslateAnimation(0,0-this.getWidth(),
                0,0);

        animation.setDuration(timeDuration);
        this.startAnimation(animation);
    }

    public void goRight(){

        TranslateAnimation animation = new TranslateAnimation(0,0+this.getWidth(),
                0,0);

        animation.setDuration(timeDuration);

        this.startAnimation(animation);

    }





}
