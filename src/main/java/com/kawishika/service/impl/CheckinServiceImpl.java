package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.CheckinDAOImpl;
import com.kawishika.dao.interfaces.CheckinDAO;
import com.kawishika.service.interfaces.CheckinService;

import static com.kawishika.dao.DAOFactory.DAOType.CHECKIN;

public class CheckinServiceImpl implements CheckinService {
    private final CheckinDAO checkinDAO = (CheckinDAOImpl) DAOFactory.getInstance().getDAO(CHECKIN);
}
