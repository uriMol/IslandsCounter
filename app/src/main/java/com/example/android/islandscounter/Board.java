package com.example.android.islandscounter;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Random;

/*
    The Board class -
    Stores the board's fields and functions
 */

class Board {
    private Cell[][] board;
    private int rows,cols;
    private int numOfIslands = 0;
    private boolean clean = true;
    private boolean solved = false;
    LinearLayout parent;
    BoardActivity activity;

    //The frequency of black cells in the board
    final double FREQUENCY = 0.5;



    /*
        Board constructor - initializes fields and builds rows
        using the createRow(rowNumber) for each row
     */
    public Board(int rows, int cols, LinearLayout parent, BoardActivity activity){
        this.rows = rows;
        this.cols = cols;
        this.board = new Cell[rows][cols];
        this.parent = parent;
        this.activity = activity;
        for(int i = 0; i < rows; i++){
            parent.addView(createRow(i));
        }
    }

    /*
        Creates a LinearLayout for each row, adds
        its cells and sets the parameters
     */
    private LinearLayout createRow(int i) {
        LinearLayout LO = new LinearLayout(activity);
        Cell tmpCell;
        for(int j = 0; j < cols; j++){
            tmpCell = new Cell(activity, i, j, this);
            board[i][j] = tmpCell;
            LO.addView(tmpCell);
        }
        setParams(LO);
        return LO;
    }

    //Sets the parameters for each row layout
    private void setParams(LinearLayout LO){
        LinearLayout.LayoutParams ROW_PARAMS = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 25);
        ROW_PARAMS.weight = 1f;
        LO.setLayoutParams(ROW_PARAMS);
        LO.setOrientation(LinearLayout.HORIZONTAL);
        LO.setBackgroundColor(Color.BLACK);
    }

    public void unClean() {
        this.clean = false;
    }


    public void unSolve() {
        this.solved = false;
    }


    /*
        Cleans the board - if board isn't clean - iterates
        on it's cells and clean them
     */
    public void cleanBoard(){
        if(!clean){
            for(Cell[] row:board){
                for(Cell cell:row){
                    cell.cleanCell();
                }
            }
            this.clean = true;
        }
        this.solved = false;
        this.numOfIslands = 0;
        Toast.makeText(activity, "Map is clean!", Toast.LENGTH_SHORT).show();
    }


    /*
        Solves the board - if board isn't solved - iterates
        on it's cells and if necessary activates paintIsland()
     */
    public void solve() {
        if(!solved){
            for(Cell[] row:board){
                for(Cell cell:row){
                    if(cell.getStat() == cellStatus.BLACK){
                        PaintIsland(cell);
                    }
                }
            }
            solved = true;
        }
        Toast.makeText(activity, "Map is solved!", Toast.LENGTH_SHORT).show();
    }

    /*
        On each call - the function paints the entire island.
        PaintIsland uses a stack to store all relevant
        neighbors cell that are part of the island and should
        be painted. Very similar to BFS algorithm
     */
    private void PaintIsland(Cell cell) {
        numOfIslands++;
        LinkedList<Integer[]> stack = new LinkedList<Integer[]>();
        int i = cell.getRow();
        int j = cell.getCol();
        stack.add(new Integer[]{i, j});
        Integer[] indexes;
        while(!stack.isEmpty()){
            indexes = stack.remove();
            i = indexes[0];
            j = indexes[1];
            board[i][j].setIslandID(numOfIslands);
            board[i][j].paint();
            addNeighborsToStack(indexes, stack);
    }
}

    private void addNeighborsToStack(Integer[] indexes, LinkedList<Integer[]> stack) {
        int i = indexes[0];
        int j = indexes[1];
        if(i > 0 && board[i-1][j].getStat() == cellStatus.BLACK){
            stack.add(new Integer[]{i-1, j});
        }
        if(i < rows - 1 && board[i+1][j].getStat() == cellStatus.BLACK){
            stack.add(new Integer[]{i+1, j});
        }
        if(j > 0 && board[i][j-1].getStat() == cellStatus.BLACK){
            stack.add(new Integer[]{i, j-1});
        }
        if(j < cols - 1 && board[i][j+1].getStat() == cellStatus.BLACK){
            stack.add(new Integer[]{i, j+1});
        }
    }

    public void randomize() {
        Random rand = new Random();
        Double random;
        for(Cell[] row:board){
            for(Cell cell:row){
                random = rand.nextDouble();
                if(random < FREQUENCY){
                    cell.setBackgroundColor(Color.BLACK);
                    cell.setStat(cellStatus.BLACK);
                    clean = false;
                } else{
                    cell.setBackgroundColor(Color.WHITE);
                    cell.setStat(cellStatus.WHITE);
                }
            }
        }
        solved = false;
        Toast.makeText(activity, "Map is randomized!", Toast.LENGTH_SHORT).show();

    }

    public Cell[][] getBoard() {
        return board;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isSolved() {
        return solved;
    }
}