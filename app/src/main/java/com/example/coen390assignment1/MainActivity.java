package com.example.coen390assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Initialize variables
    protected TextView totalCount;
    protected int counter, countA, countB, countC;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    protected Button settings, counterA, counterB, counterC, count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferenceHelper = new SharedPreferenceHelper(MainActivity.this);

        settings = (Button) findViewById(R.id.settingsButton);
        counterA = (Button) findViewById(R.id.eventA);
        counterB = (Button) findViewById(R.id.eventB);
        counterC = (Button) findViewById(R.id.eventC);
        count = (Button) findViewById(R.id.showCounts);
        totalCount = findViewById(R.id.totalCount);

        totalCount.setText(String.format("Total Count: %d", sharedPreferenceHelper.getTotalEvents()));

        // Call openSettingsActivity on click
        settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                openSettingsActivity();
            }
        });

        // Increment Counter
        counterA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < sharedPreferenceHelper.getMaxCount()){
                    counter++;
                    countA++;
                    totalCount.setText("Total Count: " + counter);
                    sharedPreferenceHelper.saveEventValue(countA, 0);
                    sharedPreferenceHelper.saveTotalEvents(counter);
                }
                else {
                    maxCountMessage();
                }
            }
        });

        counterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < sharedPreferenceHelper.getMaxCount()) {
                    counter++;
                    countB++;
                    totalCount.setText("Total Count: " + counter);
                    sharedPreferenceHelper.saveEventValue(countB, 1);
                    sharedPreferenceHelper.saveTotalEvents(counter);
                } else {
                    maxCountMessage();
                }
            }
        });

        counterC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < sharedPreferenceHelper.getMaxCount()) {
                    counter++;
                    countC++;
                    totalCount.setText("Total Count: " + counter);
                    sharedPreferenceHelper.saveEventValue(countC, 2);
                    sharedPreferenceHelper.saveTotalEvents(counter);
                } else {
                    maxCountMessage();
                }
            }
        });

        // Call openDataActivity on click
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDataActivity();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        totalCount = findViewById(R.id.totalCount);
        totalCount.setText(String.format("Total Count: %d", sharedPreferenceHelper.getTotalEvents()));

        for (int id = 0; id < 3; id++){
            String eventName = sharedPreferenceHelper.getEventName(id);
            // shouldn't need this
            if (eventName == null)
                openSettingsActivity();

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

        for (int id = 0; id < 3; id++){
            String eventName = sharedPreferenceHelper.getEventName(id);
            if (eventName == null) {
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

    public void maxCountMessage(){
        Toast toast = Toast.makeText(getApplicationContext(), "Max count achieved", Toast.LENGTH_LONG);
        toast.show();
    }

}