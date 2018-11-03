package com.example.metar.contacts_oc;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    SQLiteDatabase MyDB;
    ArrayList<String> data;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        listView = (ListView)findViewById(R.id.listView);

        data = new ArrayList<>();
        final ArrayList<String> OM;
        OM=new ArrayList<>();
        try {
            MyDB = this.openOrCreateDatabase("RegisteredUsers", MODE_PRIVATE, null);
            Cursor cursor = MyDB.rawQuery("SELECT * FROM RegisteredUsers", null);
            cursor.moveToFirst();
            int nameIndex = cursor.getColumnIndex("name");
            int phoneIndex = cursor.getColumnIndex("phone");
            int OMIndex = cursor.getColumnIndex("msg");

            while (cursor != null) {
                String myData = (cursor.getString(nameIndex) + " " + cursor.getString(phoneIndex));
                String OMData = cursor.getString(OMIndex);
                data.add(myData);
                OM.add(OMData);
                arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
                listView.setAdapter(arrayAdapter);
                cursor.moveToNext();
            }
        } catch (Exception e) {

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new android.support.v7.app.AlertDialog.Builder(Main2Activity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(OM.get(position))
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Main2Activity.this, "Optional Message Closed", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

    }
}















