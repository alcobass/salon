package gwt.beautySalon.client;

import gwt.beautySalon.client.tables.PriceListDataSource;
import gwt.beautySalon.client.tables.TablePriceList;
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SalonEntryPoint implements EntryPoint {

    ServiceSalonAsync serviceSalon;
    List<PriceList> priceListList;
    List<ServiceType> serviceTypeList;
    List<ServiceSubtype> serviceSubtypeList;
    List<PaymentType> paymentTypeList;
    List<PaymentMethod> paymentMethodList;
    private int selectedRow = 0;
    private int previousRow;
    TablePriceList tablePriceList;

    Label editServiceTypeLabel;
    Label editServiceSubtypeLabel;
    Label deleteRowLabel;

    @Override
    public void onModuleLoad() {
        // TODO Auto-generated method stub

        serviceSalon = (ServiceSalonAsync) GWT
                .create(ServiceSalon.class);
        ServiceDefTarget serviceDef = (ServiceDefTarget) serviceSalon;
        serviceDef.setServiceEntryPoint(GWT.getModuleBaseURL() + "salon");

        loadData();

    }

    public void loadData() {
        serviceSalon.loadDictionaryData(new AsyncCallback<Map<String, Object>>() {
            @Override
            public void onFailure(Throwable caught) {
                // TODO Auto-generated method stub
                Window.alert(caught.getMessage());
            }

            @SuppressWarnings("unchecked")
            @Override
            public void onSuccess(Map<String, Object> result) {
                // TODO Auto-generated method stub
                priceListList = (List<PriceList>) result.get("priceList");
                serviceTypeList = (List<ServiceType>) result.get("serviceType");
                serviceSubtypeList = (List<ServiceSubtype>) result.get("serviceSubtype");
                paymentTypeList = (List<PaymentType>) result.get("paymentType");
                paymentMethodList = (List<PaymentMethod>) result.get("paymentMethod");
                System.out.println("ok");

                loadInterface();
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
        createDocumentPanel(mainDocumentPanel);

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

        receptionButton.addStyleName("headerButton");
        documentButton.addStyleName("headerButton");
        priceListButton.addStyleName("headerButton");

        menuPanel.add(priceListButton);
        menuPanel.add(receptionButton);
        menuPanel.add(documentButton);

        contentPanel.add(mainPriceListPanel);
        contentPanel.add(mainReceptionPanel);
        contentPanel.add(mainDocumentPanel);

        RootPanel.get().add(menuPanel);
        RootPanel.get().add(contentPanel);
    }

    public void createPriceListPanel(HorizontalPanel mainPanel) {

        SimplePanel controlPriceListPanel = new SimplePanel();
        controlPriceListPanel.setStyleName("panelLeft");
        VerticalPanel labelPanel = new VerticalPanel();

        Label addServiceTypeLabel = new Label("Добавить тип");
        addServiceTypeLabel.addStyleName("labelLeftPanel");
        Label addServiceSubtypeLabel = new Label("Добавить вид");
        addServiceSubtypeLabel.addStyleName("labelLeftPanel");

        editServiceTypeLabel = new Label("Редактировать тип");
        editServiceTypeLabel.addStyleName("disabledLabelLeftPanel");
        editServiceSubtypeLabel = new Label("Редактировать вид");
        editServiceSubtypeLabel.addStyleName("disabledLabelLeftPanel");
        deleteRowLabel = new Label("Удалить");
        deleteRowLabel.addStyleName("disabledLabelLeftPanel");

        labelPanel.add(addServiceTypeLabel);
        labelPanel.add(addServiceSubtypeLabel);
        labelPanel.add(editServiceTypeLabel);
        labelPanel.add(editServiceSubtypeLabel);
        labelPanel.add(deleteRowLabel);
        controlPriceListPanel.add(labelPanel);

        addServiceTypeLabel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                DialogBox newServiceTypeDialog = createNewServiceTypeDialog();
                newServiceTypeDialog.setGlassEnabled(true);
                newServiceTypeDialog.setAnimationEnabled(true);
                newServiceTypeDialog.center();
                newServiceTypeDialog.show();
            }
        });

        addServiceSubtypeLabel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                DialogBox newServiceSubtypeDialog = createNewServiceSubtypeDialog();
                newServiceSubtypeDialog.setGlassEnabled(true);
                newServiceSubtypeDialog.setAnimationEnabled(true);
                newServiceSubtypeDialog.center();
                newServiceSubtypeDialog.show();
            }
        });

        editServiceTypeLabel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                if (editServiceTypeLabel.getStyleName().lastIndexOf("labelLeftPanel") != -1) {
                    DialogBox editServiceTypeDialog = createEditServiceTypeDialog();
                    editServiceTypeDialog.setGlassEnabled(true);
                    editServiceTypeDialog.setAnimationEnabled(true);
                    editServiceTypeDialog.center();
                    editServiceTypeDialog.show();
                }
            }
        });

        editServiceSubtypeLabel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                if (editServiceSubtypeLabel.getStyleName().lastIndexOf("labelLeftPanel") != -1) {
                    DialogBox editServiceSubtypeDialog = createEditServiceSubtypeDialog();
                    editServiceSubtypeDialog.setGlassEnabled(true);
                    editServiceSubtypeDialog.setAnimationEnabled(true);
                    editServiceSubtypeDialog.center();
                    editServiceSubtypeDialog.show();
                }

            }
        });

        SimplePanel priceListPanel = new SimplePanel();
        priceListPanel.setStyleName("panelRight");

        PriceListDataSource priceListDataSource = new PriceListDataSource(serviceTypeList,
                serviceSubtypeList, priceListList);
        tablePriceList = new TablePriceList(/*priceListDataSource*/);
        tablePriceList.createTablePriceList(priceListDataSource);

        tablePriceList.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                tablePriceList.getRowFormatter().removeStyleName(selectedRow, "selectedRow");
                makeDisabledLabelLeftPanel();

                Cell cell = tablePriceList.getCellForEvent(event);
                previousRow = selectedRow;
                selectedRow = cell.getRowIndex();

                if (selectedRow != 0 && !tablePriceList.isSelectedRow(previousRow, selectedRow)) {
                    tablePriceList.getRowFormatter().addStyleName(selectedRow, "selectedRow");
                    makeEnabledLabelLeftPanel();
                }
            }
        });

        deleteRowLabel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                if (deleteRowLabel.getStyleName().lastIndexOf("labelLeftPanel") != -1) {

                    if (tablePriceList.getRowFormatter().getStyleName(selectedRow).lastIndexOf("serviceTypeRow") != -1) {

                        int selectedServiceTypeId = getSelectedServiceTypeId(tablePriceList.getText(selectedRow, 0));
                        boolean flagExistenceSubtype = false;
                        for (ServiceSubtype subtype : serviceSubtypeList) {
                            if (subtype.getServiceTypeId() == selectedServiceTypeId) {
                                flagExistenceSubtype = true;
                                break;
                            }
                        }

                        if (!flagExistenceSubtype) {

                            serviceSalon.removeServiceType(selectedServiceTypeId,
                                    new AsyncCallback<List<ServiceType>>() {
                                        @Override
                                        public void onFailure(Throwable caught) {
                                            // TODO Auto-generated method stub
                                            Window.alert(caught.getMessage());
                                        }

                                        @Override
                                        public void onSuccess(List<ServiceType> result) {
                                            // TODO Auto-generated method stub
                                            serviceTypeList = result;
                                            PriceListDataSource priceListDataSource = new PriceListDataSource(serviceTypeList,
                                                    serviceSubtypeList, priceListList);
                                            tablePriceList.createTablePriceList(priceListDataSource);
                                            makeDisabledLabelLeftPanel();
                                        }

                                    });
                        } else
                            Window.alert("Вы не можете удалить тип услуги, который имеет подтипы.");
                    }

                    if (tablePriceList.getRowFormatter().getStyleName(selectedRow).lastIndexOf("oddRow") != -1
                            || tablePriceList.getRowFormatter().getStyleName(selectedRow).lastIndexOf("evenRow") != -1) {

                        final int selectedServiceSubtypeId = getSelectedServiceSubtype(tablePriceList.getText(selectedRow, 0)).getId();
                        serviceSalon.removePriceList(getSelectedPriceListId(selectedServiceSubtypeId), new AsyncCallback<List<PriceList>>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                // TODO Auto-generated method stub
                                Window.alert(caught.getMessage());
                            }

                            @Override
                            public void onSuccess(List<PriceList> result) {
                                // TODO Auto-generated method stub
                                priceListList = result;
                                serviceSalon.removeServiceSubtype(selectedServiceSubtypeId,
                                        new AsyncCallback<List<ServiceSubtype>>() {
                                            @Override
                                            public void onFailure(Throwable caught) {
                                                // TODO Auto-generated method stub
                                                Window.alert(caught.getMessage());
                                            }

                                            @Override
                                            public void onSuccess(List<ServiceSubtype> result) {
                                                // TODO Auto-generated method stub
                                                serviceSubtypeList = result;
                                                PriceListDataSource priceListDataSource = new PriceListDataSource(serviceTypeList,
                                                        serviceSubtypeList, priceListList);
                                                tablePriceList.createTablePriceList(priceListDataSource);
                                                makeDisabledLabelLeftPanel();
                                            }
                                        });
                            }

                        });
                    }

                }
            }
        });

        priceListPanel.add(tablePriceList);
        mainPanel.add(controlPriceListPanel);
        mainPanel.add(priceListPanel);
    }

    public void createReceptionPanel(HorizontalPanel mainPanel) {
        SimplePanel shortRegistrationListPanel = new SimplePanel();
        Label textLabel = new Label("Здесь список с короткими данными");
        shortRegistrationListPanel.setStyleName("panelLeft");
        shortRegistrationListPanel.setWidget(textLabel);
        SimplePanel registrationPanel = new SimplePanel();
        Label textLabe2 = new Label("Здесь редактирование данных о клиенте, запись");
        registrationPanel.setStyleName("panelRight");
        registrationPanel.add(textLabe2);

        mainPanel.add(shortRegistrationListPanel);
        mainPanel.add(registrationPanel);
    }

    public void createDocumentPanel(HorizontalPanel mainPanel) {
        SimplePanel documentNamePanel = new SimplePanel();
        documentNamePanel.setStyleName("panelLeft");
        Label label3 = new Label("Здесь находится список отчетов");
        documentNamePanel.add(label3);
        SimplePanel docPanel = new SimplePanel();
        docPanel.setStyleName("panelRight");
        Label label4 = new Label("Здесь отражается выбранный отчет");
        docPanel.add(label4);

        mainPanel.add(documentNamePanel);
        mainPanel.add(docPanel);
    }

    public DialogBox createNewServiceTypeDialog() {
        final DialogBox dialogBox = new DialogBox();
        dialogBox.ensureDebugId("newServiceTypeDialog");
        dialogBox.setText("Новый тип услуги");

        FlexTable contentDialogTable = new FlexTable();

        Label serviceTypeNameLabel = new Label("Тип услуги");
        serviceTypeNameLabel.addStyleName("labelDialog");
        final TextBox serviceTypeTextBox = new TextBox();

        final PushButton addButton = new PushButton("Добавить", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub               
                ServiceType newType = new ServiceType();
                newType.setName(serviceTypeTextBox.getText());
                serviceSalon.setServiceType(newType, new AsyncCallback<List<ServiceType>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        // TODO Auto-generated method stub
                        Window.alert(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<ServiceType> result) {
                        // TODO Auto-generated method stub
                        serviceTypeList = result;
                        PriceListDataSource priceListDataSource = new PriceListDataSource(serviceTypeList,
                                serviceSubtypeList, priceListList);
                        tablePriceList.createTablePriceList(priceListDataSource);
                        makeDisabledLabelLeftPanel();
                    }
                });
                dialogBox.hide();
            }
        });

        PushButton cancelButton = new PushButton("Отмена", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                dialogBox.hide();
            }
        });

        /*if (!addButton.getText().equals("")) {
                    addButton.setEnabled(true);
                }*/

        addButton.addStyleName("dialogButton");
        cancelButton.addStyleName("dialogButton");

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        contentDialogTable.setWidget(0, 0, serviceTypeNameLabel);
        contentDialogTable.setWidget(0, 1, serviceTypeTextBox);
        contentDialogTable.getFlexCellFormatter().setColSpan(1, 0, 2);
        contentDialogTable.setWidget(1, 0, buttonPanel);

        dialogBox.add(contentDialogTable);

        return dialogBox;
    }

    public DialogBox createNewServiceSubtypeDialog() {
        final DialogBox dialogBox = new DialogBox();
        dialogBox.ensureDebugId("newServiceSubtypeDialog");
        dialogBox.setText("Новый вид услуги");

        FlexTable contentDialogTable = new FlexTable();

        Label serviceTypeNameLabel = new Label("Тип услуги");
        serviceTypeNameLabel.addStyleName("labelDialog");
        final ListBox serviceTypeListBox = new ListBox();

        for (ServiceType type : serviceTypeList) {
            serviceTypeListBox.addItem(type.getName());
        }

        Label serviceSubtypeNameLabel = new Label("Вид услуги");
        serviceSubtypeNameLabel.addStyleName("labelDialog");
        final TextBox serviceSubtypeTextBox = new TextBox();

        Label costLabel = new Label("Стоимость");
        costLabel.addStyleName("labelDialog");
        final TextBox costTextBox = new TextBox();

        Label timeLabel = new Label("Длительность");
        timeLabel.addStyleName("labelDialog");
        final TextBox timeTextBox = new TextBox();

        PushButton addButton = new PushButton("Добавить", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub              
                ServiceSubtype newSubtype = new ServiceSubtype();

                newSubtype.setServiceTypeId(getSelectedServiceTypeId(serviceTypeListBox.getItemText(serviceTypeListBox.getSelectedIndex())));

                newSubtype.setName(serviceSubtypeTextBox.getText());
                newSubtype.setTime(timeTextBox.getText().concat(":00"));

                serviceSalon.setServiceSubtype(newSubtype, new AsyncCallback<List<ServiceSubtype>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<ServiceSubtype> result) {
                        serviceSubtypeList = result;

                        PriceList newPriceList = new PriceList();

                        newPriceList.setServiceSubtypeId(serviceSubtypeList.get(serviceSubtypeList.size() - 1).getId());
                        newPriceList.setCost(Double.valueOf(costTextBox.getText()));

                        serviceSalon.setPriceList(newPriceList, new AsyncCallback<List<PriceList>>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                Window.alert(caught.getMessage());
                            }

                            @Override
                            public void onSuccess(List<PriceList> result) {
                                priceListList = result;
                                PriceListDataSource newPriceListDataSource = new PriceListDataSource(serviceTypeList, serviceSubtypeList, priceListList);
                                tablePriceList.createTablePriceList(newPriceListDataSource);
                                makeDisabledLabelLeftPanel();
                            }
                        });
                    }
                });
                dialogBox.hide();
            }
        });

        PushButton cancelButton = new PushButton("Отмена", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                dialogBox.hide();
            }
        });

        addButton.addStyleName("dialogButton");
        cancelButton.addStyleName("dialogButton");

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        contentDialogTable.setWidget(0, 0, serviceTypeNameLabel);
        contentDialogTable.setWidget(0, 1, serviceTypeListBox);
        contentDialogTable.setWidget(1, 0, serviceSubtypeNameLabel);
        contentDialogTable.setWidget(1, 1, serviceSubtypeTextBox);
        contentDialogTable.setWidget(2, 0, costLabel);
        contentDialogTable.setWidget(2, 1, costTextBox);
        contentDialogTable.setWidget(3, 0, timeLabel);
        contentDialogTable.setWidget(3, 1, timeTextBox);
        contentDialogTable.getFlexCellFormatter().setColSpan(4, 0, 2);
        contentDialogTable.setWidget(4, 0, buttonPanel);

        dialogBox.add(contentDialogTable);

        return dialogBox;
    }

    public DialogBox createEditServiceTypeDialog() {
        final DialogBox dialogBox = new DialogBox();
        dialogBox.ensureDebugId("editServiceTypeDialog");
        dialogBox.setText("Редактирование типа услуги");

        FlexTable contentTable = new FlexTable();

        Label nameTypeLabel = new Label("Тип");
        final TextBox serviceTypeTextBox = new TextBox();

        final String nameSelectedServiceType = tablePriceList.getText(selectedRow, 0);
        serviceTypeTextBox.setText(nameSelectedServiceType);

        PushButton saveButton = new PushButton("Сохранить", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub              
                ServiceType updateServiceType = new ServiceType();
                updateServiceType.setName(serviceTypeTextBox.getText());
                updateServiceType.setId(getSelectedServiceTypeId(nameSelectedServiceType));

                serviceSalon.updateServiceType(updateServiceType, new AsyncCallback<List<ServiceType>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        // TODO Auto-generated method stub
                        Window.alert(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<ServiceType> result) {
                        // TODO Auto-generated method stub
                        serviceTypeList = result;
                        PriceListDataSource priceListDataSource = new PriceListDataSource(serviceTypeList, serviceSubtypeList, priceListList);
                        tablePriceList.createTablePriceList(priceListDataSource);
                        makeDisabledLabelLeftPanel();
                    }
                });

                dialogBox.hide();
            }
        });

        PushButton cancelButton = new PushButton("Отмена", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                dialogBox.hide();
            }
        });

        saveButton.addStyleName("dialogButton");
        cancelButton.addStyleName("dialogButton");

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        contentTable.setWidget(0, 0, nameTypeLabel);
        contentTable.setWidget(0, 1, serviceTypeTextBox);
        contentTable.getFlexCellFormatter().setColSpan(1, 0, 2);
        contentTable.setWidget(1, 0, buttonPanel);

        dialogBox.add(contentTable);

        return dialogBox;
    }

    public DialogBox createEditServiceSubtypeDialog() {
        final DialogBox dialogBox = new DialogBox();
        dialogBox.ensureDebugId("editServiceSubtypeDialog");
        dialogBox.setText("Редактировать вид услуги");

        final ServiceSubtype selectedServiceSubtype = getSelectedServiceSubtype(tablePriceList.getText(selectedRow, 0));

        FlexTable contentDialogTable = new FlexTable();

        Label serviceTypeNameLabel = new Label("Тип услуги");
        serviceTypeNameLabel.addStyleName("labelDialog");
        final ListBox serviceTypeListBox = new ListBox();

        for (ServiceType type : serviceTypeList) {
            serviceTypeListBox.addItem(type.getName());
            if (type.getId() == selectedServiceSubtype.getServiceTypeId()) {
                serviceTypeListBox.setItemSelected(serviceTypeListBox.getItemCount() - 1, true);
            }
        }

        Label serviceSubtypeNameLabel = new Label("Вид услуги");
        serviceSubtypeNameLabel.addStyleName("labelDialog");
        final TextBox serviceSubtypeTextBox = new TextBox();
        serviceSubtypeTextBox.setText(selectedServiceSubtype.getName());

        Label costLabel = new Label("Стоимость");
        costLabel.addStyleName("labelDialog");
        final TextBox costTextBox = new TextBox();
        costTextBox.setText(tablePriceList.getText(selectedRow, 1));

        Label timeLabel = new Label("Длительность");
        timeLabel.addStyleName("labelDialog");
        final TextBox timeTextBox = new TextBox();
        timeTextBox.setText(tablePriceList.getText(selectedRow, 2));

        PushButton saveButton = new PushButton("Сохранить", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub   
                ServiceSubtype newSubtype = new ServiceSubtype();
                newSubtype.setId(selectedServiceSubtype.getId());
                newSubtype.setName(serviceSubtypeTextBox.getText());
                newSubtype.setTime(timeTextBox.getText());
                newSubtype.setServiceTypeId(getSelectedServiceTypeId(serviceTypeListBox.getItemText(serviceTypeListBox.getSelectedIndex())));

                serviceSalon.updateServiceSubtype(newSubtype, new AsyncCallback<List<ServiceSubtype>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        // TODO Auto-generated method stub
                        Window.alert(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<ServiceSubtype> result) {
                        // TODO Auto-generated method stub
                        serviceSubtypeList = result;
                        PriceList priceList = new PriceList();

                        priceList.setCost(Double.valueOf(costTextBox.getText()));
                        priceList.setServiceSubtypeId(selectedServiceSubtype.getId());
                        priceList.setId(getSelectedPriceListId(selectedServiceSubtype.getId()));

                        serviceSalon.updatePriceList(priceList, new AsyncCallback<List<PriceList>>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                // TODO Auto-generated method stub
                                Window.alert(caught.getMessage());
                            }

                            @Override
                            public void onSuccess(List<PriceList> result) {
                                // TODO Auto-generated method stub
                                priceListList = result;
                                PriceListDataSource priceListDataSource = new PriceListDataSource(serviceTypeList, serviceSubtypeList, priceListList);
                                tablePriceList.createTablePriceList(priceListDataSource);
                                makeDisabledLabelLeftPanel();
                            }
                        });
                    }
                });

                dialogBox.hide();
            }
        });

        PushButton cancelButton = new PushButton("Отмена", new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                dialogBox.hide();
            }
        });

        saveButton.addStyleName("dialogButton");
        cancelButton.addStyleName("dialogButton");

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        contentDialogTable.setWidget(0, 0, serviceTypeNameLabel);
        contentDialogTable.setWidget(0, 1, serviceTypeListBox);
        contentDialogTable.setWidget(1, 0, serviceSubtypeNameLabel);
        contentDialogTable.setWidget(1, 1, serviceSubtypeTextBox);
        contentDialogTable.setWidget(2, 0, costLabel);
        contentDialogTable.setWidget(2, 1, costTextBox);
        contentDialogTable.setWidget(3, 0, timeLabel);
        contentDialogTable.setWidget(3, 1, timeTextBox);
        contentDialogTable.getFlexCellFormatter().setColSpan(4, 0, 2);
        contentDialogTable.setWidget(4, 0, buttonPanel);

        dialogBox.add(contentDialogTable);

        return dialogBox;
    }

    public int getSelectedServiceTypeId(String name) {
        for (ServiceType type : serviceTypeList) {
            if (name.equals(type.getName())) {
                return type.getId();
            }
        }
        return 0;
    }

    public ServiceSubtype getSelectedServiceSubtype(String name) {
        for (ServiceSubtype subtype : serviceSubtypeList) {
            if (name.equals(subtype.getName())) {
                return subtype;
            }
        }
        return null;
    }

    public int getSelectedPriceListId(int selectedServiceSubtypeId) {
        for (PriceList price : priceListList) {
            if (price.getServiceSubtypeId() == selectedServiceSubtypeId) {
                return price.getId();
            }
        }
        return 0;
    }

    public void makeDisabledLabelLeftPanel() {
        editServiceTypeLabel.removeStyleName("labelLeftPanel");
        editServiceSubtypeLabel.removeStyleName("labelLeftPanel");
        deleteRowLabel.removeStyleName("labelLeftPanel");

        editServiceTypeLabel.addStyleName("disabledLabelLeftPanel");
        editServiceSubtypeLabel.addStyleName("disabledLabelLeftPanel");
        deleteRowLabel.addStyleName("disabledLabelLeftPanel");
    }

    public void makeEnabledLabelLeftPanel() {
        if (tablePriceList.getFlexCellFormatter().getColSpan(selectedRow, 0) == 3) {
            editServiceTypeLabel.removeStyleName("disabledLabelLeftPanel");
            editServiceTypeLabel.addStyleName("labelLeftPanel");
        } else {
            editServiceSubtypeLabel.removeStyleName("disabledLabelLeftPanel");
            editServiceSubtypeLabel.addStyleName("labelLeftPanel");
        }

        deleteRowLabel.removeStyleName("disabledLabelLeftPanel");
        deleteRowLabel.addStyleName("labelLeftPanel");
    }

}