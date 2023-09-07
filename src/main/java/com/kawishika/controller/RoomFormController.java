package com.kawishika.controller;

import com.kawishika.dto.RoomDTO;
import com.kawishika.dto.tm.RoomTM;
import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.RoomServiceImpl;
import com.kawishika.service.interfaces.RoomService;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.kawishika.service.ServiceFactory.ServiceType.ROOM;
import static javafx.util.Duration.seconds;

public class RoomFormController {

    private final Stage stage = new Stage();
    private final RoomService roomService = (RoomServiceImpl) ServiceFactory.getInstance().getService(ROOM);
    @FXML
    private TableColumn<?, ?> activeStatusColumn111;
    @FXML
    private TableColumn<?, ?> editColumn;
    @FXML
    private AnchorPane pane;
    @FXML
    private ComboBox<String> roomIdBox;
    @FXML
    private TableColumn<?, ?> roomIdColumn;
    @FXML
    private TableColumn<?, ?> roomNumberColumn;
    @FXML
    private Label roomNumberLabel;
    @FXML
    private TableView<RoomTM> roomTable;
    @FXML
    private TableColumn<?, ?> roomTypeColumn;
    @FXML
    private Label roomTypeLabel;
    @FXML
    private TextField searchFld;
    @FXML
    private ComboBox<String> statusBox;
    @FXML
    private TableColumn<?, ?> statusColumn;
    private String roomId;
    private String roomNumber;

    public void initialize() {
        loadPane();
        setBoxValues();
        setListeners();
        setCellValueFactory();
        Thread thread = new Thread(this::loadTable);
        thread.start();
    }

    private void loadTable() {
        ArrayList<RoomTM> all = roomService.getAll();
        for (RoomTM roomTM : all) {
            setEditAction(roomTM.getEdit());
            setDeleteAction(roomTM.getDelete());
        }
        roomTable.getItems().clear();
        roomTable.getItems().addAll(FXCollections.observableArrayList(all));
    }

    private void setDeleteAction(Button delete) {
        delete.setOnAction(event -> {
            RoomTM selectedItem = roomTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this room?", ButtonType.YES, ButtonType.NO).showAndWait().ifPresent(buttonType -> {
                    if (buttonType == ButtonType.YES) {
                        if (roomService.delete(new RoomDTO(selectedItem.getRoomNumber(), selectedItem.getStatus()), selectedItem.getRoomId())) {
                            new Alert(Alert.AlertType.INFORMATION, "Room Deleted").show();
                            loadTable();
                            clearBtnOnAction();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Failed to delete").show();
                        }
                    }
                });
            } else {
                new Alert(Alert.AlertType.ERROR, "Please select a row").show();
            }
        });
    }

    private void setEditAction(Button edit) {
        edit.setOnAction(event -> {
            RoomTM selectedItem = roomTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                roomIdBox.getSelectionModel().select(selectedItem.getRoomType());
                roomIdBox.setDisable(true);
                roomNumberLabel.setText("Room Number: " + selectedItem.getRoomNumber());
                roomNumber = selectedItem.getRoomNumber();
                roomTypeLabel.setText("Room Type: " + selectedItem.getRoomId());
                if (selectedItem.getStatus().equals("Not Available")) {
                    statusBox.getSelectionModel().select("Not Available");
                    statusBox.setDisable(true);
                } else {
                    statusBox.getSelectionModel().select(selectedItem.getStatus());
                    statusBox.setDisable(false);
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please select a row").show();
            }
        });
    }

    private void setCellValueFactory() {
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        editColumn.setCellValueFactory(new PropertyValueFactory<>("edit"));
        activeStatusColumn111.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    private void setListeners() {
        roomIdBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                roomId = roomService.getRoomId(newValue);
                roomTypeLabel.setText("Room ID: " + roomId);
                roomNumber = roomService.getRoomNumber();
                roomNumberLabel.setText("Room Number: " + roomNumber);
            }
        });
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
        ArrayList<String> categoryIds = roomService.getCategoryIds();
        roomIdBox.getItems().addAll(categoryIds);
        statusBox.getItems().addAll("Active", "Maintenance", "Inactive");
    }

    @FXML
    void addSaveBtnOnAction() {
        if (validateDetails()) {
            if (roomService.isRoomExist(roomNumber)) {
                if (roomService.update(new RoomDTO(roomNumber, statusBox.getValue()), roomId)) {
                    new Alert(Alert.AlertType.INFORMATION, "Room Updated").show();
                    clearBtnOnAction();
                    loadTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update").show();
                }
            } else {
                if (roomService.saveRoom(new RoomDTO(roomNumber, statusBox.getValue()), roomId)) {
                    new Alert(Alert.AlertType.INFORMATION, "Room Saved").show();
                    clearBtnOnAction();
                    loadTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save").show();
                }
            }
        } else {
            validateDetails();
        }
    }

    private boolean validateDetails() {
        return validateRoomId() && validateRoomNumber() && validateRoomType() && validateStatus();
    }

    private boolean validateStatus() {
        if (statusBox.getSelectionModel().isEmpty()) {
            statusBox.setStyle("-fx-border-color: red");
            return false;
        } else {
            statusBox.setStyle("-fx-border-color: green");
            return true;
        }
    }

    private boolean validateRoomType() {
        if (roomService.validateRoomType(roomId)) {
            roomTypeLabel.setStyle("-fx-text-fill: red");
            return false;
        } else {
            roomTypeLabel.setStyle("-fx-text-fill: green");
            return true;
        }
    }

    private boolean validateRoomNumber() {
        if (roomService.validateRoomNumber(roomNumber)) {
            roomNumberLabel.setStyle("-fx-text-fill: green");
            return true;
        } else {
            roomNumberLabel.setStyle("-fx-text-fill: red");
            return false;
        }
    }

    private boolean validateRoomId() {
        if (roomService.validateRoomId(roomId)) {
            roomIdBox.setStyle("-fx-border-color: green");
            return true;
        } else {
            roomIdBox.setStyle("-fx-border-color: red");
            return false;
        }
    }

    @FXML
    void clearBtnOnAction() {
        roomIdBox.getSelectionModel().clearSelection();
        roomNumberLabel.setText("Room Number: ");
        roomTypeLabel.setText("Room Type: ");
        statusBox.getSelectionModel().clearSelection();
        roomIdBox.setStyle("-fx-border-color: none");
        roomNumberLabel.setStyle("-fx-text-fill: black");
        roomTypeLabel.setStyle("-fx-text-fill: black");
        statusBox.setStyle("-fx-border-color: none");
        roomIdBox.setDisable(false);
        statusBox.setDisable(false);
        roomIdBox.setPlaceholder(new Label("Select Room ID"));
        statusBox.setPlaceholder(new Label("Select Status"));
    }

    @FXML
    void searchOnAction() {

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
