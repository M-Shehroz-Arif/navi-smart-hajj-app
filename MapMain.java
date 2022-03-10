package com.abubakar.navismarthajj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MapMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_main);
        ImageView mapt = (ImageView) findViewById(R.id.mapp);
        mapt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapMain.this, MapsActivity2.class);
                startActivity(intent);

            }
        });

        LinearLayout navigatee = (LinearLayout) findViewById(R.id.navigatee);
        navigatee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        LinearLayout recordpath = (LinearLayout) findViewById(R.id.recordpathe);
        recordpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Intent intent = new Intent(MapMain.this, RecordPath2.class);
            //    startActivity(intent);

            }
        });

        LinearLayout findpath = (LinearLayout) findViewById(R.id.mape);
        findpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //    Intent intent = new Intent(MapMain.this,PathRecord.class);
         //       startActivity(intent);
            }
        });
    }
}
