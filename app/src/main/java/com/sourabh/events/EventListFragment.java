package com.sourabh.events;

import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.sourabh.appnews.core.R;
import com.sourabh.database.Thedb;
import com.sourabh.events.businessService.EventService;
import com.sourabh.events.entity.Event;
import com.sourabh.singletons.Location;
import com.sourabh.utility.Utilities;

import java.util.ArrayList;

public class EventListFragment extends ListFragment {

    AsyncPopulateEvents asyncPopulateEvents;
    ArrayList<Event> eventList;

    EventService eventService;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        EventDetailFragment rFragment = new EventDetailFragment();
        try {
            if (eventList.size() >= position) {
                Intent eventIntent = new Intent(getActivity().getApplicationContext(), EventDetailFragment.class);
                EventDetailFragment.event = eventList.get(position);
                getActivity().startActivity(eventIntent);
                //rFragment.setEvent(eventList.get(position));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /*
        FragmentManager fragmentManager  = getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		
		// Adding a fragment to the fragment transaction
		ft.add(R.id.event_content_frame, rFragment);
		ft.addToBackStack(null);
		
		ft.commit();*/

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        initializeVariables();
        populateEvents();
        fetchEvents();
    }

    private void fetchEvents() {
        asyncPopulateEvents = new AsyncPopulateEvents();
        asyncPopulateEvents.execute();


    }

    private void initializeVariables() {
        eventService = new EventService();

    }

    private synchronized void populateEvents() {
        try {
            Thedb databaseHandler = Thedb.getInstance(getActivity());
            eventList = databaseHandler.fetchEvents(Location.getInstance().getCity());
            EventListAdapter eventListAdapter = new EventListAdapter(getActivity(), R.layout.activity_event_list_row_layout, eventList);
            setListAdapter(eventListAdapter);
        } catch (Exception ex) {
            Utilities.write("ErrorLog", "Encountered error in populateEvents in EventListFragment of event List." + ex.getMessage());
        }

    }

    private class AsyncPopulateEvents extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... arg0) {
            try {
                ArrayList<Event> toStoreInDb;
                Thedb databaseHandler = Thedb.getInstance(getActivity());
                toStoreInDb = eventService.fetchEvents(databaseHandler.getLastEventId(), Location.getInstance().getCity());

                databaseHandler.addEvent(toStoreInDb, "SEEN");
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in AsyncPopulateEvent doInBackground of event List." + ex.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            try {
                populateEvents();
                //setAutocompleteBox();
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in AsyncPopulateEvent onPostExecute of event List." + ex.getMessage());
            }
        }


    }
}
