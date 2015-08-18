package com.sourabh.businessService;

import com.sourabh.assemblers.OperatorPlansAssembler;
import com.sourabh.constants.ServiceConstants;
import com.sourabh.entity.OperatorPlansSegregator;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by SOURABH on 8/6/2015.
 */
public class RechargeService {
    OperatorPlansAssembler operatorPlansAssembler;
    OperatorPlansSegregator operatorPlansSegregator;

    public OperatorPlansSegregator fetchOperatorAndPlans(String mobileNo) {

        operatorPlansAssembler = new OperatorPlansAssembler();
        SoapObject request = new SoapObject(ServiceConstants.WSDL_TARGET_NAMESPACE, ServiceConstants.FETCH_OPERATOR_AND_PLANS);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("mobileNo");
        pi.setValue(mobileNo);
        pi.setType(String.class);
        request.addProperty(pi);

        request.addProperty(pi);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);

        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(ServiceConstants.RECHARGE_SERVICESOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(ServiceConstants.WSDL_TARGET_NAMESPACE + ServiceConstants.FETCH_OPERATOR_AND_PLANS, envelope);
            response = envelope.getResponse();
            operatorPlansSegregator = operatorPlansAssembler.responseToPlans(response.toString());
            return operatorPlansSegregator;
        } catch (Exception exception) {
            return operatorPlansSegregator;
        }

    }
}
