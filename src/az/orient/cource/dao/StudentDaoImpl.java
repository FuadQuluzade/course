package az.orient.cource.dao;

import az.orient.cource.model.Student;
import az.orient.cource.util.JdbcUtility;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> getStudentList() throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null; // baza ile elaqe yaratmaq uchun
        CallableStatement cs = null; // sql- i run etmek ucun hazirlayir
        ResultSet rs = null; // sql den qayidan neticeni ozunde saxlayir.
        String sql = "{?=call QRUP44.STUDENT_PACKAGE.GET_STUDENT_LIST}";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs= (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADDRESS"));
                    student.setDob(rs.getDate("DOB"));
                    student.setPhone(rs.getString("PHONE"));
                    studentList.add(student);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, cs, rs);
        }
        return studentList;
    }

    @Override
    public boolean addStudent(Student student) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql="{call QRUP44.STUDENT_PACKAGE.ADD_STUDENT(?,?,?,?,?)}";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, student.getName());
                cs.setString(2, student.getSurname());
                cs.setString(3, student.getAddress());
                cs.setDate(4, new java.sql.Date(student.getDob().getTime()));
                cs.setString(5, student.getPhone());
                cs.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, cs, null);
        }
        return result;
    }

    @Override
    public Student getStudentById(Long studentId) throws Exception {
        Student student = new Student();
        Connection c = null; // baza ile elaqe yaratmaq uchun
        CallableStatement cs=null;
        ResultSet rs = null; // sql den qayidan neticeni ozunde saxlayir.
        String sql1="{?= call QRUP44.STUDENT_PACKAGE.GET_STUDENT_BY_ID(?)}";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                cs=c.prepareCall(sql1);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.setLong(2, studentId);
                cs.execute();
                rs= (ResultSet) cs.getObject(1);
                if (rs.next()) {
                    student.setId(rs.getLong(1));
                    student.setName(rs.getString(2));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADDRESS"));
                    student.setDob(rs.getDate("DOB"));
                    student.setPhone(rs.getString("PHONE"));
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           JdbcUtility.close(c, cs, rs);
        }
        return student;
    }

    @Override
    public boolean updateStudent(Student student, Long studentId) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql="{call QRUP44.STUDENT_PACKAGE.UPDATE_STUDENT(?,?,?,?,?,?)}";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, student.getName());
                cs.setString(2, student.getSurname());
                cs.setString(3, student.getAddress());
                cs.setDate(4, new java.sql.Date(student.getDob().getTime()));
                cs.setString(5, student.getPhone());
                cs.setLong(6, studentId);
                cs.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
          JdbcUtility.close(c, cs, null);
        }
        return result;
    }

    @Override
    public boolean deleteStudent(Long studentId) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql="{call QRUP44.STUDENT_PACKAGE.DELETE_STUDENT(?)}";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                cs=c.prepareCall(sql);
                cs.setLong(1, studentId);
                cs.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.commit();
         JdbcUtility.close(c, cs, null);
        }
        return result;
    }

    @Override
    public List<Student> searchStudentData(String keyword) throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql="SELECT ID,NAME,SURNAME,ADDRESS,DOB,PHONE FROM STUDENT  " +
                " WHERE ACTIVE=1 AND (ID LIKE(?) OR UPPER(NAME) LIKE UPPER(?) OR UPPER(SURNAME) LIKE  UPPER(?) OR UPPER(DOB) LIKE  UPPER(?) OR UPPER(ADDRESS) LIKE  UPPER(?) OR UPPER(PHONE) LIKE  UPPER(?) )";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                ps.setString(4, "%" + keyword + "%");
                ps.setString(5, "%" + keyword + "%");
                ps.setString(6, "%" + keyword + "%");

                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student=new Student();

                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setDob(rs.getDate("DOB"));
                    student.setAddress(rs.getString("ADDRESS"));
                    student.setPhone(rs.getString("PHONE"));

                    studentList.add(student);
                }

            } else {
                System.out.println("connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JdbcUtility.close(c, ps, rs);
        }
        return studentList;
    }
}
