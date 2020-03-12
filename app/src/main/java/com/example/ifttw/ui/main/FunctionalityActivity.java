package com.example.ifttw.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ifttw.MainActivity;
import com.example.ifttw.R;

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
        switch (view.getId()) {
            case R.id.btn_step:
                Log.d("ke halaman step counter","silakan ubah");
                Toast toast1 = Toast.makeText(getApplicationContext(), "ke halaman step counter",Toast.LENGTH_SHORT);
                toast1.show();
                break;
            case R.id.btn_phone_auto:
                Log.d("ke halaman phone auto","silakan ubah");
                Toast toast2 = Toast.makeText(getApplicationContext(), "ke halaman phone automation",Toast.LENGTH_SHORT);
                toast2.show();
                break;
            case R.id.btn_battery:
                Log.d("ke halaman battery","silakan ubah");
                Toast toast3 = Toast.makeText(getApplicationContext(), "ke halaman battery low automation",Toast.LENGTH_SHORT);
                toast3.show();
                break;
            case R.id.btn_reminder:
                Log.d("ke halaman reminder","silakan ubah");
                Toast toast4 = Toast.makeText(getApplicationContext(), "ke halaman reminder",Toast.LENGTH_SHORT);
                toast4.show();
                break;
        }
    }
}
