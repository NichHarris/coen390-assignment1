package com.example.coen390assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button settings, counterA, counterB, counterC, count;
    TextView tV;
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = (Button) findViewById(R.id.settingsButton);
        counterA = (Button) findViewById(R.id.eventA);
        counterB = (Button) findViewById(R.id.eventB);
        counterC = (Button) findViewById(R.id.eventC);
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
        counterA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tV.setText("Total Count: " + counter);
            }
        });

        counterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tV.setText("Total Count: " + counter);
            }
        });

        counterC.setOnClickListener(new View.OnClickListener() {
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