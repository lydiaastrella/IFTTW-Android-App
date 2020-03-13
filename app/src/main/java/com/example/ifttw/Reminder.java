package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Reminder extends AppCompatActivity implements  View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    TextView timeView, timeView2, dateView;
    EditText edtReminderTitle, edtReminderMessage;
    RadioButton rbAtTime, rbDateTime, rbNotifReminder;
    Button btnSave;
    CheckBox cbSenin, cbSelasa, cbRabu, cbKamis, cbJumat, cbSabtu, cbMinggu, cbOneTime;

    private AlarmReceiver alarmReceiver;

    final String DATE_PICKER_TAG = "DatePicker";
    final String TIME_PICKER_TIME = "TimePickerSpecificTime";
    final String TIME_PICKER_DATE_TIME = "TimePickerDateTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        timeView = findViewById(R.id.time_view);
        timeView2 = findViewById(R.id.time_view2);
        dateView = findViewById(R.id.date_view);
        edtReminderTitle = findViewById(R.id.notif_msg_title_input);
        edtReminderMessage = findViewById(R.id.notif_msg_detail_input);
        rbAtTime = findViewById(R.id.rb_at_time);
        rbDateTime = findViewById(R.id.rb_date_time);
        rbNotifReminder = findViewById(R.id.rb_notif_reminder);
        btnSave = findViewById(R.id.saveBattery);
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
            case R.id.saveBattery:
                Log.d("save clicked", "save");
                if (rbNotifReminder.isChecked()) {
                    Log.d("NotifReminder", "NotifReminder selected");
                    if (rbAtTime.isChecked()) {
                        Log.d("rbAtTime", "rbAtTime selected");
                        if(cbOneTime.isChecked()){
                            Log.d("One Time","One Time");
                            String onceTime = timeView.getText().toString();
                            String onceMessage = edtReminderMessage.getText().toString();
                            String onceTitle = edtReminderTitle.getText().toString()+"\n";
                            Log.d("message", onceTitle + onceMessage);
                            int day_now = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                            if(cbSenin.isChecked()) {
                                int senin = 2;
                                String formatted_new_date = getNewDate(day_now, senin);
                                Log.d("senin", formatted_new_date);
                                alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME, formatted_new_date, onceTime, onceTitle+onceMessage);
                            }
                            if(cbSelasa.isChecked()){
                                int selasa = 3;
                                String formatted_new_date = getNewDate(day_now, selasa);
                                Log.d("selasa", formatted_new_date);
                                alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME, formatted_new_date, onceTime, onceTitle+onceMessage);
                            }
                            if(cbRabu.isChecked()){
                                int rabu = 4;
                                String formatted_new_date = getNewDate(day_now, rabu);
                                Log.d("rabu", formatted_new_date);
                                alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME, formatted_new_date, onceTime, onceTitle+onceMessage);
                            }
                            if(cbKamis.isChecked()){
                                int kamis = 5;
                                String formatted_new_date = getNewDate(day_now, kamis);
                                Log.d("kamis", formatted_new_date);
                                alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME, formatted_new_date, onceTime, onceTitle+onceMessage);
                            }
                            if(cbJumat.isChecked()){
                                int jumat =6;
                                String formatted_new_date = getNewDate(day_now, jumat);
                                Log.d("jumat", formatted_new_date);
                                alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME, formatted_new_date, onceTime, onceTitle+onceMessage);
                            }
                            if (cbSabtu.isChecked()) {
                                int sabtu = 7;
                                String formatted_new_date = getNewDate(day_now, sabtu);
                                Log.d("sabtu", formatted_new_date);
                                alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME, formatted_new_date, onceTime, onceTitle+onceMessage);
                            }
                            if(cbMinggu.isChecked()){
                                int minggu = 1;
                                String formatted_new_date = getNewDate(day_now, minggu);
                                Log.d("minggu", formatted_new_date);
                                alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME, formatted_new_date, onceTime, onceTitle+onceMessage);
                            }
                        }else{
                            Log.d("Repeat","Repeat");
                            String repeatTime = timeView.getText().toString();
                            String repeatMessage = edtReminderMessage.getText().toString();
                            String repeatTitle = edtReminderTitle.getText().toString()+"\n";

                            int day_now = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                            if(cbSenin.isChecked()) {
                                int senin = 2;
                                String formatted_new_date = getNewDate(day_now, senin);
                                Log.d("senin", formatted_new_date);
                                alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING, formatted_new_date, repeatTime, repeatTitle+repeatMessage);
                            }
                            if(cbSelasa.isChecked()){
                                int selasa = 3;
                                String formatted_new_date = getNewDate(day_now, selasa);
                                Log.d("selasa", formatted_new_date);
                                alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING, formatted_new_date, repeatTime, repeatTitle+repeatMessage);
                            }
                            if(cbRabu.isChecked()){
                                int rabu = 4;
                                String formatted_new_date = getNewDate(day_now, rabu);
                                Log.d("rabu", formatted_new_date);
                                alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING, formatted_new_date, repeatTime, repeatTitle+repeatMessage);
                            }
                            if(cbKamis.isChecked()){
                                int kamis = 5;
                                String formatted_new_date = getNewDate(day_now, kamis);
                                Log.d("kamis", formatted_new_date);
                                alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING, formatted_new_date, repeatTime, repeatTitle+repeatMessage);
                            }
                            if(cbJumat.isChecked()){
                                int jumat =6;
                                String formatted_new_date = getNewDate(day_now, jumat);
                                Log.d("jumat", formatted_new_date);
                                alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING, formatted_new_date, repeatTime, repeatTitle+repeatMessage);
                            }
                            if (cbSabtu.isChecked()) {
                                int sabtu = 7;
                                String formatted_new_date = getNewDate(day_now, sabtu);
                                Log.d("sabtu", formatted_new_date);
                                alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING, formatted_new_date, repeatTime, repeatTitle+repeatMessage);
                            }
                            if(cbMinggu.isChecked()){
                                int minggu = 1;
                                String formatted_new_date = getNewDate(day_now, minggu);
                                Log.d("minggu", formatted_new_date);
                                alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING, formatted_new_date, repeatTime, repeatTitle+repeatMessage);
                            }
                            //alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING, repeatTime, repeatTitle+repeatMessage);
                        }
                    } else if (rbDateTime.isChecked()) {
                        Log.d("rbDateTime", "rbDateTime selected");
                        String onceDate = dateView.getText().toString();
                        String onceTime = timeView2.getText().toString();
                        String onceMessage = edtReminderMessage.getText().toString();
                        String onceTitle = edtReminderTitle.getText().toString()+"\n";

                        alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME, onceDate, onceTime, onceTitle+onceMessage);
                    }
                }
                break;
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

}
