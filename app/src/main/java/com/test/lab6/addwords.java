package com.test.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addwords extends AppCompatActivity implements View.OnClickListener {

    Button success, cancel;
    EditText en, mn;
    String LogTag = this.getClass().toString();
    String _En, _Mn,mode;

    String a = "content://com.test.lab4/words";
    Uri as = Uri.parse((a));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwords);



        Intent intent = getIntent();
        mode = intent.getExtras().getString("mode");
        Log.i(LogTag,mode);

        success = this.findViewById(R.id.Success);
        cancel = this.findViewById(R.id.Back);
        en = this.findViewById(R.id._English);
        mn = this.findViewById(R.id._Mongol);
        success.setOnClickListener(this);
        cancel.setOnClickListener(this);

        if( mode.equals("edit")){
            _En = intent.getExtras().getString("English");
            _Mn = intent.getExtras().getString("Mongol");
            en.setText(_En);
            mn.setText(_Mn);
            success.setText("Update");
        }



    }

    @Override
    public void onClick(View view) {


        if (view.getId() == success.getId()) {

            if (en.getText() != null && mn.getText() != null && mode.equals("add")) {
                ContentValues value = new ContentValues();
                value.put("mode", "add");
                value.put("en", String.valueOf(en.getText()));
                value.put("mn", String.valueOf(mn.getText()));

                Uri urll = getContentResolver().insert(as, value);
                finish();
            } else if (mode.equals("edit")) {

                ContentValues value = new ContentValues();
                value.put("mode", "edit");
                value.put("en", String.valueOf(en.getText()));
                value.put("mn", String.valueOf(mn.getText()));

                Uri urll = getContentResolver().insert(as, value);
                finish();
                Log.i(LogTag,"cliked");

            }


        } else {
            finish();

        }
    }
}