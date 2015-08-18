package com.sourabh.events;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.sourabh.appnews.core.R;
import com.sourabh.events.entity.Event;
import com.sourabh.utility.LoadImagesAndSerialize;
import com.sourabh.utility.Utilities;

import java.util.Locale;

public class EventDetailFragment extends Activity implements TextToSpeech.OnInitListener {


    public static Event event;
    ImageView img;
    LoadImage loadImage;
    TextToSpeech tts = null;

    @Override
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
        //Toast.makeText(getApplicationContext(), "Analytics Started", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
        //Toast.makeText(getApplicationContext(), "Analytics Stopped", Toast.LENGTH_LONG).show();
    }

    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (loadImage != null)
            loadImage.cancel(true);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                //Utilities.write(offer.getOfferName(), offer.toShare());
                String toShare = event.toShare() + "\n" + getResources().getString(R.string.APPLINK);
                if (toShare != null) {
                    Intent i = new Intent(android.content.Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(android.content.Intent.EXTRA_SUBJECT, event.getEventName());
                    i.putExtra(android.content.Intent.EXTRA_TEXT, toShare);
                    startActivity(Intent.createChooser(i, "Share via"));
                } else {
//			Toast.makeText(getApplicationContext(), getResources().getString(R.string.MSG_EVENT_NOT_AVAILABLE), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.readout:
                tts = new TextToSpeech(getApplicationContext(), this);

                break;
            default:
                super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            getMenuInflater().inflate(R.menu.event_menu_only_share, menu);
        } catch (NotFoundException e) {
            Utilities.write("ErrorLog", "Encountered error in Event detail fragment on create options menu." + e.getMessage());
            return false;
        }


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail_layout);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(51, 181, 229)));
        View v = null;
        try {
            if (event.getImages() != null && !event.getImages().equals("")) {
                loadImage = new LoadImage();
                loadImage.execute(event.getImages());
            }
//	v = inflater.inflate(R.layout.event_detail_layout, container, false);

            TextView eventName = (TextView) findViewById(R.id.eventDetailEventName);
            TextView eventOrganizerDetail = (TextView) findViewById(R.id.eventDetailOrganizerDetail);
            TextView eventAttractionPoint = (TextView) findViewById(R.id.eventDetailAttractionPoint);
            TextView eventStartDateTime = (TextView) findViewById(R.id.eventDetailEventStartDateTime);
            TextView eventEndDateTime = (TextView) findViewById(R.id.eventDetailEventEndDateTime);
            TextView eventDetailAddress = (TextView) findViewById(R.id.eventDetailAddress);
            TextView detail = (TextView) findViewById(R.id.eventDetailDetail);
            TextView location = (TextView) findViewById(R.id.eventDetailLocation);
//
            eventName.setText(event.getEventName());
            eventOrganizerDetail.setText(event.getOrganizerDetail());
            eventAttractionPoint.setText(event.getAttractionPoint());
            eventStartDateTime.setText(event.getEventStartDateTime());
            eventEndDateTime.setText(event.getEventEndDateTime());
            eventDetailAddress.setText(event.getEventAddress());
            detail.setText(event.getDetail());
            location.setText(event.getEventLocation());


            //getActivity().getActionBar().setTitle(rivers[position]);
        } catch (Exception ex) {
            Utilities.write("ErrorLog", "Encountered error in onCreateView of Event Detail Fragment." + ex.getMessage());
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = null;
        try {
            //new LoadImage().execute("http://www.tattoodesign.com/tattoo_pictures_gallery/ic/8701-8800/8777.jpg");
            if (event.getImages() != null && !event.getImages().equals("")) {
                loadImage = new LoadImage();
                loadImage.execute(event.getImages());
            }
            // Retrieving the currently selected item number
            //int position = getArguments().getInt("offerId");

            // List of rivers

            // Creating view correspoding to the fragment
            v = inflater.inflate(R.layout.event_detail_layout, container, false);

            // Getting reference to the TextView of the Fragment
            TextView eventName = (TextView) v.findViewById(R.id.eventDetailEventName);
            TextView eventOrganizerDetail = (TextView) v.findViewById(R.id.eventDetailOrganizerDetail);
            TextView eventAttractionPoint = (TextView) v.findViewById(R.id.eventDetailAttractionPoint);
            TextView eventStartDateTime = (TextView) v.findViewById(R.id.eventDetailEventStartDateTime);
            TextView eventEndDateTime = (TextView) v.findViewById(R.id.eventDetailEventEndDateTime);
            TextView eventDetailAddress = (TextView) v.findViewById(R.id.eventDetailAddress);
            TextView detail = (TextView) v.findViewById(R.id.eventDetailDetail);
            TextView location = (TextView) v.findViewById(R.id.eventDetailLocation);
//
            eventName.setText(event.getEventName());
            eventOrganizerDetail.setText(event.getOrganizerDetail());
            eventAttractionPoint.setText(event.getAttractionPoint());
            eventStartDateTime.setText(event.getEventStartDateTime());
            eventEndDateTime.setText(event.getEventEndDateTime());
            eventDetailAddress.setText(event.getEventAddress());
            detail.setText(event.getDetail());
            location.setText(event.getEventLocation());


            //getActivity().getActionBar().setTitle(rivers[position]);
        } catch (Exception ex) {
            Utilities.write("ErrorLog", "Encountered error in onCreateView of Event Detail Fragment." + ex.getMessage());
        }
        return v;
    }

    public void setEvent(Event event2) {
        event = event2;

    }

    private void speakOut() {
        try {

            String text = event.getDetail().substring(0, Math.min(event.getDetail().length(), 3000));
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        try {
            tts.stop();
        } catch (Exception ex) {
            Utilities.write("ErrorLog", ex.getMessage());
        }
        super.onPause();
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

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            pDialog = new ProgressDialog(MainActivity.this);
//            pDialog.setMessage("Loading Image ....");
//            pDialog.show();
        }

        protected Bitmap doInBackground(String... args) {
            Bitmap bitmap = null;
            try {
                bitmap = new LoadImagesAndSerialize().fetchImages(args[0], getApplicationContext());
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Load Image doInBackground of Event Detail Fragment." + ex.getMessage());
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {
            try {
                if (image != null) {
                    img = (ImageView) findViewById(R.id.eventDetailImage);
                    img.setImageBitmap(image);
//           pDialog.dismiss();
                } else {
//           pDialog.dismiss();
                    //Toast.makeText(getActivity(), getResources().getString(R.string.Image_Error), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Utilities.write("ErrorLog", "Encountered error in Load Image onPostExecute onPostExecute of Event Detail Fragment." + ex.getMessage());
            }
        }
    }

}
