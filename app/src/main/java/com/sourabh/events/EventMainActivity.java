package com.sourabh.events;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.sourabh.appnews.core.AlarmReciever;
import com.sourabh.appnews.core.R;
import com.sourabh.database.Thedb;

public class EventMainActivity extends Activity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        super.onBackPressed();
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_main);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(51, 181, 229)));
        populateEvents();
        cancelNotification();
    }

    private void cancelNotification() {
        NotificationManager mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(112212);
        AlarmReciever.previousEventCount = 0;
        updateSeenStatus();
    }

    private void updateSeenStatus() {
        Thedb databaseHandler = Thedb.getInstance(getApplicationContext());
        databaseHandler.updateEventSeenStatus();

    }

    private void populateEvents() {
        EventPagerFragment rFragment = new EventPagerFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.event_content_frame, rFragment);
        //ft .addToBackStack(null);
        fragmentManager.popBackStack();
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.event_main, menu);
        return true;
    }

}
