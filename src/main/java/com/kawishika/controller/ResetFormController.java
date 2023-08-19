package com.kawishika.controller;

import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ResetFormController {
    public PasswordField newPasswordMask;
    public TextField userNameFld;
    public TextField passwordFld;
    public TextField newPasswordFld;
    public CheckBox PasswordCheckBox;
    public CheckBox newPasswordCheckBox;
    public PasswordField passwordFldMask;

    public void initialize() {
        setPasswordUnmask();
    }

    private void setPasswordUnmask() {
        PasswordCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                passwordFld.setText(passwordFldMask.getText());
                passwordFld.setVisible(true);
                passwordFldMask.setVisible(false);
            } else {
                passwordFldMask.setText(passwordFld.getText());
                passwordFldMask.setVisible(true);
                passwordFld.setVisible(false);
            }
        });
        newPasswordCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                newPasswordFld.setText(newPasswordMask.getText());
                newPasswordFld.setVisible(true);
                newPasswordMask.setVisible(false);
            } else {
                newPasswordMask.setText(newPasswordFld.getText());
                newPasswordMask.setVisible(true);
                newPasswordFld.setVisible(false);
            }
        });
    }

    public void resetBtnOnAction() {

    }

    public void clearBtnOnAction() {
        userNameFld.clear();
        passwordFld.clear();
        newPasswordFld.clear();
        passwordFldMask.clear();
        newPasswordMask.clear();
    }
}
