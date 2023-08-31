package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.StudentDAOImpl;
import com.kawishika.dao.interfaces.StudentDAO;
import com.kawishika.dto.StudentDTO;
import com.kawishika.dto.tm.StudentTM;
import com.kawishika.entity.Student;
import com.kawishika.service.interfaces.StudentService;
import com.kawishika.util.Regex;
import javafx.scene.control.Button;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kawishika.dao.DAOFactory.DAOType.STUDENT;

public class StudentServiceImpl implements StudentService {
    private static final StudentDAO studentDAO = (StudentDAOImpl) DAOFactory.getInstance().getDAO(STUDENT);
    @Override
    public boolean validateBirthday(LocalDate value) {
        if (value == null) return false;
        System.out.println(ChronoUnit.YEARS.between(value, LocalDate.now()));
        return ChronoUnit.YEARS.between(value, LocalDate.now()) >= 18;
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        return Regex.validatePhone(phoneNumber);
    }

    @Override
    public boolean validateEmail(String email) {
        return Regex.validateEmail(email);
    }

    @Override
    public boolean validateStudentId(String id) {
        return Regex.validateStudentId(id);
    }

    @Override
    public boolean validateName(String name) {
        return Regex.validateName(name);
    }

    @Override
    public ArrayList<StudentTM> getAll() {
        ArrayList<Student> all = studentDAO.getAll(new ArrayList<>());
        ArrayList<StudentTM> studentTMS = new ArrayList<>();
        for (Student student : all) {
            Button edit = new Button("Edit");
            Button delete = new Button("Delete");
            studentTMS.add(new StudentTM(student.getStudent_ID(), student.getStudent_Name(), student.getBirthDay().toLocalDate(),student.getGender(), student.getStudent_Email(), student.getPhone_No(), student.getStatus(), edit, delete));
        }
        return studentTMS;
    }

    @Override
    public boolean delete(String id) {
        return studentDAO.delete(new Student(id, null, null, null, null, null,null));
    }

    @Override
    public boolean isStudentExists(String id) {
        return studentDAO.isExists(new Student(id, null, null, null, null, null,null));
    }

    @Override
    public boolean update(StudentDTO student) {
        return studentDAO.update(new Student(student.getStudent_ID(), student.getStudent_Name(), student.getStudent_Email(), student.getPhone_No(), student.getBirthDay(),student.getGender(), student.getStatus()));
    }

    @Override
    public boolean save(StudentDTO studentDTO) {
        return studentDAO.save(new Student(studentDTO.getStudent_ID(), studentDTO.getStudent_Name(), studentDTO.getStudent_Email(), studentDTO.getPhone_No(), studentDTO.getBirthDay(),studentDTO.getGender(), studentDTO.getStatus()));
    }

    @Override
    public StudentDTO getStudent(String id) {
        Student student = studentDAO.getStudent(id);
        if(student == null) return null;
        return new StudentDTO(student.getStudent_ID(), student.getStudent_Name(), student.getStudent_Email(), student.getPhone_No(), student.getBirthDay().toLocalDate(),student.getGender(), student.getStatus());
    }

    @Override
    public ArrayList<StudentTM> searchStudent(String searchPhrase) {
        List<Student> students = studentDAO.searchStudent(searchPhrase);
        ArrayList<StudentTM> studentTMS = new ArrayList<>();
        for (Student student : students) {
            Button edit = new Button("Edit");
            Button delete = new Button("Delete");
            studentTMS.add(new StudentTM(student.getStudent_ID(), student.getStudent_Name(), student.getBirthDay().toLocalDate(),student.getGender(), student.getStudent_Email(), student.getPhone_No(), student.getStatus(), edit, delete));
        }
        return studentTMS;
    }
}
