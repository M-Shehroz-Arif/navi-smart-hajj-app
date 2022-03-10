package com.abubakar.navismarthajj;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowGeaoDataBase extends AppCompatActivity {
    String placename, from, to, daterr;
    public ListView list;
    public ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_geao_data_base);


        Cursor resultSet = AllMapsActivity.mydatabase.rawQuery("Select * from locationpoints",
                null);
        resultSet.moveToFirst();
        while (resultSet.isAfterLast() == false) {

            placename = resultSet.getString(0);
            from = resultSet.getString(1);
            to = resultSet.getString(2);
            daterr = resultSet.getString(3);
        }




    }
}
