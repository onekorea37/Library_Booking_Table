package com.example.bookingtable.reserve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookingtable.R;

public class ZoneActivity extends AppCompatActivity {

    public static Context zoneContext;
    Button buttonZone1;
    Button buttonZone2;
    Button buttonZone3;

    TextView zone1, zone2, zone3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);

        zoneContext = this;

        buttonZone1 = (Button)findViewById(R.id.buttonZone1);
        zone1 = (TextView)findViewById(R.id.zone1);
        zone2 = (TextView)findViewById(R.id.zone2);
        zone3 = (TextView)findViewById(R.id.zone3);

        buttonZone1.setOnClickListener(view -> {
            zone1.setText("zone 1");
            Intent intent = new Intent(ZoneActivity.this, DayTimeActivity.class);
            startActivity(intent);
        });

        buttonZone2 = (Button)findViewById(R.id.buttonZone2);
        buttonZone2.setBackgroundColor(Color.LTGRAY);

        buttonZone2.setOnClickListener(view -> {
            zone2.setText("zone 2");
            Intent intent = new Intent(ZoneActivity.this, DayTimeActivity.class);
            startActivity(intent);
        });

        buttonZone3 = (Button)findViewById(R.id.buttonZone3);
        buttonZone3.setBackgroundColor(Color.LTGRAY);

        buttonZone3.setOnClickListener(view -> {
            zone3.setText("zone 3");
            Intent intent = new Intent(ZoneActivity.this, DayTimeActivity.class);
            startActivity(intent);
        });

    }
}