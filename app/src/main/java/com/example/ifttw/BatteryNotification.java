package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BatteryNotification extends AppCompatActivity {

    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_notification);

        buttonSave = (Button) findViewById(R.id.saveBattery);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent batteryIntent = new Intent(BatteryNotification.this, BatteryNotifService.class);
                EditText percentageText = (EditText) findViewById(R.id.percentageBattery);
                int percentageBattery = Integer.parseInt(percentageText.getText().toString());
                if (!percentageText.getText().toString().equals("")) {
                    if ((percentageBattery >= 0) && (percentageBattery <= 100)) {
                        batteryIntent.putExtra("BATTERY_PERCENT", percentageBattery);
                        Log.d("P", "lololiloli");
                        startService(batteryIntent);
                    }
                }
            }
        });
    }
}
