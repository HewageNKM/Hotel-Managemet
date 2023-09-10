package com.kawishika.controller;

import com.kawishika.dto.UserDTO;
import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.CreateAccountServiceImpl;
import com.kawishika.service.interfaces.CreateAccountService;
import com.kawishika.util.CustomException;
import javafx.application.Platform;
import javafx.scene.control.*;

public class CreateAccountController {

    private final CreateAccountService createAccountService = (CreateAccountServiceImpl) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CREATE_ACCOUNT);
    public TextField passwordFld;
    public TextField userNameFld;
    public CheckBox passwordCheckBox;
    public PasswordField passwordFldMask;
    public TextField emailFld;
    public Button sendBtn;
    private Thread counter;
    private String email;

    public void initialize(){
        setPasswordUnmask();
    }

    private void setPasswordUnmask() {
        passwordCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                passwordFld.setText(passwordFldMask.getText());
                passwordFldMask.setVisible(false);
                passwordFld.setVisible(true);
            }else {
                passwordFldMask.setText(passwordFld.getText());
                passwordFldMask.setVisible(true);
                passwordFld.setVisible(false);
            }
        });
    }

    public void createBtnOnAction() {
        if(validateDetails()){
            try {
                if (createAccountService.createAccount(new UserDTO(userNameFld.getText(),passwordFld.getText(),email,"Active"))){
                    new Alert(Alert.AlertType.INFORMATION,"Account Created Successfully!").show();
                    clearBtnOnAction();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Account Created Failed!").show();
                }
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,"Account Created Failed!").show();
                e.printStackTrace();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Please Enter Valid Details!").show();
        };
    }

    private boolean validateDetails() {
        return validateUserName() && validatePassword() && validateCode();
    }

    private boolean validateCode() {
        if (createAccountService.validateCode(Integer.valueOf(emailFld.getText()))){
            emailFld.setStyle("-fx-border-color: green");
            return true;
        }else {
            emailFld.setStyle("-fx-border-color: none");
            return false;
        }
    }

    private boolean validatePassword() {
        if (createAccountService.validatePassword(passwordFld.getText())){
            passwordFld.setStyle("-fx-border-color: green");
            return true;
        }else {
            passwordFld.setStyle("-fx-border-color: red");
            return false;
        }
    }

    private boolean validateUserName() {
        try {
            if (createAccountService.validateUserName(userNameFld.getText())){
                userNameFld.setStyle("-fx-border-color: green");
                return true;
            }else {
                userNameFld.setStyle("-fx-border-color: red");
                return false;
            }
        }catch (CustomException e){
            new Alert(Alert.AlertType.ERROR,"Account Created Failed!").show();
            e.printStackTrace();
            return false;
        }
    }

    public void clearBtnOnAction() {
        if (counter != null){
            counter.interrupt();
        }
        sendBtn.setDisable(false);
        userNameFld.setDisable(false);
        sendBtn.setText("Send");
        userNameFld.clear();
        passwordFld.clear();
        passwordFldMask.clear();
        emailFld.clear();
        passwordCheckBox.setSelected(false);
        userNameFld.setStyle("-fx-border-color: none");
        passwordFld.setStyle("-fx-border-color: none");
        emailFld.setStyle("-fx-border-color: none");
        userNameFld.setPromptText("User Name");
        passwordFld.setPromptText("Password");
        passwordFldMask.setPromptText("Password");
        emailFld.setPromptText("Email");
        sendBtn.setDisable(false);
    }

    public void sendBtnOnAction() {
        if (createAccountService.validateEmail(emailFld.getText())) {
            new Alert(Alert.AlertType.ERROR, "Please Enter Valid User Name!").show();
        }else {
            if (createAccountService.validateEmail(emailFld.getText())) {
                createAccountService.sendCode(emailFld.getText());
                email = emailFld.getText();
                emailFld.clear();
                userNameFld.setDisable(true);
                emailFld.setPromptText("Code");
                sendBtn.setDisable(true);
                new Alert(Alert.AlertType.INFORMATION, "Code Send Successfully!").show();
                counter = new Thread(() -> {
                    for (int i = 60; i >= 0; i--) {
                        int finalI = i;
                        Platform.runLater(() -> {
                            sendBtn.setText(String.valueOf(finalI));
                            if (finalI == 0) {
                                sendBtn.setDisable(false);
                                sendBtn.setText("Resend Code");
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
                new Alert(Alert.AlertType.ERROR, "Please Enter Valid Email!").show();
            }
        }
    }
}
