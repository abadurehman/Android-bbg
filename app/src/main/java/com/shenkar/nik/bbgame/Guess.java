package com.shenkar.nik.bbgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Guess extends AppCompatActivity {

    String word;
    TextView guessbox;
    Button guessbutton;
    EditText inputword;
    //BreakoutEngine breakoutEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        int level = bbgActivity.breakoutEngine.getLevel();
        guessbox= findViewById(R.id.guessbox);
        inputword = findViewById(R.id.inputword);
        guessbutton= findViewById(R.id.guessbutton);

        final String tmpString = Arrays.toString(bbgActivity.breakoutEngine.textarray[level]);
        //final String  guessword = tmpString.replaceAll(", ", "");
        final String  guessword = tmpString.replaceAll("[^a-zA-Z0-9]", "");


        guessbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //guessbox.setText(guessword);
                word = inputword.getText().toString();
                if(word.equals(guessword)){
                    showToast();
                    bbgActivity.breakoutEngine.levelUp();
                    finish();
                }

            }
        });
    }

    private void showToast() {
        Toast.makeText(Guess.this,"YOU RIGHT! MOVING TO NEXT STEP...!",Toast.LENGTH_LONG).show();
    }


}
