package com.example.me.quizgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView score;
    int lastScore;
    int best1, best2, best3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        score = (TextView)findViewById(R.id.newScore);

        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        lastScore = preferences.getInt("lastScore",0);
        best1 = preferences.getInt("best1",0);
        best2 = preferences.getInt("best2",0);
        best3 = preferences.getInt("best3",0);

        if(lastScore > best3){
            best3 = lastScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.apply();
        }
        if(lastScore > best2){
            int temp = best2;
            best2 = lastScore;
            best3 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.putInt("best2", best2);
            editor.apply();
        }
        if(lastScore > best1){
            int temp = best1;
            best1 = lastScore;
            best2 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best2", best2);
            editor.putInt("best1", best1);
            editor.apply();
        }
        score.setText("LAST SCORE: " + lastScore + "\n" +
                         "BEST1: " + best1 + "\n" +
                         "BEST2: " + best2 + "\n" +
                         "BEST3: " + best3);
    }

    public void onPlayAgain(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
