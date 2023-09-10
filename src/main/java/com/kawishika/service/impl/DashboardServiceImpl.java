package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.DashboardDAOImpl;
import com.kawishika.dao.interfaces.DashboardDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;
import com.kawishika.entity.Reserve;
import com.kawishika.service.interfaces.DashboardService;
import com.kawishika.util.CustomException;
import com.kawishika.util.Mail;
import com.kawishika.util.TempMails;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class DashboardServiceImpl implements DashboardService {
    private final DashboardDAO dashboardDAO = (DashboardDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DASHBOARD);

    @Override
    public ArrayList<Integer> getPieData() throws CustomException {
        return dashboardDAO.getPieData();
    }

    @Override
    public ArrayList<CustomTM> getPaymentData() throws CustomException {
        ArrayList<CustomDTO> paymentData = dashboardDAO.getPaymentData();
        ArrayList<CustomTM> customTMS = new ArrayList<>();
        for (CustomDTO customDTO : paymentData) {
            customTMS.add(new CustomTM(customDTO.getStudentId(), customDTO.getReserveId(), customDTO.getPaymentStatus()));
        }
        return customTMS;
    }

    @Override
    public ArrayList<Integer> getLineChartData() throws CustomException {
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            Double count = dashboardDAO.getLineChartData(i);
            int integer = count.intValue();
            data.add(integer);
        }
        return data;
    }

    @Override
    public ArrayList<String> getNotifications() throws CustomException {
        ArrayList<Reserve> details = dashboardDAO.getDetails();
        ArrayList<String> notifications = new ArrayList<>();
        for (Reserve detail : details) {
            Date checkOutDate = detail.getCheckOut_Date();
            LocalDate today = LocalDate.now();
            LocalDate checkOut = checkOutDate.toLocalDate();
            Period period = Period.between(today, checkOut);
            int days = period.getDays();
            if (days == 0) {
                notifications.add(detail.getReserve_ID() + " is checking out today(" + detail.getCheckOut_Date() + ")");
            }
            if (days == 5) {
                String mail = dashboardDAO.getMail(detail.getReserve_ID());
                notifications.add(detail.getReserve_ID() + " Contract is going to expire in 5 days(" + detail.getCheckOut_Date() + ")");
                if (TempMails.check(mail)) {
                    continue;
                } else {
                    Mail.getInstance().sendMail(mail, "Contract", "Your contract is going to expire in 5 days. Please contact the office for more details.\n\n\n Thank you.\n The D24 Hotel");
                    TempMails.add(mail);
                }
            }
        }
        return notifications;
    }
}
