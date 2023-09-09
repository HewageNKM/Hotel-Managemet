package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.CreateAccountDAOImpl;
import com.kawishika.dto.UserDTO;
import com.kawishika.entity.User;
import com.kawishika.service.interfaces.CreateAccountService;
import com.kawishika.util.Hashing;
import com.kawishika.util.Mail;
import com.kawishika.util.Regex;

import java.util.Random;

public class CreateAccountServiceImpl implements CreateAccountService {
    private static final CreateAccountDAOImpl createAccountDAO = (CreateAccountDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CREATE_ACCOUNT);
    private Integer code;

    @Override
    public boolean sendCode(String emailFldText) {
        Random random = new Random();
        code = random.nextInt(999999);
        Mail.getInstance().sendMail(emailFldText, "Account Verification Code", "Your Account Verification Code is: " + code + "\n\nThank You!\nThe D24 Hostel");
        return true;
    }

    @Override
    public boolean validateCode(Integer integer) {
        return code.equals(integer);
    }

    @Override
    public boolean validatePassword(String password) {
        return Regex.validatePassword(password);
    }

    @Override
    public boolean validateUserName(String userName) {
        return Regex.validateUserName(userName) && createAccountDAO.checkUserName(Hashing.getHash(userName.toLowerCase()));
    }

    @Override
    public boolean createAccount(UserDTO dto) {
        return createAccountDAO.save(new User(Hashing.getHash(dto.getUserName().toLowerCase()), Hashing.getHash(dto.getPassword()), dto.getEmail(), dto.getStatus()));
    }

    @Override
    public boolean validateEmail(String email) {
        return Regex.validateEmail(email);
    }
}
