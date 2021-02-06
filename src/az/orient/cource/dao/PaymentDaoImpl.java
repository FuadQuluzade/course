package az.orient.cource.dao;

import az.orient.cource.model.*;
import az.orient.cource.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao{
    @Override
    public List<Payment> getPaymentList() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ROWNUM r,P.ID,\n" +
                "       S.ID STUDENT_ID,\n" +
                "       S.NAME STUDENT_NAME,\n" +
                "       S.SURNAME STUDENT_SURNAME,\n" +
                "       T.ID TEACHER_ID,\n" +
                "       T.NAME TEACHER_NAME,\n" +
                "       T.SURNAME TEACHER_SURNAME,\n" +
                "       L.ID LESSON_ID,\n" +
                "       L.LESSON_NAME,\n" +
                "       P.AMOUNT,\n" +
                "       P.PAY_DATE\n" +
                "  FROM PAYMENT P\n" +
                "       INNER JOIN STUDENT S\n" +
                "          ON P.S_ID = S.ID\n" +
                "       INNER JOIN TEACHER T\n" +
                "          ON P.T_ID = T.ID\n" +
                "       INNER JOIN LESSON L\n" +
                "          ON P.L_ID = L.ID\n" +
                " WHERE P.ACTIVE = 1";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
//                    payment.setR(rs.getLong("r"));
                    payment.setId(rs.getLong("ID"));
                    Student student = new Student();
                    student.setId(rs.getLong("STUDENT_ID"));
                    student.setName(rs.getString("STUDENT_NAME"));
                    student.setSurname(rs.getString("STUDENT_SURNAME"));
                    payment.setStudent(student);
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("TEACHER_ID"));
                    teacher.setName(rs.getString("TEACHER_NAME"));
                    teacher.setSurname(rs.getString("TEACHER_SURNAME"));
                    payment.setTeacher(teacher);
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("LESSON_ID"));
                   lesson.setLessonName(rs.getString("LESSON_NAME"));
                    payment.setLesson(lesson);
                    payment.setAmount(rs.getDouble("amount"));
                    payment.setPayDate(rs.getDate("pay_date"));
                    paymentList.add(payment);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c,ps,rs);
        }
        return paymentList;
    }

    @Override
    public boolean addPayment(Payment payment) throws Exception {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO payment (ID,S_ID,T_ID,L_ID,AMOUNT) VALUES(PAYMENT_SEQ.NEXTVAL,?,?,?,?)";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, payment.getStudent().getId());
                ps.setLong(2, payment.getTeacher().getId());
                ps.setLong(3, payment.getLesson().getId());
                ps.setDouble(4, payment.getAmount());
                ps.execute();
                isAdded = true;
            } else {
                System.out.println("Connection is null!");
            }

        } catch(Exception ex) {
            ex.printStackTrace();
            c.rollback();
        } finally {
            c.commit();
          JdbcUtility.close(c, ps, null);
        }
        return isAdded;
    }
    @Override
    public Payment getPaymentById(Long paymentId) throws Exception {
        Payment payment = new Payment();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID,S_ID,T_ID,L_ID,AMOUNT FROM PAYMENT " +
                "WHERE ACTIVE = 1 AND ID = ?";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, paymentId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    payment.setId(rs.getLong("ID"));
                    Student student = new Student();
                    student.setId(rs.getLong("S_ID"));
                    payment.setStudent(student);
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("T_ID"));
                    payment.setTeacher(teacher);
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("L_ID"));
                    payment.setLesson(lesson);
                    payment.setAmount(rs.getDouble("amount"));
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
           JdbcUtility.close(c,ps,rs);
        }
        return payment;

    }

    @Override
    public boolean updatePayment(Payment payment, Long paymentId) throws  Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql=" UPDATE PAYMENT SET S_ID = ?,T_ID = ?,L_ID = ?, AMOUNT = ? " +
                " WHERE ID = ? ";
        try {
            c=DBHelper.getConnection();
            if(c!=null){
                ps=c.prepareStatement(sql);
                ps.setLong(1,payment.getStudent().getId());
                ps.setLong(2,payment.getTeacher().getId());
                ps.setLong(3,payment.getLesson().getId());
                ps.setDouble(4,payment.getAmount());
                ps.setLong(5,paymentId);
                ps.execute();
                result=true;

            }else{
                System.out.println("connection is null");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtility.close(c,ps,null);
        }
        return result;
    }

    @Override
    public boolean deletePayment(Long paymentId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql="UPDATE PAYMENT SET ACTIVE=0 WHERE ID=?";
        try {
            c=DBHelper.getConnection();
            if(c!=null){
                ps=c.prepareStatement(sql);
                ps.setLong(1,paymentId);
                ps.execute();
                result=true;
            }

        }catch (Exception e){
                e.printStackTrace();
        }finally {
            JdbcUtility.close(c,ps,null);
        }
        return result;
    }

    @Override
    public List<Payment> advancedSearchPayment(AdvancedSearch advancedSearch) throws Exception{
        List<Payment> paymentList = new ArrayList<>();
        DateFormat df=new SimpleDateFormat("YYYY-MM-DD");
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT P.ID,\n" +
                "       S.ID STUDENT_ID,\n" +
                "       S.NAME STUDENT_NAME,\n" +
                "       S.SURNAME STUDENT_SURNAME,\n" +
                "       T.ID TEACHER_ID,\n" +
                "       T.NAME TEACHER_NAME,\n" +
                "       T.SURNAME TEACHER_SURNAME,\n" +
                "       L.ID LESSON_ID,\n" +
                "       L.LESSON_NAME,\n" +
                "       P.AMOUNT,\n" +
                "       P.PAY_DATE\n" +
                "  FROM PAYMENT P\n" +
                "       INNER JOIN STUDENT S\n" +
                "          ON P.S_ID = S.ID\n" +
                "       INNER JOIN TEACHER T\n" +
                "          ON P.T_ID = T.ID\n" +
                "       INNER JOIN LESSON L\n" +
                "          ON P.L_ID = L.ID\n" +
                " WHERE P.ACTIVE = 1 ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                if(advancedSearch.getLessonId() !=0 ){
                    sql += " AND L.ID = "+advancedSearch.getLessonId();
                }
                if(advancedSearch.getTeacherId() !=0 ){
                    sql += " AND T.ID = "+advancedSearch.getTeacherId();
                }
                if(advancedSearch.getMinAmount() !=null && !advancedSearch.getMinAmount().isEmpty()){
                    sql += " AND P.AMOUNT >= "+Double.parseDouble(advancedSearch.getMinAmount());
                }
                if(advancedSearch.getMaxAmount() !=null && !advancedSearch.getMaxAmount().isEmpty()){
                    sql+= " AND P.AMOUNT = "+Double.parseDouble(advancedSearch.getMaxAmount());
                }

                if(advancedSearch.getBeginDate() !=null && !advancedSearch.getBeginDate().isEmpty()){
                    sql+= " AND P.PAY_DATE >= TO_DATE(' " + new java.sql.Date(df.parse(advancedSearch.getBeginDate()).getTime()) + "','YYYY-MM-DD')" ;
                }
                if(advancedSearch.getEndDate() !=null && !advancedSearch.getEndDate().isEmpty()){
                    sql+= " AND P.PAY_DATE < TO_DATE(' " + new java.sql.Date(df.parse(advancedSearch.getEndDate()).getTime()) + "','YYYY-MM-DD')" ;
                }
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
//                    payment.setR(rs.getLong("r"));
                    payment.setId(rs.getLong("ID"));
                    Student student = new Student();
                    student.setId(rs.getLong("STUDENT_ID"));
                    student.setName(rs.getString("STUDENT_NAME"));
                    student.setSurname(rs.getString("STUDENT_SURNAME"));
                    payment.setStudent(student);
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("TEACHER_ID"));
                    teacher.setName(rs.getString("TEACHER_NAME"));
                    teacher.setSurname(rs.getString("TEACHER_SURNAME"));
                    payment.setTeacher(teacher);
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("LESSON_ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    payment.setLesson(lesson);
                    payment.setAmount(rs.getDouble("amount"));
                    payment.setPayDate(rs.getDate("pay_date"));
                    paymentList.add(payment);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentList;
    }


}

