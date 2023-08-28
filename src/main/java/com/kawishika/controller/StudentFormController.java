package com.kawishika.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class StudentFormController {

    @FXML
    private TextField SearchFld;

    @FXML
    private TableColumn<?, ?> birthdayColumn;

    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private TextField emailFld;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<?, ?> phoneNumberColumn;

    @FXML
    private TextField phoneNumberFld;

    @FXML
    private ComboBox<?> statudBox;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TextField studentIdFld;

    @FXML
    private TextField studentNameFld;

    @FXML
    private TableView<?> studentTable;
    public void initialize(){
        loadPane();
    }

    private void loadPane() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(javafx.util.Duration.seconds(1));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    @FXML
    void addSaveBtnOnAction() {

    }

    @FXML
    void clearBtnOnAction() {

    }

    @FXML
    void deleteBtnOnAction() {

    }

    @FXML
    void searchTypeOnAction() {

    }

}

