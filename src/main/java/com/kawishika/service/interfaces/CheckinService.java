package com.kawishika.service.interfaces;

import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.ReserveDTO;
import com.kawishika.service.SuperService;
import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface CheckinService extends SuperService {
    ArrayList<String> getRoomTypes() throws CustomException;

    CustomDTO getRoomDetails(String newValue) throws CustomException;

    ArrayList<String> getStudentId(String newValue) throws CustomException;

    boolean save(ReserveDTO reserveDTO, String studentId, String roomNumber) throws CustomException;

    String getReserveId() throws CustomException;

    String checkStudentEligibility(String studentId) throws CustomException;

    String checkReservation(String id) throws CustomException;

    void sendReceipt(ReserveDTO reserveDTO, String text, String roomNumber) throws CustomException;
}
