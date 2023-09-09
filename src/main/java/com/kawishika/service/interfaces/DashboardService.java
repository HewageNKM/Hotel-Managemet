package com.kawishika.service.interfaces;

import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.SuperService;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;

public interface DashboardService extends SuperService {

    ArrayList<Integer> getPieData();

    ArrayList<CustomTM> getPaymentData();

    ArrayList<Integer> getLineChartData();

    ArrayList<String> getNotifications();
}
