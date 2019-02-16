package com.example.bluepea.cartoonpuzzlesliding;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class CountDownTimming {
    private static final int STATE_WIN=1;
    private static final int STATE_TIMEOUT=2;
    private ProgressBar progressBar;
    private int durationSecond;
    private int currentTime;
    private boolean isFinish;
    private ImageView imagePlay;
    private CountDownTimer countDown;
    private MangerSlidingLayout sliding;
    private ConstraintLayoutPlay layoutPlay;
    public CountDownTimming(ProgressBar progressBar, LevelGame level, MangerSlidingLayout sliding,ConstraintLayoutPlay layoutPlay, ImageView imagePlay){
        this.progressBar=progressBar;
        this.imagePlay =imagePlay;
        this.durationSecond=level.getDurationSecond();
        this.sliding=sliding;
        this.layoutPlay =layoutPlay;
        this.isFinish =false;
    }
    public void start(){
        final int milisecond = durationSecond*1000;
        this.countDown = new CountDownTimer(milisecond, 1000) {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(Math.round((millisUntilFinished/(float)milisecond)*100));
                if(sliding.getMovingPiece()!=null && sliding.getResult().isWin()){
                    if(sliding.getMovingPiece().isAnimationEnd()){
                        state(STATE_WIN);
                        isFinish=true;
                        this.cancel();
                    }


                }
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            public void onFinish() {
                state(STATE_TIMEOUT);
                progressBar.setProgress(0);
                isFinish=true;
            }
        }.start();
    }
    public void stop(){
        this.countDown.cancel();
    }

    public void restart(){
        this.isFinish=false;
        this.currentTime=this.durationSecond;
        this.progressBar.setProgress(100);
        start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)

    private void state(int state){
        Context context = progressBar.getContext();

        ConstraintLayout layout = (ConstraintLayout) ((Activity) context).findViewById(R.id.constraintLayout);
        ImageView leftBorder = (ImageView) ((Activity) context).findViewById(R.id.leftBorder);
        Guideline bottomButton = (Guideline) ((Activity) context).findViewById(R.id.bottomButton);
        progressBar.setVisibility(View.INVISIBLE);
        ImageView imagePlay = new ImageView(context);
        layoutPlay.invisibleBorder(leftBorder);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        imagePlay.setLayoutParams(layoutParams);
        imagePlay.setId(View.generateViewId());
        imagePlay.setScaleType(ImageView.ScaleType.FIT_XY);
        imagePlay.setImageBitmap(((BitmapDrawable)this.imagePlay.getDrawable()).getBitmap());
        layout.addView(imagePlay);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        constraintSet.connect(imagePlay.getId(),ConstraintSet.LEFT,layoutPlay.getListVerticalGuildLine().get(0).getId(),ConstraintSet.LEFT, 0);
        constraintSet.connect(imagePlay.getId(),ConstraintSet.RIGHT,layoutPlay.getListVerticalGuildLine().get(layoutPlay.getCol()).getId(),ConstraintSet.RIGHT, 0);
        constraintSet.connect(imagePlay.getId(),ConstraintSet.TOP,layoutPlay.getListHorizontalGuildLine().get(1).getId(),ConstraintSet.TOP, 0);
        constraintSet.connect(imagePlay.getId(),ConstraintSet.BOTTOM,layoutPlay.getListHorizontalGuildLine().get(layoutPlay.getRow()).getId(),ConstraintSet.BOTTOM, 0);
        constraintSet.applyTo(layout);
        ImageView imageState = new ImageView(context);
        layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        imageState.setLayoutParams(layoutParams);
        imageState.setId(View.generateViewId());
        layout.addView(imageState);
        constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        constraintSet.connect(imageState.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT, 0);
        constraintSet.connect(imageState.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT, 0);
        constraintSet.connect(imageState.getId(),ConstraintSet.TOP,bottomButton.getId(),ConstraintSet.TOP, 0);
        constraintSet.connect(imageState.getId(),ConstraintSet.BOTTOM,layoutPlay.getListHorizontalGuildLine().get(1).getId(),ConstraintSet.BOTTOM, 0);
        constraintSet.applyTo(layout);
        switch (state){
            case STATE_WIN:
                imageState.setImageResource(R.drawable.win);
                break;
            case STATE_TIMEOUT:
                imageState.setImageResource(R.drawable.timeout);
                layoutPlay.getMatrixPieceOfImage().get(0).get(0).setVisibility(View.INVISIBLE);
                break;
        }
    }

    public boolean isFinish() {
        return isFinish;
    }
}
