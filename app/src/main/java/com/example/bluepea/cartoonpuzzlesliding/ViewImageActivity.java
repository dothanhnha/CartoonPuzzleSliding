package com.example.bluepea.cartoonpuzzlesliding;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewImageActivity extends AppCompatActivity {
    private int timeViewSecond = 5;
    private int nubmerOfImage = 5;///0 - > nunberOfImage-1 name: "image_play" + index
    private TextView textView;
    private Intent toGamePlay;
    private String pathImage;
    private ImageView imagePlay;
    private CountDownTimer countDown;
    public void init(){
        this.textView.setText(String.valueOf(timeViewSecond));
        int randomIndexImage = Math.round((float)Math.random() * (this.nubmerOfImage-1));
        this.pathImage = "android.resource://com.example.bluepea.cartoonpuzzlesliding/drawable/image_play"+randomIndexImage;
        imagePlay.setImageURI(Uri.parse(this.pathImage));
        this.toGamePlay = new Intent(ViewImageActivity.this,GamePlayActivity.class);
        this.toGamePlay.putExtra("pathImage",this.pathImage);
        this.toGamePlay.putExtra("levelGame",(LevelGame)getIntent().getSerializableExtra("levelGame"));
        this.countDown = new CountDownTimer((timeViewSecond+1)*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(String.valueOf(Math.round(millisUntilFinished/1000)));
            }

            @Override
            public void onFinish() {
                startActivity(toGamePlay);
                ViewImageActivity.this.finish();
            }
        };

    }
    public void reference(){
        textView= findViewById(R.id.textTimeCountDown);
        imagePlay = findViewById(R.id.imagePlay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        reference();
        init();


    }

    @Override
    protected void onResume() {
        super.onResume();
        countDown.start();
    }

    @Override
    public void finish() {
        super.finish();
        countDown.cancel();
    }
}
