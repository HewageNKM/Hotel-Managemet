package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;
import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface CheckOutDAO extends CrudDAO {
    CustomDTO getReserveDetails(String id) throws CustomException;

    ArrayList<String> getMail(CustomTM customTM) throws CustomException;
}
