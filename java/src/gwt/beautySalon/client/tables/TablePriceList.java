package gwt.beautySalon.client.tables;

import gwt.beautySalon.shared.PriceList;
import gwt.beautySalon.shared.ServiceSubtype;
import gwt.beautySalon.shared.ServiceType;

import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;

public class TablePriceList extends FlexTable {
    PriceListDataSource dataSource;
    private boolean selectionRowFlag;

    public TablePriceList(/*PriceListDataSource dataSource*/) {
        super();
        //  this.dataSource = dataSource;
        selectionRowFlag = false;
        //this.createTablePriceList(/*dataSource*/);
    }

    public void createTablePriceList(PriceListDataSource dataSource) {
        if (dataSource == null) {
            return;
        }

        this.removeAllRows();

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
            this.getRowFormatter().addStyleName(i, "serviceTypeRow");
            i++;
            int j = 1;
            for (ServiceSubtype subtype : serviceSubtypeList) {
                if (type.getId() == subtype.getServiceTypeId()) {
                    this.setText(i, 0, subtype.getName());

                    for (PriceList price : priceListList) {
                        if (price.getServiceSubtypeId() == subtype.getId()) {
                            String costStr = String.valueOf(price.getCost());
                            this.setText(i, 1, costStr.substring(0, costStr.length()-2));
                            this.getCellFormatter().addStyleName(i, 1, "textAlignCenter");
                        }
                    }
                    this.setText(i, 2, subtype.getTime().substring(0, 5));

                    this.getCellFormatter().addStyleName(i, 2, "textAlignCenter");
                    if (j % 2 == 0) {
                        this.getRowFormatter().addStyleName(i, "evenRow");
                    } else {
                        this.getRowFormatter().addStyleName(i, "oddRow");
                    }
                    i++;
                    j++;
                }
            }
        }
    }

    public boolean isSelectedRow(int previousRow, int selectedRow) {
        if (previousRow == selectedRow) {
            return (selectionRowFlag = !selectionRowFlag);
        }
        return (selectionRowFlag = false);
    }
}