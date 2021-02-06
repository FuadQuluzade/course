package az.orient.cource.dao;

import az.orient.cource.model.Teacher;
import az.orient.cource.util.JdbcUtility;
import jdk.nashorn.internal.codegen.CompilerConstants;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.oracore.OracleType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public List<Teacher> getTeacherList() throws Exception {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        Connection c = null; // baza ile elaqe yaratmaq uchun
        CallableStatement cs = null; // sql- i run etmek ucun hazirlayir
        ResultSet rs = null; // sql den qayidan neticeni ozunde saxlayir.
        String sql = "{?=call QRUP44.GET_TEACHER_LIST_FUNC}";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs= (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("ID"));
                    teacher.setName(rs.getString("NAME"));
                    teacher.setSurname(rs.getString("SURNAME"));
                    teacher.setAddress(rs.getString("ADDRESS"));
                    teacher.setDob(rs.getDate("DOB"));
                    teacher.setPhone(rs.getString("PHONE"));
                    teacherList.add(teacher);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, cs, rs);
        }
        return teacherList;
    }

    @Override
    public boolean addTeacher(Teacher teacher) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call QRUP44.ADD_TEACHER(?,?,?,?,?)}";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, teacher.getName());
                cs.setString(2, teacher.getSurname());
                cs.setString(3, teacher.getAddress());
                cs.setDate(4, new java.sql.Date(teacher.getDob().getTime()));
                cs.setString(5, teacher.getPhone());
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
    public Teacher getTeacherById(Long teacherId) throws Exception {
        Teacher teacher = new Teacher();
        Connection c = null; // baza ile elaqe yaratmaq uchun
        CallableStatement cs = null; // sql- i run etmek ucun hazirlayir
        ResultSet rs = null; // sql den qayidan neticeni ozunde saxlayir.
        String sql = "{?=call QRUP44.GET_TEACHER_BY_ID_FUNC(?)}";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.registerOutParameter(1,OracleTypes.CURSOR);
                cs.setLong(2, teacherId);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);

                if (rs.next()) {
                    teacher.setId(rs.getLong(1));
                    teacher.setName(rs.getString(2));
                    teacher.setSurname(rs.getString("SURNAME"));
                    teacher.setAddress(rs.getString("ADDRESS"));
                    teacher.setDob(rs.getDate("DOB"));
                    teacher.setPhone(rs.getString("PHONE"));
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, cs, rs);
        }
        return teacher;
    }

    @Override
    public boolean updateTeacher(Teacher teacher, Long teacherId) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call QRUP44.UPDATE_TEACHER_PROC(?,?,?,?,?,?)}";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, teacher.getName());
                cs.setString(2, teacher.getSurname());
                cs.setString(3, teacher.getAddress());
                cs.setDate(4, new java.sql.Date(teacher.getDob().getTime()));
                cs.setString(5, teacher.getPhone());
                cs.setLong(6, teacherId);
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
    public boolean deleteTeacher(Long teacherId) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call QRUP44.DELETE_TEACHER_PROC(?)}";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(1, teacherId);
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
    public List<Teacher> getTeacherComboListByLessonId(Long lessonId) throws Exception {
        List<Teacher> teacherList=new ArrayList<>();
        Connection c=null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        String sql=" SELECT DISTINCT T.ID,T.NAME,T.SURNAME FROM PAYMENT P \n " +
                " INNER JOIN TEACHER T ON P.T_ID=T.ID \n " +
                " INNER JOIN LESSON L ON P.L_ID=L.ID \n " ;

        try {
             c=DBHelper.getConnection();
             if(c!=null){
                 if(lessonId !=0){
                     sql += " WHERE L_ID= "+lessonId;
                 }
                 ps= c.prepareStatement(sql);
                 rs=ps.executeQuery();
                 while(rs.next()){
                     Teacher teacher=new Teacher();
                     teacher.setId(rs.getLong("ID"));
                     teacher.setName(rs.getString("NAME"));
                     teacher.setSurname(rs.getString("SURNAME"));
                     teacherList.add(teacher);
                 }
             }else{
                 System.out.println("Connection is null");
             }
        }catch (Exception ex){
             ex.printStackTrace();
        }finally {
            JdbcUtility.close(c,ps,rs);
        }
        return teacherList;
    }
}
