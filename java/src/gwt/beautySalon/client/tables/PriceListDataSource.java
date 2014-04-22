package gwt.beautySalon.client.tables;

import gwt.beautySalon.shared.PriceList;
import gwt.beautySalon.shared.ServiceSubtype;
import gwt.beautySalon.shared.ServiceType;

import java.util.ArrayList;
import java.util.List;

public class PriceListDataSource {
    private List<PriceList> priceListList;
    private List<ServiceType> serviceTypeList;
    private List<ServiceSubtype> serviceSubtypeList;
    private List<String> headerList;

    public PriceListDataSource(List<ServiceType> serviceTypeList, 
            List<ServiceSubtype> serviceSubtypeList, 
            List<PriceList> priceListList) {
        headerList = new ArrayList<String>();
        headerList.add("Вид услуги");
        headerList.add("Стоимость");
        headerList.add("Время");
        this.priceListList = priceListList;
        this.serviceTypeList = serviceTypeList;
        this.serviceSubtypeList = serviceSubtypeList;       
    }
    
    public List<PriceList> getPriceList() {
        return priceListList;
    }
    
    public List<ServiceType> getServiceType() {
        return serviceTypeList;
    }
    
    public List<ServiceSubtype> getServiceSubtype() {
        return serviceSubtypeList;
    }
    
    public List<String> getHeaderList() {
        return headerList;
    }
}
