package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.dao.SuperDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;
import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<CustomDTO> {
    ArrayList<CustomDTO> search(String searchPhrase) throws CustomException;

    boolean update(String reserveId) throws CustomException;

    ArrayList<String> getMail(CustomTM selectedItem) throws CustomException;
}
