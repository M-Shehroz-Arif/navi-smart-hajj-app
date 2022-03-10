package com.abubakar.navismarthajj;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NavigationSavedPath extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_saved_path);

        String data= getIntent().getStringExtra("keyName");
        Cursor c = AllMapsActivity.mydatabase.rawQuery("SELECT * FROM locationpoints WHERE TRIM(PLACE) = '" + data.trim() + "'", null);
        String[] latitudes = c.getString(4).split(";");
        String[] lngitudes = c.getString(5).split(";");
        int k=0;
        for(int i=1; i<=latitudes.length; i++){

            k++;
        }
        String format = "geo:0,0?q=" + Double.parseDouble( latitudes[k]) + "," + Double.parseDouble(lngitudes[k]) + "( Location title)";
        Uri gmmIntentUri = Uri.parse(format);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }
}
