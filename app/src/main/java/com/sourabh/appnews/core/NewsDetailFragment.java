package com.sourabh.appnews.core;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sourabh.database.Thedb;
import com.sourabh.entity.News;
import com.sourabh.entity.User;
import com.sourabh.utility.LoadImagesAndSerialize;
import com.sourabh.utility.Utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NewsDetailFragment extends Activity {


    public static News news;
    static String filePathvideo;
    static int counter = 0;
    ImageView img;
    LoadImage loadImage;
    ProgressBar progressBar;
    LinearLayout layout1;
    Button installUninstall;
    TextView downloadAmount;


    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (loadImage != null)
            loadImage.cancel(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            ArrayList<String> arrayFiles = Utilities.read(getResources().getString(R.string.LBL_SAVED_PAGES), true);
            if (arrayFiles != null && arrayFiles.contains(news.getTitle())) {
                getMenuInflater().inflate(R.menu.menu_offer_detail_saved_offer, menu);
            } else {
                getMenuInflater().inflate(R.menu.detail_menu, menu);
            }
        } catch (NotFoundException e) {
            Utilities.write("ErrorLog", "Encountered error in offer detail fragment on create options menu." + e.getMessage());
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Intent i = new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT, news.getTitle());
                String toShare = news.toShare();
                if (toShare != null) {
                    i.putExtra(android.content.Intent.EXTRA_TEXT, toShare);
                    startActivity(Intent.createChooser(i, "Share via"));
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.MSG_NEWS_NOT_AVAILABLE), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.save:
                Utilities.write(news.getTitle(), news.toShare());
                Utilities.write(getResources().getString(R.string.LBL_SAVED_PAGES), news.getTitle());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.MSG_NEWS_SAVED), Toast.LENGTH_LONG).show();
                Intent initializer2Intent = new Intent(getApplicationContext(), Intitializer.class);
                startActivity(initializer2Intent);
                finish();
                break;
            case R.id.delete:
                Utilities.deleteFile(news.getTitle());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.MSG_FILE_DELETED), Toast.LENGTH_SHORT).show();
                Intent initializerIntent = new Intent(getApplicationContext(), Intitializer.class);
                startActivity(initializerIntent);
                finish();

                break;
            default:
                super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail_layout);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(51, 181, 229)));
        getActionBar().setTitle("News Detail");
        //Utilities.writeToSharedPref(getApplicationContext(), "INSTALLING", news.getPackageName());
        if (isPackageInstalled(Utilities.readFromSharedPref(getApplicationContext(), "INSTALLING"), getApplicationContext())) {
            Utilities.writeToSharedPref(getApplicationContext(), "INSTALLING", "");
            User.getInstance().setCredit(String.valueOf(Integer.parseInt(User.getInstance().getCredit()) + 5));
            Toast.makeText(getApplicationContext(), User.getInstance().getCredit(), Toast.LENGTH_LONG).show();
        }
        View v = null;
        try {
        /*if(news.getImages()!=null && !news.getImages().equals("")){
        loadImage=new LoadImage();
		loadImage.execute(news.getImages());
			}
*/
            TextView newsTitle = (TextView) findViewById(R.id.newsDetailNewsName);
            TextView newsReporter = (TextView) findViewById(R.id.newsDetailNewsReporter);
            TextView detail = (TextView) findViewById(R.id.newsDetailDetail);
            TextView packageName = (TextView) findViewById(R.id.packageName);
            TextView versionCode = (TextView) findViewById(R.id.versionCode);
            TextView updated = (TextView) findViewById(R.id.updated);
            TextView size = (TextView) findViewById(R.id.size);
            TextView installs = (TextView) findViewById(R.id.installs);
            TextView androidRequired = (TextView) findViewById(R.id.androidRequired);
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            layout1 = (LinearLayout) findViewById(R.id.layout1);
            TextView rating = (TextView) findViewById(R.id.rating);
            TextView permissions = (TextView) findViewById(R.id.permissions);
            TextView developer = (TextView) findViewById(R.id.developer);
            TextView whatsnew = (TextView) findViewById(R.id.whatsNew);
            LinearLayout videoArea = (LinearLayout) findViewById(R.id.hideAbleVideoArea);
            installUninstall = (Button) findViewById(R.id.install);
            downloadAmount = (TextView) findViewById(R.id.downloadAmount);
            if (news.getAdvertiseImage() == null || news.getAdvertiseImage().equals("")) {
                videoArea.setVisibility(View.GONE);
            }

            newsTitle.setText(Html.fromHtml(news.getTitle()));
            Thedb databaseHandler = Thedb.getInstance(getApplicationContext());
        /*DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMddHHmmss");
		
		DateTime dateTime = formatter.parseDateTime(news.getPostingDate());*/


            detail.setText(Html.fromHtml(news.getDetail().replaceAll("\\\\n", "\n")));//+"\n\n"+getResources().getString(R.string.LBL_EXPIRES_ON) +" : "+dateTime.toString(DateTimeFormat.forPattern("dd-MMM-yyyy HH:mm:ss")));
            //newsReporter.setText(Html.fromHtml(news.getReporter() + "\t" + dateTime.toString(DateTimeFormat.forPattern("dd-MMM-yyyy HH:mm:ss"))));
            packageName.setText(news.getPackageName());
            versionCode.setText(news.getVersionCode());
            updated.setText(news.getUpdated());
            size.setText(news.getSize());
            installs.setText(news.getInstalls());
            androidRequired.setText(news.getAndroidRequired());
            rating.setText(news.getRating());
            permissions.setText(news.getPermissions());
            developer.setText(Html.fromHtml(news.getDeveloper()));
            whatsnew.setText(news.getWhatsNew());
            if (isPackageInstalled(news.getPackageName(), getApplicationContext())) {
                installUninstall.setText("Uninstall");
            }
            // Updating the action bar title
            //getActivity().getActionBar().setTitle(rivers[position]);
        } catch (Exception ex) {
            Utilities.write("ErrorLog", "Encountered error in onCreateView of News Detail Fragment." + ex.getMessage());
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }

    public void setNews(News news) {
        NewsDetailFragment.news = news;

    }

    public void playVideo(View v) {


        if (news.getAdvertiseImage().contains("youtube")) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.getAdvertiseImage()));
            this.startActivity(i);
        } else {
            VideoPlayerActivity.url = news.getAdvertiseImage();
            Intent mediaIntent = new Intent(getApplicationContext(), VideoPlayerActivity.class);
            startActivity(mediaIntent);
        }
    }

    public void installApp(View v) {

        if (installUninstall.getText().equals("Uninstall")) {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:" + news.getPackageName()));
            startActivity(intent);
        } else {
            File sdCard = Environment.getExternalStorageDirectory();
//		String fileStr=sdCard.getAbsolutePath()+""+"app-release.apk";
            DownloadTask downloadTask = new DownloadTask();
            downloadTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, news.getWhatsNew());
            Utilities.writeToSharedPref(getApplicationContext(), "INSTALLING", news.getPackageName());
        }
//		File file=new File(sdCard,"app-release.apk");
//		Intent prompInstall=new Intent(Intent.ACTION_VIEW).setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//		startActivity(prompInstall);
        layout1.setVisibility(View.VISIBLE);


    }

    private boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
		/*int versionCode=pm.getPackageInfo(packagename,0).versionCode;
		String versionName=pm.getPackageInfo(packagename,0).versionName;*/
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//	            pDialog = new ProgressDialog(MainActivity.this);
//	            pDialog.setMessage("Loading Image ....");
//	            pDialog.show();
        }

        protected Bitmap doInBackground(String... args) {
            Bitmap bitmap = null;
            try {
                bitmap = new LoadImagesAndSerialize().fetchImages(args[0], getApplicationContext());
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Load Image doInBackground of News Detail Fragment." + ex.getMessage());
            }
            return bitmap;
        }

        protected void onPostExecute(final Bitmap image) {
            try {
                if (image != null) {
                    img = (ImageView) findViewById(R.id.newsDetailImage);
                    img.setImageBitmap(image);
                    img.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            Intent fullScreenImageIntent = new Intent(getApplicationContext(), FullScreenImage.class);
                            FullScreenImage.bitmap = image;
                            startActivity(fullScreenImageIntent);
                        }
                    });
//	           pDialog.dismiss();
                } else {
//	           pDialog.dismiss();
                    //Toast.makeText(getActivity(), getResources().getString(R.string.Image_Error), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Load Image onPostExecute onPostExecute of News Detail Fragment." + ex.getMessage());
            }
        }
    }

    private class DownloadTask extends AsyncTask<String, Integer, String> {


        @Override
        protected void onProgressUpdate(Integer... values) {
            double d = values[0] / (Double.parseDouble(news.getSize()) * 1024);
            d = d * 100;
            progressBar.setProgress((int) d);
            downloadAmount.setText(String.format("%.2f", values[0] / 1024.00) + " MB/" + news.getSize() + " MB");
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                File sdCard = Environment.getExternalStorageDirectory();

                filePathvideo = sdCard.getAbsolutePath() + "/app-release.apk";

                output = new FileOutputStream(filePathvideo);


                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    publishProgress((int) total / 1024);
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known

                        output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            File sdCard = Environment.getExternalStorageDirectory();
            counter = 0;
            if (result != null)
                Toast.makeText(getApplicationContext(), "Download error: " + result, Toast.LENGTH_LONG).show();
            else {
                try {
                    File file = new File(sdCard, "app-release.apk");
                    Intent prompInstall = new Intent(Intent.ACTION_VIEW).setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                    startActivity(prompInstall);
                    layout1.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    Utilities.write("ErrorLog", "Encountered error in video " + e.getMessage());

                    //   Toast.makeText(getActivity(), "Exception: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

                Toast.makeText(getApplicationContext(), "App downloaded", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
