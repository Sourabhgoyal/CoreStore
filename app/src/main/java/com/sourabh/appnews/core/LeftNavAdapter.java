package com.sourabh.appnews.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class LeftNavAdapter extends ArrayAdapter<String> {

    static int positionLock = 0;
    int resource;
    String response;
    Context context;
    String[] items;
    List<String> itemList;
    int images = R.drawable.deletered;

    //Initialize adapter
    public LeftNavAdapter(Context context, int resource, String[] items) {
        super(context, resource, items);
        this.resource = resource;

//        itemList=new ArrayList<String>();
//        for(int b=0;b<items.length;b++)
//        itemList.add(items[b]);
//       // this.items=items;
//        itemList.add("Saved Pages");
//
//        //this.items[items.length]="Saved Pages";
//        //int start=items.length;
//        try{
//        String[] fileNames=Utilities.read("Saved Pages").split("#$#$");
//        for(int i=0;i<fileNames.length;i++){
//        	itemList.add(fileNames[i].replace("#$#$", ""));
//        	//Utilities.read(fileNames[i].replace("#$#$", ""))
//        	//this.items[start]=Utilities.read(fileNames[i]);
//        }
//        this.items=Utilities.listToStringArray(itemList);
//        }catch(Exception ex){
//        	this.items=Utilities.listToStringArray(itemList);
//        }
        this.items = items;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout offerView = null;
        //Get the current alert object
        //  Coupons al = getItem(position);
        //      TypedArray imgs = offerView.getResources().obtainTypedArray(R.array.leftNavImages);
        //get resourceid by index


        // or set you ImageView's resource to the id
        //mImgView1.setImageResource(imgs.getResourceId(i, -1));
        //Inflate the view
        List<String> fixedMenu = Arrays.asList(getContext().getResources().getStringArray(R.array.GuestUser));
        if (convertView == null) {
            offerView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, offerView, true);
        } else {
            offerView = (LinearLayout) convertView;
        }
        TextView txt2 = (TextView) offerView.findViewById(R.id.text1);

        txt2.setText(items[position]);
//		if(items.get(position).equals(getContext().getResources().getString(R.string.LBL_SAVED_PAGES))){
//			positionLock=position;
//			txt2.setTextColor(Color.BLACK);
//
//		}
//		else if(!fixedMenu.contains(items.get(position))){
//			txt2.setTextColor(Color.GRAY);
//		}


        return offerView;
    }

}