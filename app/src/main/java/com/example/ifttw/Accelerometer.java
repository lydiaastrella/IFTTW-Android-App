package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.ifttw.recyclerview.Routine;
import com.example.ifttw.recyclerview.SQLiteDatabaseHandler;


public class Accelerometer extends AppCompatActivity implements View.OnClickListener{

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

                Intent mStartServiceIntent = new Intent(this, AccelerometerService.class);
                startService(mStartServiceIntent);

                SQLiteDatabaseHandler db = new SQLiteDatabaseHandler(this);
                Routine accelRoutine = new Routine("Accelerometer Sensor", "When acceleration significantly changed", "Get notification");
                db.addRoutine(accelRoutine,true);

                Intent redirectIntent = new Intent(Accelerometer.this, MainActivity.class);
                startActivity(redirectIntent);

            }
        }
    }
}
