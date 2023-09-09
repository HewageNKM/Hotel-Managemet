package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.dao.SuperDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;

import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<CustomDTO> {
    ArrayList<CustomDTO> search(String searchPhrase);

    boolean update(String reserveId);

    ArrayList<String> getMail(CustomTM selectedItem);
}
