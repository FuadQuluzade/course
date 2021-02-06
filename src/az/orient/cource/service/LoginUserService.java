package az.orient.cource.service;

import az.orient.cource.model.LoginUser;

public interface LoginUserService {
    LoginUser login(String username, String password) throws Exception;

    LoginUser checkLogin(String email) throws Exception;

    void updateToken(String token, long userId) throws Exception;

    LoginUser checkToken(String token) throws Exception;

    boolean changePassword(String newPassword, long id) throws Exception;
}
