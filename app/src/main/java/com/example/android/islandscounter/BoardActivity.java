package com.example.android.islandscounter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class BoardActivity extends AppCompatActivity implements View.OnClickListener {
    int rows, cols;
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

    private void setUIViews() {
        random = findViewById(R.id.btRandom);
        random.setOnClickListener(this);
        solve = findViewById(R.id.btSolve);
        solve.setOnClickListener(this);
        clean = findViewById(R.id.btClean);
        clean.setOnClickListener(this);
    }

    private void createBoard(Intent incomingIntent) {
        rows = incomingIntent.getIntArrayExtra("dimension")[0];
        cols = incomingIntent.getIntArrayExtra("dimension")[1];
        llBoard = (LinearLayout)findViewById(R.id.llBoard);
        board = new Board(rows, cols, llBoard, this);
    }


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
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        for(Cell[] row:board.getBoard()){
            for(Cell cell:row){
                savedInstanceState.putBoolean(Integer.toString(cell.getId()), cell.getStat()!=cellStatus.WHITE);
            }
        }
        savedInstanceState.putBoolean("solved", board.isSolved());
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        for(Cell[] row:board.getBoard()){
            for(Cell cell:row){
                if(savedInstanceState.getBoolean(Integer.toString(cell.getId()))){
                    cell.setStat(cellStatus.BLACK);
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
