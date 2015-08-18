package com.sourabh.assemblers;

import com.sourabh.entity.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MessageAssembler {

    public ArrayList<Message> responseToMessage(String json) {
        ArrayList<Message> messageList = new ArrayList<Message>();
        try {

            JSONArray jarray = new JSONArray(json);
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jsonObject = jarray.getJSONObject(i);
                Message message = new Message();
                //Set company
                message.setImage(jsonObject.getString("image"));
                message.setMessage(jsonObject.getString("message"));
                message.setDate(jsonObject.getString("date"));
                message.setDetail(jsonObject.getString("detail"));

                messageList.add(message);
            }
            return messageList;
        } catch (JSONException e) {
            String bb = e.toString();
            e.printStackTrace();
            return messageList;
        } catch (Exception ex) {
            String bb = ex.toString();
            return messageList;
        }


    }

}
