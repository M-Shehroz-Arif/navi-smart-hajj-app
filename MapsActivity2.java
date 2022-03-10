package com.Fyp.navismarthajj;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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


        LatLng makkah = new LatLng(21.3891, 39.8579);
        mMap.addMarker(new MarkerOptions().position(makkah).title("Makkah"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(makkah));

        LatLng masjidharam = new LatLng(21.3879533, 39.9004594);
        mMap.addMarker(new MarkerOptions().position(masjidharam).title("Masjid e Haram"));
        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(masjidharam));

        LatLng medanarafat = new LatLng(21.3547, 39.984);
        mMap.addMarker(new MarkerOptions().position(medanarafat).title("Arafat"));
        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(medanarafat));

        LatLng minah = new LatLng(21.4133, 39.8933);
        mMap.addMarker(new MarkerOptions().position(minah).title("Mina"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(minah));

        LatLng mudalfa = new LatLng(21.3878, 39.9145);
        mMap.addMarker(new MarkerOptions().position(mudalfa).title("Muzdalifah"));
        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(mudalfa));

        LatLng ramijamrat = new LatLng(21.4224, 39.8696);
        mMap.addMarker(new MarkerOptions().position(ramijamrat).title("Rami Jamarat"));
        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(ramijamrat));


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
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
            mMap.isTrafficEnabled();
            mMap.setBuildingsEnabled(true);
        }
        catch (Exception ex){

        }

    }
}
