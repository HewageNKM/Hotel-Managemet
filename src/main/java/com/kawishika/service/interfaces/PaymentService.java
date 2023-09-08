package com.kawishika.service.interfaces;

import com.kawishika.dto.tm.CustomTM;
import com.kawishika.service.SuperService;

import java.util.ArrayList;

public interface PaymentService extends SuperService {
    ArrayList<CustomTM> getAll();

    ArrayList<CustomTM> search(String searchPhrase);

    boolean update(String reserveId);
}
