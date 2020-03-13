package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;


public class StepsCounter extends AppCompatActivity implements View.OnClickListener{

    private RadioButton rbAcceleroCondition, rbAcceleroAction;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_counter);

        rbAcceleroAction = findViewById(R.id.rb_notif_accelerometer);
        rbAcceleroCondition = findViewById(R.id.cond_sensor_detect);
        btnSave = findViewById(R.id.saveAccelerometer);

        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.saveAccelerometer){
            if(rbAcceleroCondition.isChecked() && rbAcceleroAction.isChecked()) {
                Log.d("all checked", "valid");

                Intent mStartServiceIntent = new Intent(this, MyService.class);
                startService(mStartServiceIntent);

            }
        }
    }
}
