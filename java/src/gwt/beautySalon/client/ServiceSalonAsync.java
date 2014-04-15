package gwt.beautySalon.client;

import gwt.beautySalon.shared.PaymentMethod;
import gwt.beautySalon.shared.PaymentType;
import gwt.beautySalon.shared.PriceList;
import gwt.beautySalon.shared.ServiceSubtype;
import gwt.beautySalon.shared.ServiceType;

import java.util.ArrayList;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServiceSalonAsync {

    void loadDictionaryData(AsyncCallback<Map<String, Object>> callback);

    void selectPriceList_null(AsyncCallback<ArrayList<PriceList>> callback);

    void selectServiceSubtype_null(AsyncCallback<ArrayList<ServiceSubtype>> callback);

    void selectServiceType_null(AsyncCallback<ArrayList<ServiceType>> callback);

    void selectPaymentType_null(AsyncCallback<ArrayList<PaymentType>> callback);

    void selectPaymentMethod_null(AsyncCallback<ArrayList<PaymentMethod>> callback);
}