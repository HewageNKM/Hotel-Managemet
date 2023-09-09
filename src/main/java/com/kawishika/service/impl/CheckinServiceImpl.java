package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.CheckinDAOImpl;
import com.kawishika.dao.interfaces.CheckinDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.ReserveDTO;
import com.kawishika.entity.Reserve;
import com.kawishika.service.interfaces.CheckinService;
import com.kawishika.util.Mail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        return checkinDAO.save(new Reserve(reserveDTO.getReserve_ID(), reserveDTO.getReserve_Date(), reserveDTO.getCheckOut_Date(), reserveDTO.getTotal(), reserveDTO.getPayment_Status(), reserveDTO.getStatus()), studentId, roomNumber);
    }

    @Override
    public String getReserveId() {
        String id;
        while (true) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = dtf.format(now);
            id = "RE" + formattedDateTime;
            if (checkinDAO.checkReserveId(id)) {
                continue;
            } else {
                break;
            }
        }
        return id;
    }

    @Override
    public String checkStudentEligibility(String studentId) {
        return checkinDAO.checkStudentEligibility(studentId);
    }

    @Override
    public String checkReservation(String id) {
        return checkinDAO.checkReservation(id);
    }

    @Override
    public void sendReceipt(ReserveDTO reserveDTO, String studentId, String roomNumber) {
        ArrayList<String> mail = checkinDAO.getMail(studentId);
        String subject = "Reserve Successful";
        String message = "Dear " + mail.get(1) + ",\n\n" +
                "Reserve ID : " + reserveDTO.getReserve_ID() + "\n" +
                "Reserve Date : " + reserveDTO.getReserve_Date() + "\n" +
                "Check Out Date : " + reserveDTO.getCheckOut_Date() + "\n" +
                "Total : " + reserveDTO.getTotal() + "\n" +
                "Payment Status : " + reserveDTO.getPayment_Status() + "\n" +
                "Student ID : " + studentId + "\n" +
                "Room Number : " + roomNumber + "\n\n" + "Thank You For Choosing Us !" + "\n\n" +
                "Have A Nice Day !" + "\n" +
                "The D24";
        Mail.getInstance().sendMail(mail.get(0), subject, message);
    }

}
