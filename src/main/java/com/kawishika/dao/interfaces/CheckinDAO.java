package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.ReserveDTO;
import com.kawishika.entity.Reserve;

import java.util.ArrayList;

public interface CheckinDAO extends CrudDAO<Reserve> {
    ArrayList<String> getRoomType();

    CustomDTO getRoomDetails(String newValue);

    ArrayList<String> getStudentId(String newValue);

    boolean checkReserveId(String id);

    boolean save(Reserve reserveDTO, String studentId, String roomId);

    String checkStudentEligibility(String studentId);

    String checkReservation(String id);

    ArrayList<String> getMail(String studentId);
}
