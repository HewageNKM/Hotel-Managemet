package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;

import java.util.ArrayList;

public interface CheckOutDAO extends CrudDAO {
    CustomDTO getReserveDetails(String id);

    ArrayList<String> getMail(CustomTM customTM);
}
