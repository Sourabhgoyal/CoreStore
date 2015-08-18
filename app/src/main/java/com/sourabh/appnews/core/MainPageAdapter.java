package com.sourabh.appnews.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class MainPageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> categoryList;
    private int counter = 0;

    public MainPageAdapter(Context c, ArrayList<String> categoryList) {
        mContext = c;
        this.categoryList = categoryList;
    }

    public int getCount() {
        return categoryList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
            /*   View gridView = null;
               try{
			   LayoutInflater inflater = (LayoutInflater) mContext
			            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			        

			        if (convertView == null) {

			            gridView = new View(mContext);

			            // get layout from mobile.xml
			            gridView = inflater.inflate(R.layout.griditem, null);

			            // set image based on selected text
			            ImageView imageView = (ImageView) gridView
			                    .findViewById(R.id.griditemimage);

			            
			            imageView.setImageResource(R.drawable.boardimg);
			            TextView category=(TextView)gridView.findViewById(R.id.SubTitle);
			            Typeface custom_font = Typeface.createFromAsset(mContext.getAssets(),
			            	      "fonts/journal.ttf");
			            	      category.setTypeface(custom_font);
			            category.setText(categoryList.get(position));
			            
			        } else {
			            gridView = (View) convertView;
			        }
			  }catch(Exception ex){
					Utilities.write("ErrorLog", "Encountered error in getView of MainPageAdapter."+ex.getMessage());
				}
			        return gridView;*/
                    /*
			   TextView tv;
			   
			   ImageView imageView;
		      if (convertView == null) {
		      imageView = new ImageView(mContext);
		      imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
		      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		      imageView.setPadding(8, 8, 8, 8);
		    	  tv=new TextView(mContext);
		    	  tv.setText(categoryList.get(counter++));
		      } else {   
		    	  tv=(TextView)convertView;
		      imageView = (ImageView) convertView;
		      }
		      imageView.setClickable(true);
		      imageView.setImageResource(R.drawable.testbg);
		      return imageView;
		      
		      return tv;*/
        return null;
    }
    //
    // Keep all Images in array

}

