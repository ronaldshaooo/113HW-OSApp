package com.example.blackjackgameapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectBetAmount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bet_amount);

        // Display Logo Bar Code
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setDisplayShowTitleEnabled(true);  // Enable the title display
        //getSupportActionBar().setTitle("Ｏ in Strategy. 21點");   // Set the title

        // Get the Object and pass values
        PlayerRoundInformation round1 = (PlayerRoundInformation) getIntent().getSerializableExtra("roundInfo");

        // Initialize Values
        round1.setBetAmount(0);

        final TextView betAmountTextView = findViewById(R.id.betAmount);
        final Button btnBet5 = findViewById(R.id.btnBet5);
        final Button btnBet10 = findViewById(R.id.btnBet10);
        final Button btnBet20 = findViewById(R.id.btnBet20);
        final Button btnBet50 = findViewById(R.id.btnBet50);
        final Button btnBetAll = findViewById(R.id.btnBetAll);
        final Button btnSubmitBet = findViewById(R.id.btnSubmitBet);
        final Button btnBack = findViewById(R.id.btnBack);

        // Display remaining amount
        final TextView remainingAmountTextView = findViewById(R.id.remainingAmount);
        int remainingAmount = round1.getRemainingAmount();
        remainingAmountTextView.setText(String.valueOf(remainingAmount));

        // If Bankrupt
        if(round1.getRemainingAmount() <= 0){
            Intent bankrupt = new Intent(SelectBetAmount.this, GameOver.class);
            startActivity(bankrupt);
        }

        // Button Code to increase bet amount and next/back
        btnBet5.setOnClickListener(v -> {
            int betAmount = round1.getBetAmount();
            if (betAmount + 5 <= round1.getRemainingAmount()) {
                betAmount += 5;
                round1.setBetAmount(betAmount);
                betAmountTextView.setText("$" + String.valueOf(betAmount));
            }
        });

        btnBet10.setOnClickListener(v -> {
            int betAmount = round1.getBetAmount();
            if (betAmount + 10 <= round1.getRemainingAmount()) {
                betAmount += 10;
                round1.setBetAmount(betAmount);
                betAmountTextView.setText("$" + String.valueOf(betAmount));
            }
        });

        btnBet20.setOnClickListener(v -> {
            int betAmount = round1.getBetAmount();
            if (betAmount + 20 <= round1.getRemainingAmount()) {
                betAmount += 20;
                round1.setBetAmount(betAmount);
                betAmountTextView.setText("$" + String.valueOf(betAmount));
            }
        });

        btnBet50.setOnClickListener(v -> {
            int betAmount = round1.getBetAmount();
            if (betAmount + 50 <= round1.getRemainingAmount()) {
                betAmount += 50;
                round1.setBetAmount(betAmount);
                betAmountTextView.setText("$" + String.valueOf(betAmount));
            }
        });

        btnBetAll.setOnClickListener(v -> {
            round1.setBetAmount(round1.getRemainingAmount());
            betAmountTextView.setText("$" + String.valueOf(round1.getBetAmount()));
        });

        btnSubmitBet.setOnClickListener(v -> {
            if(round1.getBetAmount()!=0){
                Intent selectBetAmount = new Intent(SelectBetAmount.this, Gameplay.class);
                selectBetAmount.putExtra("roundInfo", round1);
                startActivity(selectBetAmount);
            }
            else{
                String errorMessage = "請先選擇籌碼";
                betAmountTextView.setText(errorMessage);
            }
        });

        btnBack.setOnClickListener(v -> {
            Intent howToPlay = new Intent(SelectBetAmount.this, MainActivity.class);
            startActivity(howToPlay);
        });
    }
}
