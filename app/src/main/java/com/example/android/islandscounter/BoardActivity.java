package com.example.android.islandscounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class BoardActivity extends AppCompatActivity implements View.OnClickListener {
    int rows, cols;
    Button random, solve, clean;
    LinearLayout llBoard;
    LinearLayout[][] board;
    Random rand = new Random();
    boolean isClean = true;

    double FREQUENCY = 0.5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        setButtons();
        createBoard(getIntent());
        Intent incomingIntent = getIntent();
    }

    private void setButtons() {
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
        board = new LinearLayout[rows][cols];
        llBoard = (LinearLayout)findViewById(R.id.llBoard);
        LinearLayout    tmpRow;
        LinearLayout    tmpCol;
        LinearLayout.LayoutParams innerParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT);
        innerParams.weight = 1f;
        innerParams.setMargins(1,1,1,1);
        LinearLayout.LayoutParams outerParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0);
        outerParams.weight = 1f;
        for(int i = 0; i < rows; i++){
            tmpRow = new LinearLayout(this);
            tmpRow.setOrientation(LinearLayout.HORIZONTAL);
            tmpRow.setLayoutParams(outerParams);
            tmpRow.setBackgroundColor(Color.BLACK);
            for(int j = 0; j < cols; j++){
                tmpCol = new LinearLayout(this);
                tmpCol.setBackgroundColor(Color.WHITE);
                tmpCol.setId(i*cols + j);
                tmpCol.setTag(0);
                tmpCol.setLayoutParams(innerParams);
                tmpRow.addView(tmpCol);
                board[i][j] = tmpCol;
                tmpCol.setOnClickListener(this);
            }
            llBoard.addView(tmpRow);
        }
    }


    @Override
    public void onClick(View v) {
        String tag = v.getTag().toString();
        switch(tag){
            case("random"):
                randomBoard(FREQUENCY);
                break;
            case("solve"):
                solveBoard();
                break;
            case("clean"):
                cleanBoard();
                break;
            case("0"):
                v.setBackgroundColor(Color.BLACK);
                v.setTag("1");
                isClean = false;
                break;
        }
    }

    private void cleanBoard() {
        LinearLayout tmp;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                tmp = board[i][j];
                tmp.setBackgroundColor(Color.WHITE);
                tmp.setTag("0");
            }
        }
        isClean = true;
        Toast.makeText(this, "Map is clean!", Toast.LENGTH_SHORT).show();
    }



    private void solveBoard() {
        Toast.makeText(this, "Map is solved!", Toast.LENGTH_SHORT).show();

    }

    private void randomBoard(double frequency) {
        if(!isClean){
            Toast.makeText(this, "Please clean the map in order to randomize it", Toast.LENGTH_SHORT).show();
            return;
        }
        Double random;
        LinearLayout tmp;
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                random = rand.nextDouble();
                if(random < frequency){
                    tmp = board[i][j];
                    tmp.setBackgroundColor(Color.BLACK);
                    tmp.setTag("1");
                    isClean = false;
                }
            }
        }
        Toast.makeText(this, "Map is randomized!", Toast.LENGTH_SHORT).show();
    }
}
