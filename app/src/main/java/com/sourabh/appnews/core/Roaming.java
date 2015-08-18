package com.sourabh.appnews.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sourabh.adapters.OperatorPlansListAdapter;
import com.sourabh.entity.OperatorPlan;
import com.sourabh.utility.Utilities;

import java.util.ArrayList;

/**
 * Created by SOURABH on 8/7/2015.
 */
public class Roaming extends Fragment {


    public static ArrayList<OperatorPlan> operatorPlansArrayList;


    // TODO: Rename and change types and number of parameters


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recommended, container, false);

        ListView operatorPlansList = (ListView) v.findViewById(R.id.operatorPlansListView);

        OperatorPlansListAdapter operatorPlansListAdapter = new OperatorPlansListAdapter(getActivity(), R.layout.operator_plans_list_item, operatorPlansArrayList);
        operatorPlansList.setAdapter(operatorPlansListAdapter);
        operatorPlansList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Utilities.writeToSharedPref(getActivity(), "RECHARGE_TYPE", operatorPlansArrayList.get(i).getRecharge_value());
                getActivity().finish();
                Intent intent = new Intent(getActivity(), RechargeActivity.class);
                Bundle conData = new Bundle();
                conData.putString("planType", operatorPlansArrayList.get(i).getRecharge_value());

                intent.putExtras(conData);
                getActivity().setResult(1212);
            }
        });
        return v;
    }


}
