package com.example.bluepea.cartoonpuzzlesliding;

import android.util.Log;

import java.util.ArrayList;

public class ResultManger {
    private int count;
    private int countPieceRight;



    public ResultManger(ArrayList<ArrayList<PieceOfImage>> matrixPiece, LevelGame level) {
        this.countPieceRight=0;
        PieceOfImage temp;
        this.count = level.getCol()*level.getRow();
        for(int iRow=1;iRow<level.getRow()+1;iRow++){
            for(int iCol=0;iCol<level.getCol();iCol++) {
                temp = matrixPiece.get(iRow).get(iCol);
                if (temp.getCurrentIndex().isEqual(temp.getCorrectIndex()))
                    this.countPieceRight++;
            }
        }

    }
    public void checkMovingPiece(PieceOfImage piece, IndexMatrix curent, IndexMatrix previous){
        if(curent.isEqual(piece.getCorrectIndex())){
            this.countPieceRight++;
            return;
        }
        if(!curent.isEqual(piece.getCorrectIndex())
                && previous.isEqual(piece.getCorrectIndex())){
            this.countPieceRight--;
            return;
        }
    }

    public boolean isWin() {
        return (countPieceRight==count);
    }

    public int getCount() {
        return count;
    }

    public int getCountPieceRight() {
        return countPieceRight;
    }
}
