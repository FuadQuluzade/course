package az.orient.cource.service;

import az.orient.cource.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentList() throws Exception;

    boolean addStudent(Student student) throws Exception;

    Student getStudentById(Long studentId) throws Exception;

    boolean updateStudent(Student student, Long studentId) throws Exception;

    boolean deleteStudent(Long studentId) throws Exception;
    List<Student> searchStudentData(String keyword) throws Exception;

}
