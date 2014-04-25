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
import java.util.List;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ServiceSalonImp extends RemoteServiceServlet implements ServiceSalon {
    private static final long serialVersionUID = 1L;

    private Map<String, Object> allData = new HashMap<String, Object>();
    private List<ServiceType> serviceTypeList;
    private List<ServiceSubtype> serviceSubtypeList;
    private List<PriceList> priceListList;
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
    public ArrayList<PaymentType> selectPaymentType_null() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<PaymentMethod> selectPaymentMethod_null() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ServiceType> setServiceType(ServiceType type) {
        serviceTypeSession.insertServiceType(type);
        serviceTypeList = serviceTypeSession.selectAll();
        return serviceTypeList;
    }

    @Override
    public List<ServiceType> removeServiceType(int id) {
        // TODO Auto-generated method stub
        serviceTypeSession.deleteServiceType(id);
        serviceTypeList = serviceTypeSession.selectAll();
        return serviceTypeList;
    }

    public List<ServiceSubtype> setServiceSubtype(ServiceSubtype subtype) {
        serviceSubtypeSession.insertServiceSubtype(subtype);
        serviceSubtypeList = serviceSubtypeSession.selectAll();
        return serviceSubtypeList;
    }

    @Override
    public List<PriceList> setPriceList(PriceList priceList) {
        priceListSession.insertPriceList(priceList);
        priceListList = priceListSession.selectAll();
        return priceListList;
    }

    public List<ServiceType> updateServiceType(ServiceType type) {
        // TODO Auto-generated method stub
        serviceTypeSession.updateServicetype(type);
        serviceTypeList = serviceTypeSession.selectAll();
        return serviceTypeList;
    }

    @Override
    public List<ServiceSubtype> updateServiceSubtype(ServiceSubtype subtype) {
        // TODO Auto-generated method stub
        serviceSubtypeSession.updateServiceSubtype(subtype);
        serviceSubtypeList = serviceSubtypeSession.selectAll();
        return serviceSubtypeList;
    }

    @Override
    public List<PriceList> updatePriceList(PriceList priceList) {
        // TODO Auto-generated method stub
        priceListSession.updatePriceList(priceList);
        priceListList = priceListSession.selectAll();
        return priceListList;
    }

    @Override
    public List<ServiceSubtype> removeServiceSubtype(int id) {
        // TODO Auto-generated method stub
        serviceSubtypeSession.deleteServiceSubtype(id);
        serviceSubtypeList = serviceSubtypeSession.selectAll();
        return serviceSubtypeList;
    }

    @Override
    public List<PriceList> removePriceList(int id) {
        // TODO Auto-generated method stub
        priceListSession.deletePriceList(id);
        priceListList = priceListSession.selectAll();
        return priceListList;
    }
}