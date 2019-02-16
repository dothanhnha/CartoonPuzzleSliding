package com.example.bluepea.cartoonpuzzlesliding;

import android.support.annotation.NonNull;

public class IndexMatrix{
    public int iRow;
    public int iCol;
    public IndexMatrix(){
    }
    public IndexMatrix(int iRow, int iCol){
        this.iRow=iRow;
        this.iCol=iCol;
    }
    public boolean isEqual(IndexMatrix index){
        if(index.iCol != this.iCol || index.iRow != this.iRow)
            return false;
        return true;
    }
    public void setIndex(int iRow, int iCol){
        this.iCol =iCol;
        this.iRow=iRow;
    }



}
