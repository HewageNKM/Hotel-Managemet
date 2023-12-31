package com.kawishika.controller;

import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.PaymentServiceImpl;
import com.kawishika.service.interfaces.PaymentService;
import com.kawishika.util.CustomException;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

import static com.kawishika.service.ServiceFactory.ServiceType.PAYMENT;

public class PaymentController {
    private final PaymentService paymentService = (PaymentServiceImpl) ServiceFactory.getInstance().getService(PAYMENT);
    public AnchorPane pane;
    public TextField idFld;
    public TableView<CustomTM> paymentTable;
    public TableColumn rIdColumn;
    public TableColumn sIdColumn;
    public TableColumn totalColumn;
    public TableColumn roomNumberColumn;
    public TableColumn paymentStatus;
    public TableColumn actionColumn;

    public void initialize() {
        setCellValueFactory();
        loadTable();
        loadPane();
    }

    private void loadTable() {
        try {
            ArrayList<CustomTM> all = paymentService.getAll();
            paymentTable.getItems().clear();
            for (CustomTM customTM : all) {
                setActionBtnAction(customTM.getReceived());
            }
            paymentTable.getItems().addAll(FXCollections.observableArrayList(all));
        } catch (CustomException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        rIdColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("reserveId"));
        sIdColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("studentId"));
        roomNumberColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("roomNumber"));
        totalColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("total"));
        paymentStatus.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("paymentStatus"));
        actionColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("received"));
    }

    private void loadPane() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(javafx.util.Duration.seconds(1));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void searchBtnOnAction() {
        if (idFld.getText().trim().isEmpty()) {
            paymentTable.getItems().clear();
            loadTable();
        } else {
            try {
                paymentTable.getItems().clear();
                ArrayList<CustomTM> all = paymentService.search(idFld.getText());
                for (CustomTM customTM : all) {
                    setActionBtnAction(customTM.getReceived());
                }
                paymentTable.getItems().addAll(FXCollections.observableArrayList(all));
            } catch (CustomException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                e.printStackTrace();
            }
        }
    }

    private void setActionBtnAction(Button received) {
        received.setOnAction(event -> {
            CustomTM selectedItem = paymentTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ButtonType.YES, ButtonType.NO).showAndWait().ifPresent(buttonType -> {
                    if (selectedItem.getPaymentStatus().equals("Paid")) {
                        new Alert(Alert.AlertType.WARNING, "Already Paid !", ButtonType.OK).show();
                        return;
                    }
                    if (buttonType == ButtonType.YES) {
                        try {
                            if (paymentService.update(selectedItem.getReserveId())) {
                                new Alert(Alert.AlertType.INFORMATION, "Payment Received !", ButtonType.OK).show();
                                idFld.clear();
                                new Thread(() -> {
                                    try {
                                        paymentService.sendReceipt(selectedItem);
                                    } catch (CustomException e) {
                                        Platform.runLater(() -> {
                                            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                                            e.printStackTrace();
                                        });
                                    }
                                }).start();
                                loadTable();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Something Went Wrong !", ButtonType.OK).show();
                            }
                        } catch (CustomException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                            e.printStackTrace();
                        }

                    }
                });
            } else {
                new Alert(Alert.AlertType.WARNING, "Please Select a Row !", ButtonType.OK).show();
            }
        });
    }
}
