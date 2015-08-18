package com.sourabh.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sourabh.appnews.core.R;
import com.sourabh.entity.OperatorPlan;

import java.util.ArrayList;

/**
 * Created by SOURABH on 8/7/2015.
 */
public class OperatorPlansListAdapter extends ArrayAdapter<OperatorPlan> {

    int resource;
    String response;
    Context context;
    ArrayList<OperatorPlan> itemsList;


    //Initialize adapter
    public OperatorPlansListAdapter(Context context, int resource, ArrayList<OperatorPlan> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Get the current alert object
        OperatorPlan operatorPlan = getItem(position);

        LinearLayout operatorView;
        //Inflate the view
        if (convertView == null) {
            operatorView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, operatorView, true);
        } else {
            operatorView = (LinearLayout) convertView;
        }
        //  ImageView img=(ImageView)offerView.findViewById(R.id.offerIconImage);
        TextView amount = (TextView) operatorView.findViewById(R.id.amount);
        TextView planName = (TextView) operatorView.findViewById(R.id.planName);
        TextView talktime = (TextView) operatorView.findViewById(R.id.talktime);
        TextView validity = (TextView) operatorView.findViewById(R.id.validity);
        amount.setText(operatorPlan.getRecharge_value());
        planName.setText(operatorPlan.getRecharge_short_description());
        talktime.setText(operatorPlan.getRecharge_talktime());
        validity.setText(operatorPlan.getRecharge_validity());

        return operatorView;
    }
}