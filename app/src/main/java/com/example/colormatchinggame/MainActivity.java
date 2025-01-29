package com.example.colormatchinggame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button btnPlay;
    private Button howToPlayButton;
    private Button aboutDeveloperButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.playButton);
        howToPlayButton = findViewById(R.id.howToPlayButton);
        aboutDeveloperButton = findViewById(R.id.aboutDeveloperButton);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity2.class);
                startActivity(intent);
            }
        });

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