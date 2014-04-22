package gwt.beautySalon.client.tables;

import gwt.beautySalon.shared.PriceList;
import gwt.beautySalon.shared.ServiceSubtype;
import gwt.beautySalon.shared.ServiceType;

import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;

public class TablePriceList extends FlexTable {
    PriceListDataSource dataSource;
    

    public TablePriceList(PriceListDataSource dataSource) {
        super();
        this.dataSource = dataSource;
        //this.createTablePriceList(/*dataSource*/);
    }

    public void createTablePriceList(/*PriceListDataSource dataSource*/) {
        if (dataSource == null) {
            return;
        }

        //  this.removeAllRows();

        this.addStyleName("tablePriceList");

        List<String> headerList = dataSource.getHeaderList();

        for (int i = 0; i < headerList.size(); i++) {
            this.setText(0, i, headerList.get(i));
        }

        this.getRowFormatter().addStyleName(0, "headerTablePriceList");

        List<ServiceType> serviceTypeList = dataSource.getServiceType();
        List<ServiceSubtype> serviceSubtypeList = dataSource.getServiceSubtype();
        List<PriceList> priceListList = dataSource.getPriceList();

        int i = 1;
        for (ServiceType type : serviceTypeList) {

            this.setText(i, 0, type.getName());
            this.getFlexCellFormatter().setColSpan(i, 0, 3);
            this.getRowFormatter().addStyleName(i, "typeRowTablePriceList");
            i++;
            for (ServiceSubtype subtype : serviceSubtypeList) {
                if (type.getId() == subtype.getServiceTypeId()) {
                    this.setText(i, 0, subtype.getName());

                    for (PriceList price : priceListList) {
                        if (price.getServiceSubtypeId() == subtype.getId()) {
                            this.setText(i, 1, String.valueOf(price.getCost()));
                            this.getCellFormatter().addStyleName(i, 1, "textAlignCenter");
                        }
                    }
                    this.setText(i, 2, subtype.getTime());
                    this.getCellFormatter().addStyleName(i, 2, "textAlignCenter");
                    if (i % 2 == 0) {
                        this.getRowFormatter().addStyleName(i, "evenRow");
                    } else {
                        this.getRowFormatter().addStyleName(i, "oddRow");
                    }
                    i++;
                }
            }
        }
    }
    
    public void isSelectedRow(int selectedRow, int previousRow) {
        this.getRowFormatter().removeStyleName(previousRow, "selectedRow");
        if (selectedRow != 0) {
            this.getRowFormatter().addStyleName(selectedRow, "selectedRow");
        }
    }
}