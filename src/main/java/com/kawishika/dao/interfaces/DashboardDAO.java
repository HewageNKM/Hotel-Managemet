package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;
import com.kawishika.entity.Reserve;
import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface DashboardDAO extends CrudDAO<CustomDTO> {
    ArrayList<Integer> getPieData() throws CustomException;

    ArrayList<CustomDTO> getPaymentData() throws CustomException;

    ArrayList<Reserve> getDetails() throws CustomException;

    String getMail(String reserveId) throws CustomException;

    Double getLineChartData(int i) throws CustomException;

}
