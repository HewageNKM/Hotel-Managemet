package com.kawishika.controller;

import com.kawishika.dto.UserDTO;
import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.ResetServiceImpl;
import com.kawishika.service.interfaces.ResetService;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ResetFormController {
    private final ResetService resetService = (ResetServiceImpl) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.RESET);
    public CheckBox passwordCheckBox;
    public Button sendBtn;
    public TextField codeFld;
    public TextField passwordUnmask;
    public TextField userNameFld;
    public PasswordField passwordMask;
    public Label loadingLabel;
    public AnchorPane waitingPane;
    private Thread counter;


    public void initialize() {
        waitingPane.setVisible(false);
        setPasswordUnmask();
    }

    private void setPasswordUnmask() {
        passwordCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                passwordUnmask.setText(passwordMask.getText());
                passwordUnmask.setVisible(true);
                passwordMask.setVisible(false);
            } else {
                passwordMask.setText(passwordUnmask.getText());
                passwordUnmask.setVisible(false);
                passwordMask.setVisible(true);
            }
        });
    }

    public void resetBtnOnAction() {
        if (resetService.checkCode(Integer.valueOf(codeFld.getText()))) {
            if (resetService.resetPassword(new UserDTO(userNameFld.getText(), passwordMask.getText()))) {
                new Alert(Alert.AlertType.INFORMATION, "Password Reset Successfully!").show();
                clearBtnOnAction();
            } else {
                new Alert(Alert.AlertType.ERROR, "Password Reset Failed!").show();
            }
        } else {
            codeFld.setStyle("-fx-border-color: red");
        }
    }

    public void clearBtnOnAction() {
        if (counter != null) {
            counter.interrupt();
        }
        sendBtn.setDisable(false);
        userNameFld.setDisable(false);
        sendBtn.setText("Send");
        userNameFld.clear();
        codeFld.clear();
        passwordMask.clear();
        passwordUnmask.clear();
        passwordCheckBox.setSelected(false);
        userNameFld.setStyle("-fx-border-color: none");
        codeFld.setStyle("-fx-border-color: none");
        userNameFld.setPromptText("User Name");
        codeFld.setPromptText("Code");
        passwordMask.setPromptText("Password");
        passwordUnmask.setPromptText("Password");
        sendBtn.setDisable(false);
    }

    public void codeSendBtnOnAction() {
        if (resetService.sendCode(userNameFld.getText())) {
            sendBtn.setDisable(true);
            userNameFld.setDisable(true);
            new Alert(Alert.AlertType.INFORMATION, "Code Send Successfully!").show();
            counter = new Thread(() -> {
                for (int i = 60; i >= 0; i--) {
                    int finalI = i;
                    Platform.runLater(() -> {
                        sendBtn.setText(String.valueOf(finalI));
                        if (finalI == 0) {
                            sendBtn.setDisable(false);
                            userNameFld.setDisable(false);
                            codeFld.clear();
                            codeFld.setPromptText("Resend Code");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });
            counter.start();
        } else {
            userNameFld.setStyle("-fx-border-color: red");
        }
    }
}
