package com.example.colormatchinggame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScoreActivity extends AppCompatActivity {

    Button btnReplay, btnSave;
    TextView pointer, time;
    private EditText name;
    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        dbHelper = new DatabaseHelper(this);

        name = findViewById(R.id.inputName);
        time = findViewById(R.id.Time);
        pointer = findViewById(R.id.score);
        btnReplay = findViewById(R.id.Replay);
        btnSave = findViewById(R.id.Exit);

        //Retrieve data
        String t = getIntent().getStringExtra("time");
        String s = getIntent().getStringExtra("point");

        //Display
        time.setText(t);
        pointer.setText(s);

        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ScoreActivity.this, GameActivity2.class);//redirect without saving the score
                startActivity(intent);
            }


        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){


                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
    }
}