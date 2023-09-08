package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.DashboardDAOImpl;
import com.kawishika.dao.interfaces.DashboardDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.interfaces.DashboardService;

import java.util.ArrayList;

public class DashboardServiceImpl implements DashboardService {
    private final DashboardDAO dashboardDAO =(DashboardDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DASHBOARD);
    @Override
    public ArrayList<Integer> getPieData() {
        return dashboardDAO.getPieData();
    }

    @Override
    public ArrayList<CustomTM> getPaymentData() {
        ArrayList<CustomDTO> paymentData = dashboardDAO.getPaymentData();
        ArrayList<CustomTM> customTMS = new ArrayList<>();
        for (CustomDTO customDTO : paymentData) {
            customTMS.add(new CustomTM(customDTO.getStudentId(), customDTO.getReserveId(), customDTO.getPaymentStatus()));
        }
        return customTMS;
    }

    @Override
    public ArrayList<Integer> getLineChartData() {
        return null;
    }
}
