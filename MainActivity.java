package com.abubakar.navismarthajj;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public int currentimageindex=0;
    //	Timer timer;
//	TimerTask task;
    ImageView slidingimage;
    TextView imagetexts;

    private int[] IMAGE_IDS = {
            R.drawable.splash0, R.drawable.splash1, R.drawable.splash2,
            R.drawable.splash3, R.drawable.splash4, R.drawable.splash5,
            R.drawable.splash6, R.drawable.splash7, R.drawable.splash8,
            R.drawable.splash9, R.drawable.splash10, R.drawable.splash11,
            R.drawable.splash12
    };

    private String[] IMAGE_TEXT = {
            "Well come to Makkah", "Top view of Makkah", "View of Comming Masjidal Harram",
            "View Of Minna", "Medany-e-Arafat", "Stay in Mudlifha", "Makkah city view", "Rammi Jamerat",
            "Majid-e-Nabvi (SAW)", "Rozay-e-Rasool (SAW)", "Majid", "Masjid", "Masjid"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {

                AnimateandSlideShow();

            }
        };

        int delay = 1000; // delay for 1 sec.

        int period = 5000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }

        }, delay, period);
    }

    private void AnimateandSlideShow() {


        slidingimage = (ImageView) findViewById(R.id.ImageView3_Left);
        imagetexts = (TextView) findViewById(R.id.textmakkah);
        slidingimage.setImageResource(IMAGE_IDS[currentimageindex % IMAGE_IDS.length]);
        imagetexts.setText(IMAGE_TEXT[currentimageindex % IMAGE_IDS.length]);
        currentimageindex++;

        Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation textimage = AnimationUtils.loadAnimation(this, R.anim.fade_in);


        slidingimage.startAnimation(rotateimage);
        imagetexts.startAnimation(textimage);

    }



        @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(MainActivity.this, AllMapsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, MakkahCity.class);
            startActivity(intent);


        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(MainActivity.this, HajjGuideline.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(MainActivity.this, EmergencyAlert.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
