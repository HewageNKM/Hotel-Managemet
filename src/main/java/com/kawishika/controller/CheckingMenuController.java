package com.kawishika.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class CheckingMenuController {
    public AnchorPane pane;

    public void initialize() {
        loadPane();
    }

    private void loadPane() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(javafx.util.Duration.seconds(1));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void CheckOutBtnOnAction() {
        pane.getChildren().clear();
        try {
            pane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CheckOutForm.fxml"))));
        } catch (java.io.IOException e) {
            System.out.println("Resource Not Found !");
        }
    }

    public void checkInBtnOnAction() {
        pane.getChildren().clear();
        try {
            pane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CheckInForm.fxml"))));
        } catch (java.io.IOException e) {
            System.out.println("Resource Not Found !");
        }
    }
}
