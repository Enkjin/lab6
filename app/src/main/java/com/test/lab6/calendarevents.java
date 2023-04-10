package com.test.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class calendarevents extends AppCompatActivity implements View.OnClickListener {


    String Logtag = this.getClass().toString();

    ListView listss;
    Button back;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendarevents);
        listss = this.findViewById(R.id.list_view);
        back = this.findViewById(R.id.back);
        back.setOnClickListener(this);

        ArrayList<String> arrayList = new ArrayList<>();

        ContentResolver contentResolver = getContentResolver();

        Uri url = CalendarContract.Events.CONTENT_URI;
        Cursor cursor = contentResolver.query(url, null, null, null, null);
        if (cursor.getCount() == 0) {
            Log.i(Logtag, "working.........");
        } else {

            while (cursor.moveToNext()) {

                arrayList.add(cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE)));
                ArrayAdapter ar = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
                listss.setAdapter(ar);
            }
        }


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == back.getId()) {
            Intent a = new Intent();
            setResult(RESULT_OK, a);
            finish();

        }
    }
}