package com.sourabh.appnews.core;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sourabh.adapters.ViewPagerAdapter;
import com.sourabh.database.Thedb;
import com.sourabh.entity.News;

import java.util.ArrayList;
import java.util.HashMap;

public class PagerFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Thedb databaseHandler = Thedb.getInstance(container.getContext());
        HashMap<String, String> criteria = new HashMap<String, String>();
        criteria.put("category", "Breaking News");
        ArrayList<News> newsList = FilterLayer.filteredNewsList(databaseHandler.fetchAllNewss(null), criteria, container.getContext());
        StringBuilder breakingNews = new StringBuilder();
        for (News news : newsList) {
            breakingNews.append(news.getTitle());
            breakingNews.append("     ");

        }


        // Creating view correspoding to the fragment
        View v = inflater.inflate(R.layout.activity_home, container, false);
        TextView txtBreakingNews = (TextView) v.findViewById(R.id.breakingNews);
        txtBreakingNews.setText(breakingNews.toString());
        txtBreakingNews.setSelected(true);
        //txtBreakingNews.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.move));
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(v.getContext());

        viewPager.setAdapter(adapter);

        FragmentManager fragmentManager = getFragmentManager();
        NewsList rFragment = new NewsList();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.content_frame_home, rFragment);

        ft.commit();
        return v;
    }
}
