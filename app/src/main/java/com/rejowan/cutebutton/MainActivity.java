package com.rejowan.cutebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CuteButton cuteButton = findViewById(R.id.cuteButton);
        cuteButton.setOnClickListener(v -> {
            Toast.makeText(this, "Testing Button", Toast.LENGTH_SHORT).show();
        });

    }
}