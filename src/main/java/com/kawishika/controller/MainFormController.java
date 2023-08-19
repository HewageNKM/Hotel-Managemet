package com.kawishika.controller;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;

public class MainFormController {
    public AnchorPane pane;

    public void initialize(){
        loadPane();
    }

    private void loadPane() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(javafx.util.Duration.seconds(1.75));
        translateTransition.setNode(pane);
        translateTransition.setFromX(-1000);
        translateTransition.setToX(0);
        translateTransition.play();
    }
}
