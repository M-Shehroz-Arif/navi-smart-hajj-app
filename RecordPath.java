package com.abubakar.navismarthajj;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecordPath extends FragmentActivity implements OnMapReadyCallback, LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
    String mLastUpdateTime;
    ArrayList<String> latvalues = new ArrayList<String>();
    ArrayList<String> lngvalues = new ArrayList<String>();
    String lat, lng;
    Polyline line;
    LatLng current;
    int k = 0;
    int a = 0;
    Dialog dialog;
    final Context context = this;
    EditText plac, origin, dest, datee;
    ImageView savee, start, show;
    String placee,origen,destinationn,dater;
    SQLiteDatabase mydatabase;
    public RecordPath(){

    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_path);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
       try {
           mydatabase = openOrCreateDatabase("GeoPoints", MODE_ENABLE_WRITE_AHEAD_LOGGING, null);
           mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Currentlocationpoints2(PLACE TEXT, ORIGN TEXT, DESTINATION TEXT, DATER TEXT,  LAT TEXT, LNG TEXT)");
       }
       catch (Exception ex){
           Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG);
       }

        try {
            AllMapsActivity.mydatabase2.delete("locationpoints", null, null);
        }
        catch (Exception ex ){
            Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
        }
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();



        savee = (ImageView) findViewById(R.id.savebutton);
        savee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
                showluv();

            }
        });

        start = (ImageView) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        show = (ImageView) findViewById(R.id.showsavedpath);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordPath.this,ShowSavedPathList.class);
                startActivity(intent);
                finish();
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
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onStart() {
        super.onStart();

        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();

        mGoogleApiClient.disconnect();

    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

        startLocationUpdates();
    }

    protected void startLocationUpdates() {
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
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, (LocationListener) this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
    }

    private void updateUI() {

        if (null != mCurrentLocation) {
try {
    lat = String.valueOf(mCurrentLocation.getLatitude());
    lng = String.valueOf(mCurrentLocation.getLongitude());
    latvalues.add(lat);
    lngvalues.add(lng);
}
catch (Exception ex){
    Toast.makeText(getApplicationContext(), lat +" "+lng, Toast.LENGTH_LONG).show();
}
            k++;

            try {
                AllMapsActivity.mydatabase2.execSQL("INSERT INTO locationpoints VALUES('" + lat.toString() + "', '" + lng.toString() + "') ;");
                 Toast.makeText(getApplicationContext(), "Recording....", Toast.LENGTH_SHORT).show();

            }
            catch (Exception ex){
                Toast.makeText(getApplicationContext(), "Falt in rec", Toast.LENGTH_LONG).show();
            }
            try{

            if (k > 4) {

                showPath();
          //      Toast.makeText(getApplicationContext(), "Show Path", Toast.LENGTH_LONG).show();
            }
            }
            catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, (LocationListener) this);
        //    Log.d(TAG, "Location update stopped .......................");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            //    Log.d(TAG, "Location update resumed .....................");
        }
    }

    public void showPath(){

        LatLng origin = new LatLng(Double.parseDouble( latvalues.get(a+1)), Double.parseDouble(lngvalues.get(a+1)));
         //  Toast.makeText(getApplicationContext(),a+1 +") "+ latvalues.get(a+1)+ " " + lngvalues.get(a+1), Toast.LENGTH_SHORT).show();

        a++;


        LatLng dest = new LatLng(Double.parseDouble(latvalues.get(a+1)), Double.parseDouble(lngvalues.get(a+1)));
        //    Toast.makeText(getApplicationContext(),a+1 +") "+ latvalues.get(a+1)+ " " + lngvalues.get(a+1), Toast.LENGTH_SHORT).show();

        line = mMap.addPolyline(new PolylineOptions()
                .add(origin, dest)
                .width(10)
                .color(Color.RED));
    }

    public void showluv(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        View view = this.getLayoutInflater().inflate(R.layout.layout_dialog_box, null);
        builder.setView(inflater.inflate(R.layout.layout_dialog_box, null))

                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            String finallat = null;
                            String finallng = null;

                            plac = (EditText) ((Dialog) dialog).findViewById(R.id.placedialog);
                            placee = plac.getText().toString();

                            origin = (EditText) ((Dialog) dialog).findViewById(R.id.orign);
                            origen = origin.getText().toString();

                            dest = (EditText) ((Dialog) dialog).findViewById(R.id.desttext);
                            destinationn = dest.getText().toString();


                            datee = (EditText) ((Dialog) dialog).findViewById(R.id.date);
                            dater = datee.getText().toString();


                            Cursor resultSet = AllMapsActivity.mydatabase2.rawQuery("Select * from locationpoints",
                                    null);
                            resultSet.moveToFirst();
                            Toast.makeText(getApplicationContext(), "7", Toast.LENGTH_SHORT).show();
                            while (resultSet.isAfterLast() == false) {
                                finallat = resultSet.getString(0) + ";";
                                finallng = resultSet.getString(1) + ";";
                                resultSet.moveToNext();

                            }
                            try {
                                mydatabase.execSQL("INSERT INTO Currentlocationpoints2 VALUES('" + placee + "', '" + origen + "' , '" + destinationn + "', '" + dater + "' , '" + finallat + "' , '" + finallng + "' );");
                                Toast.makeText(getApplicationContext(),placee,Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(),origen,Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(),destinationn,Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(),dater,Toast.LENGTH_SHORT).show();
                            } catch (Exception ex) {
                                Toast.makeText(getApplicationContext(), "3 exep", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(getApplicationContext(), "8", Toast.LENGTH_SHORT).show();
                            plac.setText("");
                            origin.setText("");
                            dest.setText("");
                            datee.setText("");

                        } catch (Exception ex) {
                               Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
                        }

                       // dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
