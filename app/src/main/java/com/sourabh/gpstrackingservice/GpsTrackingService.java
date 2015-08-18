package com.sourabh.gpstrackingservice;

import com.sourabh.assemblers.NewsAssembler;
import com.sourabh.constants.ServiceConstants;
import com.sourabh.entity.News;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Arrays;

public class GpsTrackingService implements GpsTrackingInterface {

    NewsAssembler newsAssembler;
    ArrayList<News> newsList;

    @Override
    public ArrayList<String> fetchLocationrelatedData(String lat, String lon) {

        newsAssembler = new NewsAssembler();
        SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.FETCH_DATA_RELATED_TO_LOCATION_OPERATION);
//		PropertyInfo pi=new PropertyInfo();
//		pi.setName("lat");
//		pi.setValue(lat);
//		pi.setType(String.class);
//		request.addProperty(pi);
//		pi.setName("lon");
//		pi.setValue(lon);
//		pi.setType(String.class);
//		request.addProperty(pi);
            /*request.addProperty("lat", "19.20085863");
            request.addProperty("lon", "72.859026");*/
        request.addProperty("lat", lat);
        request.addProperty("lon", lon);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        ArrayList<String> newsIdList = null;
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.DATA_RELATED_TO_LOCATION_SERVICESOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.FETCH_DATA_RELATED_TO_LOCATION_OPERATION, envelope);
            response = envelope.getResponse();
            newsIdList = new ArrayList<String>(Arrays.asList(response.toString().split(",")));

            return newsIdList;
        } catch (Exception exception) {
            newsIdList = new ArrayList<String>(Arrays.asList(response.toString().split(",")));
            return newsIdList;
        }


    }
//	public void sampleMethod(){
//		offerAssembler=new OfferAssembler();
//		SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE,ServiceConstants.FETCH_DATA_RELATED_TO_LOCATION_OPERATION);
//		PropertyInfo pi=new PropertyInfo();
//		pi.setName("lat");
//		pi.setValue(lat);
//		pi.setType(String.class);
//		request.addProperty(pi);
//		pi.setName("lon");
//		pi.setValue(lon);
//		pi.setType(String.class);
//		request.addProperty(pi);
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
//		SoapEnvelope.VER11);
//		ArrayList<String> companyList = null;
//		envelope.dotNet = true;
//
//		envelope.setOutputSoapObject(request);
//
//		HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.DATA_RELATED_TO_LOCATION_SERVICESOAP_ADDRESS);
//		Object response=null;
//		try
//		{
//		httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE+ServiceConstants.FETCH_DATA_RELATED_TO_LOCATION_OPERATION, envelope);
//		response =envelope.getResponse();
//		companyList=new ArrayList<String>(Arrays.asList(response.toString().split(",")));
//		
//		return companyList;
//		}
//		catch (Exception exception)
//		{
//			return companyList;
//		}
//
//		
//	}
}
