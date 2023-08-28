package com.kawishika.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

public class PaymentController {
    public AnchorPane pane;
    public void initialize(){
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
}
