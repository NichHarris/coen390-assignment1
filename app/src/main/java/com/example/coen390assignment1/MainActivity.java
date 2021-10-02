// COEN 390 - Assignment 1
// Nicholas Harris - 40111093
// harris.nicholas1998@gmail.com

package com.example.coen390assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coen390assignment1.Database.Config;
import com.example.coen390assignment1.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Initialize variables
    protected TextView totalCount;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    protected DatabaseHelper dbHelper;
    protected Button settings, counterA, counterB, counterC, count;
    protected ListView historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize shared pref helper
        sharedPreferenceHelper = new SharedPreferenceHelper(MainActivity.this);
        // Initialize db helper
        dbHelper = new DatabaseHelper(this, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);

        settings = (Button) findViewById(R.id.settingsButton);
        counterA = (Button) findViewById(R.id.eventA);
        counterB = (Button) findViewById(R.id.eventB);
        counterC = (Button) findViewById(R.id.eventC);
        count = (Button) findViewById(R.id.showCounts);
        totalCount = findViewById(R.id.totalCount);
        historyList = (ListView) findViewById(R.id.history_list);
        totalCount.setText(String.format("Total Count: %d", sharedPreferenceHelper.getTotalEvents()));

        // Call openSettingsActivity on click
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v){
                openSettingsActivity();
            }
        });

        // Increment Counter 1
        counterA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferenceHelper.getTotalEvents() < sharedPreferenceHelper.getMaxCount()) {
                    sharedPreferenceHelper.incrementTotalEvents();
                    sharedPreferenceHelper.incrementEventValue(0);
                    dbHelper.insertCounter("1", sharedPreferenceHelper.getEventValue(0));
                    totalCount.setText(String.format("Total Count: %d", sharedPreferenceHelper.getTotalEvents()));
                }
                else {
                    maxCountMessage();
                }
            }
        });

        // Increment Counter 2
        counterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferenceHelper.getTotalEvents() < sharedPreferenceHelper.getMaxCount()) {
                    sharedPreferenceHelper.incrementTotalEvents();
                    sharedPreferenceHelper.incrementEventValue(1);
                    dbHelper.insertCounter("2", sharedPreferenceHelper.getEventValue(1));
                    totalCount.setText(String.format("Total Count: %d", sharedPreferenceHelper.getTotalEvents()));
                } else {
                    maxCountMessage();
                }
            }
        });

        // Increment Counter 3
        counterC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferenceHelper.getTotalEvents() < sharedPreferenceHelper.getMaxCount()) {
                    sharedPreferenceHelper.incrementTotalEvents();
                    sharedPreferenceHelper.incrementEventValue(2);
                    dbHelper.insertCounter("3", sharedPreferenceHelper.getEventValue(2));
                    totalCount.setText(String.format("Total Count: %d", sharedPreferenceHelper.getTotalEvents()));
                } else {
                    maxCountMessage();
                }
            }
        });

        // Call openDataActivity on click
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferenceHelper.setEditMode(false);
                openDataActivity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalCount = findViewById(R.id.totalCount);
        totalCount.setText(String.format("Total Count: %d", sharedPreferenceHelper.getTotalEvents()));

        for (int id = 0; id < 3; id++) {
            String eventName = sharedPreferenceHelper.getEventName(id);

            switch (id){
                case 0:
                    counterA.setText(eventName);
                    break;
                case 1:
                    counterB.setText(eventName);
                    break;
                case 2:
                    counterC.setText(eventName);
                    break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        for (int id = 0; id < 3; id++) {
            String eventName = sharedPreferenceHelper.getEventName(id);
            if (eventName == null) {
                sharedPreferenceHelper.setEditMode(true);
                openSettingsActivity();
                break;
            }
        }
    }

    // Switch to Settings Activity Tab
    public void openSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    // Switch to Data Activity Tab
    public void openDataActivity() {
        Intent intent = new Intent(this, DataActivity.class);
        startActivity(intent);
    }

    // Output message when max count has been reached
    public void maxCountMessage() {
        Toast toast = Toast.makeText(getApplicationContext(), "Max count achieved", Toast.LENGTH_LONG);
        toast.show();
    }

}