package az.orient.cource.service;

import az.orient.cource.dao.LoginUserDao;
import az.orient.cource.model.LoginUser;

public class LoginUserServiceImpl implements LoginUserService{
    private LoginUserDao loginUserDao;



    public LoginUserServiceImpl(LoginUserDao loginUserDao) {
        this.loginUserDao = loginUserDao;
    }

    public LoginUserServiceImpl() {
    }

    @Override
    public LoginUser login(String username, String password) throws Exception {
        return loginUserDao.login(username, password);
    }

    @Override
    public LoginUser checkLogin(String email) throws Exception {
        return loginUserDao.checkLogin(email);
    }

    @Override
    public void updateToken(String token, long userId) throws Exception {
        loginUserDao.updateToken(token,userId);
    }

    @Override
    public LoginUser checkToken(String token) throws Exception {
        return loginUserDao.checkToken(token);
    }

    @Override
    public boolean changePassword(String newPassword, long id) throws Exception {
        return loginUserDao.changePassword(newPassword,id);
    }
}
