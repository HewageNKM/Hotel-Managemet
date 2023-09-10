package com.kawishika.service.interfaces;

import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.SuperService;
import com.kawishika.util.CustomException;

public interface CheckOutService extends SuperService {
    boolean checkId(String id);

    CustomTM getReserveDetails(String id) throws CustomException;

    void checkOut(CustomTM customTM) throws CustomException;

    void sendReceipt(CustomTM customTM) throws CustomException;
}
