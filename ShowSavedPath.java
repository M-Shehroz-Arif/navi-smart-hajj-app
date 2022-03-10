package com.Fyp.navismarthajj;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class ShowSavedPath extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Polyline line;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_path);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        data= getIntent().getStringExtra("keyName");
        Cursor c = AllMapsActivity.mydatabase.rawQuery("SELECT * FROM locationpoints WHERE TRIM(PLACE) = '" + data.trim() + "'", null);
        String[] latitudes = c.getString(4).split(";");
        String[] lngitudes = c.getString(5).split(";");
        int k=1;
        for(int i=1; i<=latitudes.length; i++){
            LatLng origin = new LatLng(Double.parseDouble( latitudes[i]), Double.parseDouble(lngitudes[i]));
            //    Toast.makeText(getApplicationContext(),a+1 +") "+ latvalues.get(a+1)+ " " + lngvalues.get(a+1), Toast.LENGTH_SHORT).show();

            LatLng dest = new LatLng(Double.parseDouble(latitudes[i+1]), Double.parseDouble(latitudes[i+1]));
            //    Toast.makeText(getApplicationContext(),a+1 +") "+ latvalues.get(a+1)+ " " + lngvalues.get(a+1), Toast.LENGTH_SHORT).show();

            line = mMap.addPolyline(new PolylineOptions()
                    .add(origin, dest)
                    .width(10)
                    .color(Color.RED));
            k++;
        }
        LatLng origin = new LatLng(Double.parseDouble( latitudes[k]), Double.parseDouble(lngitudes[k]));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(origin));

        Button startnavigation = (Button) findViewById(R.id.start);
        startnavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowSavedPath.this, NavigationSavedPath.class);
                intent.putExtra("keyName", data);
                startActivity(intent);

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

    }
}
