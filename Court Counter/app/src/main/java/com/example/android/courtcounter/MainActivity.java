
package com.example.android.courtcounter;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    int scoreTeamA=0;
    int scoreTeamB=0;
    ArrayList lastAddA = new ArrayList();
    ArrayList lastAddB = new ArrayList();
    TextView scoreViewTeamA;
    TextView scoreViewTeamB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreViewTeamA = (TextView) findViewById(R.id.team_a_score);
        scoreViewTeamB = (TextView) findViewById(R.id.team_b_score);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        String scoreString = scoreViewTeamA.getText().toString();
        savedInstanceState.putString("scoreViewTeamA", scoreString);

        scoreString = scoreViewTeamB.getText().toString();
        savedInstanceState.putString("scoreViewTeamB", scoreString);

        savedInstanceState.putInt("scoreTeamA", scoreTeamA);
        savedInstanceState.putInt("scoreTeamB", scoreTeamB);

        savedInstanceState.putIntegerArrayList("lastAddA", lastAddA);

        savedInstanceState.putIntegerArrayList("lastAddB", lastAddB);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        scoreViewTeamA.setText(savedInstanceState.getString("scoreViewTeamA"));
        scoreViewTeamB.setText(savedInstanceState.getString("scoreViewTeamB"));
        scoreTeamA = savedInstanceState.getInt("scoreTeamA");
        scoreTeamB = savedInstanceState.getInt("scoreTeamB");

        lastAddA = savedInstanceState.getIntegerArrayList("lastAddA");
        lastAddB = savedInstanceState.getIntegerArrayList("lastAddB");
    }

    public void displayForTeamA(int score) {
        scoreViewTeamA.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        scoreViewTeamB.setText(String.valueOf(score));
    }

    public void addThreeforTeamA(View v) {
        scoreTeamA += 3;
        lastAddA.add(3);
        displayForTeamA(scoreTeamA);
    }

    public void addTwoforTeamA(View v) {
        scoreTeamA += 2;
        lastAddA.add(2);
        displayForTeamA(scoreTeamA);
    }

    public void addOneforTeamA(View v) {
        scoreTeamA += 1;
        lastAddA.add(1);
        displayForTeamA(scoreTeamA);
    }

    public void undoLastTeamA(View v) {
        if (scoreTeamA>0) {
            scoreTeamA -= (int) lastAddA.remove(lastAddA.size()-1);
            displayForTeamA(scoreTeamA);
        }
    }

    public void addThreeforTeamB(View v) {
        scoreTeamB += 3;
        lastAddB.add(3);
        displayForTeamB(scoreTeamB);
    }

    public void addTwoforTeamB(View v) {
        scoreTeamB += 2;
        lastAddB.add(2);
        displayForTeamB(scoreTeamB);
    }

    public void addOneforTeamB(View v) {
        scoreTeamB += 1;
        lastAddB.add(1);
        displayForTeamB(scoreTeamB);
    }

    public void undoLastTeamB(View v) {
        if (scoreTeamB>0) {
            scoreTeamB -= (int) lastAddB.remove(lastAddB.size()-1);
            displayForTeamB(scoreTeamB);
        }
    }

    public void resetScore(View v) {

        scoreTeamA = 0;
        scoreTeamB = 0;
        lastAddA.clear();
        lastAddB.clear();
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    public void scoreComparation(View v) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        if (scoreTeamA == scoreTeamB) {
            Toast.makeText(this, "The match is over!", Toast.LENGTH_SHORT).show();
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle("Same result!");
            mBuilder.setContentText("Team A and Team B have "+scoreTeamA+" points.");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(100, mBuilder.build());

        }

        else if (scoreTeamA > scoreTeamB) {

            Toast.makeText(this, "The match is over!", Toast.LENGTH_SHORT).show();
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle("Congratulations!");
            mBuilder.setContentText("Team A win the match with " + scoreTeamA + " points.");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(101, mBuilder.build());

        }

        else {

            Toast.makeText(this, "The match is over!", Toast.LENGTH_SHORT).show();
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle("Congratulations!");
            mBuilder.setContentText("Team B win the match with " + scoreTeamB + " points.");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(102, mBuilder.build());
        }

    }

}