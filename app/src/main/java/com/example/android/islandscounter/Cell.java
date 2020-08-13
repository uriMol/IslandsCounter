package com.example.android.islandscounter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

/*
    The Cell class -
    Stores the cell's fields and functions
 */


class Cell extends View implements View.OnClickListener {
    private int row, col, islandID;
    private cellStatus stat = cellStatus.WHITE;
    private Board board;


    //Cell constructor - initializes fields and set parameters

    public Cell(Context context, int row, int col, Board board) {
        super(context);
        this.board = board;
        this.row = row;
        this.col = col;
        this.setOnClickListener(this);
        setParams();

    }

    private void setParams() {
        LinearLayout.LayoutParams CELL_PARAMS = new LinearLayout.LayoutParams(
                25, LinearLayout.LayoutParams.MATCH_PARENT);
        CELL_PARAMS.weight = 1f;
        CELL_PARAMS.setMargins(1,1,1,1);
        this.setLayoutParams(CELL_PARAMS);
        this.setBackgroundColor(Color.WHITE);
        this.setId(row*board.getCols() + col);
    }

    /*
        Every cell is an onClickListener (Cell implements onClickListener)
        on click paints the cell in black
     */
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

    /*
    Called when cell is tagged as an Island
    member - the ID is the islands number
     */
    public void setIslandID(int ID) {
        islandID = ID;
        stat = cellStatus.COLORED;
    }

    /*
        Paints the cell in its island's color - every
        island has its own random color
     */
    public void paint() {
        int randColor = 31 * islandID;
        this.setBackgroundColor(Color.argb(255, randColor%235 , (2*randColor)%235 , (3*randColor)%235));

    }

    public void setStat(cellStatus status) {
        stat = status;
    }
}
