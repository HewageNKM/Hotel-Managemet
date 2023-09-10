package com.kawishika.controller;

import com.kawishika.dto.UserDTO;
import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.LoginServiceImpl;
import com.kawishika.service.interfaces.LoginService;
import com.kawishika.util.CustomException;
import com.kawishika.util.SessionConfigureFactory;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginFormController {
    private final Stage stage = new Stage();
    private final LoginService loginService = (LoginServiceImpl) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.LOGIN);
    public Label welcomeLabel;
    public AnchorPane pane;
    public TextField passwordFld;
    public TextField userNameFld;
    public CheckBox passwordCheckBox;
    public PasswordField passwordFldMask;

    public void initialize() {
        new Thread(() -> {
            Session session = SessionConfigureFactory.getInstance().getSession();
            session.close();
        }).start();
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
        try {
            if (loginService.verifyLogin(new UserDTO(userNameFld.getText(), passwordFld.getText().trim().isEmpty() ? passwordFldMask.getText() : passwordFld.getText()))) {
                userNameFld.setStyle("-fx-border-color: green");
                passwordFld.setStyle("-fx-border-color: green");
                passwordFldMask.setStyle("-fx-border-color: green");
                URL resource = getClass().getResource("/view/MainForm.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader(resource);
                Parent parent = fxmlLoader.load();
                MainFormController mainFormController = fxmlLoader.getController();
                mainFormController.initialize(userNameFld.getText().toUpperCase());
                stage.setScene(new Scene(parent));
                stage.setTitle("Main Form");
                stage.setResizable(false);
                stage.setMaximized(false);
                stage.getIcons().add(new Image("/asset/main/menuIcon.png"));
                stage.show();
                Stage loginStage = (Stage) pane.getScene().getWindow();
                loginStage.close();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Username or Password !", ButtonType.OK).show();
                userNameFld.setStyle("-fx-border-color: red");
                passwordFld.setStyle("-fx-border-color: red");
                passwordFldMask.setStyle("-fx-border-color: red");
            }
        } catch (CustomException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
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
            e.printStackTrace();
        }
    }

    public void createAccountOnAction() {
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CreateAccountForm.fxml")))));
            stage.setTitle("Create Account");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println("Resource Not Found !");
            e.printStackTrace();
        }
    }
}
