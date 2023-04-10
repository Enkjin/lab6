package com.test.lab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button contact, calendar, wordss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contact = this.findViewById(R.id.myMenu);
        contact.setOnClickListener(this);

        calendar = this.findViewById(R.id.myMenu1);
        calendar.setOnClickListener(this);

        wordss = this.findViewById(R.id.myMenu2);
        wordss.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 0);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALENDAR}, 0);
        }


        if ((ContextCompat.checkSelfPermission(this, "MyProvider._READ_PERMISSION") != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(this, new String[]{"MyProvider._READ_PERMISSION"}, 0);
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == contact.getId()) {
            Intent contactIntent = new Intent();

            contactIntent.setClass(getApplicationContext(), Contactlist.class);
            startActivity(contactIntent);
        } else if (calendar.getId() == id) {

            Intent contactIntent = new Intent();

            contactIntent.setClass(getApplicationContext(), calendarevents.class);
            startActivity(contactIntent);
        } else if (wordss.getId() == id) {
            Intent contactIntent = new Intent();
            contactIntent.setClass(getApplicationContext(), lab4words.class);
            startActivity(contactIntent);
        }

    }
}