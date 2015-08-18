package com.sourabh.assemblers;

import com.sourabh.entity.OperatorPlan;
import com.sourabh.entity.OperatorPlansSegregator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SOURABH on 8/6/2015.
 */
public class OperatorPlansAssembler {
    public OperatorPlansSegregator responseToPlans(String json) {
        OperatorPlansSegregator operatorPlansSegregator = new OperatorPlansSegregator();
        ArrayList<OperatorPlan> fullTalktime = new ArrayList<OperatorPlan>();
        ArrayList<OperatorPlan> topup = new ArrayList<OperatorPlan>();
        ArrayList<OperatorPlan> special = new ArrayList<OperatorPlan>();
        ArrayList<OperatorPlan> g2 = new ArrayList<OperatorPlan>();
        ArrayList<OperatorPlan> g3 = new ArrayList<OperatorPlan>();
        ArrayList<OperatorPlan> roaming = new ArrayList<OperatorPlan>();

        ArrayList<OperatorPlan> operatorPlanArrayList = new ArrayList<OperatorPlan>();
        try {
            //JSONObject jj=new JSONObject(json);

            json = json.replaceAll("\\[|\\]", "");
            json += "]";
            json = "[" + json;
            //JSONArray jarray=jj.getJSONArray("data");
            JSONArray jarray = new JSONArray(json);
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jsonObject = jarray.getJSONObject(i);
                OperatorPlan operatorPlan = new OperatorPlan();
                //Set company
               /* operatorPlan.setId(jsonObject.getString("id"));
                operatorPlan.setRecharge_value(jsonObject.getString("recharge_value"));
                operatorPlan.setRecharge_talktime(jsonObject.getString("recharge_talktime"));
                operatorPlan.setRecharge_validity(jsonObject.getString("recharge_validity"));
                operatorPlan.setRecharge_short_description(jsonObject.getString("recharge_short_description"));
                operatorPlan.setRecharge_description(jsonObject.getString("recharge_description"));
                operatorPlan.setRecharge_description_more(jsonObject.getString("recharge_description_more"));
                operatorPlan.setProduct_type(jsonObject.getString("product_type"));
                operatorPlan.setCircle_master(jsonObject.getString("circle_master"));
                operatorPlan.setOperator_master(jsonObject.getString("operator_master"));
                operatorPlan.setRecharge_master(jsonObject.getString("recharge_master"));
                operatorPlan.setIs_prepaid(jsonObject.getString("is_prepaid"));*/
                operatorPlan.setId(jsonObject.getString("id"));
                operatorPlan.setRecharge_value(jsonObject.getString("recharge_amount"));
                operatorPlan.setRecharge_talktime(jsonObject.getString("recharge_talktime"));
                operatorPlan.setRecharge_validity(jsonObject.getString("recharge_validity"));
                operatorPlan.setRecharge_short_description(jsonObject.getString("recharge_shortdesc"));
                operatorPlan.setRecharge_description_more(jsonObject.getString("recharge_longdesc"));
                operatorPlan.setProduct_type(jsonObject.getString("recharge_type"));
                operatorPlan.setCircle_master(jsonObject.getString("circleid"));
                operatorPlan.setOperator_master(jsonObject.getString("operatorid"));
                if (operatorPlan.getProduct_type().equals("Full Talktime")) {
                    fullTalktime.add(operatorPlan);
                } else if (operatorPlan.getProduct_type().equals("Top up")) {
                    topup.add(operatorPlan);
                } else if (operatorPlan.getProduct_type().equals("Special Recharge")) {
                    special.add(operatorPlan);
                } else if (operatorPlan.getProduct_type().equals("2G Data")) {
                    g2.add(operatorPlan);
                } else if (operatorPlan.getProduct_type().equals("3G Data")) {
                    g3.add(operatorPlan);
                } else if (operatorPlan.getProduct_type().equals("Roaming")) {
                    roaming.add(operatorPlan);
                }

                //operatorPlanArrayList.add(operatorPlan);

            }
            operatorPlansSegregator.setFullTalktime(fullTalktime);
            operatorPlansSegregator.setTopup(topup);
            operatorPlansSegregator.setSpecial(special);
            operatorPlansSegregator.setG2(g2);
            operatorPlansSegregator.setG3(g3);
            operatorPlansSegregator.setRoaming(roaming);
            return operatorPlansSegregator;
        } catch (JSONException e) {
            String bb = e.toString();
            e.printStackTrace();
            return operatorPlansSegregator;
        } catch (Exception ex) {
            String bb = ex.toString();
            return operatorPlansSegregator;
        }

    }
}
