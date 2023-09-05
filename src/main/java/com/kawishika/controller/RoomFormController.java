package com.kawishika.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.util.Duration.seconds;

public class RoomFormController {

    @FXML
    private TableColumn<?, ?> activeStatusColumn111;

    @FXML
    private TableColumn<?, ?> editColumn;

    @FXML
    private AnchorPane pane;

    @FXML
    private ComboBox<?> roomIdBox;

    @FXML
    private TableColumn<?, ?> roomIdColumn;

    @FXML
    private TableColumn<?, ?> roomNumberColumn;

    @FXML
    private Label roomNumberLabel;

    @FXML
    private TableView<?> roomTable;

    @FXML
    private TableColumn<?, ?> roomTypeColumn;

    @FXML
    private Label roomTypeLabel;

    @FXML
    private TextField searchFld;

    @FXML
    private ComboBox<?> statusBox;

    @FXML
    private TableColumn<?, ?> statusColumn;
    private final Stage stage = new Stage();
    public void initialize() {
        loadPane();
        setBoxValues();
    }
    private void loadPane() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(seconds(1));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void setBoxValues() {

    }
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
    @FXML
    public void newBtnOnAction() {
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/RoomCategoryForm.fxml")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Room Category");
        stage.show();
    }
}
