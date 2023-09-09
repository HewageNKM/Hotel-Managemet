package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;
import com.kawishika.entity.Reserve;

import java.util.ArrayList;

public interface DashboardDAO extends CrudDAO<CustomDTO> {
    ArrayList<Integer> getPieData();

    ArrayList<CustomDTO> getPaymentData();

    ArrayList<Reserve> getDetails();

    String getMail(String reserveId);

    Double getLineChartData(int i);

}
