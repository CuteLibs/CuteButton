package com.rejowan.cutebutton;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
 /*
     CuteButton Library for Android
     Created by K M Rejowan Ahmmed (ahmmedrejowan)
     Min Api 21
     Target Api 30
     Java Version 1.8
     */


    /*
     Copyright 2021 K M Rejowan Ahmmed (ahmmedrejowan)

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CuteButton cuteButton = findViewById(R.id.cuteButton);
        cuteButton.setText("Hi, This is Cute Button");
        cuteButton.setBackgroundColor(Color.BLUE);
        cuteButton.setDisableColor(Color.BLACK);
        cuteButton.setFocusColor(Color.CYAN);
        cuteButton.setTextStyle(CuteButton.TEXT_STYLE_BOLD);
        cuteButton.setBorderWidth(2);
        cuteButton.setBorderWidth(1);
        cuteButton.setRadius(10);
        cuteButton.setIcon(R.drawable.ic_baseline_save_24);
        cuteButton.setIconSize(25);
        cuteButton.setIconPadding(10);
        cuteButton.setIconPosition(CuteButton.POSITION_START);


    }
}