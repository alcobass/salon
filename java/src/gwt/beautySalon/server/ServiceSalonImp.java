package gwt.beautySalon.server;

import gwt.beautySalon.client.ServiceSalon;
import gwt.beautySalon.server.session.PaymentMethodSession;
import gwt.beautySalon.server.session.PaymentTypeSession;
import gwt.beautySalon.server.session.PriceListSession;
import gwt.beautySalon.server.session.ServiceSubtypeSession;
import gwt.beautySalon.server.session.ServiceTypeSession;
import gwt.beautySalon.shared.PaymentMethod;
import gwt.beautySalon.shared.PaymentType;
import gwt.beautySalon.shared.PriceList;
import gwt.beautySalon.shared.ServiceSubtype;
import gwt.beautySalon.shared.ServiceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ServiceSalonImp extends RemoteServiceServlet implements ServiceSalon {
    private static final long serialVersionUID = 1L;

    private Map<String, Object> allData = new HashMap<String, Object>();
    private static PriceListSession priceListSession;
    private static ServiceTypeSession serviceTypeSession;
    private static ServiceSubtypeSession serviceSubtypeSession;
    private static PaymentTypeSession paymentTypeSession;
    private static PaymentMethodSession paymentMethodSession;
    
    public ServiceSalonImp() {
        priceListSession = new PriceListSession();
        serviceTypeSession = new ServiceTypeSession();
        serviceSubtypeSession = new ServiceSubtypeSession();
        paymentTypeSession = new PaymentTypeSession();
        paymentMethodSession = new PaymentMethodSession();
        allData.put("priceList", priceListSession.selectAll());
        allData.put("serviceType", serviceTypeSession.selectAll());
        allData.put("serviceSubtype", serviceSubtypeSession.selectAll());
        allData.put("paymentType", paymentTypeSession.selectAll());
        allData.put("paymentMethod", paymentMethodSession.selectAll());
    }
    
    @Override
    public Map<String, Object> loadDictionaryData() {
        // TODO Auto-generated method stub
        allData.put("priceList", priceListSession.selectAll());
        allData.put("serviceType", serviceTypeSession.selectAll());
        allData.put("serviceSubtype", serviceSubtypeSession.selectAll());
        allData.put("paymentType", paymentTypeSession.selectAll());
        allData.put("paymentMethod", paymentMethodSession.selectAll());
        return allData;
    }

    @Override
    public ArrayList<PriceList> selectPriceList_null() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<ServiceType> selectServiceType_null() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<ServiceSubtype> selectServiceSubtype_null() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<PaymentType> selectPaymentType_null() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<PaymentMethod> selectPaymentMethod_null() {
        // TODO Auto-generated method stub
        return null;
    }

}