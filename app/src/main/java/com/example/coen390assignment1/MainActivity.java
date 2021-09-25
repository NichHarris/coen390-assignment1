package com.example.coen390assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Initialize variables
    protected TextView textView;
    protected int counter;
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
        textView = findViewById(R.id.textView);

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
                counter++;
                textView.setText("Total Count: " + counter);
            }
        });

        counterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                textView.setText("Total Count: " + counter);
            }
        });

        counterC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                textView.setText("Total Count: " + counter);
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
        String event1 = sharedPreferenceHelper.getEventName(1);
        String event2 = sharedPreferenceHelper.getEventName(2);
        String event3 = sharedPreferenceHelper.getEventName(3);

        // Update counter names
        assert event1 != null;
        counterA.setText(event1);
        assert event2 != null;
        counterB.setText(event2);
        assert event3 != null;
        counterC.setText(event3);
    }

    @Override
    protected void onStart() {
        super.onStart();

        for (int id = 1; id <= 3; id++){
            String name = sharedPreferenceHelper.getEventName(id);
            if (name == "Name goes here")
                openSettingsActivity();
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
}