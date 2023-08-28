package com.kawishika.controller;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginFormController {
    public Label welcomeLabel;
    public AnchorPane pane;
    public TextField passwordFld;
    public TextField userNameFld;
    public CheckBox passwordCheckBox;
    public PasswordField passwordFldMask;
    private final Stage stage = new Stage();

    public void initialize() {
        loadPane();
        greeting();
        setToolTips();
        setPasswordUnmask();
    }

    private void setPasswordUnmask() {
        passwordCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
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
    }

    private void setToolTips() {
        userNameFld.setTooltip(new Tooltip(" Enter Your Registered Username Here"));
        passwordFld.setTooltip(new Tooltip(" Enter Your 8 Digit Password Here"));
        passwordFldMask.setTooltip(new Tooltip(" Enter Your 8 Digit Password Here"));
    }

    private void loadPane() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void greeting() {
        Thread welcomeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Timeline timeline = getTimeline();
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
            private Timeline getTimeline() {
                AtomicInteger currentIndex = new AtomicInteger();

                return new Timeline(
                        new KeyFrame(Duration.seconds(0.12), event -> {
                            if (currentIndex.get() < "Welcome, User !".length()) {
                                welcomeLabel.setText(welcomeLabel.getText() + "Welcome, User !".charAt(currentIndex.get()));
                                currentIndex.getAndIncrement();
                            }
                        })
                );
            }
        });
        welcomeThread.start();
    }

    public void loginBtnAction() throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
        Stage mainStage = new Stage();
        mainStage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MainForm.fxml")))));
        mainStage.setTitle("Main Form");
        mainStage.setResizable(false);
        mainStage.setMaximized(false);
        mainStage.getIcons().add(new Image("/asset/main/menuIcon.png"));
        mainStage.show();
    }

    public void resetBtnAction() {
        try {
            stage.setScene(new Scene(javafx.fxml.FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ResetForm.fxml")))));
            stage.setTitle("Reset Password");
            stage.getIcons().add(new Image("/asset/reset/resetIcon.png"));
            stage.setResizable(false);
            stage.show();
        } catch (java.io.IOException e) {
            System.out.println("Resource Not Found !");
        }
    }

    public void createAccountOnAction() {
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CreateAccountForm.fxml")))));
            stage.setTitle("Create Account");
            //stage.getIcons().add(new Image("/asset/create/createIcon.png"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
        }
    }
}
