package com.sourabh.gpstracking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sourabh.appnews.core.NewsList;
import com.sourabh.appnews.core.R;
import com.sourabh.database.Thedb;
import com.sourabh.gpstrackingservice.GpsTrackingInterface;
import com.sourabh.gpstrackingservice.GpsTrackingService;

import java.util.ArrayList;


public class GpsMainActivity extends Activity implements OnClickListener {
    private static final String TAG = "Debug";
    // ArrayList<Offer> offerList=null;
    AsyncFetchLocationNews asyncFetchLocationNews;
    int counter = 0;
    private LocationManager locationMangaer = null;
    private LocationListener locationListener = null;
    private ProgressBar pb = null;
    private Boolean flag = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_activity_main);
        counter = 0;

        //if you want to lock screen for always Portrait mode
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //editLocation = (EditText) findViewById(R.id.editTextLocation);


        locationMangaer = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        startSearch();

    }

    void startSearch() {
        flag = displayGpsStatus();
        if (flag) {

            Log.v(TAG, "onClick");

            // editLocation.setText("Please!! move your device to"+
            // " see the changes in coordinates."+"\nWait..");

            pb.setVisibility(View.VISIBLE);

            locationListener = new MyLocationListener();
            if (locationListener != null)
                locationMangaer.requestLocationUpdates(LocationManager
                        .GPS_PROVIDER, 0, 0, locationListener);

        } else {
            alertbox("Gps Status!!", "Your GPS is: OFF");
        }
    }

    @Override
    public void onClick(View v) {
        flag = displayGpsStatus();
        if (flag) {

            Log.v(TAG, "onClick");


            pb.setVisibility(View.VISIBLE);
            locationListener = new MyLocationListener();

            locationMangaer.requestLocationUpdates(LocationManager
                    .GPS_PROVIDER, 0, 10, locationListener);

        } else {
            alertbox("Gps Status!!", "Your GPS is: OFF");
        }

    }

    /*----Method to Check GPS is enable or disable ----- */
    private Boolean displayGpsStatus() {
        ContentResolver contentResolver = getBaseContext()
                .getContentResolver();
        boolean gpsStatus = Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
        return gpsStatus;
    }

    /*----------Method to create an AlertBox ------------- */
    protected void alertbox(String title, String mymessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your Device's GPS is Disable")
                .setCancelable(false)
                .setTitle("** Gps Status **")
                .setPositiveButton("Gps On",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // finish the current activity
                                finish();
                                Intent myIntent = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(myIntent);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // cancel the dialog box
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    //			private void bindNavigation(Offer offer) {
//
//
//				LocationOfferDetailActivity.offer=offer;
//				Intent navigationIntent=new Intent(getApplicationContext(),LocationOfferDetailActivity.class);
//				startActivity(navigationIntent);
//
//
//			}
//		});
//
//	 }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (asyncFetchLocationNews != null)
            asyncFetchLocationNews.cancel(true);
        if (locationMangaer != null && locationListener != null)
            locationMangaer.removeUpdates(locationListener);

    }

    @Override
    protected void onResume() {

        super.onResume();
        counter = 0;
        startSearch();
        Toast.makeText(getApplicationContext(), "resuming", Toast.LENGTH_LONG).show();
    }
    /* void bindList(){
         ListView categoryListView=(ListView)findViewById(R.id.locationOfferList);
		 OfferListAdapter arrayAdaofferpter = new OfferListAdapter(getApplicationContext(), R.layout.offer_row_list_layout,offerList);
	    	
			categoryListView.setAdapter(arrayAdaofferpter);
			categoryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				
					bindNavigation(offerList.get(position));
			}*/

    /*----------Listener class to get coordinates ------------- */
    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {


//	            Toast.makeText(getBaseContext(),"Location changed : Lat: " +
//	   loc.getLatitude()+ " Lng: " + loc.getLongitude(),
//	   Toast.LENGTH_SHORT).show();
            String longitude = "Longitude: " + loc.getLongitude();
            Log.v(TAG, longitude);
            String latitude = "Latitude: " + loc.getLatitude();
            Log.v(TAG, latitude);
            if (locationListener != null)
                locationMangaer.removeUpdates(locationListener);
            if (counter < 1) {
                asyncFetchLocationNews = new AsyncFetchLocationNews();
                asyncFetchLocationNews.execute(String.valueOf(loc.getLatitude()), String.valueOf(loc.getLongitude()));
                counter++;
            }
	    /*----------to get City-Name from coordinates ------------- */
	      /*
	      String cityName=null;
	      Geocoder gcd = new Geocoder(getBaseContext(),
	   Locale.getDefault());
	      List<Address>  addresses;
	      try {
	      addresses = gcd.getFromLocation(loc.getLatitude(), loc
	   .getLongitude(), 1);
	      if (addresses.size() > 0)
	         System.out.println(addresses.get(0).getLocality());
	         cityName=addresses.get(0).getLocality();
	        } catch (IOException e) {
	        e.printStackTrace();
	      }

	      String s = longitude+"\n"+latitude +
	   "\n\nMy Currrent City is: "+cityName;
	           editLocation.setText(s);
	           */
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider,
                                    int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    }

    private class AsyncFetchLocationNews extends AsyncTask<String, ArrayList<String>, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... args) {
            Thedb databaseHandler;
            GpsTrackingInterface gpsTrackingInterface = new GpsTrackingService();
            ArrayList<String> newsList = gpsTrackingInterface.fetchLocationrelatedData(args[0], args[1]);

//				databaseHandler=Thedb.getInstance(getApplicationContext());;
//				offerList= databaseHandler.fetchOffersByCompany(companyList, null);
            return newsList;
        }

        protected void onPostExecute(ArrayList<String> resultNewsIdList) {

            //Toast.makeText(getApplicationContext(), getResources().getString(R.string.Login_Successfull), Toast.LENGTH_LONG).show();
            //bindList();
            pb.setVisibility(View.INVISIBLE);
            if (locationListener != null)
                locationMangaer.removeUpdates(locationListener);
            asyncFetchLocationNews.cancel(true);
//				Intent navigationIntent=new Intent(getApplicationContext(),MainActivity.class);
            //OfferList.location=result[0]+":"+result[1];
            //OfferList.locationOffers=offerList;
            Intent intent = new Intent();

            setResult(1, intent);
            NewsList.location = "location";
            NewsList.locationNewsIdList = resultNewsIdList;

//				if(OfferList.category!=null)
//				navigationIntent.putExtra("Category", OfferList.category);
//				startActivity(navigationIntent);
            finish();
        }

    }

}