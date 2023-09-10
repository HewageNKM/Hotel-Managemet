package com.kawishika.service.interfaces;

import com.kawishika.dto.StudentDTO;
import com.kawishika.dto.tm.StudentTM;
import com.kawishika.service.SuperService;
import com.kawishika.util.CustomException;

import java.time.LocalDate;
import java.util.ArrayList;

public interface StudentService extends SuperService {
    boolean validateBirthday(LocalDate value);

    boolean validatePhoneNumber(String phoneNumber);

    boolean validateEmail(String email);

    boolean validateStudentId(String id);

    boolean validateName(String name);

    ArrayList<StudentTM> getAll() throws CustomException;

    boolean delete(String id) throws CustomException;

    boolean isStudentExists(String id) throws CustomException;

    boolean update(StudentDTO student) throws CustomException;

    boolean save(StudentDTO studentDTO) throws CustomException;

    StudentDTO getStudent(String id) throws CustomException;

    ArrayList<StudentTM> searchStudent(String searchPhrase) throws CustomException;
}
