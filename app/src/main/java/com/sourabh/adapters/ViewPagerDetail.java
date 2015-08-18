package com.sourabh.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sourabh.appnews.core.R;
import com.sourabh.entity.RowIconLoad;
import com.sourabh.utility.LoadImagesAndSerialize;
import com.sourabh.utility.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SOURABH on 7/29/2015.
 */
public class ViewPagerDetail extends PagerAdapter {

    HashMap<String, View> layoutMap;
    HashMap<String, ImageView> imageMap;
    LoadImage loadImage;
    LayoutInflater inflater;
    ArrayList<String> imagesList;
    Context context;

    public ViewPagerDetail(Context context, ArrayList<String> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
        layoutMap = new HashMap<String, View>();
        imageMap = new HashMap<String, ImageView>();

    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = null;

        // Declare Variables


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView = inflater.inflate(R.layout.view_pager_item, container, false);


        ImageView imgOffer = (ImageView) itemView
                .findViewById(R.id.view_pager_news_image);
        imgOffer.setId(position);
        imgOffer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "Clicked Offer "+v.getId(),
                // Toast.LENGTH_LONG).show();
                //    navigateToNewsDetail(newsList.get(v.getId()));

            }
        });
        /*
         * // Locate the ImageView in viewpager_item.xml imgflag = (ImageView)
		 * itemView.findViewById(R.id.flag); // Capture position and set to the
		 * ImageView imgflag.setImageResource(flag[position]);
		 */// Add viewpager_item.xml to ViewPager
        container.addView(itemView);
        if (imagesList != null && imagesList.size() > 0) {
            imageMap.put(imagesList.get(position), imgOffer);
            layoutMap.put(imagesList.get(position), itemView);
            loadImage = new LoadImage();
            loadImage.execute(imagesList.get(position));

        }
        return itemView;
    }

    private class LoadImage extends AsyncTask<String, String, RowIconLoad> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // pDialog = new ProgressDialog(MainActivity.this);
            // pDialog.setMessage("Loading Image ....");
            // pDialog.show();
        }

        protected RowIconLoad doInBackground(String... args) {
            Bitmap bitmap = null;
            RowIconLoad rowIconLoad = new RowIconLoad();
            try {
                bitmap = new LoadImagesAndSerialize().fetchImages(args[0],
                        context);

                rowIconLoad.setUrl(args[0]);
                rowIconLoad.setBitmap(bitmap);
            } catch (Exception ex) {
                Utilities.write("ErrorLog",
                        "Encountered error in Load Image doInBackground of News Pager Fragment."
                                + ex.getMessage());
            }
            return rowIconLoad;
        }

        protected void onPostExecute(RowIconLoad rowIconLoad) {
            try {
                if (rowIconLoad != null) {
                    // ImageView img=
                    // (ImageView)layoutMap.get(rowIconLoad.getUrl()).findViewById(R.id.view_pager_offer_image);
                    ImageView img = imageMap.get(rowIconLoad.getUrl());
                    img.setImageBitmap(rowIconLoad.getBitmap());
                    // pDialog.dismiss();
                } else {
                    // pDialog.dismiss();
                    // Toast.makeText(getActivity(),
                    // getResources().getString(R.string.Image_Error),
                    // Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Utilities
                        .write("ErrorLog",
                                "Encountered error in Load Image onPostExecute onPostExecute of Offer Pager Fragment."
                                        + ex.getMessage());
            }
        }
    }

}
