package com.kawishika.controller;

import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.CheckOutServiceImpl;
import com.kawishika.service.interfaces.CheckOutService;
import com.kawishika.util.CustomException;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

import static com.kawishika.service.ServiceFactory.ServiceType.CHECKOUT;

public class CheckOutController {

    private final CheckOutService checkOutService = (CheckOutServiceImpl) ServiceFactory.getInstance().getService(CHECKOUT);
    public TableView<CustomTM> checkOutTable;
    public TableColumn rIdColumn;
    public TableColumn sIdColumn;
    public TableColumn roomNumberColumn;
    public TableColumn paymentStatus;
    public TableColumn checkingColumn;
    public TableColumn checkOutColumn;
    @FXML
    private TextField idFld;
    @FXML
    private AnchorPane pane;
    private CustomTM customTM = null;
    @FXML
    private TableColumn<?, ?> totalColumn;

    public void initialize() {
        loadPane();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        rIdColumn.setCellValueFactory(new PropertyValueFactory<>("reserveId"));
        sIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        paymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        checkingColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
    }

    private void loadPane() {
        FadeTransition fadeTransition = new javafx.animation.FadeTransition();
        fadeTransition.setDuration(javafx.util.Duration.seconds(1));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    @FXML
    void backBtnOnAction(ActionEvent event) {
        pane.getChildren().clear();
        try {
            pane.getChildren().add(javafx.fxml.FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CheckingMenuForm.fxml"))));
        } catch (java.io.IOException e) {
            System.out.println("Resource Not Found !");
            e.printStackTrace();
        }
    }

    @FXML
    void checkBtnOnAction(ActionEvent event) {
        if (checkOutService.checkId(idFld.getText())) {
            try {
                customTM = checkOutService.getReserveDetails(idFld.getText());
                if (customTM == null) {
                    new Alert(Alert.AlertType.WARNING, "Invalid Details !", ButtonType.OK).show();
                    return;
                }
                checkOutTable.getItems().clear();
                checkOutTable.getItems().add(customTM);
            } catch (CustomException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Invalid Details !", ButtonType.OK).show();
        }
    }

    @FXML
    void checkOutBrnOnAction(ActionEvent event) {
        new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ButtonType.YES, ButtonType.NO).showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES) {
                if (customTM != null) {
                    if (customTM.getPaymentStatus().equals("Not Paid")) {
                        new Alert(Alert.AlertType.WARNING, "Payment Haven't Paid !", ButtonType.OK).show();
                    } else {
                        try {
                            checkOutService.checkOut(customTM);
                            new Alert(Alert.AlertType.INFORMATION, "Check Out Successfully !", ButtonType.OK).show();
                            checkOutTable.getItems().clear();
                            idFld.clear();
                        } catch (CustomException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                            e.printStackTrace();
                        }
                        new Thread(() -> {
                            try {
                                checkOutService.sendReceipt(customTM);
                            } catch (CustomException e) {
                                Platform.runLater(() -> {
                                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                                    e.printStackTrace();
                                });
                            }
                        }).start();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "Invalid Details !", ButtonType.OK).show();
                }
            }
        });
    }

}

