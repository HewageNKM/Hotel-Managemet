package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.CheckinDAOImpl;
import com.kawishika.dao.interfaces.CheckinDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.ReserveDTO;
import com.kawishika.entity.Reserve;
import com.kawishika.service.interfaces.CheckinService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import static com.kawishika.dao.DAOFactory.DAOType.CHECKIN;

public class CheckinServiceImpl implements CheckinService {
    private final CheckinDAO checkinDAO = (CheckinDAOImpl) DAOFactory.getInstance().getDAO(CHECKIN);

    @Override
    public ArrayList<String> getRoomTypes() {
        return checkinDAO.getRoomType();
    }

    @Override
    public CustomDTO getRoomDetails(String newValue) {
        return checkinDAO.getRoomDetails(newValue);
    }

    @Override
    public ArrayList<String> getStudentId(String newValue) {
        return checkinDAO.getStudentId(newValue);
    }

    @Override
    public boolean save(ReserveDTO reserveDTO, String studentId, String roomNumber) {
        return checkinDAO.save(new Reserve(reserveDTO.getReserve_ID(),reserveDTO.getReserve_Date(),reserveDTO.getCheckOut_Date(),reserveDTO.getTotal(),reserveDTO.getPayment_Status(),reserveDTO.getStatus()),studentId,roomNumber);
    }

    @Override
    public String getReserveId() {
        while (true) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            String formattedDateTime = currentDateTime.format(formatter);
            String id = "RE" + formattedDateTime;
            if (checkinDAO.checkReserveId(id))return id;
        }
    }

    @Override
    public String checkStudentEligibility(String studentId) {
        return checkinDAO.checkStudentEligibility(studentId);
    }
}
