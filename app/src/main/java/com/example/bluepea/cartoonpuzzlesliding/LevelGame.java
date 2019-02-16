package com.example.bluepea.cartoonpuzzlesliding;

import java.io.Serializable;

public class LevelGame implements Serializable {
    private int col;
    private int row;
    private int durationSecond;
    private String stringValue;

    public LevelGame(String stringValue, int col, int row, int durationSecond) {
        this.stringValue=stringValue;
        this.col = col;
        this.row = row;
        this.durationSecond = durationSecond;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getDurationSecond() {
        return durationSecond;
    }

    public void setDurationSecond(int durationSecond) {
        this.durationSecond = durationSecond;
    }

    @Override
    public String toString() {
        return this.stringValue;
    }
}
