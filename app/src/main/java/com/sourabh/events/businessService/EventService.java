package com.sourabh.events.businessService;

import com.sourabh.constants.ServiceConstants;
import com.sourabh.events.assembler.EventAssembler;
import com.sourabh.events.entity.Event;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class EventService implements IEventService {

    EventAssembler eventAssembler;
    ArrayList<Event> eventList;

    @Override
    public ArrayList<Event> fetchEvents(String lastEventId, String locationCity) {

        eventAssembler = new EventAssembler();
        SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.FETCH_EVENTS_OPERATION);
        PropertyInfo pi = new PropertyInfo();
        if (lastEventId == null)
            lastEventId = "0";
        pi.setName("lastEventId");
        pi.setValue(lastEventId);
        pi.setType(String.class);
        request.addProperty(pi);
        pi = new PropertyInfo();
        pi.setName("locationCity");
        pi.setValue(locationCity);
        pi.setType(String.class);
        request.addProperty(pi);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);

        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.EVENT_SERVICESOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.FETCH_EVENTS_OPERATION, envelope);
            response = envelope.getResponse();
            eventList = eventAssembler.responseToEventList(response.toString());
            return eventList;
        } catch (Exception exception) {
            return eventList;
        }

    }

}
