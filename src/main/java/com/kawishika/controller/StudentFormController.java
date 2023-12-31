package com.kawishika.controller;

import com.kawishika.dto.StudentDTO;
import com.kawishika.dto.tm.StudentTM;
import com.kawishika.service.ServiceFactory;
import com.kawishika.service.impl.StudentServiceImpl;
import com.kawishika.service.interfaces.StudentService;
import com.kawishika.util.CustomException;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

import static com.kawishika.service.ServiceFactory.ServiceType.STUDENT;


public class StudentFormController {

    private final StudentService studentService = (StudentServiceImpl) ServiceFactory.getInstance().getService(STUDENT);
    @FXML
    private TableColumn<?, ?> birthdayColumn;

    @FXML
    private DatePicker birthdayPicker;

    @FXML
    private TableColumn<?, ?> deleteColumn;

    @FXML
    private TableColumn<?, ?> editColumn;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private TextField emailFld;

    @FXML
    private ComboBox<String> genderBox;

    @FXML
    private TableColumn<?, ?> genderColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<?, ?> phoneNumberColumn;

    @FXML
    private TextField phoneNumberFld;

    @FXML
    private TextField searchFld;

    @FXML
    private ComboBox<String> statusBox;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TextField studentIdFld;

    @FXML
    private TextField studentNameFld;

    @FXML
    private TableView<StudentTM> studentTable;

    public void initialize() {
        loadPane();
        setBoxValues();
        setCellValues();
        Thread thread = new Thread(this::loadTable);
        thread.start();
    }

    private void loadTable() {
        try {
            ArrayList<StudentTM> all = studentService.getAll();
            for (StudentTM studentTM : all) {
                setEditAction(studentTM.getEdit());
                setDeleteAction(studentTM.getDelete());
            }
            studentTable.setItems(FXCollections.observableList(all));
        } catch (CustomException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setDeleteAction(Button delete) {
        delete.setOnAction(event -> {
            StudentTM selectedItem = studentTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this student?", ButtonType.YES, ButtonType.NO).showAndWait().ifPresent(buttonType -> {
                    if (buttonType == ButtonType.YES) {
                        try {
                            boolean deleted = studentService.delete(selectedItem.getStudent_ID());
                            if (deleted) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Student has been deleted successfully", ButtonType.OK).show();
                                loadTable();
                                clearBtnOnAction();
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Failed to delete the student", ButtonType.OK).show();
                            }
                        } catch (CustomException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        }
                    }
                });
            } else {
                new Alert(Alert.AlertType.ERROR, "Please select a student to delete", ButtonType.OK).show();
            }
        });
    }

    private void setEditAction(Button edit) {
        edit.setOnAction(event -> {
            StudentTM selectedItem = studentTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                studentIdFld.setText(selectedItem.getStudent_ID());
                studentNameFld.setText(selectedItem.getStudent_Name());
                emailFld.setText(selectedItem.getStudent_Email());
                phoneNumberFld.setText(selectedItem.getPhone_No());
                birthdayPicker.setValue(selectedItem.getBirthDay().toLocalDate());
                statusBox.setValue(selectedItem.getStatus());
                genderBox.setValue(selectedItem.getGender());
            } else {
                new Alert(Alert.AlertType.ERROR, "Please select a student to edit", ButtonType.OK).show();
            }
        });
    }

    private void setCellValues() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Student_ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Student_Name"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("BirthDay"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Phone_No"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Student_Email"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        editColumn.setCellValueFactory(new PropertyValueFactory<>("edit"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    private void setBoxValues() {
        statusBox.getItems().add("Blacklist");
        statusBox.getItems().add("Normal");
        genderBox.getItems().add("Male");
        genderBox.getItems().add("Female");

    }

    private void loadPane() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(javafx.util.Duration.seconds(1));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    @FXML
    void addSaveBtnOnAction() {
        if (validateDetails()) {
            try {
                if (studentService.isStudentExists(studentIdFld.getText())) {
                    if (studentService.update(new StudentDTO(studentIdFld.getText(), studentNameFld.getText(), emailFld.getText(), phoneNumberFld.getText(), birthdayPicker.getValue(), genderBox.getValue(), statusBox.getValue()))) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Student has been updated successfully", ButtonType.OK).show();
                        loadTable();
                        clearBtnOnAction();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to update the student", ButtonType.OK).show();
                    }
                } else {
                    if (studentService.save(new StudentDTO(studentIdFld.getText(), studentNameFld.getText(), emailFld.getText(), phoneNumberFld.getText(), birthdayPicker.getValue(), genderBox.getValue(), statusBox.getValue()))) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Student has been saved successfully", ButtonType.OK).show();
                        loadTable();
                        clearBtnOnAction();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to save the student", ButtonType.OK).show();
                    }
                }
            } catch (CustomException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                e.printStackTrace();
            }
        } else {
            validateDetails();
        }
    }

    private boolean validateDetails() {
        return validateName() & validateStudentId() & validateEmail() & validatePhoneNumber() & validateBirthday() & validateStatus() & validateGender();
    }

    private boolean validateGender() {
        if (genderBox.getSelectionModel().isEmpty()) {
            genderBox.setStyle("-fx-border-color: red");
            return false;
        } else {
            genderBox.setStyle("-fx-border-color: green");
            return true;
        }
    }

    private boolean validateStatus() {
        if (statusBox.getSelectionModel().isEmpty()) {
            statusBox.setStyle("-fx-border-color: red");
            return false;
        } else {
            statusBox.setStyle("-fx-border-color: green");
            return true;
        }
    }

    private boolean validateBirthday() {
        if (studentService.validateBirthday(birthdayPicker.getValue())) {
            birthdayPicker.setStyle("-fx-border-color: green");
            return true;
        } else {
            birthdayPicker.setStyle("-fx-border-color: red");
            return false;
        }
    }

    private boolean validatePhoneNumber() {
        if (studentService.validatePhoneNumber(phoneNumberFld.getText())) {
            phoneNumberFld.setStyle("-fx-border-color: green");
            return true;
        } else {
            phoneNumberFld.setStyle("-fx-border-color: red");
            return false;
        }
    }

    private boolean validateEmail() {
        if (studentService.validateEmail(emailFld.getText())) {
            emailFld.setStyle("-fx-border-color: green");
            return true;
        } else {
            emailFld.setStyle("-fx-border-color: red");
            return false;
        }
    }

    private boolean validateStudentId() {
        if (studentService.validateStudentId(studentIdFld.getText())) {
            studentIdFld.setStyle("-fx-border-color: green");
            return true;
        } else {
            studentIdFld.setStyle("-fx-border-color: red");
            return false;
        }
    }

    private boolean validateName() {
        if (studentService.validateName(studentNameFld.getText())) {
            studentNameFld.setStyle("-fx-border-color: green");
            return true;
        } else {
            studentNameFld.setStyle("-fx-border-color: red");
            return false;
        }
    }

    @FXML
    void clearBtnOnAction() {
        studentNameFld.clear();
        studentIdFld.clear();
        emailFld.clear();
        phoneNumberFld.clear();
        birthdayPicker.setValue(null);
        genderBox.getSelectionModel().clearSelection();
        statusBox.getSelectionModel().clearSelection();
        genderBox.setStyle("-fx-border-color: none");
        studentNameFld.setStyle("-fx-border-color: none");
        studentIdFld.setStyle("-fx-border-color: none");
        emailFld.setStyle("-fx-border-color: none");
        phoneNumberFld.setStyle("-fx-border-color: none");
        birthdayPicker.setStyle("-fx-border-color: none");
        statusBox.setStyle("-fx-border-color: none");
        studentIdFld.setPromptText("Student ID");
        studentNameFld.setPromptText("Student Name");
        emailFld.setPromptText("Email");
        phoneNumberFld.setPromptText("Phone Number");
        birthdayPicker.setPromptText("Birthday");
        genderBox.setPromptText("Gender");
        statusBox.setPromptText("Status");
    }

    @FXML
    void deleteBtnOnAction() {
        new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this student?", ButtonType.YES, ButtonType.NO).showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.YES) {
                try {
                    studentService.delete(studentIdFld.getText());
                } catch (CustomException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void searchTypeOnAction() {
        if (searchFld.getText().trim().isBlank()) {
            loadTable();
        } else {
            try {
                ArrayList<StudentTM> all = studentService.searchStudent(searchFld.getText());
                for (StudentTM studentTM : all) {
                    setEditAction(studentTM.getEdit());
                    setDeleteAction(studentTM.getDelete());
                }
                studentTable.setItems(FXCollections.observableList(all));
            } catch (CustomException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                e.printStackTrace();
            }
        }
    }

    public void enterOnAction() {
        try {
            StudentDTO studentDTO = studentService.getStudent(studentIdFld.getText());
            if (studentDTO != null) {
                studentNameFld.setText(studentDTO.getStudent_Name());
                emailFld.setText(studentDTO.getStudent_Email());
                phoneNumberFld.setText(studentDTO.getPhone_No());
                birthdayPicker.setValue(studentDTO.getBirthDay().toLocalDate());
                statusBox.setValue(studentDTO.getStatus());
            } else {
                new Alert(Alert.AlertType.ERROR, "No student found", ButtonType.OK).show();
            }
        } catch (CustomException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }
}

