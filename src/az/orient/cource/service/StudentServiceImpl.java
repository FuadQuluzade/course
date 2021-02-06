package az.orient.cource.service;

import az.orient.cource.dao.StudentDao;
import az.orient.cource.model.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService{
    private   StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getStudentList() throws Exception {
        return studentDao.getStudentList();
    }

    @Override
    public boolean addStudent(Student student) throws Exception {
        return studentDao.addStudent(student);
    }

    @Override
    public Student getStudentById(Long studentId) throws Exception {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public boolean updateStudent(Student student, Long studentId) throws Exception {
        return studentDao.updateStudent(student, studentId);
    }

    @Override
    public boolean deleteStudent(Long studentId) throws Exception {
        return studentDao.deleteStudent(studentId);
    }

    @Override
    public List<Student> searchStudentData(String keyword) throws Exception {
        return studentDao.searchStudentData(keyword);
    }
}
