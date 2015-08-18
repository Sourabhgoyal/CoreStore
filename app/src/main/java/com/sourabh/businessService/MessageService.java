package com.sourabh.businessService;

import com.sourabh.assemblers.MessageAssembler;
import com.sourabh.constants.ServiceConstants;
import com.sourabh.entity.Message;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class MessageService implements IMessageService {

    MessageAssembler assembler;

    @Override
    public ArrayList<Message> fetchMessages(String location) {
        assembler = new MessageAssembler();
        SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.FETCH_NEW_MESSAGES_OPERATION);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("location");
        pi.setValue(location);
        pi.setType(String.class);
        request.addProperty(pi);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.MESSAGE_SOAP_ADDRESS);
        Object response = null;

        try {
            httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.FETCH_NEW_MESSAGES_OPERATION, envelope);
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
        ArrayList<Message> messageListResponse = assembler.responseToMessage(response.toString());

        return messageListResponse;

    }


}
