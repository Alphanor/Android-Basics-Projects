
package com.example.android.courtcounter;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    ArrayList lastAddA = new ArrayList();
    ArrayList lastAddB = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void addThreeforTeamA(View v) {
        scoreTeamA += 3;
        lastAddA.add(3);
        displayForTeamA(scoreTeamA);
    }

    public void undoLastTeamA(View v) {
        if (scoreTeamA>0) {
            scoreTeamA -= (int) lastAddA.remove(lastAddA.size()-1);
            displayForTeamA(scoreTeamA);
        }
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

        if (scoreTeamA == scoreTeamB) {
            Toast.makeText(this, "The match is over!", Toast.LENGTH_SHORT).show();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle("Same result!");
            mBuilder.setContentText("Team A and Team B have "+scoreTeamA+" points.");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, mBuilder.build());

        }

        else if (scoreTeamA > scoreTeamB) {

            Toast.makeText(this, "The match is over!", Toast.LENGTH_SHORT).show();

            NotificationCompat.Builder mBuilder2 = new NotificationCompat.Builder(this);
                mBuilder2.setSmallIcon(R.mipmap.ic_launcher);
                mBuilder2.setContentTitle("Congratulations!");
                mBuilder2.setContentText("Team A win the match with " + scoreTeamA + " points.");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, mBuilder2.build());

        }

        else if(scoreTeamA<scoreTeamB){

            Toast.makeText(this, "The match is over!", Toast.LENGTH_SHORT).show();

            NotificationCompat.Builder mBuilder3 = new NotificationCompat.Builder(this);
            mBuilder3.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder3.setContentTitle("Congratulations!");
            mBuilder3.setContentText("Team B win the match with " + scoreTeamB + " points.");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, mBuilder3.build());
        }

    }

}