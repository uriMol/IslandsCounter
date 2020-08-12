package com.example.android.islandscounter;

import android.widget.LinearLayout;

class Board {
    LinearLayout[][] board;
    int rows,cols;
    boolean clean = true;
    boolean solved = false;

    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.board = new LinearLayout[rows][cols];

    }

}
