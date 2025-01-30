package com.example.colormatchinggame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "GamePrefs";
    private static final String SCORE_LIST_KEY = "ScoreList";

    Button btnPlay;
    private Button howToPlayButton;
    private Button aboutDeveloperButton;
    private ListView scoreListView;
    private ArrayList<String> scoreList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.playButton);
        howToPlayButton = findViewById(R.id.howToPlayButton);
        aboutDeveloperButton = findViewById(R.id.aboutDeveloperButton);
        scoreListView = findViewById(R.id.scoreListView);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity2.class);
                startActivity(intent);
            }
        });
// Retrieve and display the score list
        private void loadScoreList() {
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            String scores = prefs.getString(SCORE_LIST_KEY, ""); // Get stored scores
            scoreList = new ArrayList<>();

            if (!scores.isEmpty()) {
                String[] scoreArray = scores.split(",");
                scoreList.addAll(Arrays.asList(scoreArray));
                Collections.reverse(scoreList); // Show latest score first
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoreList);
            scoreListView.setAdapter(adapter);
        }


        // How to Play Button Logic
        howToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch How to Play Activity
                Intent intent = new Intent(MainActivity.this, HowToPlayActivity.class);
                startActivity(intent);
            }
        });

        // About Developer Button Logic
        aboutDeveloperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch About Developers Activity
                Intent intent = new Intent(MainActivity.this, AboutDevelopersActivity.class);
                startActivity(intent);
            }
        });
    }
}