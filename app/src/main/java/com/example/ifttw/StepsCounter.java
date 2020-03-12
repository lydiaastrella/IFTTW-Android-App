package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class StepsCounter extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    TextView timeView, timeView2, dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_counter);

        timeView = findViewById(R.id.time_view);
        timeView2 = findViewById(R.id.time_view2);
        dateView = findViewById(R.id.date_view);

        timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Pick The Time");
            }
        });
        timeView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker2 = new TimePickerFragment();
                timePicker2.show(getSupportFragmentManager(), "Pick The Time");
            }
        });
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Pick The Date");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        TextView timeView = findViewById(R.id.time_view);
        TextView timeView2 = findViewById(R.id.time_view2);
        timeView.setText(i + " : " + i1);
        timeView2.setText(i + " : " + i1);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        TextView dateView = findViewById(R.id.date_view);
        dateView.setText(i2 + " / " + i1 + " / " + i);
    }
}
