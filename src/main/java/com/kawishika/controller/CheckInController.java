package com.kawishika.controller;

import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.CheckinServiceImpl;
import com.kawishika.service.interfaces.CheckinService;
import com.kawishika.util.SessionConfigureFactory;
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

import static com.kawishika.service.ServiceFactory.ServiceType.CHECKIN;

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
    private final CheckinService checkinService = (CheckinServiceImpl) ServiceFactory.getInstance().getService(CHECKIN);


    @FXML
    private Label totalLabel;
    public void initialize() {
        loadPane();
        setBoxValues();
        setRoomTypeBoxListener();
    }

    private void setRoomTypeBoxListener() {
        roomTypeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case "Non-AC":
                    roomCostLabel.setText("1");
                    roomIdLabel.setText("Room ID : ");
                    break;
                case "Non-AC/Food":
                    roomCostLabel.setText("1");
                    roomIdLabel.setText("1");
                    break;
                case "AC/Food":
                    roomCostLabel.setText("3");
                    roomIdLabel.setText("6");
                    break;
                case "AC":
                    roomCostLabel.setText("8");
                    roomIdLabel.setText("0");
                    break;
                default:
                    roomCostLabel.setText("5");
                    roomIdLabel.setText("2");
                    break;
            }
        });
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

