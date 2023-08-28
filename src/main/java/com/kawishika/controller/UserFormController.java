package com.kawishika.controller;

import com.kawishika.dto.UserDTO;
import com.kawishika.dto.tm.UserTM;
import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.UserServiceImpl;
import com.kawishika.service.interfaces.UserService;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class UserFormController {
    public TableView<UserTM> userTable;
    public TableColumn emailColumn;
    public TableColumn userNameColumn;
    public TableColumn passwordColumn;
    public TableColumn activeStatusColumn;
    public AnchorPane pane;
    public TextField userNameFld;
    public TextField passwordFld;
    public TextField emailFld;
    public ComboBox<String> statusBox;

    private final UserService userService = (UserServiceImpl) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.USER);

    public void initialize() {
        loadPane();
        setCellValueFactory();
        loadTable();
        setStatusBoxValues();
    }

    private void setCellValueFactory() {
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        activeStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadTable() {
        ArrayList<UserTM> all = userService.getAll();
        userTable.setItems(FXCollections.observableList(all));
    }


    private void setStatusBoxValues() {
        statusBox.getItems().add("Active");
        statusBox.getItems().add("Inactive");
    }

    private void loadPane() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(javafx.util.Duration.seconds(1));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void addSaveBtnOnAction() {
        if(validateDetails()){
            boolean userExist = userService.isUserExist(userNameFld.getText());
            if(userExist) {
                boolean update = userService.update(new UserDTO(userNameFld.getText(), passwordFld.getText(), emailFld.getText(), statusBox.getValue()));
                if(update){
                   new Alert(Alert.AlertType.CONFIRMATION, "User update successfully !", ButtonType.OK).show();
                   loadTable();
                   clearBtnOnAction();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update the user !", ButtonType.OK).show();
                }
            }
            else {
                boolean isSaved = userService.save(new UserDTO(userNameFld.getText(), passwordFld.getText(), emailFld.getText(), statusBox.getValue()));
                if(isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully !", ButtonType.OK).show();
                    loadTable();
                    clearBtnOnAction();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save the user !", ButtonType.OK).show();
                }
            }
        }else {
            validateDetails();
        }
    }

    private boolean validateDetails() {
        return validateUserName() & validatePassword() & validateEmail() & validateStatus();
    }

    private boolean validateStatus() {
        if(statusBox.selectionModelProperty().get().getSelectedItem() != null){
            statusBox.setStyle("-fx-border-color: GREEN");
            return true;
        }else {
            statusBox.setStyle("-fx-border-color: RED");
            return false;
        }
    }

    private boolean validateUserName() {
        if(userService.validateUserName(userNameFld.getText())){
            userNameFld.setStyle("-fx-border-color: green");
            return true;
        }else {
            userNameFld.setStyle("-fx-border-color: red");
            userNameFld.setTooltip(new Tooltip("User name should be at least 4 characters long !"));
            return false;
        }
    }

    private boolean validatePassword() {
        if (userService.validatePassword(passwordFld.getText())) {
            passwordFld.setStyle("-fx-border-color: green");
            return true;
        } else {
            passwordFld.setStyle("-fx-border-color: red");
            return false;
        }
    }

    private boolean validateEmail() {
        if (userService.validateEmail(emailFld.getText())) {
            emailFld.setStyle("-fx-border-color: green");
            return true;
        } else {
            emailFld.setStyle("-fx-border-color: red");
            return false;
        }
    }

    public void clearBtnOnAction() {
        userNameFld.clear();
        passwordFld.clear();
        emailFld.clear();
        statusBox.selectionModelProperty().get().clearSelection();
        userNameFld.setStyle("-fx-border-color: transparent");
        passwordFld.setStyle("-fx-border-color: transparent");
        emailFld.setStyle("-fx-border-color: transparent");
        statusBox.setStyle("-fx-border-color: transparent");
    }

    public void deleteBtnOnAction() {
        new Alert(Alert.AlertType.CONFIRMATION, "Do You Want Delete Your ?", ButtonType.OK, ButtonType.NO).showAndWait().ifPresent(buttonType -> {
            if(buttonType == ButtonType.OK){
                boolean isDeleted = userService.delete(new UserDTO(userNameFld.getText(), passwordFld.getText(), emailFld.getText(), statusBox.selectionModelProperty().get().getSelectedItem()));
                if(isDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION, "User deleted successfully !", ButtonType.OK).show();
                    loadTable();
                    clearBtnOnAction();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the user !", ButtonType.OK).show();
                }
            }
        });
    }

    public void enterOnAction() {
        UserDTO user = userService.getUser(userNameFld.getText());
        if(user != null){
            userNameFld.setText(user.getUserName());
            passwordFld.setText(user.getPassword());
            emailFld.setText(user.getEmail());
            statusBox.selectionModelProperty().get().select(user.getStatus());
        }else {
            new Alert(Alert.AlertType.ERROR, "User not found !", ButtonType.OK).show();
        }
    }
}
