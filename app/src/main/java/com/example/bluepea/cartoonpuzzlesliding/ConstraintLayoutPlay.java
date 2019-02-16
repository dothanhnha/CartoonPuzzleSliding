package com.example.bluepea.cartoonpuzzlesliding;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;

public class ConstraintLayoutPlay {
    private final static float padding=0.05f;
    private final static int paddingBorder=2;
    private ConstraintLayout layout;
    private int currentIndexChildView;
    private int row;
    private int col;
    private float percentWidthOnePiece;
    private float percentHeightOnePiece;
    private ArrayList<Guideline> listVerticalGuildLine;
    private ArrayList<Guideline> listHorizontalGuildLine;
    private ArrayList<ArrayList<PieceOfImage>> matrixPieceOfImage;
    private ArrayList<ImageView> listBorder;
    private Context context;
    private int widthBorder;
    private IndexMatrix indexEmpty = new IndexMatrix(0,0);


    public IndexMatrix getIndexEmpty() {
        return indexEmpty;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public ArrayList<Guideline> getListVerticalGuildLine() {
        return listVerticalGuildLine;
    }

    public void setListVerticalGuildLine(ArrayList<Guideline> listVerticalGuildLine) {
        this.listVerticalGuildLine = listVerticalGuildLine;
    }

    public ArrayList<Guideline> getListHorizontalGuildLine() {
        return listHorizontalGuildLine;
    }

    public void setListHorizontalGuildLine(ArrayList<Guideline> listHorizontalGuildLine) {
        this.listHorizontalGuildLine = listHorizontalGuildLine;
    }




    public ConstraintLayoutPlay(Context context, ConstraintLayout layout, LevelGame level){
        this.listHorizontalGuildLine = new ArrayList<>();
        this.listVerticalGuildLine = new ArrayList<>();
        this.matrixPieceOfImage = new ArrayList<>();
        this.listBorder = new ArrayList<>();
        this.layout=layout;
        this.currentIndexChildView=0;
        this.col=level.getCol();
        this.row = level.getRow()+1;
        this.context =context;
        this.percentWidthOnePiece = (1-2*padding)/(float)col;
        this.percentHeightOnePiece = (1-2*padding)/(float)row;
        this.widthBorder =widthBorder = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, context.getResources().getDisplayMetrics());
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void createBorder(ImageView borderLeft){
        ImageView templine;
        ConstraintLayout.LayoutParams layoutParams;
        ConstraintSet constraintSet;
        templine = new ImageView(this.context);
        templine.setId(View.generateViewId());
        templine.setImageResource(R.drawable.border);
        layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, widthBorder);
        templine.setLayoutParams(layoutParams);
        this.layout.addView(templine);
        constraintSet = new ConstraintSet();
        constraintSet.clone(this.layout);
        constraintSet.connect(templine.getId(),ConstraintSet.LEFT,borderLeft.getId(),ConstraintSet.LEFT, 0);
        constraintSet.connect(templine.getId(),ConstraintSet.BOTTOM,listHorizontalGuildLine.get(0).getId(),ConstraintSet.TOP, 0);
        constraintSet.connect(templine.getId(),ConstraintSet.RIGHT,listVerticalGuildLine.get(1).getId(),ConstraintSet.RIGHT, 0);
        constraintSet.applyTo(layout);
        listBorder.add(templine);

        ImageView topLine = templine;

        templine = new ImageView(this.context);
        templine.setId(View.generateViewId());
        templine.setImageResource(R.drawable.border);
        layoutParams = new ConstraintLayout.LayoutParams(widthBorder, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        templine.setLayoutParams(layoutParams);
        this.layout.addView(templine);
        constraintSet = new ConstraintSet();
        constraintSet.clone(this.layout);
        constraintSet.connect(templine.getId(),ConstraintSet.LEFT,listVerticalGuildLine.get(1).getId(),ConstraintSet.LEFT, 0);
        constraintSet.connect(templine.getId(),ConstraintSet.BOTTOM,listHorizontalGuildLine.get(1).getId(),ConstraintSet.TOP, 0);
        constraintSet.connect(templine.getId(),ConstraintSet.TOP,topLine.getId(),ConstraintSet.TOP, 0);
        constraintSet.applyTo(layout);
        listBorder.add(templine);

        templine = new ImageView(this.context);
        templine.setId(View.generateViewId());
        templine.setImageResource(R.drawable.border);
        layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, widthBorder);
        templine.setLayoutParams(layoutParams);
        this.layout.addView(templine);
        constraintSet = new ConstraintSet();
        constraintSet.clone(this.layout);
        constraintSet.connect(templine.getId(),ConstraintSet.LEFT,listVerticalGuildLine.get(1).getId(),ConstraintSet.LEFT, 0);
        constraintSet.connect(templine.getId(),ConstraintSet.BOTTOM,listHorizontalGuildLine.get(1).getId(),ConstraintSet.TOP, 0);
        constraintSet.connect(templine.getId(),ConstraintSet.RIGHT,listVerticalGuildLine.get(col).getId(),ConstraintSet.RIGHT, 0);
        constraintSet.applyTo(layout);
        listBorder.add(templine);

        ImageView previousLine = templine;

        templine = new ImageView(this.context);
        templine.setId(View.generateViewId());
        templine.setImageResource(R.drawable.border);
        layoutParams = new ConstraintLayout.LayoutParams(widthBorder, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        templine.setLayoutParams(layoutParams);
        this.layout.addView(templine);
        constraintSet = new ConstraintSet();
        constraintSet.clone(this.layout);
        constraintSet.connect(templine.getId(),ConstraintSet.LEFT,listVerticalGuildLine.get(col).getId(),ConstraintSet.LEFT, 0);
        constraintSet.connect(templine.getId(),ConstraintSet.BOTTOM,listHorizontalGuildLine.get(row).getId(),ConstraintSet.BOTTOM, 0);
        constraintSet.connect(templine.getId(),ConstraintSet.TOP,previousLine.getId(),ConstraintSet.TOP, 0);
        constraintSet.applyTo(layout);
        listBorder.add(templine);

        previousLine = templine;

        templine = new ImageView(this.context);
        templine.setId(View.generateViewId());
        templine.setImageResource(R.drawable.border);
        layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, widthBorder);
        templine.setLayoutParams(layoutParams);
        this.layout.addView(templine);
        constraintSet = new ConstraintSet();
        constraintSet.clone(this.layout);
        constraintSet.connect(templine.getId(),ConstraintSet.LEFT,borderLeft.getId(),ConstraintSet.LEFT, 0);
        constraintSet.connect(templine.getId(),ConstraintSet.RIGHT,previousLine.getId(),ConstraintSet.RIGHT, 0);
        constraintSet.connect(templine.getId(),ConstraintSet.TOP,listHorizontalGuildLine.get(row).getId(),ConstraintSet.TOP, 0);
        constraintSet.applyTo(layout);
        listBorder.add(templine);


    }

    public void invisibleBorder(ImageView leftBorder){
        for(int i=0;i<=4;i++){
            listBorder.get(i).setVisibility(View.INVISIBLE);
        }
        leftBorder.setVisibility(View.INVISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void createVerticalGuideLine(){
        Guideline tempLine;
        ConstraintLayout.LayoutParams layoutParams;
        for(int i=0;i<=col;i++){
            tempLine = new Guideline(this.context);
            layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.guidePercent = padding+i*percentWidthOnePiece;

            layoutParams.orientation = LinearLayout.VERTICAL;
            tempLine.setLayoutParams(layoutParams);
            tempLine.setId(View.generateViewId());
            this.layout.addView(tempLine);
            listVerticalGuildLine.add(tempLine);
        }



    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void createHorizontalGuideLine(){
        Guideline tempLine;
        ConstraintLayout.LayoutParams layoutParams;
        for(int i=0;i<=row;i++){
            tempLine = new Guideline(this.context);

            layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.guidePercent = padding+i*percentHeightOnePiece;
            layoutParams.orientation = LinearLayout.HORIZONTAL;
            tempLine.setLayoutParams(layoutParams);
            tempLine.setId(View.generateViewId());
            this.layout.addView(tempLine);
            listHorizontalGuildLine.add(tempLine);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void initMatrixPieceImage(ImageView image){
        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap source = drawable.getBitmap();
        int eachWidth = source.getWidth()/col;
        int eachHeight = source.getHeight()/(row-1);

        Bitmap tempBitmap;
        PieceOfImage tempPiece;
        for(int i=0;i<row;i++){
            matrixPieceOfImage.add(new ArrayList<PieceOfImage>());
            for(int j=0;j<col;j++){

                tempPiece = new PieceOfImage(this.context);
                tempPiece.setScaleType(ImageView.ScaleType.FIT_XY);
                if (i == 0)
                    tempPiece.setImageResource(R.drawable.empty);
                else{
                    tempBitmap = Bitmap.createBitmap(source,j*eachWidth,(i-1)*eachHeight,eachWidth,eachHeight);
                    tempPiece.setImageBitmap(tempBitmap);
                }
                tempPiece.setId(View.generateViewId());
                tempPiece.setCorrectIndex(new IndexMatrix(i,j));
                matrixPieceOfImage.get(i).add(tempPiece);
            }
        }

        ShuffleMatrixPiece.shuffle(matrixPieceOfImage,this.col,this.row,this.col*this.row*2,indexEmpty);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void createLayoutMatrixPieceOfImage(){
        PieceOfImage tempPiece;
        ConstraintLayout.LayoutParams layoutParams ;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                tempPiece = matrixPieceOfImage.get(i).get(j);
                tempPiece.setCurrentIndex(new IndexMatrix(i,j));
                tempPiece.setPreviousIndex(tempPiece.getCurrentIndex());
                layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
                tempPiece.setLayoutParams(layoutParams);
                this.layout.addView(tempPiece);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(this.layout);

                constraintSet.connect(tempPiece.getId(),ConstraintSet.LEFT,listVerticalGuildLine.get(j).getId(),ConstraintSet.RIGHT, 1);
                constraintSet.connect(tempPiece.getId(),ConstraintSet.RIGHT,listVerticalGuildLine.get(j+1).getId(),ConstraintSet.LEFT, 1);

                constraintSet.connect(tempPiece.getId(),ConstraintSet.TOP,listHorizontalGuildLine.get(i).getId(),ConstraintSet.BOTTOM, 1);
                constraintSet.connect(tempPiece.getId(),ConstraintSet.BOTTOM,listHorizontalGuildLine.get(i+1).getId(),ConstraintSet.TOP, 1);
                constraintSet.applyTo(layout);
                matrixPieceOfImage.get(i).add(tempPiece);

            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void initToPlay(ImageView image, ImageView leftBorder){
        createVerticalGuideLine();
        createHorizontalGuideLine();
        initMatrixPieceImage(image);
        createLayoutMatrixPieceOfImage();
        createBorder(leftBorder);
    }

    public ArrayList<ArrayList<PieceOfImage>> getMatrixPieceOfImage() {
        return matrixPieceOfImage;
    }
}
