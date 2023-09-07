package com.kawishika.controller;

import com.kawishika.dto.RoomCategoryDTO;
import com.kawishika.dto.tm.RoomCategoryTM;
import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.RoomCategoryServiceImpl;
import com.kawishika.service.interfaces.RoomCategoryService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class RoomCategoryFormController {

    private final RoomCategoryService roomCategoryService = (RoomCategoryServiceImpl) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ROOM_CATEGORY);
    @FXML
    private TableColumn<?, ?> deleteColumn;
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
    private TableView<RoomCategoryTM> roomTable;
    @FXML
    private TableColumn<?, ?> roomTypeColumn;
    @FXML
    private TextField searchFld;
    @FXML
    private TextField typeFld;

    public void initialize() {
        setCellValues();
        Thread thread = new Thread(this::loadTable);
        thread.start();
    }

    private void loadTable() {
        ArrayList<RoomCategoryTM> all = roomCategoryService.getAll();
        for (RoomCategoryTM roomCategoryTM : all) {
            Platform.runLater(() -> {
                        setEditAction(roomCategoryTM.getEdit());
                        setDeleteAction(roomCategoryTM.getDelete());
                    }
            );
        }
        roomTable.setItems(FXCollections.observableList(all));
    }

    private void setDeleteAction(Button delete) {
        delete.setOnAction(event -> {
            RoomCategoryTM selectedItem = roomTable.getSelectionModel().getSelectedItem();
            System.out.println(selectedItem);
            if (selectedItem == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a row").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this record?", ButtonType.YES, ButtonType.NO).showAndWait().ifPresent(buttonType -> {
                    if (buttonType == ButtonType.YES) {
                        boolean delete1 = roomCategoryService.delete(new RoomCategoryDTO(selectedItem.getRoom_ID(), null, 0.0));
                        if (delete1) {
                            new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully").show();
                            clearBtnOnAction();
                            loadTable();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Failed to delete").show();
                        }
                    }
                });
            }
        });
    }

    private void setEditAction(Button edit) {
        edit.setOnAction(event -> {
            RoomCategoryTM selectedItem = roomTable.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a row").show();
            } else {
                idFld.setText(selectedItem.getRoom_ID());
                typeFld.setText(selectedItem.getRoom_Type());
                costFld.setText(String.valueOf(selectedItem.getCost_Per_Week()));
            }
        });
    }

    private void setCellValues() {
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("room_ID"));
        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("room_Type"));
        roomCostColumn.setCellValueFactory(new PropertyValueFactory<>("cost_Per_Week"));
        editColumn.setCellValueFactory(new PropertyValueFactory<>("edit"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    @FXML
    void addSaveBtnOnAction(ActionEvent event) {
        if (validateDetails()) {
            if (roomCategoryService.isExists(idFld.getText())) {
                boolean update = roomCategoryService.update(new RoomCategoryDTO(idFld.getText(), typeFld.getText(), Double.parseDouble(costFld.getText())));
                if (update) {
                    new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
                    clearBtnOnAction();
                    loadTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update").show();
                }
            } else {
                boolean save = roomCategoryService.save(new RoomCategoryDTO(idFld.getText(), typeFld.getText(), Double.parseDouble(costFld.getText())));
                if (save) {
                    new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
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
        return validateCost() & validateId() & validateType();
    }

    private boolean validateType() {
        if (roomCategoryService.validateType(typeFld.getText())) {
            typeFld.setStyle("-fx-border-color: green");
            return true;
        } else {
            typeFld.setStyle("-fx-border-color: red");
            return false;
        }
    }

    private boolean validateId() {
        if (roomCategoryService.validateId(idFld.getText())) {
            idFld.setStyle("-fx-border-color: green");
            return true;
        } else {
            idFld.setStyle("-fx-border-color: red");
            return false;
        }
    }

    private boolean validateCost() {
        if (roomCategoryService.validateCost(costFld.getText())) {
            costFld.setStyle("-fx-border-color: green");
            return true;
        } else {
            costFld.setStyle("-fx-border-color: red");
            return false;
        }
    }

    @FXML
    void clearBtnOnAction() {
        idFld.clear();
        idFld.setStyle("-fx-border-color: none");
        typeFld.clear();
        typeFld.setStyle("-fx-border-color: none");
        costFld.clear();
        costFld.setStyle("-fx-border-color: none");
        idFld.setPromptText("Room ID");
        typeFld.setPromptText("Room Type");
        costFld.setPromptText("Cost Per Week");
    }

    @FXML
    void deleteBtnOnAction() {
        new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this record?", ButtonType.YES, ButtonType.NO).showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES) {
                boolean delete = roomCategoryService.delete(new RoomCategoryDTO(idFld.getText(), null, 0.0));
                if (delete) {
                    new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully").show();
                    clearBtnOnAction();
                    loadTable();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete").show();
                }
            }
        });
    }

    @FXML
    void searchOnAction() {
        if (searchFld.getText().trim().isEmpty()) {
            loadTable();
        } else {
            ArrayList<RoomCategoryTM> search = roomCategoryService.search(searchFld.getText());
            roomTable.setItems(FXCollections.observableList(search));
        }
    }

}
