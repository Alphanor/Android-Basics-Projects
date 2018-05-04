package com.example.jude.pcquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Result extends AppCompatActivity {

    TextView percentageTextView;
    TextView detailsTextView;
    Button retakeTestButton;
    Button shareButton;
    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        percentageTextView = (TextView) findViewById(R.id.percentageTextView);
        detailsTextView = (TextView) findViewById(R.id.detailsTextView);
        retakeTestButton = (Button) findViewById(R.id.retakeTestButton);
        shareButton = (Button) findViewById(R.id.shareButton);

        String percentage = getIntent().getStringExtra("percentage_result");
        score = getIntent().getStringExtra("score");

        percentageTextView.setText(percentage + "%");

        if (Float.parseFloat(percentage) <= 25)
            detailsTextView.setText("Your result isn't good, you have a lot to learn to compete in our technological world!");
        else if (Float.parseFloat(percentage) > 25 && Float.parseFloat(percentage) <= 50)
            detailsTextView.setText("You are not that much bad, but you have to stay update if you want to be relevant.");
        else if (Float.parseFloat(percentage) > 50 && Float.parseFloat(percentage) <= 75)
            detailsTextView.setText("You are surely better than the majority of the people. Keep up the good work.");
        else
            detailsTextView.setText("You are a complete geek and your friends require your help for every tech problem. ");

        retakeTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startHomeActivity();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "I have scored " + score + " " + "out of 9 on the PCAppQuiz. Check it out!");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

    }

    public void startHomeActivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
