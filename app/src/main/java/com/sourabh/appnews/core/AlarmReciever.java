package com.sourabh.appnews.core;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

import com.sourabh.businessService.MessageService;
import com.sourabh.businessService.NewsService;
import com.sourabh.database.Thedb;
import com.sourabh.entity.Message;
import com.sourabh.entity.News;
import com.sourabh.events.EventMainActivity;
import com.sourabh.events.businessService.EventService;
import com.sourabh.events.entity.Event;
import com.sourabh.singletons.Location;
import com.sourabh.utility.Utilities;

import java.util.ArrayList;

public class AlarmReciever extends BroadcastReceiver {
    public static int previousNewsCount = 0;
    public static int previousEventCount = 0;
    public static int previousMessageCount = 0;
    public static String previousMessage = "";
    NewsService NewsService;
    EventService eventService;
    MessageService messageService;
    Context context;
    ArrayList<News> newsList;
    ArrayList<Event> eventList;

    @Override
    public void onReceive(Context context, Intent arg1) {
        try {
            NewsService = new NewsService();
            eventService = new EventService();
            messageService = new MessageService();

            this.context = context;
            AsyncClearClosedNews asyncClearClosedNews = new AsyncClearClosedNews();
            asyncClearClosedNews.execute();
            AsyncPopulateMessages asyncPopulateMessages = new AsyncPopulateMessages();
            asyncPopulateMessages.execute();
            AsyncPopulateNews asyncPopulateNews = new AsyncPopulateNews();
            asyncPopulateNews.execute();
            AsyncPopulateEvents asyncPopulateEvents = new AsyncPopulateEvents();
            asyncPopulateEvents.execute();

        } catch (Exception ex) {
            Utilities.write("ErrorLog", "Encountered error in Alarm reciever onRecieve." + ex.getMessage());
        }
    }

    private class AsyncPopulateNews extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg0) {
            try {
                Thedb databaseHandler = Thedb.getInstance(context);

                ArrayList<News> News = NewsService.fetchNews(databaseHandler.getLastNewsId("ALL"), Location.getInstance().getCity());

                databaseHandler.addNews(News, "NEW");
                newsList = databaseHandler.fetchAllNewNews();
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Alarm reciever backgound." + ex.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            try {
                if (previousNewsCount != newsList.size()) {
                    previousNewsCount = newsList.size();
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
                    mBuilder.setSmallIcon(R.drawable.ic_drawer);
                    mBuilder.setContentTitle(newsList.get(0).getTitle());
                    mBuilder.setContentText(newsList.size() + " " + context.getResources().getString(R.string.LBL_NEWS_NOTIFICATION));

                    Intent resultIntent = new Intent(context, Intitializer.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                    stackBuilder.addParentStack(Intitializer.class);

                    // Adds the Intent that starts the Activity to the top of the stack
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);

                    NotificationManager mNotificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.cancel(112211);


                    // notificationID allows you to update the notification later on.
                    mNotificationManager.notify(112211, mBuilder.build());
                }
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Alarm reciever postExecute." + ex.getMessage());
            }
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
                Thedb databaseHandler = Thedb.getInstance(context);
                toStoreInDb = eventService.fetchEvents(databaseHandler.getLastEventId(), Location.getInstance().getCity());

                databaseHandler.addEvent(toStoreInDb, "NEW");
                eventList = databaseHandler.fetchNewEvents();
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in AlarmReciever AsyncPopulateEvent doInBackground of event List." + ex.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            try {
                if (previousEventCount != eventList.size()) {
                    previousEventCount = eventList.size();
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
                    mBuilder.setSmallIcon(R.drawable.ic_drawer);
                    mBuilder.setContentTitle(eventList.get(0).getEventName());
                    mBuilder.setContentText(eventList.size() + " " + context.getResources().getString(R.string.LBL_EVENTS_NOTIFICATION));

                    Intent resultIntent = new Intent(context, EventMainActivity.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                    stackBuilder.addParentStack(EventMainActivity.class);

                    // Adds the Intent that starts the Activity to the top of the stack
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);

                    NotificationManager mNotificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.cancel(112212);


                    // notificationID allows you to update the notification later on.
                    mNotificationManager.notify(112212, mBuilder.build());
                }
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in AlarmReciever AsyncPopulateEvent onPostExecute of event List." + ex.getMessage());
            }
        }


    }

    private class AsyncPopulateMessages extends AsyncTask<String, ArrayList<Message>, ArrayList<Message>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<Message> doInBackground(String... arg0) {
            ArrayList<Message> messageList = null;
            try {


                messageList = messageService.fetchMessages(Location.getInstance().getCity());

            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in AlarmReciever AsyncPopulateEvent doInBackground of event List." + ex.getMessage());
            }
            return messageList;
        }

        protected void onPostExecute(ArrayList<Message> messageList) {
            try {
                if (!previousMessage.equals(messageList.get(0))) {
                    previousMessageCount = messageList.size();
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
                    mBuilder.setSmallIcon(R.drawable.ic_drawer);
                    mBuilder.setContentTitle(messageList.get(0).getMessage());
                    mBuilder.setContentText(messageList.get(0).getDetail());

                    Intent resultIntent = new Intent(context, Intitializer.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                    stackBuilder.addParentStack(Intitializer.class);

                    // Adds the Intent that starts the Activity to the top of the stack
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);

                    NotificationManager mNotificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.cancel(112213);


                    // notificationID allows you to update the notification later on.
                    mNotificationManager.notify(112213, mBuilder.build());
                }
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in AlarmReciever AsyncPopulateMessages onPostExecute of message List." + ex.getMessage());
            }
        }


    }

    private class AsyncClearClosedNews extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg0) {
            try {

                Thedb databaseHandler = Thedb.getInstance(context);
                String NewsClosed = NewsService.fetchClosedNews(Location.getInstance().getCity());

                databaseHandler.deleteClosedNews(NewsClosed);

            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Alarm reciever backgound." + ex.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void result) {

            try {
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Alarm reciever postExecute." + ex.getMessage());
            }
        }

    }
}
