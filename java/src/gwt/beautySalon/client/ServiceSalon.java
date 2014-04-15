package gwt.beautySalon.client;

import gwt.beautySalon.shared.PaymentMethod;
import gwt.beautySalon.shared.PaymentType;
import gwt.beautySalon.shared.PriceList;
import gwt.beautySalon.shared.ServiceSubtype;
import gwt.beautySalon.shared.ServiceType;

import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("salon")
public interface ServiceSalon  extends RemoteService{

    Map<String, Object> loadDictionaryData();
    
    ArrayList<PriceList> selectPriceList_null();
    
    ArrayList<ServiceType> selectServiceType_null();
    
    ArrayList<ServiceSubtype> selectServiceSubtype_null();

    ArrayList<PaymentType> selectPaymentType_null();
    
    ArrayList<PaymentMethod> selectPaymentMethod_null();

}