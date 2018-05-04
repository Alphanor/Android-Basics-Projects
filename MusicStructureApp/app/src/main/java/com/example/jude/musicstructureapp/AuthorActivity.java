package com.example.jude.musicstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AuthorActivity extends AppCompatActivity {

    ImageView authorImage;
    TextView authorName;
    TextView authorSurname;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        authorImage = findViewById(R.id.author_activity_image);
        authorName = (TextView) findViewById(R.id.author_activity_name);
        authorSurname = (TextView) findViewById(R.id.author_activity_surname);

        intent = getIntent();

        authorImage.setImageResource(intent.getExtras().getInt("author_image"));
        authorName.setText(intent.getStringExtra("author_name"));
        authorSurname.setText(intent.getStringExtra("author_surname"));
    }
}
