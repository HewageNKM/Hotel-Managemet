package com.kawishika.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class MainFormController {
    public AnchorPane pane;
    public Label userNameLabel;
    public AnchorPane root;

    public void initialize(){
        loadPane();
        loadDashboard();
    }

    private void loadDashboard() {
        root.getChildren().clear();
        try {
            root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/DashboardForm.fxml"))));
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
        }
    }

    private void loadPane() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(javafx.util.Duration.seconds(1));
        translateTransition.setNode(pane);
        translateTransition.setFromX(-1000);
        translateTransition.setToX(0);
        translateTransition.play();
    }

    public void paymentBtnOnAction() {
        root.getChildren().clear();
        try {
            root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/PaymentForm.fxml"))));
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
        }
    }

    public void dashboardBtnOnAction() {
        root.getChildren().clear();
        try {
            root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/DashboardForm.fxml"))));
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
        }
    }

    public void loginBtnOnAction() {

    }

    public void reserveBtnOnAction() {
        root.getChildren().clear();
        try {
            root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CheckingMenuForm.fxml"))));
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
        }
    }

    public void accountBtnOnAction() {
        root.getChildren().clear();
        try {
            root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/UserForm.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Resource Not Found !");
        }
    }

    public void studentBtnOnAction() {
        root.getChildren().clear();
        try {
            root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/StudentForm.fxml"))));
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
        }
    }

    public void roomBtnOnAction() {
        root.getChildren().clear();
        try {
            root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/RoomForm.fxml"))));
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
        }
    }
}
