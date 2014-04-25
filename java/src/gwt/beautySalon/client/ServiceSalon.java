package gwt.beautySalon.client;

import gwt.beautySalon.shared.PaymentMethod;
import gwt.beautySalon.shared.PaymentType;
import gwt.beautySalon.shared.PriceList;
import gwt.beautySalon.shared.ServiceSubtype;
import gwt.beautySalon.shared.ServiceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("salon")
public interface ServiceSalon  extends RemoteService{

    Map<String, Object> loadDictionaryData();
    
    List<ServiceType> setServiceType(ServiceType type);
    
    List<ServiceType> removeServiceType(int id);
    
    List<ServiceType> updateServiceType(ServiceType type);
    
    List<ServiceSubtype> setServiceSubtype (ServiceSubtype subtype); 
    
    List<ServiceSubtype> updateServiceSubtype (ServiceSubtype subtype);
    
    List<ServiceSubtype> removeServiceSubtype (int id);
    
    List<PriceList> setPriceList (PriceList priceList);
    
    List<PriceList> updatePriceList (PriceList priceList);
    
    List<PriceList> removePriceList (int id);

    ArrayList<PaymentType> selectPaymentType_null();
    
    ArrayList<PaymentMethod> selectPaymentMethod_null();
}