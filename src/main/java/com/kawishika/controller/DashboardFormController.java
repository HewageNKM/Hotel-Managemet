package com.kawishika.controller;

import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.DashboardServiceImpl;
import com.kawishika.service.interfaces.DashboardService;
import com.kawishika.util.CustomException;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import static com.kawishika.service.ServiceFactory.ServiceType.DASHBOARD;

public class DashboardFormController {
    private final DashboardService dashboardService = (DashboardServiceImpl) ServiceFactory.getInstance().getService(DASHBOARD);
    public PieChart pieChart;
    public TableView<CustomTM> parentTable;
    public TableColumn studentIdColumn;
    public TableColumn idColumn;
    public AnchorPane notificationsPane;
    public AnchorPane pane;
    public TableColumn paymentColumn;
    public VBox notificationsBox;
    public BarChart barChart;

    public void initialize() {
        loadPicChart();
        setCellValueFactory();
        loadPaymentTable();
        loadLineChart();
        loadPane();
        loadNotifications();
    }

    private void loadNotifications() {
        new Thread(() -> {
            try {
                ArrayList<String> notifications = dashboardService.getNotifications();
                for (String notification : notifications) {
                    Label label = new Label(notification);
                    Platform.runLater(() -> {
                        notificationsBox.getChildren().add(label);
                    });
                }
            } catch (Exception e) {
                Platform.runLater(() -> {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    e.printStackTrace();
                });
            }
        }).start();
    }

    private void setCellValueFactory() {
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("reserveId"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
    }

    private void loadPicChart() {
        new Thread(() -> {
            try {
                ArrayList<Integer> pieData = dashboardService.getPieData();
                Platform.runLater(() -> {
                    pieChart.getData().add(new PieChart.Data("Occupied", pieData.get(0)));
                    pieChart.getData().add(new PieChart.Data("Available", pieData.get(1)));
                });
            } catch (CustomException e) {
                Platform.runLater(() -> {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    e.printStackTrace();
                });
            }
        }).start();
    }

    private void loadPaymentTable() {
        new Thread(() -> {
            try {
                ArrayList<CustomTM> paymentData = dashboardService.getPaymentData();
                Platform.runLater(() -> {
                    parentTable.getItems().addAll(FXCollections.observableArrayList(paymentData));
                });
            } catch (CustomException e) {
                Platform.runLater(() -> {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    e.printStackTrace();
                });
            }
        }).start();
    }

    private void loadLineChart() {
        try {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Payments");
            ArrayList<Integer> lineChartData = dashboardService.getLineChartData();
            series.getData().add(new XYChart.Data<>("January", lineChartData.get(0)));
            series.getData().add(new XYChart.Data<>("February", lineChartData.get(1)));
            series.getData().add(new XYChart.Data<>("March", lineChartData.get(2)));
            series.getData().add(new XYChart.Data<>("April", lineChartData.get(3)));
            series.getData().add(new XYChart.Data<>("May", lineChartData.get(4)));
            series.getData().add(new XYChart.Data<>("June", lineChartData.get(5)));
            series.getData().add(new XYChart.Data<>("July", lineChartData.get(6)));
            series.getData().add(new XYChart.Data<>("August", lineChartData.get(7)));
            series.getData().add(new XYChart.Data<>("September", lineChartData.get(8)));
            series.getData().add(new XYChart.Data<>("October", lineChartData.get(9)));
            series.getData().add(new XYChart.Data<>("November", lineChartData.get(10)));
            series.getData().add(new XYChart.Data<>("December", lineChartData.get(11)));
            barChart.getData().add(series);
        } catch (CustomException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
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
