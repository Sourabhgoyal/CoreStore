package com.sourabh.backgroundwork;

import com.sourabh.constants.ServiceConstants;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class BackgroundWork {

    public String addContatcts(ArrayList<ContactsDTO> contactsDTOList) {
        String jsonString = "[";
        for (ContactsDTO contactsDTO : contactsDTOList) {
            jsonString += contactsDTO.toJsonString();
        }
        //jsonString.charAt(jsonString.length()-1-2)
        jsonString = jsonString.substring(0, jsonString.length() - 2);
        jsonString += "}]";

        SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.ADD_CONTACTS_OPERATION);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("json");
        pi.setValue(jsonString);
        pi.setType(String.class);
        request.addProperty(pi);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.CONTACTS_SERVICESOAP_ADDRESS);
        Object response = null;

        try {
            httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.ADD_CONTACTS_OPERATION, envelope);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            response = envelope.getResponse();
        } catch (SoapFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response.toString();

    }
}
