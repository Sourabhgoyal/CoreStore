package com.sourabh.events;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sourabh.appnews.core.R;

public class EventPagerFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Retrieving the currently selected item number

        // Creating view correspoding to the fragment
        View v = inflater.inflate(R.layout.activity_event_home, container, false);
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.event_pager);

        EventViewPagerAdapter adapter = new EventViewPagerAdapter(v.getContext());

        viewPager.setAdapter(adapter);

        EventListFragment rFragment = new EventListFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_event_frame_home, rFragment);
        //ft .addToBackStack(null);
        fragmentManager.popBackStack();
        ft.commit();
        return v;
    }
}
