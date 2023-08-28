package com.kawishika.controller;

import javafx.animation.FadeTransition;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class DashboardFormController {
    public LineChart lineChart;
    public PieChart pichart;
    public TableView parentTable;
    public TableColumn studentNameColumn;
    public TableColumn idColumn;
    public TableColumn keyPaymentColumn;
    public AnchorPane notificationsPane;
    public AnchorPane pane;

    public void initialize() {
        loadPane();
    }

    private void loadPane() {
        FadeTransition fadeTransition = new javafx.animation.FadeTransition();
        fadeTransition.setDuration(javafx.util.Duration.seconds(1));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
}
