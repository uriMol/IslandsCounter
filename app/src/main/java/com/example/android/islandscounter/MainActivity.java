package com.example.android.islandscounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etRows;
    EditText etCols;
    Button btSetBoard;
    int cols, rows;

    //MAX_DIM defines max dimension for the board, should be 1000
    final int MAX_DIM = 200;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUIViews();
        btSetBoard.setOnClickListener(this);
    }

    //Setting the UI variables to their views
    private void setUIViews() {
        etRows = (EditText)findViewById(R.id.etRows);
        etCols = (EditText)findViewById(R.id.etCols);
        btSetBoard = (Button) findViewById(R.id.buttonSetBoard);
    }


    /*
        Clicking on the SetBoard button first checks if the
        cols and rows that were filled are in the required dimension,
        then create an intent for the BoardActivity
     */
    @Override
    public void onClick(View v) {
        String strRows = etRows.getText().toString();
        String strCols = etCols.getText().toString();
        if(strRows.equals("") || strCols.equals("")){
            Toast.makeText(MainActivity.this, "Please insert number of Columns and Rows", Toast.LENGTH_LONG).show();
        } else {
            rows = Integer.parseInt(etRows.getText().toString());
            cols = Integer.parseInt(etCols.getText().toString());
            if(0 < rows && rows <= MAX_DIM && 0 < cols && cols <= MAX_DIM){
                etRows.getText().clear();
                etCols.getText().clear();
                Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                intent.putExtra("dimension", new int[]{rows, cols});
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Board's dimension must be at least 1x1" +
                        " and up to " + MAX_DIM + "x"+ MAX_DIM, Toast.LENGTH_LONG).show();
            }
        }
    }
}
