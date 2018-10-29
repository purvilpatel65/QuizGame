package com.example.me.quizgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText user;
    EditText pass;
    Button InButton;
    Button UpButton;
    TextView warn;
    String UnameProvided; //dummmy variable to check if the username provided match the username provided during signUP Activity
    String PassProvided;//dummmy variable to check if the Password provided match the Password provided during signUP Activity
    boolean check;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        user = (EditText) findViewById(R.id.UsernameTxt);
        pass = (EditText) findViewById(R.id.PassTxt);
        InButton = (Button) findViewById(R.id.SignInButton);
        UpButton = (Button) findViewById(R.id.SignUpButton);
        warn = (TextView) findViewById(R.id.WR);

        warn.setVisibility(View.INVISIBLE);
        InButton.setEnabled(false);

         pref = getSharedPreferences("PREFERENCE",0);

        if(pref.contains("username") && pref.contains("pass"))
        {
            startActivity(new Intent(getApplicationContext(), RulesActivity.class));
            finish();
        }



    }

    //the below function will be called when the user hits the "signIn" button
    public void SignInLauncher(View view)
    {
        //the following if-else is to check wheather the username and paswword entered in the text boxes is same as the username and password provided during sign-up
        if((user.getText().toString().equals(UnameProvided)) && pass.getText().toString().equals(PassProvided))
        {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("username", UnameProvided);
            editor.putString("pass", PassProvided);
            editor.commit();

            Intent goNext = new Intent(SignInActivity.this,RulesActivity.class); //use intent to transfer from one activity to another
            startActivity(goNext); //start another instance of MainActivity specified by intent variable goNext
            SignInActivity.this.finish(); //finish the signIn activity once the user successfully logIn into account
        }
        else
        {
            warn.setText("Username or Password is Incorrect!");
            warn.setVisibility(View.VISIBLE);
        }
    }

    //the below function will be called when the user hits the "signUp" button
    public void SignUpLauncher(View view)
    {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivityForResult(intent,1); //this will launch an SignUp activity from which we would like some results after the signUp activity gets finished

        InButton.setEnabled(true); //make the 'signIn' button visible once the signUp activity successfuly gets finished
    }

    //when signUp Activity exits, onActivityResult() will be called with the given requestCode
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                //the below two lines assigns the username and password from SignUp Activity to respected variables
                UnameProvided = data.getStringExtra("UnameTextValue");
                PassProvided = data.getStringExtra("PassTextValue");
            }
        }
    }


}