package com.test.lab6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class lab4words extends AppCompatActivity implements View.OnClickListener {


    String Logtag = lab4words.this.getClass().toString();
    ListView listss;
    Button back,add;
    ArrayList<String> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4words);

        listss = this.findViewById(R.id.list_view);
        back = this.findViewById(R.id.back);

        back.setOnClickListener(this);
        add = this.findViewById(R.id.addd);
        add.setOnClickListener(this);


        String a = "content://com.test.lab4/words";
        Uri as  = Uri.parse((a));
        Cursor c = getContentResolver().query(as,null,null,null,null);
        while(c.moveToNext()){

            arrayList.add(c.getString(1) + " : " + c.getString(2));
            ArrayAdapter ar  = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
            listss.setAdapter(ar);
        }


        listss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String data=(String)parent.getItemAtPosition(position);
                String en = data.split(" :")[0];
                String mn = data.split(": ")[1];
                Log.i(Logtag,en);
                Log.i(Logtag,mn);

                Intent editIntent = new Intent();
                editIntent.putExtra("mode","edit");
                editIntent.putExtra("English",en);
                editIntent.putExtra("Mongol",mn);
                editIntent.setClass(getApplicationContext(),addwords.class);
                startActivity(editIntent);



            }
        });

        listss.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {


                AlertDialog.Builder builder = new AlertDialog.Builder(lab4words.this);
                builder.setMessage("Итгэлтэй байна уу");
                builder.setTitle("Устгах");
                builder.setCancelable(false);
                builder.setPositiveButton("Устгах", (DialogInterface.OnClickListener) (dialog, which) -> {

                    ContentValues value = new ContentValues();
                    String data=(String)arg0.getItemAtPosition(index);
                    String en = data.split(" :")[0];
                    value.put("mode", "del");
                    value.put("en", en);

                    Uri urll = getContentResolver().insert(as, value);



                });
                builder.setNegativeButton("Болих", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();


                return true;
            }
        });



    }

    
    @Override
    protected void onResume() {
        super.onResume();
        arrayList.clear();

        String a = "content://com.test.lab4/words";
        Uri as  = Uri.parse((a));
        Cursor c = getContentResolver().query(as,null,null,null,null);
        while(c.moveToNext()){


            arrayList.add( c.getString(1) + " : " + c.getString(2));
            ArrayAdapter ar  = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
            listss.setAdapter(ar);
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
        else if( id == add.getId()){

            Intent newIntent = new Intent();
            newIntent.putExtra("mode","add");
            newIntent.setClass(getApplicationContext(),addwords.class);
            startActivity(newIntent);
        }

    }


}