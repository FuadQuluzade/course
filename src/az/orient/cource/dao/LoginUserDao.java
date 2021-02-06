package az.orient.cource.dao;

import az.orient.cource.model.LoginUser;

public interface LoginUserDao {
    LoginUser login(String username, String password) throws Exception;

    LoginUser checkLogin(String email) throws Exception;

    void updateToken(String token, long userId) throws Exception;

    LoginUser checkToken(String token) throws Exception;

    boolean changePassword(String newPassword, long id) throws Exception;

}
