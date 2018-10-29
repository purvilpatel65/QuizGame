package com.example.me.quizgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText Fname;
    EditText Lname;
    EditText EMAIL;
    EditText Uname;
    EditText Pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Fname = (EditText)findViewById(R.id.fnameTxt);
        Lname = (EditText)findViewById(R.id.lnameTxt);
        EMAIL = (EditText)findViewById(R.id.emailTxt);
        Uname = (EditText)findViewById(R.id.unameTxt);
        Pass = (EditText)findViewById(R.id.pTxt);


    }

    public void onSubmit(View view){

        boolean a1 = validateFname(Fname);
        boolean a2 = validateLname(Lname);
        boolean a3 = validateEmail(EMAIL);
        boolean a4 = validateUname(Uname);
        boolean a5 = validatePass(Pass);


        if(a1==true && a2==true && a3==true && a4==true && a5==true)
        {

            Intent i = new Intent();
            i.putExtra("UnameTextValue", Uname.getText().toString()); //adding extended data(in this case, username) to the intent i
            i.putExtra("PassTextValue", Pass.getText().toString()); //adding extended data(in this case, password) to the intent i
            setResult(RESULT_OK, i); //sets the result that signUp Activity will return to its caller in SignIn Activity
            finish();
        }

    }

    //validate entries from Firstname
    public boolean validateFname(EditText etext){
        if(etext.getText().toString().equals("")) {
            etext.setError("First name is mandatory!");
            return false;
        }
        else if(etext.getText().toString().length()<=3 || etext.getText().toString().length()>=30) {
            etext.setError("First name must be 3 to 30 charater long inclusive!");
            return false;
        }
        else
            return true;
    }

    //validate entries from lastName
    public boolean validateLname(EditText etext){
        if(etext.getText().toString().equals("")) {
            etext.setError("Last name is mandatory!");
            return false;
        }
        else
            return true;
    }

    //validate entries from Email
    public boolean validateEmail(EditText etext){
        if(etext.getText().toString().equals("")) {
            etext.setError("Email is mandatory!");
            return false;
        }
        else if(etext.getText().toString().contains("@")==false) {
            etext.setError("Invalid Email!");
            return false;
        }
        else
            return true;
    }

    //validate entries from username
    public boolean validateUname(EditText etext){
        if(etext.getText().toString().equals(""))
            return false;
        else if(etext.getText().toString().length()<=3 || etext.getText().toString().length()>=15)
        {
            etext.setError("Username must be 3 to 15 charater long inclusive!");
            return false;
        }
        else
        {
            String dummy = etext.getText().toString();

            //checking if all the letters are lowercase or not
            for(int i=0; i<dummy.length(); i++)
            {
                if(Character.isDigit(dummy.charAt(i))==false) {
                    if (Character.isLowerCase(dummy.charAt(i)) == false) {
                        etext.setError("Username must be in all lowercase letters!");
                        return false;
                    }
                }
            }

        }


        return true;
    }

    //validate entries from password
    public boolean validatePass(EditText etext){
        if(etext.getText().toString().equals(""))
            return false;
        else if(etext.getText().toString().length()<=5 || etext.getText().toString().length()>=15)
        {
            etext.setError("Password must be 5 to 15 charater long inclusive!");
            return false;
        }
        else
        {
            String dummy = etext.getText().toString();
            boolean checkUpper = false;
            boolean checkLower = false;
            boolean checkNumber = false;

            //checking if it atleast contains one upperrcase, lowercase and one digit
            for(int i=0; i < dummy.length(); i++)
            {
                if(Character.isLowerCase(dummy.charAt(i))==true)
                    checkLower = true;
                else if(Character.isUpperCase(dummy.charAt(i))==true)
                    checkUpper = true;
                else if(Character.isDigit(dummy.charAt(i))==true)
                    checkNumber = true;
            }

            if((checkLower==false || checkUpper==false) || checkNumber==false)
            {
                etext.setError("Password must contains atleast one lowercase, one Uppercase, and one Digit!");
                return false;
            }
        }

        return true;
    }

}

