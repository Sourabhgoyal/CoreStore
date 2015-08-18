package com.sourabh.gpstrackingservice;

import java.util.ArrayList;

public interface GpsTrackingInterface {

    ArrayList<String> fetchLocationrelatedData(String lat, String lon);
}

