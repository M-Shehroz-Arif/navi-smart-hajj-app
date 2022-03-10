package com.abubakar.navismarthajj;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;

/**
 * Location sample.
 *
 * Demonstrates use of the Location API to retrieve the last known location for a device.
 * This sample uses Google Play services (GoogleApiClient) but does not need to authenticate a user.
 * See https://github.com/googlesamples/android-google-accounts/tree/master/QuickStart if you are
 * also using APIs that need authentication.
 */
public class GetLocation extends AppCompatActivity implements
        ConnectionCallbacks, OnConnectionFailedListener {

    protected static final String TAG = "MainActivity";

    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient mGoogleApiClient;

    /**
     * Represents a geographical location.
     */
    protected Location mLastLocation;

    protected String mLatitudeLabel;
    protected String mLongitudeLabel;
    protected TextView mLatitudeText;
    protected TextView mLongitudeText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);



        buildGoogleApiClient();
    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
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
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {

            double a=mLastLocation.getLatitude();
            double b = mLastLocation.getLongitude();
            String strAdd = "";
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(a, b, 1);
                    if (addresses != null) {
                        Address returnedAddress = addresses.get(0);
                        StringBuilder strReturnedAddress = new StringBuilder("");

                        for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                            strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                        }
                        strAdd = strReturnedAddress.toString();

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("03127686019", null, "Save me at " + strAdd, null, null);

                    try{
                        Cursor resultSet = EmergencyAlert.mydatabase.rawQuery("Select * from ContactNe",
                                null);
                        resultSet.moveToFirst();
                        while (resultSet.isAfterLast() == false) {
                           /*
                            String name = resultSet.getString(0);
                            String phone = resultSet.getString(1);
                            Toast.makeText(getApplicationContext(),"dfsdfdsfsd",Toast.LENGTH_LONG).show();
                            resultSet.moveToNext();
                            */
                        }
                    }
                    catch (Exception ex) {
                        Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
                    }
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();

            }




           /* Intent loc = new Intent(this, MapsActivity2.class);

            Bundle params = new Bundle();
            params.putDouble("doubleA", a);
            params.putDouble("doubleB", b);
           // loc.putExtras(params);
           */



        } else {
            Toast.makeText(this, "location_detected", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }
    public String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();

                try{
                    Cursor resultSet = EmergencyAlert.mydatabase.rawQuery("Select * from ContactNe",
                            null);
                    resultSet.moveToFirst();
                    while (resultSet.isAfterLast() == false) {
                        String name = resultSet.getString(0);
                        String phone = resultSet.getString(1);
                        SmsManager smsManager = SmsManager.getDefault();

                        smsManager.sendTextMessage(phone, null, "Save me at " + strAdd, null, null);


                        resultSet.moveToNext();

                    }
                    EmergencyAlert.mydatabase.close();
                }
                catch (Exception ex) {
                    Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
                }




            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return strAdd;
    }
}
