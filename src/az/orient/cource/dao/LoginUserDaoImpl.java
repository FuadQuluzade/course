package az.orient.cource.dao;

import az.orient.cource.model.LoginUser;
import az.orient.cource.model.Role;
import az.orient.cource.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginUserDaoImpl implements LoginUserDao{
    @Override
    public LoginUser login(String username, String password) throws Exception {
        LoginUser loginUser = new LoginUser();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT LU.ID,\n"
                + "       LU.USERNAME,\n"
                + "       LU.NAME,\n"
                + "       LU.SURNAME,\n"
                + "       R.ID ROLE_ID,\n"
                + "       R.ROLE_NAME\n"
                + "  FROM LOGINUSER LU INNER JOIN ROLE R ON LU.R_ID = R.ID\n"
                + " WHERE     LU.ACTIVE = 1\n"
                + "       AND R.ACTIVE = 1\n"
                + "       AND LOWER (LU.USERNAME) = LOWER (?)\n"
                + "       AND LU.PASSWORD = ? ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    loginUser.setName(rs.getString("NAME"));
                    loginUser.setSurname(rs.getString("SURNAME"));
                    loginUser.setUsername(rs.getString("USERNAME"));
                    Role role = new Role();
                    role.setId(rs.getLong("ROLE_ID"));
                    role.setRoleName(rs.getString("ROLE_NAME"));
                    loginUser.setRole(role);

                } else {
                    loginUser = null;
                }

            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           JdbcUtility.close(c, ps, rs);
        }
        return loginUser;
    }

    @Override
    public LoginUser checkLogin(String email) throws Exception {
        LoginUser loginUser = new LoginUser();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT LU.ID,\n"
                + "       LU.USERNAME,\n"
                + "       LU.NAME,\n"
                + "       LU.SURNAME,\n"
                + "       R.ID ROLE_ID,\n"
                + "       R.ROLE_NAME\n"
                + "  FROM LOGINUSER LU INNER JOIN ROLE R ON LU.R_ID = R.ID\n"
                + " WHERE     LU.ACTIVE = 1\n"
                + "       AND R.ACTIVE = 1\n"
                + "       AND LOWER (LU.USERNAME) = LOWER (?) \n";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, email);

                rs = ps.executeQuery();
                if (rs.next()) {
                    loginUser.setId(rs.getLong("ID"));
                    loginUser.setName(rs.getString("NAME"));
                    loginUser.setSurname(rs.getString("SURNAME"));
                    loginUser.setUsername(rs.getString("USERNAME"));
                    Role role = new Role();
                    role.setId(rs.getLong("ROLE_ID"));
                    role.setRoleName(rs.getString("ROLE_NAME"));
                    loginUser.setRole(role);

                } else {
                    loginUser = null;
                }

            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return loginUser;
    }

    @Override
    public void updateToken(String token, long userId) throws Exception {
          Connection c= null;
          PreparedStatement ps= null;
          String sql=" UPDATE LOGINUSER SET TOKEN =? WHERE ID=? ";
          try {
             c=DBHelper.getConnection();
             if(c != null){
               ps=c.prepareStatement(sql);
               ps.setString(1, token);
               ps.setLong(2,userId);
               ps.execute();
             }else{
                 System.out.println("Connection is null !");
             }
          }catch (Exception ex){
              ex.printStackTrace();
          }finally {
              c.commit();
              JdbcUtility.close(c,ps,null);
          }
    }

    @Override
    public LoginUser checkToken(String token) throws Exception {
        LoginUser loginUser = new LoginUser();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT LU.ID,\n"
                + "       LU.USERNAME,\n"
                + "       LU.NAME,\n"
                + "       LU.SURNAME,\n"
                + "       R.ID ROLE_ID,\n"
                + "       R.ROLE_NAME\n"
                + "  FROM LOGINUSER LU INNER JOIN ROLE R ON LU.R_ID = R.ID\n"
                + " WHERE     LU.ACTIVE = 1\n"
                + "       AND R.ACTIVE = 1\n"
                + "       AND TOKEN = ? \n";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, token);

                rs = ps.executeQuery();
                if (rs.next()) {
                    loginUser.setId(rs.getLong("ID"));
                    loginUser.setName(rs.getString("NAME"));
                    loginUser.setSurname(rs.getString("SURNAME"));
                    loginUser.setUsername(rs.getString("USERNAME"));
                    Role role = new Role();
                    role.setId(rs.getLong("ROLE_ID"));
                    role.setRoleName(rs.getString("ROLE_NAME"));
                    loginUser.setRole(role);

                } else {
                    loginUser = null;
                }

            } else {
                System.out.println("Connection error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return loginUser;
    }

    @Override
    public boolean changePassword(String newPassword, long id) throws Exception {
        boolean result =false;
        Connection c= null;
        PreparedStatement ps= null;
        String sql=" UPDATE LOGINUSER SET PASSWORD =? WHERE ID=? ";
        try {
            c=DBHelper.getConnection();
            if(c != null){
                ps=c.prepareStatement(sql);
                ps.setString(1, newPassword);
                ps.setLong(2,id);
                ps.execute();
                result=true;
            }else{
                System.out.println("Connection is null !");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            c.commit();
            JdbcUtility.close(c,ps,null);
        }
        return result;
    }
}
