package com.sourabh.businessService;

import com.sourabh.entity.News;

import java.util.ArrayList;

public interface NewsInterface {

    ArrayList<News> fetchNews(String lastOfferId, String location);

    String fetchClosedNews(String location);

}
