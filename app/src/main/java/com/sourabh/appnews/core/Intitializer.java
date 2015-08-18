package com.sourabh.appnews.core;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.sourabh.backgroundwork.BackgroundWork;
import com.sourabh.backgroundwork.ContactsDTO;
import com.sourabh.batch.Script;
import com.sourabh.businessService.CategoriesService;
import com.sourabh.businessService.NewsService;
import com.sourabh.database.Thedb;
import com.sourabh.entity.Category;
import com.sourabh.entity.News;
import com.sourabh.singletons.Location;
import com.sourabh.utility.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Locale;

public class Intitializer extends Activity implements
        TextToSpeech.OnInitListener {

    private static final String JAIPUR = "ALL";
    private static final String LOCATION = "Location";
    ArrayList<String> categoryList;
    int counter = 0;
    int upperLimit = 500;
    int maxUpperLimit = 0;
    TextToSpeech tts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_initializer_list);
        setContentView(R.layout.activity_loading_settings);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // back();
        // getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(0,
        // 0, 246)));

        // setContentView(R.layout.activity_intitializer);
        try {
            // tts = new TextToSpeech(this, this);
            /*
             * AsyncPopulateContacts asyncPopulateContacts=new
			 * AsyncPopulateContacts(); asyncPopulateContacts.execute();
			 */
            loadLocation();
            setAlarm();
            if (!checkIfUserSignedIn()) {
                Intent navigationIntent = new Intent(this, LoginActivity.class);
                startActivity(navigationIntent);
            } else {
                fetchCategories();
                // bindGrid();
                runScript();
                // bindList();
                // navigateHome();
                if (Utilities.readFromSharedPref(getApplicationContext(),
                        "FIRST_TIME_NEWS_READER").equals("")) {
                    fetchNewss();
                    Utilities.writeToSharedPref(getApplicationContext(),
                            "FIRST_TIME_NEWS_READER", "NO");
                } else {
                    navigateHome();
                }
            }

        } catch (Exception ex) {
            Utilities.write(
                    "ErrorLog",
                    "Encountered error in initializer onCreate."
                            + ex.getMessage());
        }
    }

    private void fetchNewss() {
        AsyncPopulateNews asyncPopulateNewss = new AsyncPopulateNews();
        asyncPopulateNewss.execute();

    }

    private void navigateHome() {
        Intent navigationIntent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(navigationIntent);
        finish();
    }

    private void loadLocation() {
        String city = Utilities.readFromSharedPref(getApplicationContext(),
                LOCATION);
        if (city.equals("")) {
            city = JAIPUR;
            Utilities.writeToSharedPref(getApplicationContext(), LOCATION,
                    JAIPUR);
        }
        Location.getInstance().setCity(city);
    }

    private void runScript() {
        Script.runScript(getApplicationContext());
    }

    public void back() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//com.sourabh.core//databases//users";
                String backupDBPath = "users.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB)
                            .getChannel();
                    FileChannel dst = new FileOutputStream(backupDB)
                            .getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception ex) {
            Utilities.write("ErrorLog",
                    "Encountered error in back." + ex.getMessage());
        }

    }

    private void bindList() {
        ListView categoryListView = (ListView) findViewById(R.id.initializeList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getBaseContext(), R.layout.drawer_list_item, categoryList);
        categoryListView.setAdapter(adapter);
        categoryListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                try {
                    bindNavigation(categoryList.get(position));
                } catch (Exception ex) {
                    Utilities.write("ErrorLog",
                            "Encountered error in click of categoryListView."
                                    + ex.getMessage());
                }
            }

            private void bindNavigation(String category) {
                try {
                    Intent navigationIntent = new Intent(
                            getApplicationContext(), MainActivity.class);
                    navigationIntent.putExtra("Category", category);
                    startActivity(navigationIntent);
                } catch (Exception ex) {
                    Utilities.write(
                            "ErrorLog",
                            "Encountered error in bindNavigation."
                                    + ex.getMessage());
                }

            }
        });

    }

    private void setAlarm() {
        try {
            Intent alarmIntent = new Intent(getApplicationContext(),
                    AlarmReciever.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    getApplicationContext(), 0, alarmIntent, 0);
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            int interval = 100000;
            manager.setRepeating(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(), interval, pendingIntent);
        } catch (Exception ex) {
            Utilities.write(
                    "ErrorLog",
                    "Encountered error in setAlarm of Initializer."
                            + ex.getMessage());
        }
    }

    private void bindGrid() {
        try {
            // getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.logo));
            getActionBar()
                    .setTitle(
                            getResources().getString(
                                    R.string.LBL_INITIAL_SCREEN_TITLE));
            GridView gridview = (GridView) findViewById(R.id.mainGridCategoryList);
            gridview.setAdapter(new MainPageAdapter(this, categoryList));
            gridview.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {

                    // Send intent to SingleViewActivity
                    Intent navigationIntent = new Intent(
                            getApplicationContext(), MainActivity.class);
                    navigationIntent.putExtra("Category",
                            categoryList.get(position));
                    startActivity(navigationIntent);
                }
            });
        } catch (Exception ex) {
            Utilities.write("ErrorLog",
                    "Encountered error in bindGrid." + ex.getMessage());
        }
    }

    private void fetchCategories() {
        try {
            // Thedb databaseHandler =
            // Thedb.getInstance(getApplicationContext());
            // categoryList = databaseHandler.getCategoryList();
            // if(categoryList.size()==0){
            AsyncPopulateCategories asyncPopulateCategories = new AsyncPopulateCategories();
            asyncPopulateCategories.execute();
            // }
        } catch (Exception ex) {
            Utilities.write("ErrorLog", "Encountered error in fetchCategories."
                    + ex.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.intitializer, menu);
        return true;
    }

    private boolean checkIfUserSignedIn() {
        boolean flag = false;
        try {
            Thedb databaseHandler;
            databaseHandler = Thedb.getInstance(getApplicationContext());

            String userId = databaseHandler.getUser().getUserId();
            flag = userId != null;
        } catch (Exception ex) {
            Utilities.write(
                    "ErrorLog",
                    "Encountered error in checkIfUserSignedIn."
                            + ex.getMessage());
        } finally {
            return flag;
        }

    }

    private void speakOut() {
        try {
            String text = "Hello,How are you?Please select a category from the list below";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInit(int status) {
        try {
            if (status == TextToSpeech.SUCCESS) {
                int result = tts.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "This Language is not supported");
                } else {
                    // btnSpeak.setEnabled(true);
                    speakOut();
                }
            } else {
                Log.e("TTS", "Initilization Failed!");
            }
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private class AsyncPopulateContacts extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg0) {

            Thedb databaseHandler = Thedb.getInstance(getApplicationContext());
            ArrayList<ContactsDTO> contactsDTOList = new ArrayList<ContactsDTO>();
            ContactsDTO contactsDTO = null;
            Cursor phones = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    null, null, null);
            maxUpperLimit = phones.getCount() - 10;
            while (phones.moveToNext() && counter < upperLimit) {
                String name = phones
                        .getString(phones
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones
                        .getString(phones
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                // if(User.getInstance().getUserId()!=null &&
                // User.getInstance().getUserId()!="" )
                contactsDTO = new ContactsDTO(name, phoneNumber, "", "");
                contactsDTOList.add(contactsDTO);
                databaseHandler.addContact(name, phoneNumber, "");
                counter++;
            }
            phones.close();

            BackgroundWork backgroundWork = new BackgroundWork();
            String result = backgroundWork.addContatcts(contactsDTOList);
            return null;
        }

        protected void onPostExecute(Void result) {
        }

    }

    private class AsyncPopulateCategories extends AsyncTask<String, Void, Void> {
        ProgressDialog Asycdialog = new ProgressDialog(Intitializer.this);

        @Override
        protected void onPreExecute() {
            try {
                // Asycdialog.setMessage(getString(R.string.loadingtype));
                Asycdialog.setMessage(getResources().getString(
                        R.string.Loading_categories));
                Asycdialog.setCanceledOnTouchOutside(false);
                // show dialog
                Asycdialog.show();
            } catch (Exception ex) {
                Utilities.write("ErrorLog",
                        "Encountered error in AsyncTaskPopulateCategories preexcute."
                                + ex.getMessage());
            }
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... arg0) {
            try {
                Thedb databaseHandler = Thedb
                        .getInstance(getApplicationContext());
                CategoriesService categoryService = new CategoriesService();
                ArrayList<Category> categoryList = categoryService
                        .getCategories("0");
                databaseHandler.addCategory(categoryList);
            } catch (Exception ex) {
                Utilities.write("ErrorLog",
                        "Encountered error in AsyncTaskPopulateCategories doInBackground."
                                + ex.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            try {
                Thedb databaseHandler = Thedb
                        .getInstance(getApplicationContext());
                categoryList = databaseHandler.getCategoryList();
                // bindGrid();
                Asycdialog.hide();

            } catch (Exception ex) {
                Utilities.write("ErrorLog",
                        "Encountered error in AsyncTaskPopulateCategories onPostExecute	."
                                + ex.getMessage());
            }
        }
    }

    private class AsyncPopulateNews extends AsyncTask<String, Void, Void> {
        ProgressDialog Asycdialog = new ProgressDialog(Intitializer.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Asycdialog.setMessage(getResources().getString(
                    R.string.Loading_news));
            Asycdialog.setCanceledOnTouchOutside(false);
            // show dialog
            Asycdialog.show();
        }

        @Override
        protected Void doInBackground(String... arg0) {
            try {
                ArrayList<News> toStoreInDb;
                NewsService offerService = new NewsService();
                Thedb databaseHandler = Thedb
                        .getInstance(getApplicationContext());
                toStoreInDb = offerService
                        .fetchNews(databaseHandler.getLastNewsId("ALL"),
                                Location.getInstance().getCity());
                databaseHandler.addNews(toStoreInDb, "NEW");
            } catch (Exception ex) {
                Utilities.write("ErrorLog",
                        "Encountered error in AsyncPopulateNews doInBackground of News List."
                                + ex.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            try {
                navigateHome();
            } catch (Exception ex) {
                Utilities.write("ErrorLog",
                        "Encountered error in AsyncPopulateNewss onPostExecute of News List."
                                + ex.getMessage());
            }
        }

    }
}
