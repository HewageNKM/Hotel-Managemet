package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.CheckOutDAOImpl;
import com.kawishika.dao.interfaces.CheckOutDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.interfaces.CheckOutService;
import com.kawishika.util.Mail;
import com.kawishika.util.Regex;

import java.util.ArrayList;

import static com.kawishika.dao.DAOFactory.DAOType.CHECKOUT;

public class CheckOutServiceImpl implements CheckOutService {
    private final CheckOutDAO checkOutDAO = (CheckOutDAOImpl) DAOFactory.getInstance().getDAO(CHECKOUT);
    @Override
    public boolean checkId(String id) {
        return Regex.validateStudentId(id) | Regex.validateReserveId(id) | Regex.validateRoomNumber(id);
    }

    @Override
    public CustomTM getReserveDetails(String id) {
        CustomDTO dto = checkOutDAO.getReserveDetails(id);
        if (dto == null) {
            return null;
        }
        return new CustomTM(dto.getReserveId(), dto.getStudentId(), dto.getRoomNumber(), dto.getTotal(), dto.getPaymentStatus(), dto.getCheckInDate(), dto.getCheckOutDate());
    }

    @Override
    public void checkOut(CustomTM customTM) {
        checkOutDAO.update(customTM);
    }

    @Override
    public void sendReceipt(CustomTM customTM) {
        ArrayList<String> mail = checkOutDAO.getMail(customTM);
        String message;
        String subject = "Check Out Successful";
        if (Regex.validateReserveId(customTM.getReserveId())) {
            message = "Dear Sir/Madam" + "\n\n" +
                    "Thank you for choosing our service.\n\n" +
                    "Best Regards,\n" +
                    "The D24 Hostel";
        }else {
            message = "Dear " + mail.get(1) + ",\n\n" +
                    "Thank you for choosing our service.\n\n" +
                    "Best Regards,\n" +
                    "The D24 Hostel";
        }
        Mail.getInstance().sendMail(mail.get(0), subject, message);
    }
}
