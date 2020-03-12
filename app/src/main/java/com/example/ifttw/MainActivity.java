package com.example.ifttw;

import android.content.Intent;
import android.os.Bundle;

import com.example.ifttw.recyclerview.Routine;
import com.example.ifttw.recyclerview.SQLiteDatabaseHandler;
import com.example.ifttw.ui.main.FunctionalityActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.ifttw.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);

        SQLiteDatabaseHandler db = new SQLiteDatabaseHandler(this);

        //CONTOH MASUKKIN DATA
//        Routine routine1 = new Routine("Steps", "When target (6000) achieved", "Notify me");
//        Routine routine2 = new Routine("Phone Automation", "At 00:00", "Mute Phone");
//        Routine routine3 = new Routine("Battery Low Automation", "When battery less than 15%", "Turn off wifi");
//        Routine routine4 = new Routine("Reminder", "At 01.00", "Notify me that this is an inactive routine");
//        db.addRoutine(routine1, true);
//        db.addRoutine(routine2, true);
//        db.addRoutine(routine3, true);
//        db.addRoutine(routine4, false);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity(view);
            }
        });
    }

    private void startNewActivity(View view) {
        if(view.getId() == R.id.fab){
            Intent intent = new Intent(MainActivity.this, FunctionalityActivity.class);
            startActivity(intent);
        }
    }

}