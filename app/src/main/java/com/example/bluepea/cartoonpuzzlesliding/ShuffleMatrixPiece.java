package com.example.bluepea.cartoonpuzzlesliding;

import android.drm.DrmStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

public class ShuffleMatrixPiece {
    private static ArrayList<Integer> listSliding;
    public final static int LEFT =1;
    public final static int RIGHT =-1;
    public final static int UP =2;
    public final static int DOWN =-2;
    private static int _col;
    private static int _row;
    public static IndexMatrix _indexEmpty;
    private static ArrayList<ArrayList<PieceOfImage>> _matrix;

    public static void shuffle(ArrayList<ArrayList<PieceOfImage>> matrix,int col, int row, int countStep, IndexMatrix indexEmpty){
        _col=col;
        _row=row;
        _matrix=matrix;
        _indexEmpty = indexEmpty;
        initListSliding();

        shuffle(UP,_matrix,_row,_col);
        int previousDirection=UP;
        for(int i=0;i<countStep;i++){
            Collections.shuffle(listSliding);
            for(int j=0;j<3;j++){
                if(isPosibleTo(listSliding.get(j)) && listSliding.get(j)!= -previousDirection){
                    shuffle(listSliding.get(j),_matrix,_row,_col);
                    previousDirection = listSliding.get(j);
                    break;
                }
            }
        }
    }

    public static void initListSliding(){
        listSliding= new ArrayList<>();
        listSliding.add(LEFT);
        listSliding.add(RIGHT);
        listSliding.add(UP);
        listSliding.add(DOWN);
    }
    public static boolean isPosibleTo(int action){
        switch (action){
            case UP:
                if(_indexEmpty.iRow==(_row-1))
                    return false;
                return true;
            case DOWN:
                if(_indexEmpty.iRow==1 && _indexEmpty.iCol!=0 || _indexEmpty.iRow==0)
                    return false;
                return true;
            case LEFT:
                if(_indexEmpty.iCol==(_col-1) || _indexEmpty.iRow==0)
                    return false;
                return true;
            case RIGHT:
                if(_indexEmpty.iCol==0 || _indexEmpty.iRow==0)
                    return false;
                return true;
            default:
                return false;
        }
    }
    public static void shuffle(int direction, ArrayList<ArrayList<PieceOfImage>> matrix,int row, int col){
        PieceOfImage movingPiece=null;
        switch (direction){
            case UP:
                movingPiece = _matrix.get(_indexEmpty.iRow+1).get(_indexEmpty.iCol);
                break;
            case DOWN:
                movingPiece = _matrix.get(_indexEmpty.iRow-1).get(_indexEmpty.iCol);
                break;
            case LEFT:
                movingPiece = _matrix.get(_indexEmpty.iRow).get(_indexEmpty.iCol+1);
                break;
            case RIGHT:
                movingPiece = _matrix.get(_indexEmpty.iRow).get(_indexEmpty.iCol-1);
                break;
    }
    movingPiece.doShuffle(direction, matrix,_indexEmpty);
    }





}
