package com.example.bluepea.cartoonpuzzlesliding;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class GamePlayActivity extends AppCompatActivity {
    private LevelGame levelGame;
    private ConstraintLayout layout;
    private ConstraintLayoutPlay layoutPlay;
    private Intent backViewImageAc;
    private ImageButton homeButton;
    private ImageButton refreshButton;
    private ProgressBar progressBar;
    private Poisition previousDown = new Poisition() ;
    private MangerSlidingLayout sliding;
    private CountDownTimming timing;
    private ImageView imagePlay;
    private ImageView leftBorder;
    private Guideline bottomButton;
    private float deltaXTouch;
    private float deltaYTouch;
    private float distanceAction =30;
    private boolean isActive= false;
    private boolean isWin =false;

    private void reference() {
        layout = findViewById(R.id.constraintLayout);
        homeButton = findViewById(R.id.homeButton);
        refreshButton = findViewById(R.id.refreshButton);
        progressBar = findViewById(R.id.progressBar);
        leftBorder= findViewById(R.id.leftBorder);
        bottomButton = findViewById(R.id.bottomButton);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void init(){

        levelGame = (LevelGame) getIntent().getSerializableExtra("levelGame");
        backViewImageAc = new Intent(GamePlayActivity.this,ViewImageActivity.class).putExtra("levelGame",levelGame);imagePlay = new ImageView(GamePlayActivity.this);
        imagePlay.setImageURI(Uri.parse(getIntent().getStringExtra("pathImage")));
        layoutPlay = new ConstraintLayoutPlay(this, this.layout, levelGame);
        layoutPlay.initToPlay(imagePlay,leftBorder);
        sliding = new MangerSlidingLayout(layoutPlay.getMatrixPieceOfImage(),layoutPlay.getIndexEmpty(), levelGame,timing);
        timing = new CountDownTimming(progressBar,levelGame,sliding,layoutPlay,imagePlay);
        timing.start();
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        reference();
        init();


        homeButton.bringToFront();
        refreshButton.bringToFront();
        progressBar.bringToFront();
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GamePlayActivity.this,MainActivity.class));
                GamePlayActivity.this.finish();

            }
        });
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GamePlayActivity.this,ViewImageActivity.class).putExtra("levelGame",levelGame));
                GamePlayActivity.this.finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(timing.isFinish())
            return super.onTouchEvent(event);
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                isActive=false;
                previousDown.x= event.getX();
                previousDown.y= event.getY();
                return true;
            case (MotionEvent.ACTION_MOVE):
                if(isActive)
                    return true;
                deltaXTouch =event.getX()-previousDown.x;
                deltaYTouch =event.getY()-previousDown.y;
                if(Math.abs(deltaXTouch) > Math.abs(deltaYTouch)){//left-right
                    if(deltaXTouch >distanceAction){//right
                        sliding.doSlide(MangerSlidingLayout.RIGHT);
                        isActive=true;
                    }
                    if(deltaXTouch <-distanceAction){//left
                        sliding.doSlide(MangerSlidingLayout.LEFT);
                        isActive=true;
                    }
                }
                else{//up-down
                    if(deltaYTouch >distanceAction){//down
                        sliding.doSlide(MangerSlidingLayout.DOWN);
                        isActive=true;
                    }

                    if(deltaYTouch <-distanceAction){//up
                        sliding.doSlide(MangerSlidingLayout.UP);
                        isActive=true;
                    }

                }
                return true;
            case (MotionEvent.ACTION_UP):

                return true;
            case (MotionEvent.ACTION_CANCEL):
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                return true;
            default:
                return super.onTouchEvent(event);

        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        startActivity(backViewImageAc);
        this.finish();
    }
}
