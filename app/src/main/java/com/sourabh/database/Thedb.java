package com.sourabh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.sourabh.appnews.core.FilterLayer;
import com.sourabh.entity.Category;
import com.sourabh.entity.News;
import com.sourabh.entity.Subcategory;
import com.sourabh.entity.Subcategory2;
import com.sourabh.entity.Subcategory3;
import com.sourabh.entity.User;
import com.sourabh.events.entity.Event;
import com.sourabh.singletons.Location;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Thedb extends SQLiteOpenHelper {
    private static final String KEY_ID = "id";
    private static final String KEY_USERID = "userid";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_AGE = "age";
    private static final String KEY_CITY = "city";
    private static final String KEY_DATE = "dateOfRegistration";
    private static final String KEY_DETAILS = "details";
    private static final String KEY_EMAIL = "emailAddress";
    private static final String KEY_LOCALITY = "locality";
    private static final String KEY_MOBILE_NO = "mobileNo";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PINCODE = "pincode";
    private static final String KEY_REFFERAL_ID = "refferalId";
    private static final String KEY_STATE = "state";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_STATUS = "status";
    private static final String KEY_CREDIT = "credit";
    private static final String KEY_OFFER_ID = "offerId";

    // Offers table fields
    private static final String KEY_OFFER_NAME = "offerName";
    private static final String KEY_COMPANY_ID = "companyId";
    private static final String KEY_START_DATE = "startDate";
    private static final String KEY_END_DATE = "enddate";
    private static final String KEY_VALUE = "value";
    private static final String KEY_COST = "cost";
    private static final String KEY_CATEGORY = "categoryid";
    private static final String KEY_SUBCATEGORY = "subcategoryid";
    private static final String KEY_SUBCATEGORY2 = "subcategory2id";
    private static final String KEY_SUBCATEGORY3 = "subcategory3id";
    private static final String KEY_CATEGORY_NAME = "category";
    private static final String KEY_SUB_CATEGORY_NAME = "subcategory";
    private static final String KEY_SUB_CATEGORY2_NAME = "subcategory2";
    private static final String KEY_SUB_CATEGORY3_NAME = "subcategory3";
    private static final String KEY_COMPANY_NAME = "name";
    private static final String KEY_LOGO = "logo";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_MOBILE2 = "mobile2";
    private static final String KEY_CONTACT_PERSON = "contactPerson";
    private static final String KEY_IMAGES = "images";
    private static final String KEY_NEWS_ID = "newsId";
    //News table fields
    private static final String KEY_NEWS_TITLE = "newsTitle";
    private static final String KEY_POSTING_DATE = "postingDate";
    private static final String KEY_COMMENTS_ID = "commentsId";
    private static final String KEY_TYPE = "type";
    //private static final String KEY_END_DATE = "enddate";
    //private static final String KEY_CATEGORY = "categoryid";
    //private static final String KEY_LOCALITY = "locality";
    //private static final String KEY_CITY = "city";
    //private static final String KEY_STATE = "state";
//	private static final String KEY_COUNTRY = "country";
    private static final String KEY_REPORTER = "contactPerson";
    //private static final String KEY_IMAGES = "images";
    //private static final String KEY_DETAILS = "details";
    //private static final String KEY_STATUS = "status";
    private static final String KEY_ADVERTISE_IMAGE = "advertiseImage";
    private static final String KEY_PACKAGE_NAME = "packageName";
    private static final String KEY_VERSION_CODE = "versionCode";
    private static final String KEY_UPDATED = "updated";
    private static final String KEY_SIZE = "size";
    private static final String KEY_INSTALLS = "installs";
    private static final String KEY_ANDROID_REQ = "androidRequired";
    private static final String KEY_RATING = "rating";
    private static final String KEY_PERMISSIONS = "permissions";
    private static final String KEY_DEVELOPER = "developer";
    private static final String KEY_WHATS_NEW = "whatsnew";
    // Events Table
    private static final String KEY_EVENT_ID = "eventId";
    private static final String KEY_ATTRACTION_POINT = "attractionPoint";
    private static final String KEY_EVENT_CATEGORY = "category";
    // private static final String KEY = "detail";
    private static final String KEY_EVENT_ADDRESS = "eventAddress";
    private static final String KEY_EVENT_END_DATE_TIME = "eventEndDateTime";
    private static final String KEY_EVENT_LOCATION = "eventLocation";
    private static final String KEY_EVENT_NAME = "eventName";
    private static final String KEY_EVENT_START_DATE_TIME = "eventStartDateTime";
    private static final String KEY_ORGANIZER_DETAIL = "organizerDetail";
    private static final String KEY_PEOPLE_JOINING = "peopleJoining";
    private static final String KEY_GOING = "going";
    public static Thedb theDbInstance;
    // KEY_STATUS
    // +KEY_IMAGES+" TEXT,"
    // private static final String KEY_CITY = "city";
    // private static final String KEY_STATE = "state";
    // private static final String KEY_COUNTRY = "country";
    private static Context context = null;

    private Thedb(Context context) {
        super(context, "store", null, 1);
        Thedb.context = context;
    }

    public static Thedb getInstance(Context context) {
        if (theDbInstance == null) {
            synchronized (Thedb.class) {
                if (theDbInstance == null) {
                    theDbInstance = new Thedb(context);
                }
            }

        }
        return theDbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String CREATE_USERS_TABLE = "CREATE TABLE USERS(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERID + " TEXT,"
                + KEY_ADDRESS + " TEXT," + KEY_AGE + " TEXT," + KEY_CITY
                + " TEXT," + KEY_DATE + " TEXT," + KEY_DETAILS + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_LOCALITY + " TEXT,"
                + KEY_MOBILE_NO + " TEXT," + KEY_PASSWORD + " TEXT,"
                + KEY_PINCODE + " TEXT," + KEY_REFFERAL_ID + " TEXT,"
                + KEY_STATE + " TEXT," + KEY_USERNAME + " TEXT," + KEY_CREDIT
                + " TEXT," + KEY_STATUS + " TEXT)";

        db.execSQL(CREATE_USERS_TABLE);


        // News Table
        String CREATE_NEWS_TABLE = "CREATE TABLE NEWS(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NEWS_ID
                + " TEXT," + KEY_NEWS_TITLE + " TEXT," + KEY_REPORTER
                + " TEXT," + KEY_POSTING_DATE + " TEXT," + KEY_END_DATE
                + " TEXT," + KEY_IMAGES + " TEXT," + KEY_DETAILS + " TEXT,"
                + KEY_CATEGORY + " TEXT," + KEY_COMMENTS_ID + " TEXT,"
                + KEY_TYPE + " TEXT," + KEY_STATUS + " TEXT,"
                + KEY_LOCALITY + " TEXT," + KEY_CITY + " TEXT,"
                + KEY_STATE + " TEXT," + KEY_COUNTRY + " TEXT,"
                + KEY_ADVERTISE_IMAGE + " TEXT,"
                + KEY_PACKAGE_NAME + " TEXT," + KEY_VERSION_CODE + " TEXT,"
                + KEY_UPDATED + " TEXT," + KEY_SIZE + " TEXT,"
                + KEY_INSTALLS + " TEXT," + KEY_ANDROID_REQ + " TEXT,"
                + KEY_RATING + " TEXT,"
                + KEY_PERMISSIONS + " TEXT," + KEY_DEVELOPER + " TEXT,"
                + KEY_WHATS_NEW + " TEXT)";

        db.execSQL(CREATE_NEWS_TABLE);
/*
        String CREATE_COMPANY_TABLE = "CREATE TABLE COMPANY(" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_COMPANY_ID
				+ " TEXT," + KEY_COMPANY_NAME + " TEXT," + KEY_LOGO + " TEXT,"
				+ KEY_ADDRESS + " TEXT," + KEY_LOCALITY + " TEXT," + KEY_CITY
				+ " TEXT," + KEY_STATE + " TEXT," + KEY_COUNTRY + " TEXT,"
				+ KEY_EMAIL + " TEXT," + KEY_MOBILE_NO + " TEXT," + KEY_MOBILE2
				+ " TEXT," + KEY_CONTACT_PERSON + " TEXT," + KEY_STATUS
				+ " TEXT)";

		db.execSQL(CREATE_COMPANY_TABLE);
*/
        // Contacts Table
        String CREATE_CONTACTS_TABLE = "CREATE TABLE CONTACTS(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME
                + " TEXT," + KEY_MOBILE_NO + " TEXT," + KEY_DETAILS + " TEXT)";

        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_CATEGORY_TABLE = "CREATE TABLE CATEGORY(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CATEGORY
                + " TEXT," + KEY_CATEGORY_NAME + " TEXT," + KEY_DETAILS
                + " TEXT)";

        db.execSQL(CREATE_CATEGORY_TABLE);

        String CREATE_SUBCATEGORY_TABLE = "CREATE TABLE SUBCATEGORY(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_SUBCATEGORY
                + " TEXT," + KEY_CATEGORY + " TEXT)";

        db.execSQL(CREATE_SUBCATEGORY_TABLE);
        String CREATE_SUBCATEGORY2_TABLE = "CREATE TABLE SUBCATEGORY2("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SUBCATEGORY2 + " TEXT," + KEY_SUBCATEGORY + " TEXT)";

        db.execSQL(CREATE_SUBCATEGORY2_TABLE);
        String CREATE_SUBCATEGORY3_TABLE = "CREATE TABLE SUBCATEGORY3("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SUBCATEGORY3 + " TEXT," + KEY_SUBCATEGORY2 + " TEXT)";

        db.execSQL(CREATE_SUBCATEGORY3_TABLE);

        String CREATE_EVENTS_TABLE = "CREATE TABLE EVENTS(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ATTRACTION_POINT
                + " TEXT," + KEY_EVENT_CATEGORY + " TEXT," + KEY_DETAILS
                + " TEXT," + KEY_EVENT_ADDRESS + " TEXT,"
                + KEY_EVENT_END_DATE_TIME + " TEXT," + KEY_EVENT_ID + " TEXT,"
                + KEY_EVENT_LOCATION + " TEXT," + KEY_EVENT_NAME + " TEXT,"
                + KEY_EVENT_START_DATE_TIME + " TEXT," + KEY_GOING + " TEXT,"
                + KEY_IMAGES + " TEXT," + KEY_ORGANIZER_DETAIL + " TEXT,"
                + KEY_PEOPLE_JOINING + " TEXT,"

                + KEY_STATUS + " TEXT,"

                + KEY_CITY + " TEXT," + KEY_STATE + " TEXT," + KEY_COUNTRY
                + " TEXT)";

        db.execSQL(CREATE_EVENTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
//		db.execSQL("Drop table if exists users");
//		db.execSQL("Drop table if exists events");

        onCreate(db);

    }

    public void addContact(String contactName, String contactNumber,
                           String details) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, contactName);
        values.put(KEY_DETAILS, details);
        values.put(KEY_MOBILE_NO, contactNumber);

        db.insert("contacts", null, values);

        // db.close(); // Closing database connection

    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERID, user.getUserId());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_ADDRESS, user.getAddress());
        values.put(KEY_CITY, user.getCity());
        values.put(KEY_DATE, user.getDateOfRegistration());
        values.put(KEY_DETAILS, user.getDetails());
        values.put(KEY_EMAIL, user.getEmailAddress());
        values.put(KEY_LOCALITY, user.getLocality());
        values.put(KEY_MOBILE_NO, user.getMobileNo());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_PINCODE, user.getPincode());
        values.put(KEY_REFFERAL_ID, user.getRefferalId());
        values.put(KEY_STATE, user.getState());
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_CREDIT, user.getCredit());

        values.put(KEY_STATUS, "LOGGEDIN");
        db.insert("users", null, values);

        // db.close(); // Closing database connection

    }

    public User getUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "Select * from users where status='LOGGEDIN'", null);
        User user = User.getInstance();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                user.setUserId(cursor.getString(1));

                user.setAddress(cursor.getString(2));
                user.setAge(cursor.getString(3));
                user.setCity(cursor.getString(4));
                user.setDateOfRegistration(cursor.getString(5));
                user.setDetails(cursor.getString(6));
                user.setEmailAddress(cursor.getString(7));

                user.setLocality(cursor.getString(8));
                user.setMobileNo(cursor.getString(9));
                user.setPassword(cursor.getString(10));
                user.setPincode(cursor.getString(11));
                user.setRefferalId(cursor.getString(12));
                user.setState(cursor.getString(13));
                user.setUsername(cursor.getString(14));
                user.setCredit(cursor.getString(15));
                // break;
            } while (cursor.moveToNext());
        }
        return user;

    }

    public boolean logOut(String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from users");
        db.close();
        return true;

    }

    public synchronized void addNews(ArrayList<News> newsList, String status) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            for (News news : newsList) {
                Cursor cursor = db.rawQuery(
                        "Select count(*) from news where newsId='"
                                + news.getNewsId() + "'", null);
                // Cursor cursor=db.rawQuery("Select * from newss", null);
                cursor.moveToFirst();
                if (cursor.getInt(0) <= 0) {


                    ContentValues values = new ContentValues();
                    values.put(KEY_NEWS_ID, news.getNewsId());
                    values.put(KEY_NEWS_TITLE, news.getTitle());
                    values.put(KEY_REPORTER, news.getReporter());
                    values.put(KEY_POSTING_DATE, news.getPostingDate());
                    values.put(KEY_END_DATE, news.getEndDate());
                    values.put(KEY_IMAGES, news.getImages());
                    values.put(KEY_DETAILS, news.getDetail());
                    values.put(KEY_CATEGORY, news.getCategoryId());
                    values.put(KEY_COMMENTS_ID, news.getCommentsId());
                    values.put(KEY_TYPE, news.getType());
                    values.put(KEY_STATUS, status);
                    values.put(KEY_LOCALITY, news.getLocality());
                    values.put(KEY_CITY, news.getCity());
                    values.put(KEY_STATE, news.getState());
                    values.put(KEY_COUNTRY, news.getCountry());
                    values.put(KEY_ADVERTISE_IMAGE, news.getAdvertiseImage());
                    values.put(KEY_PACKAGE_NAME, news.getPackageName());
                    values.put(KEY_VERSION_CODE, news.getVersionCode());
                    values.put(KEY_UPDATED, news.getUpdated());
                    values.put(KEY_SIZE, news.getSize());
                    values.put(KEY_INSTALLS, news.getInstalls());
                    values.put(KEY_ANDROID_REQ, news.getAndroidRequired());
                    values.put(KEY_RATING, news.getRating());
                    values.put(KEY_PERMISSIONS, news.getPermissions());
                    values.put(KEY_DEVELOPER, news.getDeveloper());

                    values.put(KEY_WHATS_NEW, news.getWhatsNew());


                    db.insert("news", null, values);
                }
            }
        } catch (Exception ex) {
            Log.e("Exception in adding news", ex.getMessage());
            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File(sdcard, "ErrorLog.txt");
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.append(ex.getMessage());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public String getLastNewsId(String category) {
        String lastNewsId = "0";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (category.equals("ALL")) {
            cursor = db
                    .rawQuery(
                            "select max(newsId) from news where city='"
                                    + Location.getInstance().getCity() + "'",
                            null);
        } else {
            cursor = db
                    .rawQuery(
                            "Select max(newsId) from news where categoryId=(Select categoryId from category where category='"
                                    + category + "')", null);
        }
        try {

            cursor.moveToFirst();

            lastNewsId = String.valueOf(cursor.getInt(0));
        } catch (Exception ex) {
            return lastNewsId;
        }
        return lastNewsId;
    }

    public ArrayList<News> fetchAllNewNews() {
        ArrayList<News> newsList = new ArrayList<News>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db
                .rawQuery(
                        "select * from news where city='" + Location.getInstance().getCity() + "' and status='NEW'",
                        null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                News news = new News();
                news.setNewsId(cursor.getString(1));
                news.setTitle(cursor.getString(2));
                news.setReporter(cursor.getString(3));
                news.setPostingDate(cursor.getString(4));
                news.setEndDate(cursor.getString(5));
                news.setImages(cursor.getString(6));
                news.setDetail(cursor.getString(7));
                news.setCategory(getCategory(cursor.getString(8)));
                news.setCategoryId(cursor.getString(8));
                news.setCommentsId(cursor.getString(9));
                news.setType(cursor.getString(10));
                news.setStatus(cursor.getString(11));
                news.setLocality(cursor.getString(12));
                news.setCity(cursor.getString(13));
                news.setState(cursor.getString(14));
                news.setCountry(cursor.getString(15));
                news.setAdvertiseImage(cursor.getString(16));
                news.setPackageName(cursor.getString(17));
                news.setVersionCode(cursor.getString(18));
                news.setUpdated(cursor.getString(19));
                news.setSize(cursor.getString(20));
                news.setInstalls(cursor.getString(21));
                news.setAndroidRequired(cursor.getString(22));
                news.setRating(cursor.getString(23));
                news.setPermissions(cursor.getString(24));
                news.setDeveloper(cursor.getString(25));
                news.setWhatsNew(cursor.getString(26));

                newsList.add(news);
            } while (cursor.moveToNext());
        }
        return newsList;

    }

    public ArrayList<News> fetchAllNewss(HashMap<String, String> criteria) {
        ArrayList<News> newsList = new ArrayList<News>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db
                .rawQuery(
                        "select * from news  where city='"
                                + Location.getInstance().getCity() + "' order by cast(newsId as INTEGER) desc", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                News news = new News();
                news.setNewsId(cursor.getString(1));
                news.setTitle(cursor.getString(2));
                news.setReporter(cursor.getString(3));
                news.setPostingDate(cursor.getString(4));
                news.setEndDate(cursor.getString(5));
                news.setImages(cursor.getString(6));
                news.setDetail(cursor.getString(7));
                news.setCategory(getCategory(cursor.getString(8)));
                news.setCategoryId(cursor.getString(8));
                news.setCommentsId(cursor.getString(9));
                news.setType(cursor.getString(10));
                news.setStatus(cursor.getString(11));
                news.setLocality(cursor.getString(12));
                news.setCity(cursor.getString(13));
                news.setState(cursor.getString(14));
                news.setCountry(cursor.getString(15));
                news.setAdvertiseImage(cursor.getString(16));
                news.setPackageName(cursor.getString(17));
                news.setVersionCode(cursor.getString(18));
                news.setUpdated(cursor.getString(19));
                news.setSize(cursor.getString(20));
                news.setInstalls(cursor.getString(21));
                news.setAndroidRequired(cursor.getString(22));
                news.setRating(cursor.getString(23));
                news.setPermissions(cursor.getString(24));
                news.setDeveloper(cursor.getString(25));
                news.setWhatsNew(cursor.getString(26));

                newsList.add(news);
            } while (cursor.moveToNext());
        }

        if (criteria != null && criteria.size() > 0)
            newsList = FilterLayer.filteredNewsList(newsList, criteria, context);
        return newsList;

    }

    public News fetchNewsByName(String newsName) {
        News news = new News();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db
                .rawQuery(
                        "select * from news where city='"
                                + Location.getInstance().getCity() + "' and newsTitle='" + newsName + "'", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {


                news.setNewsId(cursor.getString(1));
                news.setTitle(cursor.getString(2));
                news.setReporter(cursor.getString(3));
                news.setPostingDate(cursor.getString(4));
                news.setEndDate(cursor.getString(5));
                news.setImages(cursor.getString(6));
                news.setDetail(cursor.getString(7));
                news.setCategory(getCategory(cursor.getString(8)));
                news.setCategoryId(cursor.getString(8));
                news.setCommentsId(cursor.getString(9));
                news.setType(cursor.getString(10));
                news.setStatus(cursor.getString(11));
                news.setLocality(cursor.getString(12));
                news.setCity(cursor.getString(13));
                news.setState(cursor.getString(14));
                news.setCountry(cursor.getString(15));
                news.setAdvertiseImage(cursor.getString(16));
                news.setPackageName(cursor.getString(17));
                news.setVersionCode(cursor.getString(18));
                news.setUpdated(cursor.getString(19));
                news.setSize(cursor.getString(20));
                news.setInstalls(cursor.getString(21));
                news.setAndroidRequired(cursor.getString(22));
                news.setRating(cursor.getString(23));
                news.setPermissions(cursor.getString(24));
                news.setDeveloper(cursor.getString(25));
                news.setWhatsNew(cursor.getString(26));

            } while (cursor.moveToNext());
            return news;
        } else {
            return null;
        }


    }

    public boolean addCategory(ArrayList<Category> categoryList) {

        SQLiteDatabase db = this.getWritableDatabase();
        for (Category category : categoryList) {
            Cursor cursor = db.rawQuery(
                    "Select * from category where categoryId='"
                            + category.getId() + "'", null);
            if (cursor.getCount() > 0) {

            } else {
                ContentValues values = new ContentValues();
                values.put(KEY_CATEGORY, category.getId());
                values.put(KEY_CATEGORY_NAME, category.getCategory());
                values.put(KEY_DETAILS, category.getDetail());

                db.insert("category", null, values);
            }
        }

        // db.close(); // Closing database connection
        return true;
    }

    public ArrayList<String> getCategoryList() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> categoryList = new ArrayList<String>();
        Cursor cursor = db.rawQuery("Select * from category", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                categoryList.add(cursor.getString(2));

            } while (cursor.moveToNext());
        }
        return categoryList;
    }

    public Category getCategory(String categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> categoryList = new ArrayList<String>();
        Category category = new Category();
        Cursor cursor = db.rawQuery("Select * from category where categoryId='" + categoryId + "'", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                categoryList.add(cursor.getString(2));
                category.setId(cursor.getString(1));
                category.setCategory(cursor.getString(2));
                category.setDetail(cursor.getString(3));
                break;

            } while (cursor.moveToNext());
        }
        return category;
    }

    public boolean addSubcategory(ArrayList<Subcategory> subcategoryList) {

        SQLiteDatabase db = this.getWritableDatabase();
        for (Subcategory subcategory : subcategoryList) {
            ContentValues values = new ContentValues();
            values.put(KEY_SUBCATEGORY, subcategory.getId());
            values.put(KEY_SUB_CATEGORY_NAME, subcategory.getSubcategory());
            values.put(KEY_CATEGORY, subcategory.getId());

            db.insert("subcategory", null, values);
        }
        // db.close(); // Closing database connection
        return true;
    }

    public boolean addSubcategory2(ArrayList<Subcategory2> subcategory2List) {

        SQLiteDatabase db = this.getWritableDatabase();
        for (Subcategory2 subcategory2 : subcategory2List) {
            ContentValues values = new ContentValues();
            values.put(KEY_SUBCATEGORY2, subcategory2.getId());
            values.put(KEY_SUB_CATEGORY2_NAME, subcategory2.getSubcategory2());
            values.put(KEY_SUBCATEGORY, subcategory2.getSubcategoryId());

            db.insert("subcategory2", null, values);
        }

        // db.close(); // Closing database connection
        return true;
    }

    public boolean addSubcategory3(ArrayList<Subcategory3> subcategory3List) {

        SQLiteDatabase db = this.getWritableDatabase();
        for (Subcategory3 subcategory3 : subcategory3List) {
            ContentValues values = new ContentValues();
            values.put(KEY_SUBCATEGORY3, subcategory3.getId());
            values.put(KEY_SUB_CATEGORY3_NAME, subcategory3.getSubcategory3());
            values.put(KEY_SUBCATEGORY2, subcategory3.getSubcategory2Id());

            db.insert("subcategory3", null, values);
        }
        // db.close(); // Closing database connection
        return true;
    }


    public ArrayList<News> getNewssInCity(String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<News> newsList = new ArrayList<News>();

        Cursor cursor = db
                .rawQuery(
                        "Select * from news  where city='"
                                + city + "' order by cast(newsId as INTEGER) desc", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                News news = new News();
                news.setNewsId(cursor.getString(1));
                news.setTitle(cursor.getString(2));
                news.setReporter(cursor.getString(3));
                news.setPostingDate(cursor.getString(4));
                news.setEndDate(cursor.getString(5));
                news.setImages(cursor.getString(6));
                news.setDetail(cursor.getString(7));
                news.setCategory(getCategory(cursor.getString(8)));
                news.setCategoryId(cursor.getString(8));
                news.setCommentsId(cursor.getString(9));
                news.setType(cursor.getString(10));
                news.setStatus(cursor.getString(11));
                news.setLocality(cursor.getString(12));
                news.setCity(cursor.getString(13));
                news.setState(cursor.getString(14));
                news.setCountry(cursor.getString(15));
                news.setAdvertiseImage(cursor.getString(16));
                news.setPackageName(cursor.getString(17));
                news.setVersionCode(cursor.getString(18));
                news.setUpdated(cursor.getString(19));
                news.setSize(cursor.getString(20));
                news.setInstalls(cursor.getString(21));
                news.setAndroidRequired(cursor.getString(22));
                news.setRating(cursor.getString(23));
                news.setPermissions(cursor.getString(24));
                news.setDeveloper(cursor.getString(25));
                news.setWhatsNew(cursor.getString(26));

                newsList.add(news);
            } while (cursor.moveToNext());
        }
        return newsList;

    }

    //	public int getCompanyCount() {
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery("Select count(*) from company", null);
//		cursor.moveToFirst();
//		return cursor.getInt(0);
//	}
    public ArrayList<String> getLocalityList() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> localityList = new ArrayList<String>();
        Cursor cursor = db.rawQuery("Select distinct(locality) from news where city='"
                + Location.getInstance().getCity() + "'", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                localityList.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        return localityList;
    }//@Deprecated
//	public ArrayList<String> getCompanyList() {
//		SQLiteDatabase db = getWritableDatabase();
//		ArrayList<String> companyNameList=new ArrayList<String>();
//		Cursor cursor = db.rawQuery("Select distinct(name) from company where city='" + Location.getInstance().getCity()+ "'", null);
//		Company company = new Company();
//		if (cursor.getCount() > 0) {
//			cursor.moveToFirst();
//			do{
//			companyNameList.add(cursor.getString(0  ));
//			}while(cursor.moveToNext());
//				cursor.close();
//		}
//		return companyNameList;
//	}

    public void updateSeenStatus() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("update news set status='SEEN'");

    }

    public void updateEventSeenStatus() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("update events set status='SEEN'");

    }

    public ArrayList<Event> fetchNewEvents() {
        ArrayList<Event> eventList = new ArrayList<Event>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "Select * from events where status='NEW' and city='"
                        + Location.getInstance().getCity() + "'", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                Event event = new Event();
                event.setAttractionPoint(cursor.getString(1));
                event.setCategory(cursor.getString(2));
                event.setDetail(cursor.getString(3));
                event.setEventAddress(cursor.getString(4));
                event.setEventStartDateTime(cursor.getString(5));
                event.setEventId(cursor.getString(6));
                event.setEventLocation(cursor.getString(7));
                event.setEventName(cursor.getString(8));
                event.setEventStartDateTime(cursor.getString(9));
                event.setGoing(cursor.getString(10));
                event.setImages(cursor.getString(11));
                event.setOrganizerDetail(cursor.getString(12));
                event.setPeopleJoining(cursor.getString(13));
                event.setStatus(cursor.getString(14));
                event.setCity(cursor.getString(15));
                event.setState(cursor.getString(16));
                event.setCountry(cursor.getString(17));
                eventList.add(event);
            } while (cursor.moveToNext());
        }
        return eventList;
    }

    public ArrayList<Event> fetchEvents(String location) {
        ArrayList<Event> eventList = new ArrayList<Event>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from events where city='"
                + location + "'", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                Event event = new Event();
                event.setAttractionPoint(cursor.getString(1));
                event.setCategory(cursor.getString(2));
                event.setDetail(cursor.getString(3));
                event.setEventAddress(cursor.getString(4));
                event.setEventStartDateTime(cursor.getString(5));
                event.setEventId(cursor.getString(6));
                event.setEventLocation(cursor.getString(7));
                event.setEventName(cursor.getString(8));
                event.setEventStartDateTime(cursor.getString(9));
                event.setGoing(cursor.getString(10));
                event.setImages(cursor.getString(11));
                event.setOrganizerDetail(cursor.getString(12));
                event.setPeopleJoining(cursor.getString(13));
                event.setStatus(cursor.getString(14));
                event.setCity(cursor.getString(15));
                event.setState(cursor.getString(16));
                event.setCountry(cursor.getString(17));
                eventList.add(event);
            } while (cursor.moveToNext());
        }
        return eventList;
    }

    public String getLastEventId() {
        String lastEventId = "0";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;

        cursor = db.rawQuery("Select max(eventId) from events where city='"
                + Location.getInstance().getCity() + "'", null);

        try {

            cursor.moveToFirst();

            lastEventId = String.valueOf(cursor.getInt(0));
        } catch (Exception ex) {
            return lastEventId;
        }
        return lastEventId;
    }

    public void addEvent(ArrayList<Event> eventList, String status) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            for (Event event : eventList) {
                Cursor cursor = db.rawQuery(
                        "Select * from events where eventId='"
                                + event.getEventId() + "'", null);
                // Cursor cursor=db.rawQuery("Select * from offers", null);
                if (cursor.getCount() <= 0) {

                    ContentValues eventValues = new ContentValues();
                    eventValues.put(KEY_EVENT_ID, event.getEventId());
                    eventValues.put(KEY_EVENT_NAME, event.getEventName());
                    eventValues.put(KEY_ATTRACTION_POINT,
                            event.getAttractionPoint());
                    eventValues.put(KEY_EVENT_CATEGORY, event.getCategory());
                    eventValues.put(KEY_DETAILS, event.getDetail());
                    eventValues.put(KEY_EVENT_ADDRESS, event.getEventAddress());
                    eventValues.put(KEY_EVENT_END_DATE_TIME,
                            event.getEventEndDateTime());
                    eventValues.put(KEY_EVENT_LOCATION,
                            event.getEventLocation());
                    eventValues.put(KEY_EVENT_START_DATE_TIME,
                            event.getEventStartDateTime());
                    eventValues.put(KEY_ORGANIZER_DETAIL,
                            event.getOrganizerDetail());
                    eventValues.put(KEY_PEOPLE_JOINING,
                            event.getPeopleJoining());
                    eventValues.put(KEY_GOING, event.getGoing());
                    eventValues.put(KEY_IMAGES, event.getImages());
                    eventValues.put(KEY_STATUS, status);
                    eventValues.put(KEY_CITY, event.getCity());
                    eventValues.put(KEY_STATE, event.getState());
                    eventValues.put(KEY_COUNTRY, event.getCountry());
                    db.insert("events", null, eventValues);

                }
            }
        } catch (Exception ex) {

            File sdcard = Environment.getExternalStorageDirectory();
            File file = new File(sdcard, "ErrorLog.txt");
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.append(ex.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void deleteClosedNews(String newsIdList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete from news where newsId IN (" + newsIdList + ")");
        //db.execSQL(Script.DELETE_EXPIRED_COMPANIES);
    }

}
