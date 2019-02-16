package com.example.bluepea.cartoonpuzzlesliding;

import android.graphics.drawable.BitmapDrawable;
import android.os.CountDownTimer;
import android.util.Log;

import java.util.ArrayList;

public class MangerSlidingLayout {
    public final static int LEFT =1;
    public final static int RIGHT =-1;
    public final static int UP =2;
    public final static int DOWN =-2;
    private int col;
    private int row;
    private IndexMatrix indexEmpty;
    private PieceOfImage movingPiece;
    private ResultManger result;
    private ArrayList<ArrayList<PieceOfImage>> matrixPieceOfImage;

    public MangerSlidingLayout(ArrayList<ArrayList<PieceOfImage>> matrixPieceOfImage, IndexMatrix indexEmpty, LevelGame level, CountDownTimming timing){
        this.matrixPieceOfImage =matrixPieceOfImage;
        this.indexEmpty = indexEmpty;
        this.col=level.getCol();
        this.row=level.getRow()+1;
        this.result = new ResultManger(this.matrixPieceOfImage,level);
    }

    public PieceOfImage getMovingPiece() {
        return movingPiece;
    }

    public void doSlide(int direction){
        movingPiece=null;
        IndexMatrix previousIndex = new IndexMatrix();
        IndexMatrix currentIndex = new IndexMatrix();
        switch (direction){
            case UP:
                if(indexEmpty.iRow==(row-1))
                    break;
                previousIndex.setIndex(indexEmpty.iRow+1,indexEmpty.iCol);
                movingPiece = matrixPieceOfImage.get(indexEmpty.iRow+1).get(indexEmpty.iCol);
                movingPiece.doGo(PieceOfImage.UP,matrixPieceOfImage,indexEmpty);
                currentIndex.setIndex(previousIndex.iRow-1,previousIndex.iCol);
                break;
            case DOWN:
                if(indexEmpty.iRow==1 && indexEmpty.iCol!=0 || indexEmpty.iRow==0)
                    break;
                previousIndex.setIndex(indexEmpty.iRow-1,indexEmpty.iCol);
                movingPiece = matrixPieceOfImage.get(indexEmpty.iRow-1).get(indexEmpty.iCol);
                movingPiece.doGo(PieceOfImage.DOWN,matrixPieceOfImage,indexEmpty);
                currentIndex.setIndex(previousIndex.iRow+1,previousIndex.iCol);
                break;
            case LEFT:
                if(indexEmpty.iCol==(col-1) || indexEmpty.iRow==0)
                    break;
                previousIndex.setIndex(indexEmpty.iRow,indexEmpty.iCol+1);
                movingPiece = matrixPieceOfImage.get(indexEmpty.iRow).get(indexEmpty.iCol+1);
                movingPiece.doGo(PieceOfImage.LEFT,matrixPieceOfImage,indexEmpty);
                currentIndex.setIndex(previousIndex.iRow,previousIndex.iCol-1);
                break;
            case RIGHT:
                if(indexEmpty.iCol==0 || indexEmpty.iRow==0)
                    break;
                previousIndex.setIndex(indexEmpty.iRow,indexEmpty.iCol-1);
                movingPiece = matrixPieceOfImage.get(indexEmpty.iRow).get(indexEmpty.iCol-1);
                movingPiece.doGo(PieceOfImage.RIGHT,matrixPieceOfImage,indexEmpty);
                currentIndex.setIndex(previousIndex.iRow,previousIndex.iCol+1);
                break;
        }
        if(movingPiece!=null)
            this.result.checkMovingPiece(movingPiece,currentIndex,previousIndex);
    }


    public void slideUp(){
        if(indexEmpty.iRow==(row-1))
            return;
        PieceOfImage movingPiece = matrixPieceOfImage.get(indexEmpty.iRow+1).get(indexEmpty.iCol);
        movingPiece.setNextPiece(matrixPieceOfImage.get(indexEmpty.iRow).get(indexEmpty.iCol));
        movingPiece.goUp();
        indexEmpty.iRow++;

    }
    public void slideDown(){
        if(indexEmpty.iRow==1 && indexEmpty.iCol!=0 || indexEmpty.iRow==0)
            return;
        PieceOfImage movingPiece = matrixPieceOfImage.get(indexEmpty.iRow-1).get(indexEmpty.iCol);
        movingPiece.setNextPiece(matrixPieceOfImage.get(indexEmpty.iRow).get(indexEmpty.iCol));
        movingPiece.goDown();
        indexEmpty.iRow--;
    }
    public void slideLeft(){
        if(indexEmpty.iCol==col-1 || indexEmpty.iRow==0)
            return;
        PieceOfImage movingPiece =matrixPieceOfImage.get(indexEmpty.iRow).get(indexEmpty.iCol+1);
        movingPiece.setNextPiece(matrixPieceOfImage.get(indexEmpty.iRow).get(indexEmpty.iCol));
        matrixPieceOfImage.get(indexEmpty.iRow).get(indexEmpty.iCol+1).goLeft();
        indexEmpty.iCol++;
    }
    public void slideRight(){
        if(indexEmpty.iCol==0 || indexEmpty.iRow==0)
            return;
        PieceOfImage movingPiece =matrixPieceOfImage.get(indexEmpty.iRow).get(indexEmpty.iCol-1);
        movingPiece.setNextPiece(matrixPieceOfImage.get(indexEmpty.iRow).get(indexEmpty.iCol));
        movingPiece.goRight();
        indexEmpty.iCol--;
    }

    public ResultManger getResult() {
        return result;
    }
}
