package com.example.drawonetouch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        final DrawView drawView = findViewById(R.id.draw_view);


        drawView.buttonCurveWasTouched = true;
        drawView.buttonRectangleWasTouched = false;
        drawView.buttonStraightWasTouched = false;

        Button resetButton = findViewById(R.id.button_reset);
        Button buttonCurve = findViewById(R.id.button_curve);
        Button buttonRectangle = findViewById(R.id.button_rectangle);
        Button buttonStraight = findViewById(R.id.button_straight);
        Button buttonRed = findViewById(R.id.button_red);
        Button buttonGreen = findViewById(R.id.button_green);
        Button buttonBlue = findViewById(R.id.button_blue);

        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColorForPainting("red");
            }
        });

        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColorForPainting("green");
            }
        });

        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setColorForPainting("blue");
            }
        });


        buttonCurve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.buttonCurveWasTouched = true;
                drawView.buttonRectangleWasTouched = false;
                drawView.buttonStraightWasTouched = false;
            }
        });

        buttonRectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.buttonRectangleWasTouched = true;
                drawView.buttonCurveWasTouched = false;
                drawView.buttonStraightWasTouched = false;
            }
        });

        buttonStraight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.buttonStraightWasTouched = true;
                drawView.buttonCurveWasTouched = false;
                drawView.buttonRectangleWasTouched = false;
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.reset();
            }
        });
    }
}

