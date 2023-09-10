package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.ResetDAOImpl;
import com.kawishika.dao.interfaces.ResetDAO;
import com.kawishika.dto.UserDTO;
import com.kawishika.entity.User;
import com.kawishika.service.interfaces.ResetService;
import com.kawishika.util.CustomException;
import com.kawishika.util.Hashing;
import com.kawishika.util.Mail;

import java.util.ArrayList;
import java.util.Random;

public class ResetServiceImpl implements ResetService {
    private final ResetDAO resetDAO = (ResetDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESET);
    private Integer code;

    @Override
    public boolean sendCode(String userName) throws CustomException {
        ArrayList<String> arrayList = resetDAO.sendCode(new User(Hashing.getHash(userName.toLowerCase()), null));
        System.out.println(arrayList);
        if (arrayList.get(0).equals("false")) {
            return false;
        } else {
            Random random = new Random();
            code = random.nextInt(999999);
            Mail.getInstance().sendMail(arrayList.get(1), "Password Reset Code", "Your Password Reset Code is: " + code + "\n\nThank You!\nThe D24 Hostel");
        }
        return true;
    }

    @Override
    public boolean checkCode(Integer code) {
        return code.equals(this.code);
    }

    @Override
    public boolean resetPassword(UserDTO userDTO) throws CustomException {
        return resetDAO.update(new User(Hashing.getHash(userDTO.getUserName().toLowerCase()), Hashing.getHash(userDTO.getPassword())));
    }
}
