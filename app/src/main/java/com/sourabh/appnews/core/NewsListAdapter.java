package com.sourabh.appnews.core;


import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sourabh.entity.News;
import com.sourabh.entity.RowIconLoad;
import com.sourabh.utility.LoadImagesAndSerialize;
import com.sourabh.utility.Utilities;

import java.util.HashMap;
import java.util.List;

public class NewsListAdapter extends ArrayAdapter<News> {

    int resource;
    String response;
    Context context;
    List<News> itemsList;

    LoadImage loadImage;

    HashMap<String, LinearLayout> layoutMap;

    //Initialize adapter
    public NewsListAdapter(Context context, int resource, List<News> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        layoutMap = new HashMap<String, LinearLayout>();
        if (items != null) {
            this.itemsList = items;
        } else {
            return;
        }
    }

    @Override
    public int getCount() {
        return itemsList == null ? 0 : itemsList.size();
    }

    private boolean isPackageInstalled(String packagename) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Get the current alert object
        News news = getItem(position);

        LinearLayout newsView;
        //Inflate the view
        if (convertView == null) {
            newsView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, newsView, true);
        } else {
            newsView = (LinearLayout) convertView;
        }
        //  ImageView img=(ImageView)offerView.findViewById(R.id.offerIconImage);
        TextView txt1 = (TextView) newsView.findViewById(R.id.newsName);
        TextView developer = (TextView) newsView.findViewById(R.id.companyName);
        developer.setText(news.getDeveloper());
        TextView free = (TextView) newsView.findViewById(R.id.endDate);
        //img.setBackground(R.drawable.ic_launcher);

        txt1.setText(news.getTitle().replaceAll("\\\\n", "\n"));
        if (isPackageInstalled(news.getPackageName())) {
            free.setText("INSTALLED");
        }
    /*	if(news.getImages()!=null && !news.getImages().equals("")){
            layoutMap.put(news.getImages(), newsView);
			loadImage=new LoadImage();
        	loadImage.execute(news.getImages());
        }
         */
        return newsView;
    }

    private class LoadImage extends AsyncTask<String, String, RowIconLoad> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//	            pDialog = new ProgressDialog(MainActivity.this);
//	            pDialog.setMessage("Loading Image ....");
//	            pDialog.show();
        }

        protected RowIconLoad doInBackground(String... args) {
            Bitmap bitmap = null;
            RowIconLoad rowIconLoad = new RowIconLoad();
            try {
                bitmap = new LoadImagesAndSerialize().fetchImages(args[0], layoutMap.get(args[0]).getContext());
                rowIconLoad.setUrl(args[0]);
                rowIconLoad.setBitmap(bitmap);

            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Load Image doInBackground of Offer List Fragment." + ex.getMessage());
            }
            return rowIconLoad;
        }

        protected void onPostExecute(RowIconLoad rowIconLoad) {
            try {
                if (rowIconLoad != null) {
                    ImageView img = (ImageView) layoutMap.get(rowIconLoad.getUrl()).findViewById(R.id.newsIconImage);
                    img.setImageBitmap(rowIconLoad.getBitmap());
//		           pDialog.dismiss();
                } else {
//		           pDialog.dismiss();
                    //Toast.makeText(getActivity(), getResources().getString(R.string.Image_Error), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Load Image postExecute of Offer List Fragment." + ex.getMessage());
            }
        }
    }

}