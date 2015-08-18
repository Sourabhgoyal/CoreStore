package com.sourabh.appnews.core;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.sourabh.businessService.NewsService;
import com.sourabh.database.Thedb;
import com.sourabh.entity.News;
import com.sourabh.singletons.Location;
import com.sourabh.utility.Utilities;

import java.util.ArrayList;

public class AppsGridActivity extends Fragment {
    static GridView gridView;
    ArrayList<News> toStoreInDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_apps_grid, container, false);
        GridView gridView = (GridView) v.findViewById(R.id.appsGrid);
        AsyncPopulateOffers asyncPopulateOffers = new AsyncPopulateOffers();
        asyncPopulateOffers.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        return v;
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

    public void populateFilteredOffers() {
        NewsListAdapter newsListAdapter = new NewsListAdapter(getActivity(), R.layout.griditem, toStoreInDb);
        gridView.setAdapter(newsListAdapter);
    }

    private class AsyncPopulateOffers extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... arg0) {
            try {

                NewsService newsService = new NewsService();
                Thedb databaseHandler = Thedb.getInstance(getActivity());
                //news= newsService.fetchOffers(databaseHandler.getLastOfferId());

                toStoreInDb = newsService.fetchNews(databaseHandler.getLastNewsId("ALL"), Location.getInstance().getCity());

                databaseHandler.addNews(toStoreInDb, "SEEN");
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in AsyncPopulateOffer doInBackground of Offer List." + ex.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            try {
                populateFilteredOffers();

            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in AsyncPopulateOffers onPostExecute of Offer List." + ex.getMessage());
            }
        }

    }
}
