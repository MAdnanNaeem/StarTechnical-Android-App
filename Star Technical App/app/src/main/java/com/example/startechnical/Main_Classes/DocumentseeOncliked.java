package com.example.startechnical.Main_Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.startechnical.R;
import com.squareup.picasso.Picasso;

public class DocumentseeOncliked extends AppCompatActivity {

    ImageView imgDOncliked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentsee_oncliked);
        imgDOncliked = findViewById(R.id.imgDOncliked);
        Intent intent =getIntent();
        String name = intent.getStringExtra("imguri");

        Picasso.with(this).load(name).into(imgDOncliked);
    }
}