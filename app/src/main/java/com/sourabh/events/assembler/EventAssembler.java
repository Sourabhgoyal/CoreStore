package com.sourabh.events.assembler;

import com.sourabh.events.entity.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventAssembler {

    public ArrayList<Event> responseToEventList(String json) {
        ArrayList<Event> eventList = new ArrayList<Event>();
        try {

            JSONArray jarray = new JSONArray(json);
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jsonObject = jarray.getJSONObject(i);
                Event event = new Event();
                //Set company


                event.setEventId(jsonObject.getString("eventId"));
                event.setAttractionPoint(jsonObject.getString("attractionPoint"));
                event.setCategory(jsonObject.getString("category"));
                event.setDetail(jsonObject.getString("detail"));
                event.setEventAddress(jsonObject.getString("eventAddress"));
                event.setEventStartDateTime(jsonObject.getString("eventStartDateTime"));
                event.setEventEndDateTime(jsonObject.getString("eventEndDateTime"));
                event.setEventLocation(jsonObject.getString("eventLocation"));
                event.setEventName(jsonObject.getString("eventName"));
                event.setOrganizerDetail(jsonObject.getString("organizerDetail"));
                event.setPeopleJoining(jsonObject.getString("peopleJoining"));
                event.setStatus(jsonObject.getString("status"));
                event.setImages(jsonObject.getString("images"));
                event.setCity(jsonObject.getString("city"));
                event.setState(jsonObject.getString("state"));
                event.setCountry(jsonObject.getString("country"));
                eventList.add(event);

            }
            return eventList;
        } catch (JSONException e) {
            String bb = e.toString();
            e.printStackTrace();
            return eventList;
        } catch (Exception ex) {
            String bb = ex.toString();
            return eventList;
        }


    }


}
