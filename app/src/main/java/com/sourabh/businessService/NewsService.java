package com.sourabh.businessService;

import com.sourabh.assemblers.NewsAssembler;
import com.sourabh.constants.ServiceConstants;
import com.sourabh.entity.News;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class NewsService implements NewsInterface {

    NewsAssembler newsAssembler;
    ArrayList<News> newsList;

    @Override
    public ArrayList<News> fetchNews(String lastNewsId, String location) {

        newsAssembler = new NewsAssembler();
        SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.FETCH_ALL_NEWS_OPERATION);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("lastNewsId");
        pi.setValue(lastNewsId);
        pi.setType(String.class);
        request.addProperty(pi);
        pi = new PropertyInfo();
        pi.setName("locationCity");
        pi.setValue(location);
        pi.setType(String.class);
        request.addProperty(pi);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);

        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.NEWS_SERVICESOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.FETCH_ALL_NEWS_OPERATION, envelope);
            response = envelope.getResponse();
            newsList = newsAssembler.responseToNews(response.toString());
            return newsList;
        } catch (Exception exception) {
            return newsList;
        }

    }

    @Override
    public String fetchClosedNews(String location) {
        String newsList = "";
        newsAssembler = new NewsAssembler();
        SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.FETCH_CLOSED_NEWSOPERATION);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("locationCity");
        pi.setValue(location);
        pi.setType(String.class);
        request.addProperty(pi);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);

        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.NEWS_SERVICESOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.FETCH_CLOSED_NEWSOPERATION, envelope);
            response = envelope.getResponse();
            newsList = response.toString();

            return newsList;
        } catch (Exception exception) {
            return newsList;
        }
    }

}
