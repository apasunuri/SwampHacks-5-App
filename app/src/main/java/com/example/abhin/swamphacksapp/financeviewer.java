package com.example.abhin.swamphacksapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class financeviewer extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financeviewer);

        TextView textView = (TextView) findViewById(R.id.budgetRemaining);
        if(getfinance.finalResult < 0) {
            textView.setText("You are over your Weekly Budget");
        }
        else {
            textView.setText("$" + (getfinance.finalResult) + " Remaining in Your Weekly Budget");
        }
        String[] purchases = new String[Camera.spendings.size()];
        for(int i = 0; i < purchases.length; i++) {
            purchases[i] = "$" + Camera.spendings.get(i) + ", Date of Spending: 01/20/2019";
        }
        ListAdapter purchasesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, purchases);
        ListView purchasesListView = (ListView) findViewById(R.id.spendingsList);
        purchasesListView.setAdapter(purchasesAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}

