package az.orient.cource.dao;

import az.orient.cource.model.Lesson;

import java.util.List;

public interface LessonDao {
    List<Lesson> getLessonList() throws Exception;
}
