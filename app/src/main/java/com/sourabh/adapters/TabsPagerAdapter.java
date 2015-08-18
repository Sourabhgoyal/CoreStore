package com.sourabh.adapters;

/**
 * Created by SOURABH on 8/7/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sourabh.appnews.core.G2Data;
import com.sourabh.appnews.core.G3Data;
import com.sourabh.appnews.core.Recommended;
import com.sourabh.appnews.core.Roaming;
import com.sourabh.appnews.core.Special;
import com.sourabh.appnews.core.Topup;
import com.sourabh.entity.OperatorPlansSegregator;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    OperatorPlansSegregator operatorPlansSegregator = null;

    public TabsPagerAdapter(FragmentManager fm, OperatorPlansSegregator operatorPlansSegregator) {
        super(fm);
        this.operatorPlansSegregator = operatorPlansSegregator;
    }

    @Override
    public Fragment getItem(int index) {
        Fragment fragment = null;
        switch (index) {
            case 0:
                Recommended.operatorPlansArrayList = operatorPlansSegregator.getFullTalktime();
                // Top Rated fragment activity
                //  return new TopRatedFragment();
                fragment = new Recommended();
                break;
            case 1:
                Topup.operatorPlansArrayList = operatorPlansSegregator.getTopup();
                // Top Rated fragment activity
                //  return new TopRatedFragment();
                fragment = new Topup();
                break;
            case 2:
                Special.operatorPlansArrayList = operatorPlansSegregator.getSpecial();
                // Top Rated fragment activity
                //  return new TopRatedFragment();
                fragment = new Special();
                break;
            case 3:
                G2Data.operatorPlansArrayList = operatorPlansSegregator.getG2();
                // Top Rated fragment activity
                //  return new TopRatedFragment();
                fragment = new G2Data();
                break;
            case 4:
                G3Data.operatorPlansArrayList = operatorPlansSegregator.getG3();
                // Top Rated fragment activity
                //  return new TopRatedFragment();
                fragment = new G3Data();
                break;
            case 5:
                Roaming.operatorPlansArrayList = operatorPlansSegregator.getRoaming();
                // Top Rated fragment activity
                //  return new TopRatedFragment();
                fragment = new Roaming();
                break;

        }

        return fragment;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 6;
    }

}