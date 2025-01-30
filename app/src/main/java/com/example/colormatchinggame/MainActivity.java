package com.example.colormatchinggame;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "GamePrefs";
    private static final String SCORE_LIST_KEY = "ScoreList";

    Button btnPlay, btnManageNicknames;
    private Button howToPlayButton;
    private Button aboutDeveloperButton;
    private ListView scoreListView;
    private ArrayList<String> scoreList;
    private ArrayAdapter<String> adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        btnPlay = findViewById(R.id.playButton);
        howToPlayButton = findViewById(R.id.howToPlayButton);
        aboutDeveloperButton = findViewById(R.id.aboutDeveloperButton);
        scoreListView = findViewById(R.id.scoreListView);
        btnManageNicknames = findViewById(R.id.ManageNicknames);

        // Load and display scores
        loadScoreList();

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
                Intent intent = new Intent(MainActivity.this, HowToPlayActivity.class);
                startActivity(intent);
            }
        });

        // About Developer Button Logic
        aboutDeveloperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutDevelopersActivity.class);
                startActivity(intent);
            }
        });
        btnManageNicknames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNicknameManagementDialog();
            }
        });
    }

    private void showNicknameManagementDialog() {
        String[] options = { "Edit Nickname",  "Search Nickname", "Delete Nickname"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Manage Nicknames")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {

                            case 0:
                                editNickname();
                                break;
                            case 2:
                                deleteNickname();
                                break;
                            case 1:
                                searchNickname();
                                break;
                        }
                    }
                })
                .show();
    }
    // ✅ Moved loadScoreList() outside onCreate()
    private void loadScoreList() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Get leaderboard data from the database
        scoreList = (ArrayList<String>) dbHelper.getLeaderboard(); // Fetch nicknames & scores

        // Reverse the list if you want latest scores first
        Collections.reverse(scoreList);

        // Use a custom adapter instead of android.R.layout.simple_list_item_1
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.listItemText, scoreList);

        scoreListView.setAdapter(adapter);

    }


    private void viewAllNicknames() {
        List<String> nicknames = (List<String>) dbHelper.getAllNicknames();
        if (nicknames.isEmpty()) {
            Toast.makeText(this, "No nicknames found.", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder builder = new StringBuilder();
            for (String nickname : nicknames) {
                builder.append(nickname).append("\n");
            }
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle("All Nicknames")
                    .setMessage(builder.toString())
                    .setPositiveButton("OK", null)
                    .setNeutralButton("Edit Nickname", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editNickname();
                        }
                    })
                    .show();
        }
    }

    private void editNickname() {
        List<String> nicknames = dbHelper.getAllNicknames(); // Now returns List<String>
        if (nicknames.isEmpty()) {
            Toast.makeText(this, "No nicknames to edit.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a dialog with a list of nicknames
        final CharSequence[] nicknameArray = nicknames.toArray(new CharSequence[nicknames.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Nickname to Edit")
                .setItems(nicknameArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String selectedNickname = nicknameArray[which].toString();
                        final EditText input = new EditText(MainActivity.this);
                        input.setText(selectedNickname);

                        AlertDialog.Builder updateDialog = new AlertDialog.Builder(MainActivity.this);
                        updateDialog.setTitle("Edit Nickname")
                                .setView(input)
                                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String newNickname = input.getText().toString().trim();
                                        if (!newNickname.isEmpty() && !newNickname.equals(selectedNickname)) {
                                            boolean isUpdated = dbHelper.updateNickname(selectedNickname, newNickname);
                                            if (isUpdated) {
                                                loadScoreList();
                                                Toast.makeText(MainActivity.this, "Nickname updated successfully!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(MainActivity.this, "Failed to update nickname.", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(MainActivity.this, "Nickname cannot be empty or the same.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNegativeButton("Cancel", null)
                                .show();
                    }
                })
                .show();
    }



    private void searchNickname() {
        final EditText input = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search Nickname")
                .setView(input)
                .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nickname = input.getText().toString().trim();
                        if (!nickname.isEmpty()) {
                            boolean exists = dbHelper.searchNickname(nickname);
                            if (exists) {
                                Toast.makeText(MainActivity.this, "Nickname found!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Nickname not found.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Please enter a nickname to search.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }


    private void promptForNewNickname(final String oldNickname) {
        final EditText input = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Nickname")
                .setView(input)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newNickname = input.getText().toString().trim();
                        if (!newNickname.isEmpty()) {
                            // Update the nickname in the database
                            boolean isUpdated = dbHelper.updateNickname(oldNickname, newNickname);
                            if (isUpdated) {
                                Toast.makeText(MainActivity.this, "Nickname updated successfully!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Failed to update nickname.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Nickname cannot be empty.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }


    private void deleteNickname() {
        // Fetch the list of nicknames from the database
        List<String> nicknames = dbHelper.getAllNicknames(); // Assuming getAllNicknames returns a List<String> of nicknames
        if (nicknames.isEmpty()) {
            Toast.makeText(this, "No nicknames to delete.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert the list of nicknames to CharSequence array for the dialog
        final CharSequence[] nicknameArray = nicknames.toArray(new CharSequence[nicknames.size()]);

        // Create an AlertDialog with a list of nicknames
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Nickname to Delete")
                .setItems(nicknameArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String selectedNickname = nicknameArray[which].toString();

                        // Confirm deletion
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Delete Nickname")
                                .setMessage("Are you sure you want to delete this nickname: " + selectedNickname + "?")
                                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Call the method to delete the selected nickname
                                        boolean isDeleted = dbHelper.deleteNickname(selectedNickname);
                                        if (isDeleted) {
                                            loadScoreList(); // Reload the list after deletion
                                            Toast.makeText(MainActivity.this, "Nickname deleted successfully!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(MainActivity.this, "Failed to delete nickname.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNegativeButton("Cancel", null)
                                .show();
                    }
                })
                .show();
    }
}
