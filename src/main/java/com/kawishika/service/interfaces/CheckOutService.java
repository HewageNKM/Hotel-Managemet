package com.kawishika.service.interfaces;

import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.SuperService;

public interface CheckOutService extends SuperService {
    boolean checkId(String id);

    CustomTM getReserveDetails(String id);

    void checkOut(CustomTM customTM);
}
