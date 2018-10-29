package com.example.me.quizgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    TextView nextSign;
    TextView Rules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        nextSign = (TextView)findViewById(R.id.next);
        Rules = (TextView)findViewById(R.id.rules);

        Rules.setText("1) Once the quiz has started, you cannot go back or quit till the end.\n\n" +
                      "2) No time limit.\n\n" +
                      "3) Each question worth 1 points. The maximum score is 5 for the whole quiz.\n\n"+
                      "4) At the end, the total score will be displayed along with the last three best scores.\n\n"+
                      "5) Quiz will consists of single-choice answer, muliple-choice answer and fill in the blanks.");

        nextSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RulesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


}
