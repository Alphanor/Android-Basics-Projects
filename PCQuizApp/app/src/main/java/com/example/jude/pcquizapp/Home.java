package com.example.jude.pcquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    Button button;
    Intent intent;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = (Button) findViewById(R.id.sendButton);
        editText = (EditText) findViewById(R.id.name_edittext);

    }

    public void startActivity(View v) {

        String name = editText.getText().toString();
        if(name.equals(null) || name.length()<3) {
            Toast.makeText(this, "Plase write your name!", Toast.LENGTH_SHORT).show();
        }
        else {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("USER_NAME", name);
            startActivity(intent);
        }
    }
}
