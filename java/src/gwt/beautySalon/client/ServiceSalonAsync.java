package gwt.beautySalon.client;

import gwt.beautySalon.shared.PaymentMethod;
import gwt.beautySalon.shared.PaymentType;
import gwt.beautySalon.shared.PriceList;
import gwt.beautySalon.shared.ServiceSubtype;
import gwt.beautySalon.shared.ServiceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServiceSalonAsync {

    void loadDictionaryData(AsyncCallback<Map<String, Object>> callback);

    void selectPaymentType_null(AsyncCallback<ArrayList<PaymentType>> callback);

    void selectPaymentMethod_null(AsyncCallback<ArrayList<PaymentMethod>> callback);

    void setServiceType(ServiceType type, AsyncCallback<List<ServiceType>> callback);

    void removeServiceType(int id, AsyncCallback<List<ServiceType>> callback);

    void setServiceSubtype(ServiceSubtype subtype, AsyncCallback<List<ServiceSubtype>> callback);

    void setPriceList(PriceList priceList, AsyncCallback<List<PriceList>> callback);

    void updateServiceType(ServiceType type, AsyncCallback<List<ServiceType>> callback);

    void updateServiceSubtype(ServiceSubtype subtype, AsyncCallback<List<ServiceSubtype>> callback);

    void updatePriceList(PriceList priceList, AsyncCallback<List<PriceList>> callback);

    void removeServiceSubtype(int id, AsyncCallback<List<ServiceSubtype>> callback);

    void removePriceList(int id, AsyncCallback<List<PriceList>> callback);
}