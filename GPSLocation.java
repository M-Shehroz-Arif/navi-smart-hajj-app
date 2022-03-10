package com.abubakar.navismarthajj;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.SQLException;

public class GPSLocation extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mmMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpslocation);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LocationManager mylocman = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener myloclist = new MylocListener();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng ramijamrat = new LatLng(21.4224, 39.8696);
        mmMap.addMarker(new MarkerOptions().position(ramijamrat).title("Rami Jamarat"));
        mmMap.moveCamera(CameraUpdateFactory.newLatLng(ramijamrat));


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        try {
            mmMap.setMyLocationEnabled(true);
            mmMap.getUiSettings().setZoomControlsEnabled(true);
            mmMap.animateCamera(CameraUpdateFactory.zoomTo(10));
            mmMap.isTrafficEnabled();
        } catch (Exception ex) {

        }
    }
  /*  public void onClick(View v) {
        Context context = getApplicationContext();
        GPSDatabase myDatabase=new GPSDatabase(this);
        myDatabase.open();
        Cursor cursor=myDatabase.getAllRows();
        cursor.moveToFirst();
        listContents= new ArrayList();
        for (int i = 0; i<= cursor.getCount(); i++) {
            listContents.add("Lat=" +cursor.getString(1) +"  "+"Log "+ cursor.getString(2));
            cursor.moveToNext();
        }
        myDatabase.close();
        ListAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line, listContents);
        list=(ListView)findViewById(R.id.ListView01);
        list.setAdapter(adapter);
    }
*/

    class MylocListener implements LocationListener {
        String lat, log;
        private GoogleMap mMap;

        public void onLocationChanged(Location loc) {
            // TODO Auto-generated method stub
            String text = " My location is  Latitude =" + loc.getLatitude() + " Longitude =" + loc.getLongitude();
            String lat = loc.getLatitude() + "";
            String log = loc.getLongitude() + "";
            LatLng sydney = new LatLng(loc.getLatitude(), loc.getLongitude());
            mMap.addMarker(new MarkerOptions().position(sydney).title("Current Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            updateDatabase();
        }

        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }

        public void updateDatabase() {
            Context context = null;
            GPSDatabase myDatabase = new GPSDatabase(context);
            try {
                myDatabase.open();
                myDatabase.insertRows(lat.substring(0, 4), log.substring(0, 4));
                myDatabase.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }
}