package com.kawishika.service.interfaces;

import com.kawishika.dto.StudentDTO;
import com.kawishika.dto.tm.StudentTM;
import com.kawishika.service.SuperService;

import java.time.LocalDate;
import java.util.ArrayList;

public interface StudentService extends SuperService {
    boolean validateBirthday(LocalDate value);

    boolean validatePhoneNumber(String phoneNumber);

    boolean validateEmail(String email);

    boolean validateStudentId(String id);

    boolean validateName(String name);

    ArrayList<StudentTM> getAll();

    boolean delete(String id);

    boolean isStudentExists(String id);

    boolean update(StudentDTO student);

    boolean save(StudentDTO studentDTO);

    StudentDTO getStudent(String id);
}
