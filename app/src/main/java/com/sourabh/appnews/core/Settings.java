package com.sourabh.appnews.core;


import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sourabh.singletons.Location;
import com.sourabh.utility.Utilities;

public class Settings extends Activity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        super.onBackPressed();
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        try {
            setAutocompleteBox();
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(51, 181, 229)));
            final EditText txtCitySettings = (EditText) findViewById(R.id.txtCitySettings);
            txtCitySettings.setText(Utilities.readFromSharedPref(getApplicationContext(), "Location"));
            if (txtCitySettings.getText() != null && !txtCitySettings.getText().equals("")) {
                Button button = (Button) findViewById(R.id.saveSettings);
                button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Location.getInstance().setCity(txtCitySettings.getText().toString());

                        Utilities.writeToSharedPref(getApplicationContext(), "Location", txtCitySettings.getText().toString());
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.MSG_CITY_CHANGED), Toast.LENGTH_LONG).show();
                    }
                });
            }
            // Updating the action bar title
            //getActivity().getActionBar().setTitle(rivers[position]);
        } catch (Exception ex) {
            Utilities.write("ErrorLog", "Encountered error in onCreateView of Offer Detail Fragment." + ex.getMessage());
        }

    }

    private void setAutocompleteBox() {
        AutoCompleteTextView actv;
        actv = (AutoCompleteTextView) findViewById(R.id.txtCitySettings);

        String[] cities = getResources().
                getStringArray(R.array.cities);
        ArrayAdapter adapter = new ArrayAdapter
                (this, android.R.layout.simple_list_item_1, cities);
        actv.setAdapter(adapter);
    }
/*
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = null;
		try{
		v = inflater.inflate(R.layout.activity_settings, container, false);
		
		final EditText txtCitySettings=(EditText)v.findViewById(R.id.txtCitySettings);
		txtCitySettings.setText(Utilities.readFromSharedPref(getActivity(), "Location"));
		if(txtCitySettings.getText()!=null && !txtCitySettings.getText().equals("")){
		Button button = (Button) v.findViewById(R.id.saveSettings);
			   button.setOnClickListener(new OnClickListener()
			   {
			             @Override
			             public void onClick(View v)
			             {
			            	Location.getInstance().setCity(txtCitySettings.getText().toString());
			            	
			            	Utilities.writeToSharedPref(getActivity(), "Location", txtCitySettings.getText().toString());
			            	Toast.makeText(getActivity(), getResources().getString(R.string.MSG_CITY_CHANGED), Toast.LENGTH_LONG).show();
			             } 
			   }); 
		}
		// Updating the action bar title
		//getActivity().getActionBar().setTitle(rivers[position]);
		}catch(Exception ex){
			Utilities.write("ErrorLog", "Encountered error in onCreateView of Offer Detail Fragment."+ex.getMessage());
		}		
		return v;
	}

*/
}
