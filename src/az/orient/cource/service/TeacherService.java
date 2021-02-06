package az.orient.cource.service;

import az.orient.cource.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getTeacherList() throws Exception;
    boolean addTeacher(Teacher teacher) throws Exception;
    Teacher getTeacherById(Long teacherId) throws Exception;
    boolean updateTeacher(Teacher teacher,Long teacherId) throws Exception;

    boolean deleteTeacher(Long teacherId) throws Exception;

    List<Teacher> getTeacherComboListByLessonId(Long lessonId) throws Exception;

}
