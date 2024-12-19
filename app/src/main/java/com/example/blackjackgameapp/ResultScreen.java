package com.example.blackjackgameapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;


public class ResultScreen extends AppCompatActivity {
    MediaPlayer failedMusic;
    MediaPlayer winMusic;
    public static double winCount = 0;
    public static double loseCount = 0;
    public static double gameCount = 0;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnReplay), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Display Logo Bar Code
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setDisplayShowTitleEnabled(true);  // Enable the title display
        //getSupportActionBar().setTitle("Ｏ in Strategy. 21點");   // Set the title

        // Get the Object and pass values
        PlayerRoundInformation round1 = (PlayerRoundInformation) getIntent().getSerializableExtra("roundInfo");
        TextView betAmountTextView = findViewById(R.id.postBetAmount);
        TextView remainingAmountTextView = findViewById(R.id.postAmount);
        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView alertMessageTextView = findViewById(R.id.alertMessage);
        TextView winningRateTextView = findViewById(R.id.winningRate);

        Random rand=new Random();
        if(round1.getDealerWin()){
            failedMusic = MediaPlayer.create(this, R.raw.failed);
            failedMusic.start();

            gameCount++;
            loseCount++;
            txtTitle.setText("你輸了...");
            String[] words = {"賭博會讓你失去更多，不只是金錢，還有時間、精力、甚至朋友和家人。",
                    "賭博並不能解決問題：，如果你有經濟上的困難，賭博只會讓情況更糟。",
                    "機率永遠站在賭場這邊，每一場賭局，莊家的勝率都比你高，長久下來，你輸掉的機率遠大於贏。"};
            int j=rand.nextInt(words.length);
            alertMessageTextView.setText(words[j]);
        }
        else if(round1.getPlayerWin()){
            winMusic = MediaPlayer.create(this, R.raw.win);
            winMusic.start();
            gameCount++;
            winCount++;
            txtTitle.setText("贏拉");
            String[] words = {"別被花俏的遊戲和廣告迷惑，賭場會用各種方式吸引你，但背後的目的只有一個，就是讓你輸錢。",
                    "贏錢只是暫時的，輸錢卻是永久的，偶爾的贏錢只會讓你更想賭，但長期下來，你會輸掉更多。",
                    "賭場永遠是贏家，不論遊戲多麼簡單，賭場的規則都是設計來讓他們長期獲利。"};
            int j=rand.nextInt(words.length);
            alertMessageTextView.setText(words[j]);
        }
        else if(round1.getDraw()){
            MediaPlayer speaker_drewMusic = MediaPlayer.create(this, R.raw.failed);
            speaker_drewMusic.start();

            gameCount++;
            txtTitle.setText("平手");
            String[] words = {"賭博就像個無底洞，你投入的越多，輸得就越多。",
                    "賭場是專業的，你只是玩家，他們設計遊戲，就是為了讓你輸。",
                    "別以為運氣會一直站在你這邊，莊家才是真正的贏家。"};
            int j=rand.nextInt(words.length);
            alertMessageTextView.setText(words[j]);
        }
        BigDecimal bd = new BigDecimal(winCount / gameCount);
        BigDecimal roundOff = bd.setScale(2, RoundingMode.FLOOR);

        //BigDecimal bd = new BigDecimal((winCount / gameCount)).setScale(2, RoundingMode.HALF_UP);
        double winRate = roundOff.doubleValue() * 100;
        //double winRate = (winCount / gameCount) * 100;
        winningRateTextView.setText("目前勝率: "+winRate + "%");

        int betAmount = round1.getBetAmount();
        betAmountTextView.setText("籌碼是: $" + betAmount +"塊錢");
        int remainingAmount = round1.getRemainingAmount();
        remainingAmountTextView.setText("你口袋還有: $" + remainingAmount +"塊錢");

        Button btnNewGame = findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(v -> {
            Intent selectBetAmount = new Intent(ResultScreen.this, SelectBetAmount.class);
            selectBetAmount.putExtra("roundInfo", round1);
            startActivity(selectBetAmount);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

//        if(failedMusic != null){
//            failedMusic.release();
//        }
//
//        if(winMusic != null){
//            winMusic.release();
//        }
    }
}
