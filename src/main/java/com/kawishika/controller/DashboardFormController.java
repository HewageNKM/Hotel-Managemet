package com.kawishika.controller;

import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.DashboardServiceImpl;
import com.kawishika.service.interfaces.DashboardService;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

import static com.kawishika.service.ServiceFactory.ServiceType.DASHBOARD;

public class DashboardFormController {
    public LineChart lineChart;
    public PieChart pichart;
    public TableView<CustomTM> parentTable;
    public TableColumn studentIdColumn;
    public TableColumn idColumn;
    public AnchorPane notificationsPane;
    public AnchorPane pane;
    private final DashboardService dashboardService = (DashboardServiceImpl) ServiceFactory.getInstance().getService(DASHBOARD);
    public TableColumn paymentColumn;

    public void initialize() {
        loadPicChart();
        setCellValueFactory();
        loadPaymentTable();
        loadLineChart();
        loadPane();
    }

    private void setCellValueFactory() {
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("reserveId"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
    }

    private void loadPicChart() {
        ArrayList<Integer> pieData = dashboardService.getPieData();
        pichart.getData().add(new PieChart.Data("Occupied", pieData.get(0)));
        pichart.getData().add(new PieChart.Data("Available", pieData.get(1)));
    }

    private void loadPaymentTable() {
        ArrayList<CustomTM> paymentData = dashboardService.getPaymentData();
        parentTable.getItems().addAll(FXCollections.observableArrayList(paymentData));
    }

    private void loadLineChart() {
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
