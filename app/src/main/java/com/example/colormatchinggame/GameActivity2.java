package com.example.colormatchinggame;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.Cleaner;
import java.util.Arrays;
import java.util.Collections;


public class GameActivity2 extends AppCompatActivity {


    ImageView c11,c12,c13,c14,c21,c22,c23,c24,c31,c32,c33,c34,c41,c42,c43,c44;
    Integer[] cardsArray = {101,102,103,104,105,106,107,108,201,202,203,204,205,206,207,208};
    int image101,image102,image103,image104,image105,image106,image107,image108;
    int image201,image202,image203,image204,image205,image206,image207,image208;

    int firstCard,secondCard;
    int clicked1, clicked2;
    int cardNumber = 1;

    int turn = 1;
    int playerPoints = 0;
    int comboStreak = 0;
    int countScore = 0;
    double time = 0;
    int cardFliped=0;


    private ImageButton resetButton;
    private ImageButton exitButton;
    TextView tScore;
    private TextView timerValue;
    private long startTime = 0L, timeInMilliseconds = 0L;
    private Handler customHandler = new Handler();
    private boolean gameStarted = false;

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            int secs = (int) (timeInMilliseconds / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (timeInMilliseconds % 1000);

            // Display the time
            timerValue.setText(String.format("%02d", mins) + ":" + String.format("%02d", secs) + ":" + String.format("%03d", milliseconds));

            customHandler.postDelayed(this, 0);
        }
    };

    private void flipCard(final ImageView card, final int cardIndex, boolean isFront1, boolean isFront2) {
        AnimatorSet flipOut = (AnimatorSet) AnimatorInflater.loadAnimator(GameActivity2.this, R.animator.card_flip_out);
        AnimatorSet flipIn = (AnimatorSet) AnimatorInflater.loadAnimator(GameActivity2.this, R.animator.card_flip_in);

        flipOut.setTarget(card);
        flipIn.setTarget(card);

        flipOut.start();
        flipOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (isFront1) {
                    // Show the front of the card
                    switch (cardsArray[cardIndex]) {
                        case 101:
                            card.setImageResource(image101);
                            break;
                        case 102:
                            card.setImageResource(image102);
                            break;
                        case 103:
                            card.setImageResource(image103);
                            break;
                        case 104:
                            card.setImageResource(image104);
                            break;
                        case 105:
                            card.setImageResource(image105);
                            break;
                        case 106:
                            card.setImageResource(image106);
                            break;
                        case 107:
                            card.setImageResource(image107);
                            break;
                        case 108:
                            card.setImageResource(image108);
                            break;
                        case 201:
                            card.setImageResource(image201);
                            break;
                        case 202:
                            card.setImageResource(image202);
                            break;
                        case 203:
                            card.setImageResource(image203);
                            break;
                        case 204:
                            card.setImageResource(image204);
                            break;
                        case 205:
                            card.setImageResource(image205);
                            break;
                        case 206:
                            card.setImageResource(image206);
                            break;
                        case 207:
                            card.setImageResource(image207);
                            break;
                        case 208:
                            card.setImageResource(image208);
                            break;
                    }
                } else if (isFront2) {
                    // Show the front of the card
                    switch (cardsArray[cardIndex]) {
                        case 101:
                            card.setImageResource(image101);
                            break;
                        case 102:
                            card.setImageResource(image102);
                            break;
                        case 103:
                            card.setImageResource(image103);
                            break;
                        case 104:
                            card.setImageResource(image104);
                            break;
                        case 105:
                            card.setImageResource(image105);
                            break;
                        case 106:
                            card.setImageResource(image106);
                            break;
                        case 107:
                            card.setImageResource(image107);
                            break;
                        case 108:
                            card.setImageResource(image108);
                            break;
                        case 201:
                            card.setImageResource(image201);
                            break;
                        case 202:
                            card.setImageResource(image202);
                            break;
                        case 203:
                            card.setImageResource(image203);
                            break;
                        case 204:
                            card.setImageResource(image204);
                            break;
                        case 205:
                            card.setImageResource(image205);
                            break;
                        case 206:
                            card.setImageResource(image206);
                            break;
                        case 207:
                            card.setImageResource(image207);
                            break;
                        case 208:
                            card.setImageResource(image208);
                            break;
                    }
                } else {
                    //if isFront1 && isFront2 false
                    // Show the back of the card
                    card.setImageResource(R.drawable.card);
                }
                flipIn.start();
            }
        });
    }

    private void flipCardBack(final ImageView card, final int cardIndex, boolean isBack) {
        AnimatorSet flipOut = (AnimatorSet) AnimatorInflater.loadAnimator(GameActivity2.this, R.animator.card_flip_out);
        AnimatorSet flipIn = (AnimatorSet) AnimatorInflater.loadAnimator(GameActivity2.this, R.animator.card_flip_in);

        flipOut.setTarget(card);
        flipIn.setTarget(card);

        flipOut.start();
        flipOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (isBack) {

                    //if isFront1 && isFront2 false
                    // Show the back of the card
                    if (clicked1 == 0) {
                        c11.setImageResource(R.drawable.card);
                    } else if (clicked1 == 1) {
                        c12.setImageResource(R.drawable.card);
                    } else if (clicked1 == 2) {
                        c13.setImageResource(R.drawable.card);
                    } else if (clicked1 == 3) {
                        c14.setImageResource(R.drawable.card);
                    } else if (clicked1 == 4) {
                        c21.setImageResource(R.drawable.card);
                    } else if (clicked1 == 5) {
                        c22.setImageResource(R.drawable.card);
                    } else if (clicked1 == 6) {
                        c23.setImageResource(R.drawable.card);
                    } else if (clicked1 == 7) {
                        c24.setImageResource(R.drawable.card);
                    } else if (clicked1 == 8) {
                        c31.setImageResource(R.drawable.card);
                    } else if (clicked1 == 9) {
                        c32.setImageResource(R.drawable.card);
                    } else if (clicked1 == 10) {
                        c33.setImageResource(R.drawable.card);
                    } else if (clicked1 == 11) {
                        c34.setImageResource(R.drawable.card);
                    } else if (clicked1 == 12) {
                        c41.setImageResource(R.drawable.card);
                    } else if (clicked1 == 13) {
                        c42.setImageResource(R.drawable.card);
                    } else if (clicked1 == 14) {
                        c43.setImageResource(R.drawable.card);
                    } else if (clicked1 == 15) {
                        c44.setImageResource(R.drawable.card);
                    }

                    if (clicked2 == 0) {
                        c11.setImageResource(R.drawable.card);
                    } else if (clicked2 == 1) {
                        c12.setImageResource(R.drawable.card);
                    } else if (clicked2 == 2) {
                        c13.setImageResource(R.drawable.card);
                    } else if (clicked2 == 3) {
                        c14.setImageResource(R.drawable.card);
                    } else if (clicked2 == 4) {
                        c21.setImageResource(R.drawable.card);
                    } else if (clicked2 == 5) {
                        c22.setImageResource(R.drawable.card);
                    } else if (clicked2 == 6) {
                        c23.setImageResource(R.drawable.card);
                    } else if (clicked2 == 7) {
                        c24.setImageResource(R.drawable.card);
                    } else if (clicked2 == 8) {
                        c31.setImageResource(R.drawable.card);
                    } else if (clicked2 == 9) {
                        c32.setImageResource(R.drawable.card);
                    } else if (clicked2 == 10) {
                        c33.setImageResource(R.drawable.card);
                    } else if (clicked2 == 11) {
                        c34.setImageResource(R.drawable.card);
                    } else if (clicked2 == 12) {
                        c41.setImageResource(R.drawable.card);
                    } else if (clicked2 == 13) {
                        c42.setImageResource(R.drawable.card);
                    } else if (clicked2 == 14) {
                        c43.setImageResource(R.drawable.card);
                    } else if (clicked2 == 15) {
                        c44.setImageResource(R.drawable.card);
                    }
                }
                flipIn.start();
            }
        });
    }

    //main Code is here
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        // Initialize buttons

        resetButton = findViewById(R.id.resetButton);
        exitButton = findViewById(R.id.exitButton);

        tScore = findViewById(R.id.score);
        timerValue = findViewById(R.id.timerValue);

        //auto start timer
        Toast.makeText(GameActivity2.this, "Game Started", Toast.LENGTH_SHORT).show();
        // Start the GameActivity and start the timer
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);


        // Reset Button Logic
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic for resetting the game
                Toast.makeText(GameActivity2.this, "Game Reset", Toast.LENGTH_SHORT).show();
                // Reset game state here
                cardNumber = 1;
                cardFliped=0;
                playerPoints = 0;

                try {
                    @SuppressLint("StringFormatMatches") String display = String.format(getString(R.string.prompt, playerPoints));
                    runOnUiThread(() -> tScore.setText(display));

                } catch (Exception e) {
                    Log.e("GameActivity2", "Error updating score", e);
                }

                c11.setImageResource(R.drawable.card);
                c12.setImageResource(R.drawable.card);
                c13.setImageResource(R.drawable.card);
                c14.setImageResource(R.drawable.card);
                c21.setImageResource(R.drawable.card);
                c22.setImageResource(R.drawable.card);
                c23.setImageResource(R.drawable.card);
                c24.setImageResource(R.drawable.card);
                c31.setImageResource(R.drawable.card);
                c32.setImageResource(R.drawable.card);
                c33.setImageResource(R.drawable.card);
                c34.setImageResource(R.drawable.card);
                c41.setImageResource(R.drawable.card);
                c42.setImageResource(R.drawable.card);
                c43.setImageResource(R.drawable.card);
                c44.setImageResource(R.drawable.card);

                c11.setTag("0");
                c12.setTag("1");
                c13.setTag("2");
                c14.setTag("3");
                c21.setTag("4");
                c22.setTag("5");
                c23.setTag("6");
                c24.setTag("7");
                c31.setTag("8");
                c32.setTag("9");
                c33.setTag("10");
                c34.setTag("11");
                c41.setTag("12");
                c42.setTag("13");
                c43.setTag("14");
                c44.setTag("15");

                frontOfCardResources();
                Collections.shuffle(Arrays.asList(cardsArray));

                //reset timer here
                customHandler.removeCallbacks(updateTimerThread);
                startTime = 0L;
                timeInMilliseconds = 0L;
                gameStarted = false;
                timerValue.setText("00:00:000");

                Toast.makeText(GameActivity2.this, "Game Started", Toast.LENGTH_SHORT).show();
                // Start the GameActivity and start the timer
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
            }
        });

        // Exit Button Logic
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic for exiting the app
                Toast.makeText(GameActivity2.this, "Exiting Game", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GameActivity2.this, MainActivity.class);
                startActivity(intent); // Close the activity and exit the app
            }
        });



        //pt = (TextView) findViewById(R.id.point);

        c11 = (ImageView) findViewById(R.id.card11);
        c12 = (ImageView) findViewById(R.id.card12);
        c13 = (ImageView) findViewById(R.id.card13);
        c14 = (ImageView) findViewById(R.id.card14);
        c21 = (ImageView) findViewById(R.id.card21);
        c22 = (ImageView) findViewById(R.id.card22);
        c23 = (ImageView) findViewById(R.id.card23);
        c24 = (ImageView) findViewById(R.id.card24);
        c31 = (ImageView) findViewById(R.id.card31);
        c32 = (ImageView) findViewById(R.id.card32);
        c33 = (ImageView) findViewById(R.id.card33);
        c34 = (ImageView) findViewById(R.id.card34);
        c41 = (ImageView) findViewById(R.id.card41);
        c42 = (ImageView) findViewById(R.id.card42);
        c43 = (ImageView) findViewById(R.id.card43);
        c44 = (ImageView) findViewById(R.id.card44);

        c11.setTag("0");
        c12.setTag("1");
        c13.setTag("2");
        c14.setTag("3");
        c21.setTag("4");
        c22.setTag("5");
        c23.setTag("6");
        c24.setTag("7");
        c31.setTag("8");
        c32.setTag("9");
        c33.setTag("10");
        c34.setTag("11");
        c41.setTag("12");
        c42.setTag("13");
        c43.setTag("14");
        c44.setTag("15");

        frontOfCardResources();
        Collections.shuffle(Arrays.asList(cardsArray));

        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());

                doStuff(c11, theCard);

            }
        });
        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c12, theCard);
            }
        });
        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c13, theCard);
            }
        });
        c14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c14, theCard);
            }
        });
        c21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c21, theCard);
            }
        });
        c22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c22, theCard);
            }
        });
        c23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c23, theCard);
            }
        });
        c24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c24, theCard);
            }
        });
        c31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c31, theCard);
            }
        });
        c32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c32, theCard);
            }
        });
        c33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c33, theCard);
            }
        });
        c34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c34, theCard);
            }
        });
        c41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c41, theCard);
            }
        });
        c42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c42, theCard);
            }
        });
        c43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c43, theCard);
            }
        });
        c44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(c44, theCard);
            }
        });
    }

    private void frontOfCardResources(){
        image101 = R.drawable.blue;
        image102 = R.drawable.green;
        image103 = R.drawable.cyan;
        image104 = R.drawable.orange;
        image105 = R.drawable.pink;
        image106 = R.drawable.purple;
        image107 = R.drawable.red;
        image108 = R.drawable.yellow;
        image201 = R.drawable.blue;
        image202 = R.drawable.green;
        image203 = R.drawable.cyan;
        image204 = R.drawable.orange;
        image205 = R.drawable.pink;
        image206 = R.drawable.purple;
        image207 = R.drawable.red;
        image208 = R.drawable.yellow;
    }

    private void doStuff(ImageView c, int card){
        flipCard(c, card, cardNumber == 1,cardNumber == 2);

        if(cardNumber == 1){
            firstCard = cardsArray[card];
            if(firstCard>200){
                firstCard=firstCard - 100;
            }
            cardNumber = 2;
            clicked1 = card;

            c.setEnabled(false);
        } else if(cardNumber == 2) {
            secondCard = cardsArray[card];
            if (secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clicked2 = card;

            c11.setEnabled(false);
            c12.setEnabled(false);
            c13.setEnabled(false);
            c14.setEnabled(false);
            c21.setEnabled(false);
            c22.setEnabled(false);
            c23.setEnabled(false);
            c24.setEnabled(false);
            c31.setEnabled(false);
            c32.setEnabled(false);
            c33.setEnabled(false);
            c34.setEnabled(false);
            c41.setEnabled(false);
            c42.setEnabled(false);
            c43.setEnabled(false);
            c44.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    calculate();//check if the image selected equal
                }
            },1000);
        }
    }

    private void calculate(){
        if(firstCard == secondCard){
            if(clicked1 == 0){
                c11.setTag("-1");
            } else if(clicked1 == 1){
                c12.setTag("-1");
            } else if(clicked1 == 2){
                c13.setTag("-1");
            } else if(clicked1 == 3){
                c14.setTag("-1");
            } else if(clicked1 == 4){
                c21.setTag("-1");
            } else if(clicked1 == 5){
                c22.setTag("-1");
            } else if(clicked1 == 6){
                c23.setTag("-1");
            } else if(clicked1 == 7){
                c24.setTag("-1");
            } else if(clicked1 == 8){
                c31.setTag("-1");
            } else if(clicked1 == 9){
                c32.setTag("-1");
            } else if(clicked1 == 10){
                c33.setTag("-1");
            } else if(clicked1 == 11){
                c34.setTag("-1");
            } else if(clicked1 == 12){
                c41.setTag("-1");
            } else if(clicked1 == 13){
                c42.setTag("-1");
            } else if(clicked1 == 14){
                c43.setTag("-1");
            } else if(clicked1 == 15){
                c44.setTag("-1");
            }

            if(clicked2 == 0){
                c11.setTag("-1");
            } else if(clicked2 == 1){
                c12.setTag("-1");
            } else if(clicked2 == 2){
                c13.setTag("-1");
            } else if(clicked2 == 3){
                c14.setTag("-1");
            } else if(clicked2 == 4){
                c21.setTag("-1");
            } else if(clicked2 == 5){
                c22.setTag("-1");
            } else if(clicked2 == 6){
                c23.setTag("-1");
            } else if(clicked2 == 7){
                c24.setTag("-1");
            } else if(clicked2 == 8){
                c31.setTag("-1");
            } else if(clicked2 == 9){
                c32.setTag("-1");
            } else if(clicked2 == 10){
                c33.setTag("-1");
            } else if(clicked2 == 11){
                c34.setTag("-1");
            } else if(clicked2 == 12){
                c41.setTag("-1");
            } else if(clicked2 == 13){
                c42.setTag("-1");
            } else if(clicked2 == 14){
                c43.setTag("-1");
            } else if(clicked2 == 15){
                c44.setTag("-1");
            }

            //add points
            if (turn == 1){
                playerPoints++;
                comboStreak++;
                String timeString = timerValue.getText().toString(); // "00:10:691"
                time = parseTimeToSeconds(timeString); // Convert to double

                //score = (baseScore / time taken)* SpeedMultiplier + (streak * bonus)
                countScore = (int) Math.round(((10000 / time) * 1.2) + (comboStreak * 50));
                playerPoints += countScore;

                try {
                    @SuppressLint("StringFormatMatches")
                    String display = String.format(getString(R.string.prompt, playerPoints));

                    // Update the UI on the main thread
                    runOnUiThread(() -> tScore.setText(display));

                } catch (Exception e) {
                    Log.e("GameActivity2", "Error updating score", e);
                }

            }

            cardFliped++;
            Toast.makeText(this, "You found a match!", Toast.LENGTH_SHORT).show();

            checkEnd();
        } else {
            //flip over
            comboStreak = 0;

            ImageView card1 = getCardImageView(clicked1); // Get the ImageView for clicked1
            ImageView card2 = getCardImageView(clicked2); // Get the ImageView for clicked2
            flipCardBack(card1, clicked1, true); // Flip card1
            flipCardBack(card2, clicked2, true); // Flip card2
            cardNumber = 1;


            Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();

        }

        if(clicked1 != 0 || clicked2 != 0)
        {if(c11.getTag() != "-1"){
            c11.setEnabled(true);
        }}
        if(clicked1 != 1 || clicked2 != 1)
        {if(c12.getTag() != "-1"){
            c12.setEnabled(true);
        }}
        if(clicked1 != 2 || clicked2 != 2)
        {if(c13.getTag() != "-1"){
            c13.setEnabled(true);
        }}
        if(clicked1 != 3 || clicked2 != 3)
        {if(c14.getTag() != "-1"){
            c14.setEnabled(true);
        }}
        if(clicked1 != 4 || clicked2 != 4)
        {if(c21.getTag() != "-1"){
            c21.setEnabled(true);
        }}
        if(clicked1 != 5 || clicked2 != 5)
        {if(c22.getTag() != "-1"){
            c22.setEnabled(true);
        }}
        if(clicked1 != 6 || clicked2 != 6)
        {if(c23.getTag() != "-1"){
            c23.setEnabled(true);
        }}
        if(clicked1 != 7 || clicked2 != 7)
        {if(c24.getTag() != "-1"){
            c24.setEnabled(true);
        }}
        if(clicked1 != 8 || clicked2 != 8)
        {if(c31.getTag() != "-1"){
            c31.setEnabled(true);
        }}
        if(clicked1 != 9 || clicked2 != 9)
        {if(c32.getTag() != "-1"){
            c32.setEnabled(true);
        }}
        if(clicked1 != 10 || clicked2 != 10)
        {if(c33.getTag() != "-1"){
            c33.setEnabled(true);
        }}
        if(clicked1 != 11 || clicked2 != 11)
        {if(c34.getTag() != "-1"){
            c34.setEnabled(true);
        }}
        if(clicked1 != 12 || clicked2 != 12)
        {if(c41.getTag() != "-1"){
            c41.setEnabled(true);
        }}
        if(clicked1 != 13 || clicked2 != 13)
        {if(c42.getTag() != "-1"){
            c42.setEnabled(true);
        }}
        if(clicked1 != 14 || clicked2 != 14)
        {if(c43.getTag() != "-1"){
            c43.setEnabled(true);
        }}
        if(clicked1 != 15 || clicked2 != 15)
        {if(c44.getTag() != "-1"){
            c44.setEnabled(true);
        }}
        // enable the other card

        //checkEnd();
    }

    private void checkEnd(){
        if(cardFliped == 8) {


            Toast.makeText(this, "You complete the game!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
            intent.putExtra("point", String.valueOf(playerPoints));
            intent.putExtra("time", String.valueOf(timerValue.getText()));

                    startActivity(intent);
                    finish();

        }
    }

    private ImageView getCardImageView(int index) {
        switch (index) {
            case 0: return c11;
            case 1: return c12;
            case 2: return c13;
            case 3: return c14;
            case 4: return c21;
            case 5: return c22;
            case 6: return c23;
            case 7: return c24;
            case 8: return c31;
            case 9: return c32;
            case 10: return c33;
            case 11: return c34;
            case 12: return c41;
            case 13: return c42;
            case 14: return c43;
            case 15: return c44;
            default: return null;
        }
    }
    private double parseTimeToSeconds(String timeString) {
        try {
            String[] parts = timeString.split(":"); // Split by ":"

            int minutes = Integer.parseInt(parts[0]); // "00"
            int seconds = Integer.parseInt(parts[1]); // "10"
            int milliseconds = Integer.parseInt(parts[2]); // "691"

            // Convert to total seconds (e.g., 10.691 seconds)
            return minutes * 60 + seconds + (milliseconds / 1000.0);

        } catch (Exception e) {
            Log.e("GameActivity2", "Error parsing time: " + timeString, e);
            return 1.0; // Avoid division by zero in score calculation
        }
    }


}