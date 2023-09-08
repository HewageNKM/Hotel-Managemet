package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.CheckOutDAOImpl;
import com.kawishika.dao.interfaces.CheckOutDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.interfaces.CheckOutService;
import com.kawishika.util.Regex;

import static com.kawishika.dao.DAOFactory.DAOType.CHECKOUT;

public class CheckOutServiceImpl implements CheckOutService {
    private final CheckOutDAO checkOutDAO = (CheckOutDAOImpl) DAOFactory.getInstance().getDAO(CHECKOUT);
    @Override
    public boolean checkId(String id) {
        return Regex.validateStudentId(id) | Regex.validateReserveId(id);
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
}
