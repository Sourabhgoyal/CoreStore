package com.sourabh.appnews.core;

import android.content.Context;

import com.sourabh.constants.AllScreens;
import com.sourabh.entity.News;
import com.sourabh.utility.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterLayer {
    static String[] allCriterias = null;
    static Predicate<News> validNewsPredicate = new Predicate<News>() {
        public boolean apply(News news, HashMap<String, String> criteria) {
            boolean flag = true;
            for (int i = 0; i < allCriterias.length; i++) {

                if (criteria.get(allCriterias[i]) != null && allCriterias[i].equals("category")) {
                    if (criteria.get(allCriterias[i]).equals("ALL"))
                        flag = true;
                    else
                        flag = news.getCategory().getCategory().equals(criteria.get(allCriterias[i]));
                }
            }
            if (criteria.get(AllScreens.OTHER_CRITERIA_LOCALITY) != null && !criteria.get(AllScreens.OTHER_CRITERIA_LOCALITY).equals("") && flag) {
                flag = news.getLocality().equals(criteria.get(AllScreens.OTHER_CRITERIA_LOCALITY));
            }
            if (criteria.get(AllScreens.OTHER_CRITERIA_LOCATION) != null && !criteria.get(AllScreens.OTHER_CRITERIA_LOCATION).equals("") && flag) {
                Utilities util = new Utilities();
                //TODO
                flag = NewsList.locationNewsIdList.contains(news.getNewsId());
            }
            if (criteria.get("search") != null && !criteria.get("search").equals("") && flag) {
                String[] aa;
                StringBuilder ssf;

                String searchStringWords = criteria.get("search");
                String[] searchStringArray = criteria.get("search").split(" ");
                for (int j = searchStringArray.length - 1; j >= 0; j--) {
                    //flag=news.getNewsName().contains(searchStringWords);
                    flag = news.toStringBuilder().contains(searchStringWords.toLowerCase());
                    if (flag) {
                        break;
                    } else {
                        searchStringWords = searchStringWords.replace(searchStringArray[j], "");
                    }
                }
            }

            return flag;
        }

        @Override
        public boolean apply(News news, String criteria) {
            boolean flag = true;
            criteria = criteria.toLowerCase();
            return news.getTitle().toLowerCase().contains(criteria) || news.getCategory().getCategory().toLowerCase().contains(criteria) || news.getReporter().toLowerCase().contains(criteria);
        }
    };
    //
    static Predicate<News> validNewsSearch = new Predicate<News>() {
        public boolean apply(News news, String criteria) {
            boolean flag = true;

            return news.getTitle().contains(criteria) || news.getCategory().getCategory().contains(criteria) || news.getReporter().contains(criteria);
        }

        @Override
        public boolean apply(News type, HashMap<String, String> criteria) {
            // TODO Auto-generated method stub
            return false;
        }
    };

    public static <T> ArrayList<T> filter(ArrayList<T> col, Predicate<T> predicate, HashMap<String, String> criteria) {
        ArrayList<T> result = new ArrayList<T>();

        for (T element : col) {
            if (predicate.apply(element, criteria)) {
                result.add(element);
            }
        }
        return result;
    }

    public static <T> ArrayList<T> filter(ArrayList<T> col, Predicate<T> predicate, String criteria) {
        ArrayList<T> result = new ArrayList<T>();
        for (T element : col) {
            if (predicate.apply(element, criteria)) {
                result.add(element);
            }
        }
        return result;
    }

    public static ArrayList<News> filteredNewsList(ArrayList<News> allNewsList, HashMap<String, String> criteria, Context context) {
        allCriterias = context.getResources().getStringArray(R.array.FilterCriterias);
        ArrayList<News> filteredList = filter(allNewsList, validNewsPredicate, criteria);
        return filteredList;
    }

    public static ArrayList<News> searchNewsList(ArrayList<News> allNewsList, String criteria, Context context) {

        ArrayList<News> filteredList = filter(allNewsList, validNewsPredicate, criteria);
        return filteredList;
    }
}