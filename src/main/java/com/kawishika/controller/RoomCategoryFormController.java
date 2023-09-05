package com.kawishika.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class RoomCategoryFormController {

    @FXML
    private TableColumn<?, ?> activeStatusColumn111;

    @FXML
    private TextField costFld;

    @FXML
    private TableColumn<?, ?> editColumn;

    @FXML
    private TextField idFld;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<?, ?> roomCostColumn;

    @FXML
    private TableColumn<?, ?> roomIdColumn;

    @FXML
    private TableView<?> roomTable;

    @FXML
    private TableColumn<?, ?> roomTypeColumn;

    @FXML
    private TextField searchFld;

    @FXML
    private TextField typeFld;

    @FXML
    void addSaveBtnOnAction(ActionEvent event) {

    }

    @FXML
    void clearBtnOnAction(ActionEvent event) {

    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void searchOnAction(KeyEvent event) {

    }

}
