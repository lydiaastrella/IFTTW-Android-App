package com.example.ifttw;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AccelerometerService extends Service implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private float lastX, lastY, lastZ;

    private float deltaXMax = 0;
    private float deltaYMax = 0;
    private float deltaZMax = 0;

    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;

    private float vibrateThreshold = 0;

    private AlarmReceiver alarmReceiver;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d("onStartCommand","start command");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometer.getMaximumRange() / 2;
        }

        alarmReceiver = new AlarmReceiver();

        /*// Tapping the notification will open the specified Activity.
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                activityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // This always shows up in the notifications area when this Service is running.
        // TODO: String localization
        Notification not = new Notification.Builder(this).
                setContentTitle(getText(R.string.app_name)).
                setContentInfo("Doing stuff in the background...").setSmallIcon(R.mipmap.ic_launcher).
                setContentIntent(pendingIntent).build();
        startForeground(1, not);

        // Other code goes here...

        return super.onStartCommand(intent, flags, startId);*/

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
        //return null;
    }

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
