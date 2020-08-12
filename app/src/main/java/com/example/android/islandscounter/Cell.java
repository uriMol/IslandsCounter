package com.example.android.islandscounter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

class Cell extends View implements View.OnClickListener {
    int row, col, islandID;
    cellStatus stat = cellStatus.WHITE;
    final LinearLayout.LayoutParams CELL_PARAMS = new LinearLayout.LayoutParams(
            25, LinearLayout.LayoutParams.MATCH_PARENT);
    Board board;



    public Cell(Context context, int row, int col, Board board) {
        super(context);
        this.board = board;
        this.row = row;
        this.col = col;
        this.setOnClickListener(this);
        setParams();

    }

    private void setParams() {
        CELL_PARAMS.weight = 1f;
        CELL_PARAMS.setMargins(1,1,1,1);
        this.setLayoutParams(CELL_PARAMS);
        this.setBackgroundColor(Color.WHITE);
        this.setId(row*board.getCols() + col);
    }

    @Override
    public void onClick(View v) {
        if(stat == cellStatus.WHITE){
            stat = cellStatus.BLACK;
            this.setBackgroundColor(Color.BLACK);
            board.unClean();
        }
    }

    public void cleanCell() {
        stat = cellStatus.WHITE;
        this.setBackgroundColor(Color.WHITE);
    }

    public cellStatus getStat() {
        return stat;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

    public void setIslandID(int ID) {
        islandID = ID;
        stat = cellStatus.COLORED;
    }

    public void paint() {
        int randColor = 31 * islandID;
        this.setBackgroundColor(Color.argb(255, (randColor%235) + 20 , ((2*randColor)%235) + 20, ((3*randColor)%235)) + 20);

    }

    public void setStat(cellStatus status) {
        stat = status;
    }
}
