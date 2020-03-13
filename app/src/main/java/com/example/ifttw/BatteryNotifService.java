package com.example.ifttw;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BatteryNotifService extends Service {

    boolean running = false;
    int batteryLowConstraint;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        running = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        batteryLowConstraint = intent.getIntExtra("BATTERY_PERCENT", 0);
        running = true;
        myThread();
        Log.d("P", "loluulololo");
        return START_STICKY;
    }

    public float batteryLevel() {
        Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        if (level == -1 || scale == -1) {
            return 50.0f;
        }

        return ((float) level / (float) scale) * 100f;
    }

    public void myThread() {
        Log.d("P", "loleeelololo");
        Thread thread = new Thread(){
            public void run() {
                try {
                    while(running) {
                        Log.d("P", "lololololo");
                        sleep(5000);
                        float batteryStatus = batteryLevel();
                        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                        if (batteryStatus <= batteryLowConstraint) {
                            wifiManager.setWifiEnabled(false);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
