package com.sourabh.businessService;

import android.util.Log;

import com.sourabh.DTO.LoginDTO;
import com.sourabh.DTO.RegisterDTO;
import com.sourabh.DTO.UserDetailsDTO;
import com.sourabh.assemblers.LoginAssembler;
import com.sourabh.constants.ServiceConstants;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class AuthenticationService implements AuthenticationInterface {

    private LoginAssembler assembler;

    @Override
    public UserDetailsDTO doLogin(LoginDTO loginDTO) {
        assembler = new LoginAssembler();
        SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.LOGIN_OPERATION);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("json");
        pi.setValue(loginDTO.toJsonString());
        pi.setType(String.class);
        request.addProperty(pi);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.SOAP_ADDRESS);
        Object response = null;

        try {
            httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.LOGIN_OPERATION, envelope);
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
        UserDetailsDTO userDetailsDTO = null;
        try {

            userDetailsDTO = assembler.loginResponseToUser(response.toString());

        } catch (Exception ex) {
            Log.d("ERR", "");
        }
        return userDetailsDTO;


    }

    @Override
    public UserDetailsDTO doRegister(RegisterDTO registerDTO) {
        assembler = new LoginAssembler();
        SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.REGISTER_OPERATION);
        PropertyInfo pi = new PropertyInfo();
        /*pi.setName("username");
                pi.setValue(registerDTO.getUsername());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi=new PropertyInfo();
		        pi.setName("password");
		        pi.setValue(registerDTO.getPassword());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi.setName("refferalid");
		        pi.setValue(registerDTO.getRefferalCode());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi=new PropertyInfo();
		        pi.setName("dateofregistration");
		        pi.setValue(registerDTO.getPassword());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi=new PropertyInfo();
		        pi.setName("emailaddress");
		        pi.setValue(registerDTO.getEmailId());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi=new PropertyInfo();
		        pi.setName("mobileno");
		        pi.setValue(registerDTO.getMobileNo());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi=new PropertyInfo();
		        pi.setName("address");
		        pi.setValue(registerDTO.getPassword());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi=new PropertyInfo();
		        pi.setName("locality");
		        pi.setValue(registerDTO.getPassword());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi=new PropertyInfo();
		        pi.setName("city");
		        pi.setValue(registerDTO.getPassword());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi=new PropertyInfo();
		        pi.setName("state");
		        pi.setValue(registerDTO.getPassword());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi=new PropertyInfo();
		        pi.setName("pincode");
		        pi.setValue(registerDTO.getPassword());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi=new PropertyInfo();
		        pi.setName("age");
		        pi.setValue(registerDTO.getPassword());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi=new PropertyInfo();
		        pi.setName("details");
		        pi.setValue(registerDTO.getPassword());
		        pi.setType(String.class);
		        request.addProperty(pi);
		        pi=new PropertyInfo();
		        pi.setName("credit");
		        pi.setValue(registerDTO.getPassword());
		        pi.setType(String.class);
		        request.addProperty(pi);
*/
        pi.setName("json");
        pi.setValue(registerDTO.toJsonString().replaceAll("null", ""));
        pi.setType(String.class);
        request.addProperty(pi);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        UserDetailsDTO userDetailsDTO = null;
        HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.REGISTER_OPERATION, envelope);
            response = envelope.getResponse();
            userDetailsDTO = assembler.loginResponseToUser(response.toString());
            return userDetailsDTO;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        //return response.toString();
        return userDetailsDTO;
    }


}
