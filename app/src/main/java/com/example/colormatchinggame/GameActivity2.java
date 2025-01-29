package com.example.colormatchinggame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.Cleaner;
import java.util.Arrays;
import java.util.Collections;


public class GameActivity2 extends AppCompatActivity {

    TextView pt;
    ImageView c11,c12,c13,c14,c21,c22,c23,c24,c31,c32,c33,c34,c41,c42,c43,c44;
    Integer[] cardsArray = {101,102,103,104,105,106,107,108,201,202,203,204,205,206,207,208};
    int image101,image102,image103,image104,image105,image106,image107,image108;
    int image201,image202,image203,image204,image205,image206,image207,image208;

    int firstCard,secondCard;
    int clicked1, clicked2;
    int cardNumber = 1;

    int turn = 1;
    int playerPoints = 0;
    int cardFliped=0;

    private Button playButton;
    private Button resetButton;
    private Button exitButton;
    private Button howToPlayButton;
    private Button aboutDeveloperButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        // Initialize buttons

        resetButton = findViewById(R.id.resetButton);
        exitButton = findViewById(R.id.exitButton);
        howToPlayButton = findViewById(R.id.howToPlayButton);
        aboutDeveloperButton = findViewById(R.id.aboutDeveloperButton);



        // Reset Button Logic
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic for resetting the game
                Toast.makeText(GameActivity2.this, "Game Reset", Toast.LENGTH_SHORT).show();
                // Reset game state here
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

        // How to Play Button Logic
        howToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch How to Play Activity
                Intent intent = new Intent(GameActivity2.this, HowToPlayActivity.class);
                startActivity(intent);
            }
        });

        // About Developer Button Logic
        aboutDeveloperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch About Developers Activity
                Intent intent = new Intent(GameActivity2.this, AboutDevelopersActivity.class);
                startActivity(intent);
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
        if(cardsArray[card] == 101){
            c.setImageResource(image101);
        } else if(cardsArray[card] == 102){
            c.setImageResource(image102);
        } else if(cardsArray[card] == 103){
            c.setImageResource(image103);
        } else if(cardsArray[card] == 104){
            c.setImageResource(image104);
        } else if(cardsArray[card] == 105){
            c.setImageResource(image105);
        } else if(cardsArray[card] == 106){
            c.setImageResource(image106);
        } else if(cardsArray[card] == 107){
            c.setImageResource(image107);
        } else if(cardsArray[card] == 108){
            c.setImageResource(image108);
        } else if(cardsArray[card] == 201){
            c.setImageResource(image201);
        } else if(cardsArray[card] == 202){
            c.setImageResource(image202);
        } else if(cardsArray[card] == 203){
            c.setImageResource(image203);
        } else if(cardsArray[card] == 204){
            c.setImageResource(image204);
        } else if(cardsArray[card] == 205){
            c.setImageResource(image205);
        } else if(cardsArray[card] == 206){
            c.setImageResource(image206);
        } else if(cardsArray[card] == 207){
            c.setImageResource(image207);
        } else if(cardsArray[card] == 208){
            c.setImageResource(image208);
        }

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
            /*if (turn == 1){
                playerPoints++;
                pt.setText(playerPoints);
            }*/

            cardFliped++;
            Toast.makeText(this, "You found a match!", Toast.LENGTH_SHORT).show();

            checkEnd();
        } else {
            //flip over

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

            Intent intent = new Intent(getApplicationContext(), GameActivity2.class);
                    startActivity(intent);
                    finish();

        }
    }
}