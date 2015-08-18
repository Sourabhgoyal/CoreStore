package com.sourabh.businessService;

import com.sourabh.assemblers.CategoriesAssembler;
import com.sourabh.constants.ServiceConstants;
import com.sourabh.entity.Category;
import com.sourabh.entity.Subcategory;
import com.sourabh.entity.Subcategory2;
import com.sourabh.entity.Subcategory3;
import com.sourabh.utility.Utilities;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class CategoriesService implements ICategoriesService {

    CategoriesAssembler assembler;

    @Override
    public ArrayList<Category> getCategories(String lastCategoryId) {
        ArrayList<Category> categoryListResponse = new ArrayList<Category>();
        try {
            assembler = new CategoriesAssembler();
            SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.FETCH_NEW_CATEGORIES_OPERATION);
            PropertyInfo pi = new PropertyInfo();
            pi.setName("lastCategoryId");
            pi.setValue(lastCategoryId);
            pi.setType(String.class);
            request.addProperty(pi);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.CATEGORY_SERVICESOAP_ADDRESS);
            Object response = null;

            try {
                httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.FETCH_NEW_CATEGORIES_OPERATION, envelope);
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
            categoryListResponse = assembler.responseToCategoriesAll(response.toString());

            return categoryListResponse;
        } catch (Exception ex) {
            Utilities.write("ErrorLog", "Encountered error in Category Service." + ex.getMessage());
            return categoryListResponse;
        }

    }

    @Override
    public ArrayList<Subcategory> getSubcategories(String lastSubCategoryId) {
        assembler = new CategoriesAssembler();
        SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.FETCH_NEW_SUBCATEGORIES_OPERATION);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("json");
        pi.setValue(lastSubCategoryId);
        pi.setType(String.class);
        request.addProperty(pi);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.CATEGORY_SERVICESOAP_ADDRESS);
        Object response = null;

        try {
            httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.FETCH_NEW_SUBCATEGORIES_OPERATION, envelope);
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
        ArrayList<Subcategory> subcategoryListResponse = assembler.responseToSubCategoriesAll(response.toString());

        return subcategoryListResponse;
    }

    @Override
    public ArrayList<Subcategory2> getSubcategories2(String lastSubCategory2Id) {
        assembler = new CategoriesAssembler();
        SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.FETCH_NEW_SUBCATEGORIES2_OPERATION);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("json");
        pi.setValue(lastSubCategory2Id);
        pi.setType(String.class);
        request.addProperty(pi);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.CATEGORY_SERVICESOAP_ADDRESS);
        Object response = null;

        try {
            httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.FETCH_NEW_SUBCATEGORIES2_OPERATION, envelope);
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
        ArrayList<Subcategory2> subcategory2ListResponse = assembler.responseToSubCategories2All(response.toString());

        return subcategory2ListResponse;
    }

    @Override
    public ArrayList<Subcategory3> getSubcategories3(String lastSubcategory3Id) {
        assembler = new CategoriesAssembler();
        SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.FETCH_NEW_SUBCATEGORIES3_OPERATION);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("json");
        pi.setValue(lastSubcategory3Id);
        pi.setType(String.class);
        request.addProperty(pi);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.CATEGORY_SERVICESOAP_ADDRESS);
        Object response = null;

        try {
            httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.FETCH_NEW_SUBCATEGORIES3_OPERATION, envelope);
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
        ArrayList<Subcategory3> subcategory3ListResponse = assembler.responseToSubCategories3All(response.toString());

        return subcategory3ListResponse;
    }


}
