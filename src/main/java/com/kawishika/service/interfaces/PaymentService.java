package com.kawishika.service.interfaces;

import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.SuperService;
import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface PaymentService extends SuperService {
    ArrayList<CustomTM> getAll() throws CustomException;

    ArrayList<CustomTM> search(String searchPhrase) throws CustomException;

    boolean update(String reserveId) throws CustomException;

    void sendReceipt(CustomTM selectedItem) throws CustomException;
}
