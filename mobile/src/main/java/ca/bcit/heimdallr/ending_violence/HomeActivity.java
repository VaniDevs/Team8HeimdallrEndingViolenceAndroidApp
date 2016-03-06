package ca.bcit.heimdallr.ending_violence;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class HomeActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location loc;

    private ImageButton PrevThreatButton;
    private ImageButton ChildButton;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private boolean listOfChildShow = false;
    private boolean listOfPrevThreatsShow = false;

    boolean portfolioShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // Set a toolbar which will replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup the viewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // Setup the Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        // By using this method the tabs will be populated according to viewPager's count and

        // This method ensures that tab selection events update the ViewPager and page changes update the selected tab.
        tabLayout.setupWithViewPager(viewPager);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                loc = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(String provider) {}

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        System.out.println("PASS 0");
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 4000, 0, locationListener);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("PASS 1");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.INTERNET, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION

                }, 10);
                System.out.println("PASS 2");
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4000, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 4000, 0, locationListener);
            return;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.LogOut) {
            SharedPreferences preferences = getSharedPreferences("Preferences", 0);
            preferences.edit().remove("tokenVal").commit();
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                return;
        }
    }

    public void press(final View v){
        Toast.makeText(HomeActivity.this, "Your help request has been sent to the helpers",
                Toast.LENGTH_LONG).show();
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.beep);
        mp.start();
        try {
<<<<<<< HEAD
            coordinate_textview = (TextView) findViewById(R.id.coord);
            coordinate_textview.setText(loc.getLatitude() + " " + loc.getLongitude());


            Intent intent = new Intent(this, CameraActivity.class);


=======
            Intent intent = new Intent(this, CameraActivity_v2.class);
>>>>>>> 2e2e5f98a697c96b578db3ad9e8166837515babc
            new IncidentsReport(loc.getLatitude(), loc.getLongitude()).execute((Void) null);
            startActivity(intent);
        }catch(Exception e){
            ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                   press(v);
                }
            };
            worker.schedule(task,2, TimeUnit.SECONDS);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Home Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://ca.bcit.heimdallr.ending_violence/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW,
                "Home Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://ca.bcit.heimdallr.ending_violence/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0: return Fragment1.newInstance();
                case 1: return Fragment3.newInstance();
                default: return Fragment1.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Home";
            } else {
                return "Profile";
            }
        }
    }

    public void childrenDrop(View v){
        LinearLayout LL = (LinearLayout)findViewById(R.id.listOfChild);
        ChildButton = (ImageButton)findViewById(R.id.listOfChildButton);
        if(listOfChildShow) {
            LL.setVisibility(LinearLayout.GONE);
            listOfChildShow = false;
            ChildButton.setImageResource(R.drawable.down);
        } else {
            LL.setVisibility(LinearLayout.VISIBLE);
            listOfChildShow = true;
            ChildButton.setImageResource(R.drawable.up);
        }
    }

    public void prevThreatDrop(View v){
        LinearLayout LL = (LinearLayout)findViewById(R.id.listOfPrevThreats);
        PrevThreatButton = (ImageButton)findViewById(R.id.PreviousThreatsButton);

        if(listOfPrevThreatsShow) {
            LL.setVisibility(LinearLayout.GONE);
            listOfPrevThreatsShow = false;
            PrevThreatButton.setImageResource(R.drawable.down);

        } else {
            LL.setVisibility(LinearLayout.VISIBLE);
            listOfPrevThreatsShow = true;
            PrevThreatButton.setImageResource(R.drawable.up);

        }
    }

    public void saveProfile(View v){
        EditText FirstName = (EditText) findViewById(R.id.FirstName);
        String FirstNameString = FirstName.getText().toString();

        EditText LastName = (EditText) findViewById(R.id.LastName);
        String LastNameString = LastName.getText().toString();

        EditText Address = (EditText) findViewById(R.id.Address);
        String AddressString = Address.getText().toString();

        EditText PhoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        String PhoneNumberString = PhoneNumber.getText().toString();

        try {
            new updateProfile(FirstNameString,LastNameString,AddressString, PhoneNumberString).execute((Void) null);
            determineShow();
            new Fragment3().setText();
        } catch(Exception e){

        }

    }

    public void LoadEdit(View v){
        determineShow();
    }

    public void determineShow(){

        RelativeLayout PortfolioEdit = (RelativeLayout) findViewById(R.id.PortfolioEdit);
        LinearLayout Portfolio = (LinearLayout) findViewById(R.id.Portfolio);

        if(portfolioShow){
            PortfolioEdit.setVisibility(RelativeLayout.VISIBLE);
            Portfolio.setVisibility(LinearLayout.GONE);
            portfolioShow = false;
        } else {
            PortfolioEdit.setVisibility(RelativeLayout.GONE);
            Portfolio.setVisibility(LinearLayout.VISIBLE);
            portfolioShow = true;
        }
    }


    public class IncidentsReport extends AsyncTask<Void, Void, Void> {
        String response;
        double latitude;
        double longtitude;

        public IncidentsReport(double latitude, double longtitude){
            this.latitude = latitude;
            this.longtitude = longtitude;
        }
        protected Void doInBackground(Void... params){
            try {
                SharedPreferences settings;
                String text;
                String json = APIRequest.jsonIncidents(latitude, longtitude);
                System.out.println(json + " hi");

                settings = getSharedPreferences("Preferences", Context.MODE_PRIVATE); //1
                text = settings.getString("tokenVal", null);
                System.out.println("https://quiet-falls-67309.herokuapp.com/api/incident" +
                        "?api_token=" + text);

                response = APIRequest.post("https://quiet-falls-67309.herokuapp.com/api/incident" +
                        "?api_token=" + text, json);
                System.out.println(response + "RESPONSE");

            } catch (Exception e){
                System.out.println("ERROR");
            }
            return null;
        }
    }



    public class updateProfile extends AsyncTask<Void, Void, Void> {
        String response;
        String firstNameAsyncString;
        String lastNameAsyncString;
        String address;
        String phoneNumber;

        public updateProfile(String _firstNameAsyncString, String _lastNameAsyncString, String _address, String _phoneNumber) {
            firstNameAsyncString = _firstNameAsyncString;
            lastNameAsyncString = _lastNameAsyncString;
            address = _address;
            phoneNumber = _phoneNumber;
        }

        protected Void doInBackground(Void... params) {
            try {
                SharedPreferences settings;
                String text;
                String json = APIRequest.updateProfileJSON(firstNameAsyncString, lastNameAsyncString, address, phoneNumber);
                System.out.println(json + "hi");

                settings = getSharedPreferences("Preferences", Context.MODE_PRIVATE); //1
                text = settings.getString("tokenVal", null);
                System.out.println("https://quiet-falls-67309.herokuapp.com/api/profile" +
                        "?api_token=" + text);

                response = APIRequest.post("https://quiet-falls-67309.herokuapp.com/api/profile" +
                        "?api_token=" + text, json);
                System.out.println(response + "RESPONSE");

            } catch (Exception e) {
                System.out.println("ERROR");
            }
            return null;
        }
    }
}

