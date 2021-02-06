package az.orient.cource.service;

import az.orient.cource.model.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> getLessonList() throws Exception;
}
