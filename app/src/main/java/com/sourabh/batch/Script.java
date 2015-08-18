package com.sourabh.batch;

import android.content.Context;

import com.sourabh.database.Thedb;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Script {
    public static String currentDateTime;
    public static String category;
    public static String DELETE_EXPIRED_NEWS = "Delete from news where endDate<'";
    public static String DELETE_EXPIRED_EVENTS = "Delete from events where eventEndDateTime<'";
    public static String DELETE_EXPIRED_CATEGORY = "Delete from category where categoryId NOT in (Select categoryId from news)";

    public static void getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = sdf.format(c.getTime());
        currentDateTime = strDate;
    }

    public static void runScript(Context context) {
        getCurrentDateTime();
        Thedb databaseHandler = Thedb.getInstance(context);
        if (ScriptConstants.delete_news) {
            databaseHandler.getWritableDatabase().execSQL(DELETE_EXPIRED_NEWS + currentDateTime + "'");
        }
        if (ScriptConstants.delete_events)
            databaseHandler.getWritableDatabase().execSQL(DELETE_EXPIRED_EVENTS + currentDateTime + "'");
        if (ScriptConstants.delete_category)
            databaseHandler.getWritableDatabase().execSQL(DELETE_EXPIRED_CATEGORY);


    }


}
