package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.ReserveDTO;
import com.kawishika.entity.Reserve;
import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface CheckinDAO extends CrudDAO<Reserve> {
    ArrayList<String> getRoomType() throws CustomException;

    CustomDTO getRoomDetails(String newValue) throws CustomException;

    ArrayList<String> getStudentId(String newValue) throws CustomException;

    boolean checkReserveId(String id) throws CustomException;

    boolean save(Reserve reserveDTO, String studentId, String roomId) throws CustomException;

    String checkStudentEligibility(String studentId) throws CustomException;

    String checkReservation(String id) throws CustomException;

    ArrayList<String> getMail(String studentId) throws CustomException;
}
