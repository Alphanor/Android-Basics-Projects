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
    EditText editTextName;
    EditText editTextSurname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        button = (Button) findViewById(R.id.sendButton);
        editTextName = (EditText) findViewById(R.id.name_edittext);
        editTextSurname = (EditText) findViewById(R.id.surname_edittext);

    }

    public void startActivity(View v) {

        String name = editTextName.getText().toString();
        String surname = editTextSurname.getText().toString();

        if(name.equals(null) || name.length()<2) {
            Toast.makeText(this, "Plase write your name!", Toast.LENGTH_SHORT).show();
        }
        else if(surname.equals(null) || surname.length()<2) {
            Toast.makeText(this, "Plase write your surname!", Toast.LENGTH_SHORT).show();
        }
        else {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("USER_NAME", name);
            intent.putExtra("SURNAME", surname);
            startActivity(intent);
        }
    }
}
