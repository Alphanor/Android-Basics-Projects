package com.example.jude.pcquizapp;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner, spinner2;
    RadioButton firstAnswerFirstQuestion, secondAnswerFirstQuestion, thirdAnswerFirstQuestion, firstAnswerThirdQuestion, secondAnswerThirdQuestion, thirdAnswerThirdQuestion, firstAnswerSeventhQuestion, secondAnswerSeventhQuestion, thirdAnswerSeventhQuestion, forthAnswerSeventhQuestion;
    CheckBox firstAnswerFifthQuestion, secondAnswerFifthQuestion, thirdAnswerFifthQuestion, firstAnswerEighthQuestion, secondAnswerEighthQuestion, thirdAnswerEighthQuestion, forthAnswerEighthQuestion;
    EditText secondAnswer, sixthAnswer;
    Button sendResult;
    float score=0;
    String comparation;
    ArrayAdapter<CharSequence> adapter, adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstAnswerFirstQuestion = (RadioButton) findViewById(R.id.firstAnswerFirstQuestion);
        secondAnswerFirstQuestion = (RadioButton) findViewById(R.id.secondAnswerFirstQuestion);
        thirdAnswerFirstQuestion = (RadioButton) findViewById(R.id.thirdAnswerFirstQuestion);
        firstAnswerThirdQuestion = (RadioButton) findViewById(R.id.firstAnswerThirdQuestion);
        secondAnswerThirdQuestion = (RadioButton) findViewById(R.id.secondAnswerThirdQuestion);
        thirdAnswerThirdQuestion = (RadioButton) findViewById(R.id.thirdAnswerThirdQuestion);
        firstAnswerSeventhQuestion = (RadioButton) findViewById(R.id.firstAnswerSeventhQuestion);
        secondAnswerSeventhQuestion = (RadioButton) findViewById(R.id.secondAnswerSeventhQuestion);
        thirdAnswerSeventhQuestion = (RadioButton) findViewById(R.id.thirdAnswerSeventhQuestion);
        forthAnswerSeventhQuestion = (RadioButton) findViewById(R.id.forthAnswerSeventhQuestion);

        firstAnswerFifthQuestion = (CheckBox) findViewById(R.id.firstAnswerFifthQuestion);
        secondAnswerFifthQuestion = (CheckBox) findViewById(R.id.secondAnswerFifthQuestion);
        thirdAnswerFifthQuestion = (CheckBox) findViewById(R.id.thirdAnswerFifthQuestion);
        firstAnswerEighthQuestion = (CheckBox) findViewById(R.id.firstAnswerEighthQuestion);
        secondAnswerEighthQuestion = (CheckBox) findViewById(R.id.secondAnswerEighthQuestion);
        thirdAnswerEighthQuestion = (CheckBox) findViewById(R.id.thirdAnswerEighthQuestion);

        secondAnswer = (EditText) findViewById(R.id.editTextSecondQuestion);
        sixthAnswer = (EditText) findViewById(R.id.editTextSixthAnswer);

        sendResult = (Button) findViewById(R.id.sendButton);

        sendResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendResult();
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);

        adapter = ArrayAdapter.createFromResource(this, R.array.company_choose, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.spinner2);

        adapter2 = ArrayAdapter.createFromResource(this, R.array.founder_choose, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
    }

    public void sendResult() {

        if(firstAnswerFirstQuestion.isChecked())
            score+=1;

        comparation = secondAnswer.getText().toString().toLowerCase();

        if(comparation.toLowerCase().equals("basic input output system"))
            score+=1;

        if(secondAnswerThirdQuestion.isChecked())
            score+=1;

        if(spinner.getSelectedItem().equals("Intel"))
            score+=1;

        if(secondAnswerFifthQuestion.isChecked())
            score+=0.5;

        if(thirdAnswerFifthQuestion.isChecked())
            score+=0.5;

        comparation = sixthAnswer.getText().toString().toLowerCase();

        if(comparation.toLowerCase().equals("graphic processing unit"))
            score+=1;

        if(firstAnswerSeventhQuestion.isChecked())
            score+=1;

        if(firstAnswerEighthQuestion.isChecked())
            score+=0.5;

        if(secondAnswerEighthQuestion.isChecked())
            score+=0.5;

        if(spinner2.getSelectedItem().equals("Bill Gates"))
            score+=1;

        Toast.makeText(this, "You final result is "+score+" out of 9!", Toast.LENGTH_LONG).show();

        String name = getIntent().getStringExtra("USER_NAME");
        String surname = getIntent().getStringExtra("SURNAME");

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle("Dear "+name+" "+surname)
                        .setContentText("Your final result is "+score+" out of 9!");

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotifyMgr.notify(001, mBuilder.build());

        String percentage = String.format("%.2f", (score/9)*100);

        Intent intent = new Intent(this, Result.class);
        intent.putExtra("percentage_result", percentage);
        intent.putExtra("score", String.valueOf(score));
        startActivity(intent);

        score=0;
    }

}
