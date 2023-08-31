package com.kawishika.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class CheckOutController {

    @FXML
    private TableColumn<?, ?> checkOutColumn;

    @FXML
    private TableView<?> checkOutTable;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TextField idFld;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<?, ?> rIdColumn;

    @FXML
    private TableColumn<?, ?> reservedDateColumn;

    @FXML
    private TableColumn<?, ?> roomCostColumn;

    @FXML
    private TableColumn<?, ?> roomTypeColumn;

    @FXML
    private TableColumn<?, ?> sIdColumn;

    @FXML
    private TableColumn<?, ?> timeColumn;

    @FXML
    private TableColumn<?, ?> totalColumn;
    public void initialize() {
        loadPane();
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
    void checkBtnOnAction(ActionEvent event) {

    }

    @FXML
    void checkOutBrnOnAction(ActionEvent event) {

    }

}

