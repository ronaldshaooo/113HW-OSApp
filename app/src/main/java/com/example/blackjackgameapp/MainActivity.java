package com.example.blackjackgameapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnReplay), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Get the Object and pass values
        PlayerRoundInformation round1 = (PlayerRoundInformation) getIntent().getSerializableExtra("roundInfo");

        // Display Logo Bar Code
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);  // Enable the title display
        getSupportActionBar().setTitle("Blackjack Game");   // Set the title

        final EditText nameEditText = findViewById(R.id.nameHint);
        final Spinner noOfDecksSpinner = findViewById(R.id.noOfDecksSpinner);
        final Button btnStart = findViewById(R.id.btnStart);
        final Button btnHelp = findViewById(R.id.btnHelp);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Class to store information and pass around
                PlayerRoundInformation round1 = new PlayerRoundInformation();
                String name = nameEditText.getText().toString();
                int noOfDecks = Integer.parseInt(noOfDecksSpinner.getSelectedItem().toString());
                round1.setName(name);
                round1.setNoOfDecks(noOfDecks);

                // Leads to set bet amount class
                Intent selectBetAmount = new Intent(MainActivity.this, SelectBetAmount.class);
                selectBetAmount.putExtra("roundInfo", round1);
                startActivity(selectBetAmount);
            }
        });

        // How to Play Button
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectBetAmount = new Intent(MainActivity.this, HowToPlay.class);
                startActivity(selectBetAmount);
            }
        });
    }
}