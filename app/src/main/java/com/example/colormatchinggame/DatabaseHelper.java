package com.example.colormatchinggame;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final String TABLE_NAME = "users";
    private static final String COL_ID = "id";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String TABLE_LEADERBOARD = "leaderboard";
    private static final String LEADERBOARD_ID = "leaderboard_id";
    private static final String COL_ID_FK = "id";
    public static final String COL_SCORE = "score";
    private static final String TABLE_NICKNAMES = "nicknames"; // Table for storing nicknames
    private static final String COL_NICKNAME_ID = "nickname_id";
    private static final String COL_NICKNAME = "nickname";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the nicknames table
        String createNicknamesTable = "CREATE TABLE " + TABLE_NICKNAMES + " (" +
                COL_NICKNAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NICKNAME + " TEXT NOT NULL," +
                COL_SCORE + " TEXT NOT NULL);";
        db.execSQL(createNicknamesTable);
        // Create the users table
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EMAIL + " TEXT UNIQUE, " +
                COL_PASSWORD + " TEXT)";
        db.execSQL(createTable);

        // Create the leaderboard table with nickname reference
        String createTable2 = "CREATE TABLE " + TABLE_LEADERBOARD + " (" +
                LEADERBOARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_SCORE + " INTEGER NOT NULL, " +
                COL_ID_FK + " INTEGER, " + // Link to nickname_id instead of user_id
                "FOREIGN KEY(" + COL_ID_FK + ") REFERENCES " + TABLE_NICKNAMES + "(" + COL_NICKNAME_ID + "));";
        db.execSQL(createTable2);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEADERBOARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NICKNAMES);
        onCreate(db);
    }

    // Register a new user
    public boolean registerUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_PASSWORD, password);

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1; // Returns true if the insert was successful
    }

    // Login user with email and password
    public boolean loginUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                COL_EMAIL + " = ? AND " + COL_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }

    // Insert a nickname into the database
    // Insert a nickname along with the score into the database
    public boolean insertNickname(String nickname, String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NICKNAME, nickname);
        values.put(COL_SCORE, score);

        long result = db.insert(TABLE_NICKNAMES, null, values);
        return result != -1; // Returns true if the insert was successful
    }
    // ✅ Insert nickname AND add to leaderboard
    public boolean insertNicknameAndAddToLeaderboard(String nickname, String score) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Insert nickname into the nicknames table
        ContentValues values = new ContentValues();
        values.put(COL_NICKNAME, nickname);
        values.put(COL_SCORE, score); // Store the score as a string

        long nicknameResult = db.insert(TABLE_NICKNAMES, null, values);

        if (nicknameResult == -1) {
            return false; // Insert failed
        }

        // Retrieve the nickname ID
        Cursor cursor = db.rawQuery("SELECT " + COL_NICKNAME_ID + " FROM " + TABLE_NICKNAMES + " WHERE " + COL_NICKNAME + " = ?", new String[]{nickname});

        int nicknameId = -1;
        if (cursor.moveToFirst()) {
            nicknameId = cursor.getInt(cursor.getColumnIndexOrThrow(COL_NICKNAME_ID));
        }
        cursor.close();

        if (nicknameId == -1) {
            return false; // Nickname not found, should not happen
        }

        // Insert into leaderboard
        ContentValues leaderboardValues = new ContentValues();
        leaderboardValues.put(COL_ID_FK, nicknameId);
        leaderboardValues.put(COL_SCORE, score); // Score is stored as a string

        long leaderboardResult = db.insert(TABLE_LEADERBOARD, null, leaderboardValues);
        return leaderboardResult != -1; // Returns true if both inserts were successful
    }

    // Retrieve all nicknames and scores from the database
    public List<String> getAllNicknames() {
        List<String> nicknames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get all nicknames
        Cursor cursor = db.query(TABLE_NICKNAMES, new String[]{COL_NICKNAME}, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // Assuming COL_NICKNAME is the correct column for the nickname
                @SuppressLint("Range") String nickname = cursor.getString(cursor.getColumnIndex(COL_NICKNAME));
                nicknames.add(nickname);
            }
            cursor.close();
        }
        db.close();

        return nicknames;
    }


    // Search for a specific nickname in the database
    public boolean searchNickname(String nickname) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NICKNAMES + " WHERE " + COL_NICKNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nickname});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Delete a nickname from the database
    public boolean deleteNickname(String nickname) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NICKNAMES, COL_NICKNAME + " = ?", new String[]{nickname});
        return result > 0; // Returns true if the nickname was deleted
    }

    // Update an existing nickname and score in the database
    /*
    public boolean updateNickname(String oldNickname, String newNickname, String newScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NICKNAME, newNickname);
        values.put(COL_SCORE, newScore);

        int result = db.update(TABLE_NICKNAMES, values, COL_NICKNAME + " = ?", new String[]{oldNickname});
        return result > 0; // Returns true if the update was successful
    }*/

    public boolean updateNickname(String oldNickname, String newNickname) {
        // Trim the nicknames to remove any leading or trailing spaces
        oldNickname = oldNickname.trim();
        newNickname = newNickname.trim();

        // Log the values to make sure they are correct
        Log.d("UpdateNickname", "Old Nickname: '" + oldNickname + "' New Nickname: '" + newNickname + "'");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NICKNAME, newNickname);

        // Perform the update
        int result = db.update(TABLE_NICKNAMES, values, COL_NICKNAME + " = ?", new String[]{oldNickname});

        // Check if the update was successful
        return result > 0;
    }



    // Insert a leaderboard entry with nickname, score, and time
    public boolean insertLeaderboardEntry(String nickname, int score, String time) {
        SQLiteDatabase db = this.getWritableDatabase();

        // First, check if the nickname exists
        Cursor cursor = db.rawQuery("SELECT " + COL_NICKNAME_ID + " FROM " + TABLE_NICKNAMES + " WHERE " + COL_NICKNAME + " = ?", new String[]{nickname});
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int nicknameId = cursor.getInt(cursor.getColumnIndex(COL_NICKNAME_ID));

            // Insert the leaderboard entry
            ContentValues values = new ContentValues();
            values.put(COL_ID_FK, nicknameId); // Save nickname_id (from nicknames table)
            values.put(COL_SCORE, score);
            values.put("time", time);

            long result = db.insert(TABLE_LEADERBOARD, null, values);
            cursor.close();
            return result != -1; // Returns true if the insert was successful
        } else {
            cursor.close();
            return false; // No such nickname found
        }
    }
    // Retrieve the leaderboard entries (sorted by score)
    public List<String> getLeaderboard() {
        List<String> leaderboard = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL_NICKNAME + ", " + COL_SCORE + " FROM " + TABLE_NICKNAMES + " ORDER BY " + COL_SCORE + " ASC";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String nickname = cursor.getString(cursor.getColumnIndex(COL_NICKNAME));
                @SuppressLint("Range") String score = cursor.getString(cursor.getColumnIndex(COL_SCORE));
                leaderboard.add(nickname + " - " + score); // Format for ListView
            } while (cursor.moveToNext());
        }
        cursor.close();
        return leaderboard;
    }


}
