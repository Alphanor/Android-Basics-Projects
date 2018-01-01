
package com.example.android.courtcounter;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    int scoreTeamA=0;
    int scoreTeamB=0;
    ArrayList lastAddA = new ArrayList();
    ArrayList lastAddB = new ArrayList();
    TextView teamA;
    TextView teamB;
    TextView scoreViewTeamA;
    TextView scoreViewTeamB;
    Button changeTeamName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamA = (TextView) findViewById(R.id.teamA);
        teamB = (TextView) findViewById(R.id.teamB);
        scoreViewTeamA = (TextView) findViewById(R.id.team_a_score);
        scoreViewTeamB = (TextView) findViewById(R.id.team_b_score);
        changeTeamName = (Button) findViewById(R.id.teamNameButton);

        changeTeamName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_layout, null);
                final EditText nameTeamA = (EditText) mView.findViewById(R.id.nameTeamA);
                final EditText nameTeamB = (EditText) mView.findViewById(R.id.nameTeamB);
                Button confirmButton = (Button) mView.findViewById(R.id.confirmButton);
                alertDialog.setView(mView);
                final AlertDialog dialog = alertDialog.create();
                dialog.show();
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!nameTeamA.getText().toString().isEmpty() && !nameTeamB.getText().toString().isEmpty()) {
                            teamA.setText(nameTeamA.getText());
                            teamB.setText(nameTeamB.getText());
                            dialog.cancel();
                        }
                        else {
                            if(nameTeamA.getText().toString().isEmpty())
                                nameTeamA.setHint("Insert a valid name!");
                            else
                                nameTeamB.setHint("Insert a valid name!");
                        }
                    }
                });


            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("scoreViewTeamA", scoreViewTeamA.getText().toString());
        savedInstanceState.putString("scoreViewTeamB", scoreViewTeamB.getText().toString());

        savedInstanceState.putInt("scoreTeamA", scoreTeamA);
        savedInstanceState.putInt("scoreTeamB", scoreTeamB);

        savedInstanceState.putIntegerArrayList("lastAddA", lastAddA);
        savedInstanceState.putIntegerArrayList("lastAddB", lastAddB);

        savedInstanceState.putString("nameTeamA", teamA.getText().toString());
        savedInstanceState.putString("nameTeamB", teamB.getText().toString());
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

        teamA.setText(savedInstanceState.getString("nameTeamA"));
        teamB.setText(savedInstanceState.getString("nameTeamB"));
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
            mBuilder.setSmallIcon(R.drawable.ball);
            mBuilder.setContentTitle("Same result!");
            mBuilder.setContentText(teamA.getText()+" and "+teamB.getText()+" have "+scoreTeamA+" points.");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, mBuilder.build());

        }

        else if (scoreTeamA > scoreTeamB) {

            Toast.makeText(this, "The match is over!", Toast.LENGTH_SHORT).show();
            mBuilder.setSmallIcon(R.drawable.ball);
            mBuilder.setContentTitle("Congratulations!");
            mBuilder.setContentText(teamA.getText()+" win the match with " + scoreTeamA + " points.");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(2, mBuilder.build());

        }

        else {

            Toast.makeText(this, "The match is over!", Toast.LENGTH_SHORT).show();
            mBuilder.setSmallIcon(R.drawable.ball);
            mBuilder.setContentTitle("Congratulations!");
            mBuilder.setContentText(teamB.getText()+" win the match with " + scoreTeamB + " points.");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(3, mBuilder.build());
        }

    }

}