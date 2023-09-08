package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.dto.CustomDTO;

public interface CheckOutDAO extends CrudDAO {
    CustomDTO getReserveDetails(String id);
}
