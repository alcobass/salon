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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

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
        menuPanel.ensureDebugId("menuPanel");
        
        VerticalPanel contentPanel = new VerticalPanel();
        contentPanel.ensureDebugId("contentPanel");
        
        final HorizontalPanel mainReceptionPanel = new HorizontalPanel();
        mainReceptionPanel.ensureDebugId("mainReceptionPanel");
        final HorizontalPanel mainPriceListPanel = new HorizontalPanel();
        mainPriceListPanel.ensureDebugId("mainPriceListPanel");
        final HorizontalPanel mainDocumentPanel = new HorizontalPanel();
        mainDocumentPanel.ensureDebugId("mainDocumentPanel");
        
        mainReceptionPanel.setVisible(false);
        mainDocumentPanel.setVisible(false);
             
        createPriceListPanel(mainPriceListPanel);
        createReceptionPanel(mainReceptionPanel);
                     
        PushButton receptionButton = new PushButton("Ресепшен", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                mainReceptionPanel.setVisible(true);
                mainPriceListPanel.setVisible(false);
                mainDocumentPanel.setVisible(false);

            }
        });

        PushButton documentButton = new PushButton("Документы", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                mainReceptionPanel.setVisible(false);
                mainPriceListPanel.setVisible(false);
                mainDocumentPanel.setVisible(true);
            }
        });

        PushButton priceListButton = new PushButton("Прайс-лист", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                mainReceptionPanel.setVisible(false);
                mainPriceListPanel.setVisible(true);
                mainDocumentPanel.setVisible(false);
            }
        });

        menuPanel.add(priceListButton);
        menuPanel.add(receptionButton);
        menuPanel.add(documentButton);
       
        contentPanel.add(mainPriceListPanel);
        contentPanel.add(mainReceptionPanel);
        contentPanel.add(mainDocumentPanel);
        
        RootPanel.get().add(menuPanel);
        RootPanel.get().add(contentPanel);
    }

    public void createPriceListPanel (HorizontalPanel mainPanel) {
        SimplePanel controlPriceListPanel = new SimplePanel();
        controlPriceListPanel.setStyleName("panel");
        controlPriceListPanel.setWidth("300px");
        Label label5 = new Label("Панель для редактирования прайс-листа");
        controlPriceListPanel.add(label5);
        SimplePanel priceListPanel = new SimplePanel();
        priceListPanel.setStyleName("panel");
        priceListPanel.setWidth("900px");
        Label label6 = new Label("Панель с прайс-листом");
        priceListPanel.add(label6);
        
        mainPanel.add(controlPriceListPanel);
        mainPanel.add(priceListPanel);
    }
    
    public void createReceptionPanel (HorizontalPanel mainPanel) {
        SimplePanel shortRegistrationListPanel = new SimplePanel();
        Label textLabel = new Label("Здесь список с короткими данными");
        shortRegistrationListPanel.setStyleName("panel");
        shortRegistrationListPanel.setWidth("450px");
        shortRegistrationListPanel.setWidget(textLabel);
        SimplePanel registrationPanel = new SimplePanel();
        Label textLabe2 = new Label("Здесь редактирование данных о клиенте, запись");
        registrationPanel.setStyleName("panel");
        registrationPanel.setWidth("886px");
        registrationPanel.add(textLabe2);
    
        mainPanel.add(shortRegistrationListPanel);
        mainPanel.add(registrationPanel);
    }
    
    public void createDocumentPanel(HorizontalPanel mainPanel) {
        SimplePanel documentNamePanel = new SimplePanel();
        documentNamePanel.setStyleName("panel");
        documentNamePanel.setWidth("300px");
        Label label3 = new Label("Здесь находится список отчетов");
        documentNamePanel.add(label3);
        SimplePanel docPanel = new SimplePanel();
        docPanel.setStyleName("panel");
        docPanel.setWidth("900px");
        Label label4 = new Label("Здесь отражается выбранный отчет");
        docPanel.add(label4);
        
        mainPanel.add(documentNamePanel);
        mainPanel.add(docPanel);
    }
}