package com.example.colormatchinggame;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

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

        // Retrieve data
        String t = getIntent().getStringExtra("time");
        String s = getIntent().getStringExtra("point");

        // Display
        time.setText(t);
        pointer.setText(s);

        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, GameActivity2.class);
                startActivity(intent);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = name.getText().toString().trim();


                if (!nickname.isEmpty()) {
                    boolean isInserted = dbHelper.insertNickname(nickname,s);
                    if (isInserted) {
                        Toast.makeText(ScoreActivity.this, "Nickname saved successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            boolean success = dbHelper.insertNicknameAndAddToLeaderboard(nickname, s);
                            if (success) {
                                Toast.makeText(ScoreActivity.this, "Nickname and score saved to leaderboard!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ScoreActivity.this, "Failed to save nickname and score!", Toast.LENGTH_SHORT).show();
                            }



                        } catch (Exception e) {
                            Log.e("GameActivity2", "Error updating score", e);
                        }
                        Toast.makeText(ScoreActivity.this, "Failed to save nickname.", Toast.LENGTH_SHORT).show();
                    }

                    // Now create an Intent to navigate to MainActivity
                    Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                    // Pass the score and time to MainActivity
                    intent.putExtra("score", s);
                    intent.putExtra("time", t);
                    startActivity(intent);

                } else {
                    Toast.makeText(ScoreActivity.this, "Please enter a nickname.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    /*
    private void addNickname() {
        final EditText input = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Nickname")
                .setView(input)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nickname = input.getText().toString().trim();
                        if (!nickname.isEmpty()) {
                            boolean isInserted = dbHelper.insertNickname(nickname);
                            if (isInserted) {
                                Toast.makeText(ScoreActivity.this, "Nickname added successfully!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ScoreActivity.this, "Failed to add nickname.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ScoreActivity.this, "Nickname cannot be empty.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }*/




    private void showLeaderboard() {
        List<String> leaderboard = dbHelper.getLeaderboard();
        if (leaderboard.isEmpty()) {
            Toast.makeText(this, "Leaderboard is empty.", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder builder = new StringBuilder();
            for (String entry : leaderboard) {
                builder.append(entry).append("\n");
            }
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle("Leaderboard")
                    .setMessage(builder.toString())
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

}


