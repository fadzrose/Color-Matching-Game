package com.example.colormatchinggame;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AboutDevelopersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developers);

        // Close Button Logic
        Button closeAboutButton = findViewById(R.id.closeAboutButton);
        closeAboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Close the About Developers activity and return to the previous screen
            }
        });
    }
}
