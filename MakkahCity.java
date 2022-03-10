package com.abubakar.navismarthajj;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MakkahCity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;

    LinearLayout hotels, retaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makkah_city);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        hotels = (LinearLayout) findViewById(R.id.hotellist);
        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakkahCity.this, AllHotels.class);
                startActivity(intent);
            }
        });

        retaurants = (LinearLayout) findViewById(R.id.restaurentlist);
        retaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakkahCity.this,Restaurants.class);
                startActivity(intent);
            }
        });


        }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
            mMap.isTrafficEnabled();
            mMap.setBuildingsEnabled(true);
        }
        catch (Exception ex){

        }
        LatLng makkah = new LatLng(21.3891, 39.8579);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(makkah));

        LatLng AlBaik = new LatLng(21.4133618,39.7928552);
        mMap.addMarker(new MarkerOptions().position(AlBaik).title("Al Baik").icon(BitmapDescriptorFactory.fromResource(R.drawable.lunch2)));

        LatLng AlBaik1 = new LatLng(21.4351237,39.8019529);
        mMap.addMarker(new MarkerOptions().position(AlBaik1).title("Al Baik").icon(BitmapDescriptorFactory.fromResource(R.drawable.lunch2)));

        LatLng Talah = new LatLng(21.5465804,39.1328561);
        mMap.addMarker(new MarkerOptions().position(Talah).title("Talah For Dates").icon(BitmapDescriptorFactory.fromResource(R.drawable.lunch2)));

        LatLng Talah1 = new LatLng(21.541845,39.1120234);
        mMap.addMarker(new MarkerOptions().position(Talah1).title("Talah For Dates").icon(BitmapDescriptorFactory.fromResource(R.drawable.lunch2)));

        LatLng Charlotte = new LatLng(21.4219752,39.7484913);
        mMap.addMarker(new MarkerOptions().position(Charlotte).title("Charlotte").icon(BitmapDescriptorFactory.fromResource(R.drawable.lunch2)));

        LatLng Atyaf = new LatLng(21.4055634,39.7676957);
        mMap.addMarker(new MarkerOptions().position(Atyaf).title("Atyaf").icon(BitmapDescriptorFactory.fromResource(R.drawable.lunch2)));

        LatLng Baharat = new LatLng(21.5727607,39.1113549);
        mMap.addMarker(new MarkerOptions().position(Baharat).title("Baharat").icon(BitmapDescriptorFactory.fromResource(R.drawable.lunch2)));

        LatLng Narjese = new LatLng(21.419907,39.8229673);
        mMap.addMarker(new MarkerOptions().position(Narjese).title("Narjes Tea Lounge").icon(BitmapDescriptorFactory.fromResource(R.drawable.lunch2)));

        LatLng Mazaq = new LatLng(21.4869649,39.9285748);
        mMap.addMarker(new MarkerOptions().position(Mazaq).title("Mazaq").icon(BitmapDescriptorFactory.fromResource(R.drawable.lunch2)));

        LatLng Ajwaa = new LatLng(21.4867168,39.7184036);
        mMap.addMarker(new MarkerOptions().position(Ajwaa).title("Ajwaa").icon(BitmapDescriptorFactory.fromResource(R.drawable.lunch2)));

        LatLng Hyatt = new LatLng(21.4206149, 39.8203111);
        mMap.addMarker(new MarkerOptions().position(Hyatt).title("Hyatt Regency Makkah").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Raffles = new LatLng(21.419198,39.8226063);
        mMap.addMarker(new MarkerOptions().position(Raffles).title("Raffles Makkah Palace").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Dar = new LatLng(21.4186451, 39.8247371);
        mMap.addMarker(new MarkerOptions().position(Dar).title("Dar Al Ghufran").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Hilton = new LatLng(21.4204507,39.8217267);
        mMap.addMarker(new MarkerOptions().position(Hilton).title("Makkah Hilton Towers").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Hajar = new LatLng(21.4185001,39.8235715);
        mMap.addMarker(new MarkerOptions().position(Hajar).title("Moevenpick Hotel & Residences Hajar Tower Makkah").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Swisstole = new LatLng(21.4180175,39.8236142);
        mMap.addMarker(new MarkerOptions().position(Swisstole).title("Swissotel Makkah").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng ZamZam = new LatLng(21.4191798,39.8222308);
        mMap.addMarker(new MarkerOptions().position(ZamZam).title("ZamZam Pullman Makkah").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Tawhid = new LatLng(21.4209732,39.8206611);
        mMap.addMarker(new MarkerOptions().position(Tawhid).title("InterContinental Dar Al Tawhid ").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Fairmont = new LatLng(21.4179663,39.8230293);
        mMap.addMarker(new MarkerOptions().position(Fairmont).title("Makkah Clock Royal Tower, A Fairmont Hotel").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Anjum = new LatLng(21.4243911,39.8178216);
        mMap.addMarker(new MarkerOptions().position(Anjum).title("Anjum Hotel Makkah").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Marriott = new LatLng(21.423697,39.8168753);
        mMap.addMarker(new MarkerOptions().position(Marriott).title("Makkah Marriott Hotel").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Kinda = new LatLng(21.4188082,39.8221968);
        mMap.addMarker(new MarkerOptions().position(Kinda).title("Elaf Kinda Hotel").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Rayhaan = new LatLng(21.4182178,39.8226977);
        mMap.addMarker(new MarkerOptions().position(Rayhaan).title("Al Marwa Rayhaan by Rotana-Makkah").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Eiman = new LatLng(21.4194985,39.8242807);
        mMap.addMarker(new MarkerOptions().position(Eiman).title("Royal Dar Al Eiman").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Meridien = new LatLng(21.4203081,39.8269012);
        mMap.addMarker(new MarkerOptions().position(Meridien).title("Le Meridien Makkah").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Orchid = new LatLng(21.4197578,39.8241569);
        mMap.addMarker(new MarkerOptions().position(Orchid).title("Al Safwah Royale Orchid").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Makarem = new LatLng(21.402561,39.8286303);
        mMap.addMarker(new MarkerOptions().position(Makarem).title("Makarem Umm Alqura Hotel").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Bakkah = new LatLng(21.4224473,39.8467612);
        mMap.addMarker(new MarkerOptions().position(Bakkah).title("Elaf Bakkah Hotel").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Shohada = new LatLng(21.4147966,39.8257881);
        mMap.addMarker(new MarkerOptions().position(Shohada).title("Hotel Al Shohada").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Drnef = new LatLng(21.3866761,39.8221507);
        mMap.addMarker(new MarkerOptions().position(Drnef).title("Drnef Hotel Makkah").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Almashaer = new LatLng(21.418114,39.8265423);
        mMap.addMarker(new MarkerOptions().position(Almashaer).title("Elaf Almashaer").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

        LatLng Infinity = new LatLng(21.4132806,39.8256862);
        mMap.addMarker(new MarkerOptions().position(Infinity).title("Infinity Hotel Makkah").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotelicon2)));

    }

    }



