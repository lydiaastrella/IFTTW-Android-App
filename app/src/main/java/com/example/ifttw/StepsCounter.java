package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StepsCounter extends AppCompatActivity implements SensorEventListener, View.OnClickListener{

    private RadioButton rbAcceleroCondition, rbAcceleroAction;
    private Button btnSave;
    AlarmReceiver alarmReceiver;

    private float lastX, lastY, lastZ;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float deltaXMax = 0;
    private float deltaYMax = 0;
    private float deltaZMax = 0;

    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;

    private float vibrateThreshold = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_counter);

        rbAcceleroAction = findViewById(R.id.rb_notif_accelerometer);
        rbAcceleroCondition = findViewById(R.id.cond_sensor_detect);
        btnSave = findViewById(R.id.saveAccelerometer);

        btnSave.setOnClickListener(this);

        alarmReceiver = new AlarmReceiver();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.saveAccelerometer){
            if(rbAcceleroCondition.isChecked() && rbAcceleroAction.isChecked()){
                Log.d("all checked","valid");
                sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
                    accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                    sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                    vibrateThreshold = accelerometer.getMaximumRange() / 2;
                }
            }
        }
    }

    //onResume() register the accelerometer for listening the events
    /*protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }*/

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        alternateMaxValues();

        deltaX = Math.abs(lastX - sensorEvent.values[0]);
        deltaY = Math.abs(lastY - sensorEvent.values[1]);
        deltaZ = Math.abs(lastZ - sensorEvent.values[2]);

        if (deltaX < 2)
            deltaX = 0;
        if (deltaY < 2)
            deltaY = 0;
        if(deltaZ < 2)
            deltaZ = 0;
        if ((deltaX >  vibrateThreshold) || (deltaY > vibrateThreshold) || (deltaZ > vibrateThreshold)) {
            Log.d("shaking","shaking");
            SimpleDateFormat df = new SimpleDateFormat("MM / dd / yyyy");
            SimpleDateFormat tf = new SimpleDateFormat("HH : mm");
            Date now = Calendar.getInstance().getTime();
            String date = df.format(now);
            String time = tf.format(now);
            Log.d("date",date);
            Log.d("time", time);
            alarmReceiver.setOneTimeAlarm(this,AlarmReceiver.TYPE_ONE_TIME,date,time,"Accelerometer\nAcceleration changes significantly");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void alternateMaxValues() {
        if (deltaX > deltaXMax) {
            deltaXMax = deltaX;
        }
        if (deltaY > deltaYMax) {
            deltaYMax = deltaY;
        }
        if (deltaZ > deltaZMax) {
            deltaZMax = deltaZ;
        }
    }
}
