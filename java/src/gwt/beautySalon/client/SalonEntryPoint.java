package gwt.beautySalon.client;

import gwt.beautySalon.shared.PaymentMethod;
import gwt.beautySalon.shared.PaymentType;
import gwt.beautySalon.shared.PriceList;
import gwt.beautySalon.shared.ServiceSubtype;
import gwt.beautySalon.shared.ServiceType;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SalonEntryPoint implements EntryPoint {

    ServiceSalonAsync serviceSalon;
    List<PriceList> priceList;
    List<ServiceType> serviceTypeList;
    List<ServiceSubtype> serviceSubtypeList;
    List<PaymentType> paymentTypeList;
    List<PaymentMethod> paymentMethodList;

    @Override
    public void onModuleLoad() {
        // TODO Auto-generated method stub

        serviceSalon = (ServiceSalonAsync) GWT
                .create(ServiceSalon.class);
        ServiceDefTarget serviceDef = (ServiceDefTarget) serviceSalon;
        serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL() + "salon");

        loadData();
        loadInterface();
    }

    public void loadData() {
        serviceSalon.loadDictionaryData(new AsyncCallback<Map<String, Object>>() {
            @Override
            public void onFailure(Throwable caught) {
                // TODO Auto-generated method stub
                System.out.println("bad");
            }

            @SuppressWarnings("unchecked")
            @Override
            public void onSuccess(Map<String, Object> result) {
                // TODO Auto-generated method stub
                priceList = (List<PriceList>) result.get("priceList");
                serviceTypeList = (List<ServiceType>) result.get("serviceType");
                serviceSubtypeList = (List<ServiceSubtype>) result.get("serviceSubtype");
                paymentTypeList = (List<PaymentType>) result.get("paymentType");
                paymentMethodList = (List<PaymentMethod>) result.get("paymentMethod");
                System.out.println("ok");
            }
        });
    }

    public void loadInterface() {
        HorizontalPanel menuPanel = new HorizontalPanel();
        
        PushButton receptionButton = new PushButton();
        receptionButton.setText("Ресепшен");       
        PushButton documentButton = new PushButton();
        receptionButton.setText("Документы");
        PushButton priceListButton = new PushButton();
        receptionButton.setText("Прайс-лист");
        
        menuPanel.add(receptionButton);
        menuPanel.add(documentButton);
        menuPanel.add(priceListButton);

        SimplePanel contentPanel = new SimplePanel();
        
        RootPanel.get().add(menuPanel);
        RootPanel.get().add(contentPanel);
    }
}