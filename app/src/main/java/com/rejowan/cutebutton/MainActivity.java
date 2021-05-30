package com.rejowan.cutebutton;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CuteButton cuteButton = findViewById(R.id.cuteButton);
        cuteButton.setOnClickListener(v -> {
            cuteButton.setBackgroundColor(Color.MAGENTA);
            cuteButton.setText("Changed");
            cuteButton.setBorderColor(Color.BLUE);
        });

    }
}