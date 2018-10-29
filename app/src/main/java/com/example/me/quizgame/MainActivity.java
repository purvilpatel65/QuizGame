package com.example.me.quizgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView QuesNum;
    TextView Ques;
    RadioGroup rg;
    Button next;
    RadioButton selectedRadioButton;
    EditText fillAns;

    public CheckBox box1, box2, box3, box4;
    ArrayList<Item> items;

    int currentPos = 0;
    int result=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_answer);
        items = new ArrayList<>();
        setViewLayout();
        setUpQuestion();

        setData(currentPos);


    }

    public void click(View view)
    {
        checkAnswer(currentPos);
        if(next.getText().toString().equals("Next")) {
            currentPos++;
            if (currentPos < (items.size())) {
                if(currentPos == 4)
                {
                    setContentView(R.layout.fill_answer);
                    setViewLayout();
                    setData(currentPos);
                    if (currentPos == 4) next.setText("Submit");
                }
                else if (currentPos == 1 || currentPos == 3) {

                    setContentView(R.layout.multi_answer);
                    setViewLayout();
                    setData(currentPos);

                    //Toast.makeText(this, "Result: " + result + "/4", Toast.LENGTH_SHORT).show();



                } else {
                    setContentView(R.layout.single_answer);
                    setViewLayout();
                    setData(currentPos);

                    //Toast.makeText(this, "Result: " + result + "/4", Toast.LENGTH_SHORT).show();


                }
            }
        }
        else
        {
            SharedPreferences preferences = getSharedPreferences("PREFS",0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("lastScore", result);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    }

    public void setUpQuestion(){
        items.add(new Item(1,"Where is Italy located in Europe?", "North", "East", "South", "West", "South"));
        items.add(new Item(2,"Which countries are in Europe?", "Greece", "Burma", "Luxemburg", "Peru", "GreeceLuxemburg"));
        items.add(new Item(3,"Which is the capital of New York?", "Lindenhurst", "NYC", "Queens", "Albany", "Albany"));
        items.add(new Item(4,"Choose all the SUNY campuses:", "Farmingdale", "Oneota", "NYIT", "StonyBrook", "FarmingdaleOneotaStonyBrook"));
        items.add(new Item(5,"Where is the Effiel Tower located?", "", "", "", "", ""));

    }

    public void setData(int pos){
        if(pos==4)
        {
            QuesNum.setText(Integer.toString(items.get(pos).getNum()));
            Ques.setText(items.get(pos).getQuestion());

        }
        else if(pos==0 || pos==2) {
            QuesNum.setText(Integer.toString(items.get(pos).getNum()));
            Ques.setText(items.get(pos).getQuestion());
            ((RadioButton) rg.getChildAt(0)).setText(items.get(pos).getOption1());
            ((RadioButton) rg.getChildAt(1)).setText(items.get(pos).getOption2());
            ((RadioButton) rg.getChildAt(2)).setText(items.get(pos).getOption3());
            ((RadioButton) rg.getChildAt(3)).setText(items.get(pos).getOption4());
        }
        else
        {
            QuesNum.setText(Integer.toString(items.get(pos).getNum()));
            Ques.setText(items.get(pos).getQuestion());
            box1.setText(items.get(pos).getOption1());
            box2.setText(items.get(pos).getOption2());
            box3.setText(items.get(pos).getOption3());
            box4.setText(items.get(pos).getOption4());
        }
    }

public void setViewLayout()
{
    box1 = (CheckBox)findViewById(R.id.chkopt1);
    box2 = (CheckBox)findViewById(R.id.chkopt2);
    box3 = (CheckBox)findViewById(R.id.chkopt3);
    box4 = (CheckBox)findViewById(R.id.chkopt4);
    QuesNum = (TextView) findViewById(R.id.quesNum);
    Ques = (TextView)findViewById(R.id.ques);
    rg = (RadioGroup)findViewById(R.id.radioGroup);
    next = (Button)findViewById(R.id.nextBtn);
    fillAns = (EditText)findViewById(R.id.fillAnsTxt);
}

public void checkAnswer(int pos)
{
    switch(pos)
    {
        case 0:
        {
            if(rg.getCheckedRadioButtonId()!=-1) {
                selectedRadioButton = (RadioButton) findViewById(rg.getCheckedRadioButtonId());

                String yourVote = selectedRadioButton.getText().toString();

                if (yourVote.equals("South")) result++;
                break;
            }
            break;
        }
        case 1:
        {
            if(box1.isChecked()==true && box2.isChecked()==false && box3.isChecked()==true && box4.isChecked()==false) result++;
            break;
        }
        case 2:
        {
            if(rg.getCheckedRadioButtonId()!=-1) {
                selectedRadioButton = (RadioButton) findViewById(rg.getCheckedRadioButtonId());

                String yourVote = selectedRadioButton.getText().toString();

                if (yourVote.equals("Albany")) result++;
                break;
            }
            break;
        }
        case 3:
        {
            if(box1.isChecked()==true && box2.isChecked()==true && box3.isChecked()==false && box4.isChecked()==true) result++;
            break;
        }
        case 4:
        {
            if(fillAns.getText().toString().toLowerCase().equals("paris")) result++;
            break;
        }
    }

}

}
