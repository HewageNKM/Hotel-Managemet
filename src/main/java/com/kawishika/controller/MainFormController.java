package com.kawishika.controller;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MainFormController {
    public AnchorPane pane;
    public Label userNameLabel;
    public AnchorPane root;
    public Label dateLabel;

    public void initialize(String userName) {
        loadPane();
        loadDashboard();
        setDateTime();
        userNameLabel.setText("Hi, "+userName);
    }

    private void setDateTime() {
        new Thread(() -> {
            while (true){
                Platform.runLater(() -> {
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
                    String formattedDateTime = currentDateTime.format(formatter);
                    dateLabel.setText(formattedDateTime);
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    private void loadDashboard() {
        root.getChildren().clear();
        try {
            root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/DashboardForm.fxml"))));
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public void dashboardBtnOnAction() {
        root.getChildren().clear();
        try {
            root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/DashboardForm.fxml"))));
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
            e.printStackTrace();
        }
    }

    public void logOutBtnOnAction() {
        new Alert(Alert.AlertType.INFORMATION, "You are already log out !", ButtonType.YES,ButtonType.NO).showAndWait().ifPresent(buttonType -> {
            if (buttonType.equals(ButtonType.YES)){
                Stage stage = (Stage) pane.getScene().getWindow();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginForm.fxml")))));
                    stage.setTitle("Login Form");
                    stage.setResizable(false);
                    stage.setMaximized(false);
                    stage.getIcons().add(new Image("asset/login/loginIcon.gif"));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void reserveBtnOnAction() {
        root.getChildren().clear();
        try {
            root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CheckingMenuForm.fxml"))));
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public void roomBtnOnAction() {
        root.getChildren().clear();
        try {
            root.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/RoomForm.fxml"))));
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
            e.printStackTrace();
        }
    }
}
