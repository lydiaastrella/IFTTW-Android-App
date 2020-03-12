package com.example.ifttw.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ifttw.BatteryNotification;
import com.example.ifttw.MainActivity;
import com.example.ifttw.PhoneAutomation;
import com.example.ifttw.R;
import com.example.ifttw.Reminder;
import com.example.ifttw.StepsCounter;

public class FunctionalityActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStep, btnPhone, btnBattery, btnReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functionality);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnStep = findViewById(R.id.btn_step);
        btnPhone = findViewById(R.id.btn_phone_auto);
        btnBattery = findViewById(R.id.btn_battery);
        btnReminder = findViewById(R.id.btn_reminder);
        btnStep.setOnClickListener(this);
        btnPhone.setOnClickListener(this);
        btnBattery.setOnClickListener(this);
        btnReminder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_step:
                intent = new Intent(FunctionalityActivity.this, StepsCounter.class);
                startActivity(intent);
                break;
            case R.id.btn_phone_auto:
                intent = new Intent(FunctionalityActivity.this, PhoneAutomation.class);
                startActivity(intent);
                break;
            case R.id.btn_battery:
                intent = new Intent(FunctionalityActivity.this, BatteryNotification.class);
                startActivity(intent);
                break;
            case R.id.btn_reminder:
                intent = new Intent(FunctionalityActivity.this, Reminder.class);
                startActivity(intent);
                break;
        }
    }
}
