package com.example.android.islandscounter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BoardActivity extends AppCompatActivity implements View.OnClickListener {
    private int rows, cols;
    TextView tvNumOfIslands;
    Button random, solve, clean;
    LinearLayout llBoard;
    Board board;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        setUIViews();
        createBoard(getIntent());
    }

    //Setting UI variables to their views
    private void setUIViews() {
        tvNumOfIslands = findViewById(R.id.tvNumOfIslands);
        random = findViewById(R.id.btRandom);
        random.setOnClickListener(this);
        solve = findViewById(R.id.btSolve);
        solve.setOnClickListener(this);
        clean = findViewById(R.id.btClean);
        clean.setOnClickListener(this);
    }

    //Called by onCreate - creates a new board with the input dimension
    private void createBoard(Intent incomingIntent) {
        rows = incomingIntent.getIntArrayExtra("dimension")[0];
        cols = incomingIntent.getIntArrayExtra("dimension")[1];
        llBoard = (LinearLayout)findViewById(R.id.llBoard);
        board = new Board(rows, cols, llBoard, this);
    }


    /*
        This class implements onClickListener, used by the
        three top buttons. On each click, the onClick identifies
        the clicked button by it's tag and actives the relevant function
     */
    @Override
    public void onClick(View v) {
        String tag = v.getTag().toString();
        switch(tag){
            case("random"):
                board.randomize();
                break;
            case("solve"):
                board.solve();
                break;
            case("clean"):
                board.cleanBoard();
                break;
        }
        refreshNumOfIslands();
    }

    private void refreshNumOfIslands() {
        String num;
        if ((board.isClean() == true) || board.getNumOfIslands() == 0) {
            num = "-";
        } else {
            num = "" + board.getNumOfIslands();
        }
        tvNumOfIslands.setText("No. of islands: " + num);
    }


    /*
    Taking care of the onSaveInstanceState -
    We're saving a boolean "isWhite" variable for each cell,
    and saving the "solved" field of the board
     */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        for(Cell[] row:board.getBoard()){
            for(Cell cell:row){
                savedInstanceState.putBoolean(Integer.toString(cell.getId()), cell.getStat()!=CellStatus.WHITE);
            }
        }
        savedInstanceState.putBoolean("solved", board.isSolved());
    }

    /*
        On restore - we make sure that every "not white" cell
        is painted in black, and if the "solved" field was true
        prior to onSaveInstanceState - we solve the board
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for(Cell[] row:board.getBoard()){
            for(Cell cell:row){
                if(savedInstanceState.getBoolean(Integer.toString(cell.getId()))){
                    cell.setStat(CellStatus.BLACK);
                    cell.setBackgroundColor(Color.BLACK);
                    board.unClean();
                }
            }
        }
        if(savedInstanceState.getBoolean("solved")){
            board.solve();
        }
    }




}
