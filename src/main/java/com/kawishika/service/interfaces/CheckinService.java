package com.kawishika.service.interfaces;

import com.kawishika.service.SuperService;

import java.util.ArrayList;

public interface CheckinService extends SuperService {
    ArrayList<String> getRoomTypes();
}
