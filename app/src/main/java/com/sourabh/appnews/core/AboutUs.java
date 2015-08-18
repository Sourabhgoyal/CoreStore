package com.sourabh.appnews.core;


import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

public class AboutUs extends Activity {

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
        setContentView(R.layout.aboutus);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(51, 181, 229)));
    }

//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View v = null;
//		try{
//		v = inflater.inflate(R.layout.aboutus, container, false);
//		AllScreens.ACTIONBARTITLE="About Us";
//		getActivity().getActionBar().setTitle(AllScreens.ACTIONBARTITLE);
//		
//
//		}catch(Exception ex){
//			Utilities.write("ErrorLog", "Encountered error in onCreateView of About Us."+ex.getMessage());
//		}		
//		return v;
//	}

}
