package az.orient.cource.dao;

import az.orient.cource.model.Lesson;
import az.orient.cource.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LessonDoImpl implements LessonDao{
    @Override
    public List<Lesson> getLessonList() throws Exception {
        List<Lesson> lessonList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID,LESSON_NAME,LESSON_TIME,LESSON_PRICE FROM LESSON \n" +
                "WHERE ACTIVE = 1 ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    lesson.setLessonTime(rs.getInt("LESSON_TIME"));
                    lesson.setLessonPrice(rs.getDouble("LESSON_PRICE"));
                    lessonList.add(lesson);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
           JdbcUtility.close(c, ps, rs);
        }
        return lessonList;
    }

}
