package com.sourabh.appnews.core;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sourabh.businessService.CategoriesService;
import com.sourabh.businessService.NewsService;
import com.sourabh.constants.AllScreens;
import com.sourabh.database.Thedb;
import com.sourabh.entity.Category;
import com.sourabh.entity.News;
import com.sourabh.entity.Subcategory;
import com.sourabh.entity.Subcategory2;
import com.sourabh.entity.Subcategory3;
import com.sourabh.singletons.Location;
import com.sourabh.utility.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class NewsList extends ListFragment {

    public static String category = "";
    public static String locality = "";
    public static String location = "";
    public static ArrayList<String> locationNewsIdList = new ArrayList<String>();
    public static HashMap<String, String> searchCriteria = new HashMap<String, String>();
    public static ArrayList<News> locationOffers = new ArrayList<News>();
    boolean flagInCategory = false;
    AsyncPopulateOffers asyncPopulateOffers;
    View v;
    ArrayList<News> news;
    Context context;
    NewsService newsService;
    CategoriesService categoryService;
    ArrayList<Category> categoryList;
    ArrayList<Subcategory> subcategoryList;
    ArrayList<Subcategory2> subcategory2List;
    ArrayList<Subcategory3> subcategory3List;
    ListView filterList;

    public static void clearAll() {
        category = "";
        locality = "";
        location = "";
        locationNewsIdList = new ArrayList<String>();
        searchCriteria = new HashMap<String, String>();
        locationOffers = new ArrayList<News>();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        asyncPopulateOffers.cancel(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);

        try {
            if (news.size() >= position) {
                Intent newsDetail = new Intent(getActivity(), NewsDetailFragment.class);
                NewsDetailFragment.news = news.get(position);
                getActivity().startActivity(newsDetail);


//				rFragment.setOffer(news.get(position));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//		FragmentManager fragmentManager  = getFragmentManager();
//		FragmentTransaction ft = fragmentManager.beginTransaction();
//
//		// Adding a fragment to the fragment transaction
//		ft.add(R.id.content_frame, rFragment);
//		ft.addToBackStack(null);
//
//		ft.commit();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        newsService = new NewsService();
        categoryService = new CategoriesService();
        filterList = (ListView) getActivity().findViewById(R.id.filter_list);
        flagInCategory = false;
        populateFilteredOffers();
        fetchOffers();

        getActivity().getActionBar().setTitle(getResources().getString(R.string.News));

    }


    private synchronized void populateFilteredOffers() {
        try {
            TextView filterCriteriaView = (TextView) getActivity().findViewById(R.id.FilterCriteria);
            filterCriteriaView.setVisibility(1);
            Thedb databaseHandler = Thedb.getInstance(getActivity());
            news = new ArrayList<News>();
            Utilities utility = new Utilities();
            if (category != "")
                searchCriteria.put("category", category);
            if (locality != "")
                searchCriteria.put(AllScreens.OTHER_CRITERIA_LOCALITY, locality);
            if (location != "") {
                searchCriteria.put(AllScreens.OTHER_CRITERIA_LOCATION, AllScreens.OTHER_CRITERIA_LOCATION);
                //searchCriteria.put("locationCompanyList",utility.listToQueryList(locationCompanyList));
            }
            news = databaseHandler.fetchAllNewss(searchCriteria);
        /*
        if(searchCriteria.get("category")!=null && searchCriteria.get("locality")!=null && !searchCriteria.get("category").equals("ALL")){
			news=databaseHandler.fetchOffersByCategoryLocality(category,locality);
			flagInCategory=true;
		}
		else if(searchCriteria.get("category")!=null && searchCriteria.get("location")!=null){
			news=locationOffers;
			news= databaseHandler.fetchOffersByCompany(locationCompanyList, category);
			flagInCategory=true;
		}else if(searchCriteria.get("location")!=null){
			news= databaseHandler.fetchOffersByCompany(locationCompanyList, null);
			flagInCategory=true;
		}else if(searchCriteria.get("category")!=null && !searchCriteria.get("category").equals("ALL")){
			news=databaseHandler.fetchOffersByCategory(category);
			AllScreens.ACTIONBARTITLE=searchCriteria.get("category");
			flagInCategory=true;
		}else if(searchCriteria.get("locality")!=null){
			news=databaseHandler.fetchOffersByCategoryLocality("ALL",locality);
			flagInCategory=true;
		}else{
			
			
				filterCriteriaView.setVisibility(-1);
			
			news=databaseHandler.fetchAllOffers(null);
		}
		*/
            if (news.size() > 0 && getActivity() != null) {
                try {
                    NewsListAdapter arrayAdanewspter = new NewsListAdapter(getActivity(), R.layout.news_row_list_layout, news);
                    setListAdapter(arrayAdanewspter);

                } catch (Exception ex) {
                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                NewsListAdapter arrayAdanewspter = new NewsListAdapter(getActivity(), R.layout.news_row_list_layout, null);
                setListAdapter(arrayAdanewspter);
            }
            try {

                final ArrayAdapter<String> arrayAdapterCriteria = new ArrayAdapter<String>(getActivity(), R.layout.filter_layout, new ArrayList<String>(searchCriteria.values()));
                filterList.setAdapter(arrayAdapterCriteria);
                filterList.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        for (Entry<String, String> entry : searchCriteria.entrySet()) {
                            if (entry.getValue().equals(new ArrayList<String>(searchCriteria.values()).get(arg2))) {
                                category = entry.getKey().equals("category") ? "" : category;
                                locality = entry.getKey().equals("locality") ? "" : locality;
                                location = entry.getKey().equals("location") ? "" : location;
                                searchCriteria.remove(entry.getKey());
                                break;
                            }

                        }
                        filterList.setAdapter(arrayAdapterCriteria);

                        populateFilteredOffers();

                    }
                });
            } catch (Exception ex) {
                if (getActivity() != null)
                    Toast.makeText(getActivity(), "Error in populating criteria :" + ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Utilities.write("ErrorLog", "Encountered error in PopulateOffers in Offer List." + ex.getMessage());
        }

        //Set the above adapter as the adapter of choice for our list

    }

    private void fetchOffers() {

        try {
            asyncPopulateOffers = new AsyncPopulateOffers();
            asyncPopulateOffers.execute();
        } catch (Exception ex) {
            Utilities.write("ErrorLog", "Encountered error in fetchOffers of Offer List." + ex.getMessage());
        }
    }

    public void setCategory(String category) {
        NewsList.category = category;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        NewsList.locality = locality;
    }

    private void setAutocompleteBox() {
        try {
            AutoCompleteTextView actv;
            actv = (AutoCompleteTextView) getActivity().findViewById(R.id.autoCompleteTextView1);
            Thedb databaseHandler = Thedb.getInstance(getActivity());
            ArrayList<String> localityList = databaseHandler.getLocalityList();
            ArrayAdapter adapter = new ArrayAdapter
                    (getActivity(), android.R.layout.simple_list_item_1, localityList);
            actv.setAdapter(adapter);
        } catch (Exception ex) {
            Utilities.write("ErrorLog", "Encountered error in setAutoCompleteBox of Offer list." + ex.getMessage());
        }
    }

    private class AsyncPopulateOffers extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... arg0) {
            try {
                ArrayList<News> toStoreInDb;
                Thedb databaseHandler = Thedb.getInstance(getActivity());
                //news= newsService.fetchOffers(databaseHandler.getLastOfferId());
                if (category == null || category.equals("")) {
                    //category="ALL";
                    //searchCriteria.put("category", "ALL");

                }
                toStoreInDb = newsService.fetchNews(databaseHandler.getLastNewsId(category == null || category.equals("") ? "ALL" : category), Location.getInstance().getCity());

                databaseHandler.addNews(toStoreInDb, "SEEN");
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in AsyncPopulateOffer doInBackground of Offer List." + ex.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            try {
                populateFilteredOffers();
                setAutocompleteBox();
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in AsyncPopulateOffers onPostExecute of Offer List." + ex.getMessage());
            }
        }

    }

    private class AsyncPopulateSubCategories extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg0) {
            Thedb databaseHandler = Thedb.getInstance(getActivity());
            //news= newsService.fetchOffers(databaseHandler.getLastOfferId());
            subcategoryList = categoryService.getSubcategories("0");
            //news=databaseHandler.fetchAllOffers();
            databaseHandler.addSubcategory(subcategoryList);
            return null;
        }

        protected void onPostExecute(Void result) {
            NewsListAdapter arrayAdanewspter = new NewsListAdapter(getActivity(), R.layout.news_row_list_layout, news);

            //Set the above adapter as the adapter of choice for our list
            setListAdapter(arrayAdanewspter);

//			populateOffers();

        }

    }

    private class AsyncPopulateSubCategories2 extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg0) {
            Thedb databaseHandler = Thedb.getInstance(getActivity());
            //news= newsService.fetchOffers(databaseHandler.getLastOfferId());
            subcategory2List = categoryService.getSubcategories2("0");
            //news=databaseHandler.fetchAllOffers();
            databaseHandler.addSubcategory2(subcategory2List);
            return null;
        }

        protected void onPostExecute(Void result) {
            NewsListAdapter arrayAdanewspter = new NewsListAdapter(getActivity(), R.layout.news_row_list_layout, news);

            //Set the above adapter as the adapter of choice for our list
            setListAdapter(arrayAdanewspter);

        }

    }

    private class AsyncPopulateSubCategories3 extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg0) {
            //Thedb databaseHandler=new Thedb.getInstance(getActivity());
            //news= newsService.fetchOffers(databaseHandler.getLastOfferId());
            subcategory3List = categoryService.getSubcategories3("0");
            //news=databaseHandler.fetchAllOffers();
            //databaseHandler.addSubcategory3(subcategory3List);
            return null;
        }

        protected void onPostExecute(Void result) {
            NewsListAdapter arrayAdanewspter = new NewsListAdapter(getActivity(), R.layout.news_row_list_layout, news);

            //Set the above adapter as the adapter of choice for our list
            setListAdapter(arrayAdanewspter);

        }

    }
}
