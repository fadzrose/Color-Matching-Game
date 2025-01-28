package com.example.colormatchinggame;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HowToPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        // Close Button Logic
        Button closeHowToPlayButton = findViewById(R.id.closeHowToPlayButton);
        closeHowToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Close the How to Play activity and return to the previous screen
            }
        });
    }
}
