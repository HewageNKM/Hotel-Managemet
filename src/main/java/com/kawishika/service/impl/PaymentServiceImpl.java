package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.PaymentDAOImpl;
import com.kawishika.dao.interfaces.PaymentDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.interfaces.PaymentService;
import javafx.scene.control.Button;

import java.util.ArrayList;

import static com.kawishika.dao.DAOFactory.DAOType.PAYMENT;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO = (PaymentDAOImpl) DAOFactory.getInstance().getDAO(PAYMENT);

    @Override
    public ArrayList<CustomTM> getAll() {
        ArrayList<CustomDTO> all = paymentDAO.getAll(new ArrayList<>());
        ArrayList<CustomTM> customTMS = new ArrayList<>();
        for (CustomDTO customDTO : all) {
            Button button = new Button("Received");
            boolean add = customTMS.add(new CustomTM(
                    customDTO.getReserveId(),
                    customDTO.getStudentId(),
                    customDTO.getRoomNumber(),
                    customDTO.getTotal(),
                    customDTO.getPaymentStatus(),
                    button
            ));
        }
        return customTMS;
    }

    @Override
    public ArrayList<CustomTM> search(String searchPhrase) {
        ArrayList<CustomDTO> all = paymentDAO.search(searchPhrase);
        ArrayList<CustomTM> customTMS = new ArrayList<>();
        for (CustomDTO customDTO : all) {
            Button button = new Button("Received");
            customTMS.add(new CustomTM(
                    customDTO.getReserveId(),
                    customDTO.getStudentId(),
                    customDTO.getRoomNumber(),
                    customDTO.getTotal(),
                    customDTO.getPaymentStatus(),
                    button
            ));
        }
        return customTMS;
    }

    @Override
    public boolean update(String reserveId) {
        return paymentDAO.update(reserveId);
    }
}
