package com.sourabh.events.businessService;

import com.sourabh.events.entity.Event;

import java.util.ArrayList;

public interface IEventService {

    ArrayList<Event> fetchEvents(String lastEventId, String locationCity);
}
