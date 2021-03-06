package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ifttw.openweather.JSONWeatherParser;
import com.example.ifttw.openweather.Weather;
import com.example.ifttw.openweather.WeatherHttpClient;
import com.example.ifttw.recyclerview.Routine;
import com.example.ifttw.recyclerview.SQLiteDatabaseHandler;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PhoneAutomation extends AppCompatActivity implements  View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    TextView timeView, timeView2, dateView;
    RadioButton rbAtTime, rbDateTime, rbOnWifi, rbOffWifi, rbOpenWeather;
    Button btnSave;
    CheckBox cbSenin, cbSelasa, cbRabu, cbKamis, cbJumat, cbSabtu, cbMinggu, cbOneTime;
    String functionality, condition, action;

    private AlarmReceiver alarmReceiver;

    final String DATE_PICKER_TAG = "DatePicker";
    final String TIME_PICKER_TIME = "TimePickerSpecificTime";
    final String TIME_PICKER_DATE_TIME = "TimePickerDateTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_automation);

        timeView = findViewById(R.id.time_view);
        timeView2 = findViewById(R.id.time_view2);
        dateView = findViewById(R.id.date_view);
        rbAtTime = findViewById(R.id.rb_at_time);
        rbDateTime = findViewById(R.id.rb_date_time);
        rbOnWifi = findViewById(R.id.onWifi);
        rbOffWifi = findViewById(R.id.offWifi);
        rbOpenWeather = findViewById(R.id.open_weather);
        btnSave = findViewById(R.id.saveAutomation);
        cbSenin = findViewById(R.id.senin);
        cbSelasa = findViewById(R.id.selasa);
        cbRabu = findViewById(R.id.rabu);
        cbKamis = findViewById(R.id.kamis);
        cbJumat = findViewById(R.id.jumat);
        cbSabtu = findViewById(R.id.sabtu);
        cbMinggu = findViewById(R.id.minggu);
        cbOneTime = findViewById(R.id.onetime);

        btnSave.setOnClickListener(this);
        timeView.setOnClickListener(this);
        timeView2.setOnClickListener(this);
        dateView.setOnClickListener(this);

        alarmReceiver = new AlarmReceiver();
    }
    @Override
    public void onDialogDateSet(String tag, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM / dd / yyyy", Locale.getDefault());

        dateView.setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public void onDialogTimeSet(String tag, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH : mm", Locale.getDefault());

        switch (tag){
            case TIME_PICKER_TIME:
                timeView.setText(dateFormat.format(calendar.getTime()));
                break;
            case TIME_PICKER_DATE_TIME:
                timeView2.setText(dateFormat.format(calendar.getTime()));
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.time_view:
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.show(getSupportFragmentManager(), TIME_PICKER_TIME);
                break;
            case R.id.time_view2:
                TimePickerFragment timePickerFragmentDateTime = new TimePickerFragment();
                timePickerFragmentDateTime.show(getSupportFragmentManager(), TIME_PICKER_DATE_TIME);
                break;
            case R.id.date_view:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), DATE_PICKER_TAG);
                break;
            case R.id.saveAutomation:
                Log.d("save clicked", "save");
                if(rbAtTime.isChecked()){
                    Log.d("rbAtTime", "rbAtTime selected");
                    if(cbOneTime.isChecked()){
                        functionality = "One time";
                        Log.d("One Time","One Time");
                        String onceTime = timeView.getText().toString();
                        int day_now = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                        if(cbSenin.isChecked()) {
                            int senin = 2;
                            condition = "Senin";
                            String formatted_new_date = getNewDate(day_now, senin);
                            Log.d("senin", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather one time", "OW one time");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,onceTime,"one time"});
                            }
                        }
                        if(cbSelasa.isChecked()){
                            int selasa = 3;
                            condition = "Selasa";
                            String formatted_new_date = getNewDate(day_now, selasa);
                            Log.d("selasa", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather one time", "OW one time");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,onceTime,"one time"});
                            }
                        }
                        if(cbRabu.isChecked()){
                            int rabu = 4;
                            condition = "Rabu";
                            String formatted_new_date = getNewDate(day_now, rabu);
                            Log.d("rabu", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather one time", "OW one time");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,onceTime,"one time"});
                            }
                        }
                        if(cbKamis.isChecked()){
                            int kamis = 5;
                            condition = "Kamis";
                            String formatted_new_date = getNewDate(day_now, kamis);
                            Log.d("kamis", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather one time", "OW one time");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,onceTime,"one time"});
                            }
                        }
                        if(cbJumat.isChecked()){
                            int jumat =6;
                            condition = "Jumat";
                            String formatted_new_date = getNewDate(day_now, jumat);
                            Log.d("jumat", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather one time", "OW one time");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,onceTime,"one time"});
                            }
                        }
                        if (cbSabtu.isChecked()) {
                            int sabtu = 7;
                            condition = "Sabtu";
                            String formatted_new_date = getNewDate(day_now, sabtu);
                            Log.d("sabtu", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather one time", "OW one time");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,onceTime,"one time"});
                            }
                        }
                        if(cbMinggu.isChecked()){
                            int minggu = 1;
                            condition = "Minggu";
                            String formatted_new_date = getNewDate(day_now, minggu);
                            Log.d("minggu", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather one time", "OW one time");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,onceTime,"one time"});
                            }
                        }
                    } else {
                        functionality = "Repeat Time";
                        Log.d("Repeat", "Repeat");
                        String repeatTime = timeView.getText().toString();
                        int day_now = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                        if (cbSenin.isChecked()) {
                            int senin = 2;
                            condition = "Senin";
                            String formatted_new_date = getNewDate(day_now, senin);
                            Log.d("senin", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather repeat", "OW repeat");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,repeatTime,"repeat"});
                            }
                        }
                        if (cbSelasa.isChecked()) {
                            int selasa = 3;
                            condition = "Selasa";
                            String formatted_new_date = getNewDate(day_now, selasa);
                            Log.d("selasa", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather repeat", "OW repeat");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,repeatTime,"repeat"});
                            }
                        }
                        if (cbRabu.isChecked()) {
                            int rabu = 4;
                            condition = "Rabu";
                            String formatted_new_date = getNewDate(day_now, rabu);
                            Log.d("rabu", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather repeat", "OW repeat");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,repeatTime,"repeat"});
                            }
                        }
                        if (cbKamis.isChecked()) {
                            int kamis = 5;
                            condition = "Kamis";
                            String formatted_new_date = getNewDate(day_now, kamis);
                            Log.d("kamis", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather repeat", "OW repeat");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,repeatTime,"repeat"});
                            }
                        }
                        if (cbJumat.isChecked()) {
                            int jumat = 6;
                            condition = "Jumat";
                            String formatted_new_date = getNewDate(day_now, jumat);
                            Log.d("jumat", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather repeat", "OW repeat");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,repeatTime,"repeat"});
                            }
                        }
                        if (cbSabtu.isChecked()) {
                            int sabtu = 7;
                            condition = "Sabtu";
                            String formatted_new_date = getNewDate(day_now, sabtu);
                            Log.d("sabtu", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather repeat", "OW repeat");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,repeatTime,"repeat"});
                            }
                        }
                        if (cbMinggu.isChecked()) {
                            int minggu = 1;
                            condition = "Minggu";
                            String formatted_new_date = getNewDate(day_now, minggu);
                            Log.d("minggu", formatted_new_date);
                            if(rbOpenWeather.isChecked()){
                                action = "OpenWeather notif";
                                Log.d("OpenWeather repeat", "OW repeat");
                                JSONWeatherTask task = new JSONWeatherTask();
                                task.execute(new String[]{"Bandung,ID",formatted_new_date,repeatTime,"repeat"});
                            }
                        }
                    }
                }else if(rbDateTime.isChecked()){
                    functionality = "Specific date and time";
                    Log.d("rbDateTime", "rbDateTime selected");
                    String onceDate = dateView.getText().toString();
                    String onceTime = timeView2.getText().toString();
                    condition = onceDate + " " + onceTime;
                    if(rbOpenWeather.isChecked()){
                        Log.d("OpenWeather datetime", "OW datetime");
                        JSONWeatherTask task = new JSONWeatherTask();
                        task.execute(new String[]{"Bandung,ID",onceDate,onceTime,"one time"});
                        action = "OpenWeather notif";
                    }
                }
                SQLiteDatabaseHandler db = new SQLiteDatabaseHandler(getApplicationContext());
                Routine addedRoutine = new Routine(functionality, condition, action);
                db.addRoutine(addedRoutine, true);

                Intent redirectIntent = new Intent(PhoneAutomation.this, MainActivity.class);
                startActivity(redirectIntent);

        }
    }

    private String getNewDate(int day_now, int day_ahead){
        SimpleDateFormat df = new SimpleDateFormat("MM / dd / yyyy");
        int selisih;
        if(day_now<=day_ahead){
            selisih = day_ahead-day_now;
        }else{
            selisih = 7-day_now+day_ahead;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, +selisih);
        Date newDate = calendar.getTime();
        return df.format(newDate);
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

            try {
                weather = JSONWeatherParser.getWeather(data);
                weather.setDate(params[1]);
                weather.setTime(params[2]);
                weather.setIsRepeat(params[3]);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;
        }


        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if(weather.getIsRepeat().equals("one time")){
                alarmReceiver.setOneTimeAlarm(getApplicationContext(), AlarmReceiver.TYPE_ONE_TIME, weather.getDate(), weather.getTime(), weather.currentCondition.getCondition()+"\n"+weather.currentCondition.getDescr());
            }else{
                alarmReceiver.setRepeatingAlarm(getApplicationContext(),AlarmReceiver.TYPE_REPEATING,weather.getDate(),weather.getTime(),weather.currentCondition.getCondition()+"\n"+weather.currentCondition.getDescr());
            }
        }
    }
}
