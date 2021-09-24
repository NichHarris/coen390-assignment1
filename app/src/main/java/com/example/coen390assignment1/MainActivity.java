package com.example.coen390assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button settings, eventA, eventB, eventC, count;
    TextView tV;
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = (Button) findViewById(R.id.settingsButton);
        eventA = (Button) findViewById(R.id.eventA);
        eventB = (Button) findViewById(R.id.eventB);
        eventC = (Button) findViewById(R.id.eventC);
        count = (Button) findViewById(R.id.showCounts);
        tV = findViewById(R.id.textView);

        // Call openSettingsActivity on click
        settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                openSettingsActivity();
            }
        });

        // Increment Counter
        eventA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tV.setText("Total Count: " + counter);
            }
        });

        eventB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tV.setText("Total Count: " + counter);
            }
        });

        eventC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tV.setText("Total Count: " + counter);
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