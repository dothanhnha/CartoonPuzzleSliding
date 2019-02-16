package com.example.bluepea.cartoonpuzzlesliding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<LevelGame> listLevel;
    private Intent toViewImageAc;
    private Button buttonPlay;
    private ImageButton arrowLeft;
    private ImageButton arrowRight;
    private int currentIndexLevel ;
    private TextView textLevelGame;
    private void init(){

        toViewImageAc = new Intent(MainActivity.this,ViewImageActivity.class);
        listLevel = new ArrayList<>();
        listLevel.add(new LevelGame("Easy (3x3)",3,3,45));
        listLevel.add(new LevelGame("Normal (5x3)",3,5,180));
        listLevel.add(new LevelGame("Hard (5x5)",5,5,300));
        currentIndexLevel =0;
        textLevelGame.setText(listLevel.get(0).toString());
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toViewImageAc.putExtra("levelGame",(LevelGame)listLevel.get(currentIndexLevel));
                startActivity(toViewImageAc);
            }
        });
        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndexLevel<listLevel.size()-1)
                    textLevelGame.setText(listLevel.get(++currentIndexLevel).toString());
            }
        });

        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndexLevel>0)
                    textLevelGame.setText(listLevel.get(--currentIndexLevel).toString());
            }
        });
    }
    private void reference(){
        arrowLeft = findViewById(R.id.arrowLeft);
        arrowRight = findViewById(R.id.arrowRight);
        textLevelGame = findViewById(R.id.textLevelGame);
        buttonPlay = findViewById(R.id.buttonPlay);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference();
        init();
    }
}
