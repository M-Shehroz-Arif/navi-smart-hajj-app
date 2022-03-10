package com.abubakar.navismarthajj;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

public class ShowDatabase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);

        Context context = getApplicationContext();

        GPSDatabase myDatabase=new GPSDatabase(this);

        try {
            myDatabase.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor=myDatabase.getAllRows();

        cursor.moveToFirst();

        ArrayList listContents = new ArrayList();

        while(cursor.isAfterLast() == false) {

            listContents.add("Lat=" + cursor.getString(0) + "  " + "Log " + cursor.getString(1));

            cursor.moveToNext();

        }

        myDatabase.close();

        ListAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line, listContents);

        ListView list = (ListView) findViewById(R.id.listView);

        list.setAdapter(adapter);

    }
}
