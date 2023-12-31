package com.kawishika.controller;

import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.ReserveDTO;
import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.CheckinServiceImpl;
import com.kawishika.service.interfaces.CheckinService;
import com.kawishika.util.CustomException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static com.kawishika.service.ServiceFactory.ServiceType.CHECKIN;

public class CheckInController {

    private final CheckinService checkinService = (CheckinServiceImpl) ServiceFactory.getInstance().getService(CHECKIN);
    private final Stage stage = new Stage();
    @FXML
    private DatePicker checkInPicker;
    @FXML
    private DatePicker checkOutPicker;
    @FXML
    private TextField idFld;
    @FXML
    private AnchorPane pane;
    @FXML
    private ComboBox<String> paymentOptionBox;
    @FXML
    private Label roomCostLabel;
    @FXML
    private Label roomIdLabel;
    @FXML
    private Label roomNumberLabel;
    @FXML
    private ComboBox<String> roomTypeBox;
    private String roomNumber;
    private Double total;
    @FXML
    private Label totalLabel;
    private String reserveId;

    public void initialize() {
        try {
            reserveId = checkinService.getReserveId();
        } catch (CustomException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
        loadPane();
        setBoxValues();
        setRoomTypeBoxListener();
        checkInPicker.setValue(LocalDate.now());
        checkInPicker.setDisable(true);
        setListenerToCheckOutPicker();
    }

    private void setListenerToCheckOutPicker() {
        checkOutPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (checkInPicker.getValue() != null) {
                    if (checkInPicker.getValue().isAfter(newValue)) {
                        new Alert(Alert.AlertType.ERROR, "Check Out Date Should Be After Check In Date !").show();
                        totalLabel.setText("Total: ");
                        checkOutPicker.setValue(null);
                        return;
                    }
                }
                if (roomTypeBox.getSelectionModel().getSelectedItem() != null) {
                    try {
                        CustomDTO dto = checkinService.getRoomDetails(roomTypeBox.getSelectionModel().getSelectedItem());
                        if (checkInPicker.getValue() != null) {
                            long days = newValue.toEpochDay() - checkInPicker.getValue().toEpochDay();
                            total = days * (dto.getCost() / 7);
                            totalLabel.setText("Total: " + (int) total.doubleValue());
                        }
                    } catch (CustomException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void setRoomTypeBoxListener() {
        roomTypeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    CustomDTO dto = checkinService.getRoomDetails(newValue);
                    if (dto.getRoomId() == null) {
                        new Alert(Alert.AlertType.ERROR, "No Room Available At The Moment !").show();
                        return;
                    }
                    roomNumber = dto.getRoomNumber();
                    roomIdLabel.setText("Room ID: " + dto.getRoomId());
                    roomCostLabel.setText("Cost Per Week: " + dto.getCost().toString());
                    roomNumberLabel.setText("Room Number: " + dto.getRoomNumber());
                } catch (CustomException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void setBoxValues() {
        try {
            ArrayList<String> roomTypes = checkinService.getRoomTypes();
            roomTypeBox.getItems().addAll(roomTypes);
            paymentOptionBox.getItems().addAll("Now", "Later");
        } catch (CustomException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
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
    void clearBtnAction() {
        idFld.clear();
        checkInPicker.getEditor().clear();
        checkOutPicker.getEditor().clear();
        paymentOptionBox.getSelectionModel().clearSelection();
        roomTypeBox.getSelectionModel().clearSelection();
        roomIdLabel.setText("");
        roomCostLabel.setText("");
        totalLabel.setText("");
        idFld.setStyle("-fx-border-color: none");
        checkInPicker.setStyle("-fx-border-color: none");
        checkOutPicker.setStyle("-fx-border-color: none");
        paymentOptionBox.setStyle("-fx-border-color: none");
        roomTypeBox.setStyle("-fx-border-color: none");
        roomIdLabel.setStyle("-fx-border-color: none");
        roomCostLabel.setStyle("-fx-border-color: none");
        totalLabel.setStyle("-fx-border-color: none");
        paymentOptionBox.setPromptText("Payment");
        roomTypeBox.setPromptText("Room Type");
        roomNumberLabel.setText("Room No:");
        roomIdLabel.setText("Room ID:");
        roomCostLabel.setText("Cost Per Week:");
        totalLabel.setText("Total:");
    }

    @FXML
    void enterOnAction(ActionEvent event) {
        try {
            if (checkinService.getStudentId(idFld.getText()).isEmpty()) {
                idFld.setStyle("-fx-border-color: red");
            } else {
                idFld.setStyle("-fx-border-color: green");
            }
        } catch (CustomException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }


    @FXML
    void newOnAction() {
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/StudentForm.fxml")))));
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
            e.printStackTrace();
        }
        stage.setTitle("Student Form");
        stage.show();
    }

    @FXML
    void reserveBtnOnAction(ActionEvent event) {
        try {
            if (validateDetails()) {
                if (checkinService.checkStudentEligibility(idFld.getText()).equals("Blacklist")) {
                    new Alert(Alert.AlertType.ERROR, "Student Has Blacklisted !").show();
                    return;
                }
                if (checkinService.checkReservation(idFld.getText()).equals("Active")) {
                    new Alert(Alert.AlertType.ERROR, "Student Has Active Reservation !").show();
                    return;
                }
                checkinService.save(new ReserveDTO(reserveId, Date.valueOf(checkInPicker.getValue()), Date.valueOf(checkOutPicker.getValue()), total, paymentOptionBox.getValue().equals("Now") ? "Paid" : "Not Paid", "Active"), idFld.getText(), roomNumber);
                ReserveDTO dto = new ReserveDTO(reserveId, Date.valueOf(checkInPicker.getValue()), Date.valueOf(checkOutPicker.getValue()), total, paymentOptionBox.getValue().equals("Now") ? "Paid" : "Not Paid", "Active");
                checkinService.sendReceipt(dto, idFld.getText(), roomNumber);
                clearBtnAction();
                try {
                    reserveId = checkinService.getReserveId();
                } catch (CustomException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
                new Alert(Alert.AlertType.INFORMATION, "Reserved Successfully !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Fill All The Fields Correctly !").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private boolean validateDetails() {
        return validateStudentId() & validateCheckOutDate() && validatePaymentOption() && validateRoomType();
    }

    private boolean validateRoomType() {
        return roomTypeBox.getSelectionModel().getSelectedItem() != null;
    }

    private boolean validatePaymentOption() {
        return paymentOptionBox.getSelectionModel().getSelectedItem() != null;
    }

    private boolean validateCheckOutDate() {
        return checkOutPicker.getValue() != null && checkOutPicker.getValue().isAfter(checkInPicker.getValue());
    }

    private boolean validateStudentId() {
        try {
            return checkinService.getStudentId(idFld.getText()).get(0).equals(idFld.getText());
        } catch (CustomException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
            return false;
        }
    }

}

