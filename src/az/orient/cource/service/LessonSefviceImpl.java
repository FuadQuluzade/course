package az.orient.cource.service;

import az.orient.cource.dao.LessonDao;
import az.orient.cource.model.Lesson;

import java.util.List;

public class LessonSefviceImpl implements LessonService {
    private LessonDao lessonDao;

    public LessonSefviceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public List<Lesson> getLessonList() throws Exception {
        return lessonDao.getLessonList();
    }
}
