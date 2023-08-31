package com.kawishika.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class CheckInController {

    @FXML
    private Label RoomNumberLabel;

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
    private Button reserveBtn;

    @FXML
    private Label roomCostLabel;

    @FXML
    private Label roomIdLabel;

    @FXML
    private ComboBox<String> roomTypeBox;

    @FXML
    private Label totalLabel;
    public void initialize() {
        loadPane();
        setBoxValues();
    }

    private void setBoxValues() {
        paymentOptionBox.getItems().add("Pay Later");
        paymentOptionBox.getItems().add("Pay Now");
        roomTypeBox.getItems().add("Non-AC");
        roomTypeBox.getItems().add("Non-AC/Food");
        roomTypeBox.getItems().add("AC/Food");
        roomTypeBox.getItems().add("AC");
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
        }
    }

    @FXML
    void clearBtnAction(ActionEvent event) {

    }

    @FXML
    void enterOnAction(ActionEvent event) {

    }

    @FXML
    void idFldTypeOnAction(KeyEvent event) {

    }

    @FXML
    void newOnAction(ActionEvent event) {

    }

    @FXML
    void reserveBtnOnAction(ActionEvent event) {

    }

}

