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
    Button buttonSetBoard;
    int cols, rows;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUIviews();
        buttonSetBoard.setOnClickListener(this);
    }

    private void setUIviews() {
        etRows = (EditText)findViewById(R.id.etRows);
        etCols = (EditText)findViewById(R.id.etCols);
        buttonSetBoard = (Button) findViewById(R.id.buttonSetBoard);
    }

    @Override
    public void onClick(View v) {
        String strRows = etRows.getText().toString();
        String strCols = etCols.getText().toString();
        if(strRows.equals("") || strCols.equals("")){
            Toast.makeText(MainActivity.this, "Please insert number of Columns and Rows", Toast.LENGTH_LONG).show();
        } else {
            rows = Integer.parseInt(etRows.getText().toString());
            cols = Integer.parseInt(etCols.getText().toString());
            if(0 < rows && rows <= 1000 && 0 < cols && cols <= 1000){
                Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                intent.putExtra("dimension", new int[]{rows, cols});
                startActivity(intent);
            } else {
                etRows.getText().clear();
                etCols.getText().clear();
                Toast.makeText(MainActivity.this, "Board's dimension must be at least 1x1 and up to 1000x1000", Toast.LENGTH_LONG).show();
            }
        }
    }
}
