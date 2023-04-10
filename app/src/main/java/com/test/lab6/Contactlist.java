package com.test.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Contactlist extends AppCompatActivity implements View.OnClickListener {

    String Logtag = this.getClass().toString();

    ListView listss;
    Button back;


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactlist);
        listss = this.findViewById(R.id.list_view);
        back = this.findViewById(R.id.back);
        back.setOnClickListener(this);


        ContentResolver contentResolver = getContentResolver();
        ArrayList<String> arrayList = new ArrayList<>();

        Uri url = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver.query(url,null,null,null,null);

        if(cursor.getCount() == 0 ){
            Log.i(Logtag,"working");
        }
        else {

            while(cursor.moveToNext()){

                arrayList.add(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                Log.i(Logtag, cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                ArrayAdapter ar  = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
                listss.setAdapter(ar);
            }
        }


}

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if(id == back.getId()){
            Intent a = new Intent();
            setResult(RESULT_OK, a);
            finish();

        }

    }
}