package com.kawishika.service.interfaces;

import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.ReserveDTO;
import com.kawishika.service.SuperService;

import java.util.ArrayList;

public interface CheckinService extends SuperService {
    ArrayList<String> getRoomTypes();

    CustomDTO getRoomDetails(String newValue);

    ArrayList<String> getStudentId(String newValue);

    boolean save(ReserveDTO reserveDTO, String studentId, String roomNumber);

    String getReserveId();

    String checkStudentEligibility(String studentId);

    String checkReservation(String id);
}
