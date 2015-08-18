package com.sourabh.events;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sourabh.appnews.core.R;
import com.sourabh.entity.RowIconLoad;
import com.sourabh.events.entity.Event;
import com.sourabh.utility.LoadImagesAndSerialize;
import com.sourabh.utility.Utilities;

import java.util.HashMap;
import java.util.List;

public class EventListAdapter extends ArrayAdapter<Event> {
    int resource;
    String response;
    Context context;
    List<Event> itemsList;
    ImageView img;
    LoadImage loadImage;
    HashMap<String, LinearLayout> layoutMap;

    //Initialize adapter
    public EventListAdapter(Context context, int resource, List<Event> items) {
        super(context, resource, items);
        layoutMap = new HashMap<String, LinearLayout>();
        this.resource = resource;
        if (items != null) {
            this.itemsList = items;
        } else {
            return;
        }
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemsList == null ? 0 : itemsList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout eventView;
        //Get the current alert object
        Event event = getItem(position);


        //Inflate the view
        if (convertView == null) {
            eventView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, eventView, true);
        } else {
            eventView = (LinearLayout) convertView;
        }
        ImageView img = (ImageView) eventView.findViewById(R.id.eventIconImage);
        TextView txt1 = (TextView) eventView.findViewById(R.id.eventName);
        TextView txt2 = (TextView) eventView.findViewById(R.id.eventAttractionPoint);
        TextView txt3 = (TextView) eventView.findViewById(R.id.eventStartDateTime);
        //TextView txt4 = (TextView) offerView.findViewById(R.id.cost);
        //img.setBackground(R.drawable.ic_launcher);

        txt1.setText(event.getEventName().replaceAll("\\\\n", "\n"));
        try {
            txt2.setText(event.getAttractionPoint());
        } catch (Exception ex) {
            txt2.setText("");
        }
        txt3.setText(event.getEventStartDateTime());
        if (event.getImages() != null) {
            layoutMap.put(event.getImages(), eventView);
            loadImage = new LoadImage();
            loadImage.execute(event.getImages());

        }
        //alertDate.setText(al.alertdate);

        return eventView;
    }

    private class LoadImage extends AsyncTask<String, String, RowIconLoad> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//		            pDialog = new ProgressDialog(MainActivity.this);
//		            pDialog.setMessage("Loading Image ....");
//		            pDialog.show();
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
                    ImageView img = (ImageView) layoutMap.get(rowIconLoad.getUrl()).findViewById(R.id.eventIconImage);
                    img.setImageBitmap(rowIconLoad.getBitmap());
//			           pDialog.dismiss();
                } else {
//			           pDialog.dismiss();
                    //Toast.makeText(getActivity(), getResources().getString(R.string.Image_Error), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Load Image postExecute of Offer List Fragment." + ex.getMessage());
            }
        }
    }


}