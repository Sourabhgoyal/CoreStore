package com.sourabh.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sourabh.appnews.core.NewsDetailFragment;
import com.sourabh.appnews.core.R;
import com.sourabh.database.Thedb;
import com.sourabh.entity.News;
import com.sourabh.entity.RowIconLoad;
import com.sourabh.singletons.Location;
import com.sourabh.utility.LoadImagesAndSerialize;
import com.sourabh.utility.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    String[] rank;
    String[] country;
    String[] population;
    int[] flag;
    ArrayList<News> newsList;
    LayoutInflater inflater;
    LoadImage loadImage;
    HashMap<String, View> layoutMap;
    HashMap<String, ImageView> imageMap;


    public ViewPagerAdapter(Context context) {
        this.context = context;
        newsList = new ArrayList<News>();
        Thedb databaseHandler = Thedb.getInstance(context);
        newsList = databaseHandler.getNewssInCity(Location.getInstance()
                .getCity());
        layoutMap = new HashMap<String, View>();
        imageMap = new HashMap<String, ImageView>();
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        News news = newsList.get(position);
        View itemView = null;

        // Declare Variables
        TextView txtNewsName;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView = inflater.inflate(R.layout.view_pager_item, container, false);

        // Locate the TextViews in viewpager_item.xml
        txtNewsName = (TextView) itemView
                .findViewById(R.id.view_pager_news_name);

        // Capture position and set to the TextViews
        txtNewsName.setText(newsList.get(position).getTitle());
        txtNewsName.setId(position + 100);
        // txtNewsName.setAlpha(15);

        ImageView imgOffer = (ImageView) itemView
                .findViewById(R.id.view_pager_news_image);
        imgOffer.setId(position);
        imgOffer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "Clicked Offer "+v.getId(),
                // Toast.LENGTH_LONG).show();
                navigateToNewsDetail(newsList.get(v.getId()));

            }
        });
        /*
         * // Locate the ImageView in viewpager_item.xml imgflag = (ImageView)
		 * itemView.findViewById(R.id.flag); // Capture position and set to the
		 * ImageView imgflag.setImageResource(flag[position]);
		 */// Add viewpager_item.xml to ViewPager
        container.addView(itemView);
        /*if (news.getImages() != null && !news.getImages().equals("")) {
			imageMap.put(news.getImages(), imgOffer);
			layoutMap.put(news.getImages(), itemView);
			loadImage = new LoadImage();
			loadImage.execute(news.getImages());

		}*/
        return itemView;
    }

    protected void navigateToNewsDetail(News news) {
        // OfferDetailFragment rFragment=new OfferDetailFragment();
        // try{
        //
        // rFragment.setOffer(offer);
        // }catch(Exception ex){
        // ex.printStackTrace();
        // }
        // FragmentManager fragmentManager =((Activity)
        // context).getFragmentManager();
        // FragmentTransaction ft = fragmentManager.beginTransaction();
        //
        // // Adding a fragment to the fragment transaction
        // ft.add(R.id.content_frame, rFragment);
        // ft.addToBackStack(null);
        //
        // ft.commit();
        Intent offerDetail = new Intent(context, NewsDetailFragment.class);
        NewsDetailFragment.news = news;
        context.startActivity(offerDetail);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        container.removeView((RelativeLayout) object);

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