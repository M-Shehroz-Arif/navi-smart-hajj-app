package com.Fyp.navismarthajj;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowList extends AppCompatActivity {
    ListView list;
    public ArrayAdapter<String> listAdapter;
    Button addcontacts, deletebtn;
    String name, phone;
    Cursor resultSet;
    ArrayList<String> values = new ArrayList<String>();
    int i = 0;
    int j = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resultSet = EmergencyAlert.mydatabase.rawQuery("Select * from Contacts",
                null);
        resultSet.moveToFirst();
        while (resultSet.isAfterLast() == false) {
            name = resultSet.getString(i);
            phone = resultSet.getString(j);
            values.add(name + "     " + phone);
            resultSet.moveToNext();

        }

        list = (ListView) findViewById(R.id.listv2);
        final ArrayAdapter<String> stlist = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        list.setAdapter(stlist);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
