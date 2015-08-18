package com.sourabh.events;

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

import com.sourabh.appnews.core.R;
import com.sourabh.database.Thedb;
import com.sourabh.entity.RowIconLoad;
import com.sourabh.events.entity.Event;
import com.sourabh.singletons.Location;
import com.sourabh.utility.LoadImagesAndSerialize;
import com.sourabh.utility.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

public class EventViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    String[] rank;
    String[] country;
    String[] population;
    int[] flag;
    ArrayList<Event> eventList;
    LayoutInflater inflater;
    LoadImage loadImage;
    ImageView img;
    HashMap<String, ImageView> imageMap;
    HashMap<String, View> layoutMap;

    public EventViewPagerAdapter(Context context) {
        this.context = context;
        layoutMap = new HashMap<String, View>();
        imageMap = new HashMap<String, ImageView>();
        Thedb databaseHandler = Thedb.getInstance(context);
        eventList = databaseHandler.fetchEvents(Location.getInstance().getCity());
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Event event = eventList.get(position);


        // Declare Variables
        TextView txtEventName;
        View itemView;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemView = inflater.inflate(R.layout.view_pager_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        txtEventName = (TextView) itemView.findViewById(R.id.view_pager_news_name);

        // Capture position and set to the TextViews
        txtEventName.setText(eventList.get(position).getEventName());
        ImageView imgEvent = (ImageView) itemView.findViewById(R.id.view_pager_news_image);
        imgEvent.setId(position);
        imgEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                navigateToEventDetail(eventList.get(v.getId()));

            }
        });
 /*	
        // Locate the ImageView in viewpager_item.xml
		imgflag = (ImageView) itemView.findViewById(R.id.flag);
		// Capture position and set to the ImageView
		imgflag.setImageResource(flag[position]);
 
	*/    // Add viewpager_item.xml to ViewPager
        container.addView(itemView);
        if (event.getImages() != null && !event.getImages().equals("")) {
            imageMap.put(event.getImages(), imgEvent);
            layoutMap.put(event.getImages(), itemView);
            loadImage = new LoadImage();
            loadImage.execute(event.getImages());
        }
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        container.removeView((RelativeLayout) object);

    }

    protected void navigateToEventDetail(Event event) {
        /*EventDetailFragment rFragment=new EventDetailFragment();
        try{

		rFragment.setEvent(event);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		FragmentManager fragmentManager  =((Activity) context).getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();

		// Adding a fragment to the fragment transaction
		ft.add(R.id.event_content_frame, rFragment);
		ft.addToBackStack(null);

		ft.commit();
*/
        Intent eventIntent = new Intent(context, EventDetailFragment.class);
        EventDetailFragment.event = event;
        context.startActivity(eventIntent);

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
                bitmap = new LoadImagesAndSerialize().fetchImages(args[0], context);

                rowIconLoad.setUrl(args[0]);
                rowIconLoad.setBitmap(bitmap);
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Load Image doInBackground of Offer Pager Fragment." + ex.getMessage());
            }
            return rowIconLoad;
        }

        protected void onPostExecute(RowIconLoad rowIconLoad) {
            try {
                if (rowIconLoad != null) {
                    ImageView img = imageMap.get(rowIconLoad.getUrl());
                    img.setImageBitmap(rowIconLoad.getBitmap());
//		           pDialog.dismiss();
                } else {
//		           pDialog.dismiss();
                    //Toast.makeText(getActivity(), getResources().getString(R.string.Image_Error), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Load Image onPostExecute onPostExecute of Offer Pager Fragment." + ex.getMessage());
            }
        }
    }
}