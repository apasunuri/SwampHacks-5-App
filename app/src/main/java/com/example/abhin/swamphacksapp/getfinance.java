package com.example.abhin.swamphacksapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class getfinance extends AppCompatActivity {
    public Button contBtn;
    //TextView totalTextView;
    EditText inputAmount;
    TextView finalBudget;
    public Button calcBtn;
    public static double finalResult = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getfinance);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    public void init() {
        contBtn = (Button) findViewById(R.id.contBtn);
        calcBtn = (Button) findViewById(R.id.calcButton);
        finalBudget = (TextView) findViewById(R.id.finalBudget);
        inputAmount = (EditText) findViewById(R.id.annualIncome);
        contBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(finalBudget.getText().length() != 0) {
                    Intent button = new Intent(getfinance.this, Camera.class);
                    startActivity(button);
                }
            }
        });
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                double amount = Double.parseDouble(inputAmount.getText().toString());
                double percentage = 30.0 / 100;
                finalResult = (percentage * amount) / 52;
                finalResult = Math.round(finalResult * 100) / 100.0;
                finalBudget.setText("Your Recommended Weekly Budget is $" + finalResult);
            }
        });
    }
}