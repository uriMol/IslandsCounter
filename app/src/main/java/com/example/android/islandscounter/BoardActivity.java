package com.example.android.islandscounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BoardActivity extends AppCompatActivity {
    int rows, cols;
    LinearLayout llBoard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        createBoard(getIntent());
        Intent incomingIntent = getIntent();
    }

    private void createBoard(Intent incomingIntent) {
        rows = incomingIntent.getIntArrayExtra("dimension")[0];
        cols = incomingIntent.getIntArrayExtra("dimension")[1];
        llBoard = (LinearLayout)findViewById(R.id.llBoard);
        LinearLayout    tmpLayout;
        Button          tmpButton;
        LinearLayout.LayoutParams loparams;
        for(int i = 0; i < rows; i++){
            tmpLayout = new LinearLayout(this);
            tmpLayout.setOrientation(LinearLayout.HORIZONTAL);
            tmpLayout.setLayoutParams(new ActionBar.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
            tmpLayout.setWeightSum(1);
            for(int j = 0; j < cols; j++){
                tmpButton = new Button(this);
                tmpButton.setText(i + " " + j);
                tmpButton.setId(i*cols + j);
                tmpButton.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
                tmpButton.setWidth(0);
                loparams = (LinearLayout.LayoutParams) tmpButton.getLayoutParams();
                loparams.weight = 1;
                tmpButton.setLayoutParams(loparams);
                tmpLayout.addView(tmpButton);
            }
            llBoard.addView(tmpLayout);
        }
    }


}
