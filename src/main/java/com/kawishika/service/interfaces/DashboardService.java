package com.kawishika.service.interfaces;

import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.SuperService;
import com.kawishika.util.CustomException;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;

public interface DashboardService extends SuperService {

    ArrayList<Integer> getPieData() throws CustomException;

    ArrayList<CustomTM> getPaymentData() throws CustomException;

    ArrayList<Integer> getLineChartData() throws CustomException;

    ArrayList<String> getNotifications() throws CustomException;
}
