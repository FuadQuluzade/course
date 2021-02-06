package az.orient.cource.web;

import az.orient.cource.dao.LoginUserDao;
import az.orient.cource.dao.LoginUserDaoImpl;
import az.orient.cource.model.LoginUser;
import az.orient.cource.service.LoginUserService;
import az.orient.cource.service.LoginUserServiceImpl;
import az.orient.cource.util.Utility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "LoginServlet", urlPatterns = "/ls")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = "";
        String address = "";
        LoginUserDao loginUserDao = new LoginUserDaoImpl();
        LoginUserService loginUserService = new LoginUserServiceImpl(loginUserDao);

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        try {
            if (action.equalsIgnoreCase("login")) {


                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                    LoginUser loginUser = loginUserService.login(username, password);
                    if (loginUser != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("login", loginUser);
                        address = "main.jsp";
                    } else {
                        request.setAttribute("invalid", "username or password is invalid");
                        address = "login.jsp";
                    }

                } else {
                    request.setAttribute("invalid", "username or password is empty");
                    address = "login.jsp";
                }
            } else if (action.equalsIgnoreCase("forget")) {
                address = "/WEB-INF/pages/forget.jsp";
            } else if (action.equalsIgnoreCase("sendEmail")) {
                String email = request.getParameter("email");
                if(email !=null && !email.isEmpty()){
                  LoginUser loginUser=loginUserService.checkLogin(email);
                  if(loginUser !=null){
                      String token= UUID.randomUUID().toString();
                      loginUserService.updateToken(token,loginUser.getId());
                      String emailText="Your change password link: http://localhost:8089/ls?action=changePassword&token="+token;
                      boolean isSendMail= Utility.sendMail(email,"change password",emailText);
                         if(isSendMail){
                             request.setAttribute("success" , "E- mail is succesfully sended!");
                             address="WEB-INF/pages/forget.jsp";
                         }
                  }else {
                      request.setAttribute("invalid" , "E- mail is invalid!");
                      address="WEB-INF/pages/forget.jsp";
                  }
                }else {
                    request.setAttribute("invalid" , "E- mail is empty!");
                    address="WEB-INF/pages/forget.jsp";
                }
            }else if(action.equalsIgnoreCase("changePassword")){
                     String token=request.getParameter("token");
                     LoginUser loginUser=loginUserService.checkToken(token);
                     if(loginUser !=null) {
                         request.setAttribute("token", token);
                         address = "/WEB-INF/pages/changePassword.jsp";
                     }else {
                         address = "/WEB-INF/pages/404.jsp";

                     }
            }else if(action.equalsIgnoreCase("change")){
                     String newPassword=request.getParameter("newPassword");
                     String confirmPassword=request.getParameter("confirmPassword");
                     String token=request.getParameter("token");
                     LoginUser loginUser=loginUserService.checkToken(token);
                     if(loginUser !=null){
                     if(newPassword.equals(confirmPassword)){
                         boolean isChange = loginUserService.changePassword(newPassword,loginUser.getId());
                         if(isChange){
                             loginUserService.updateToken(null,loginUser.getId());
                             request.setAttribute("success","Password has succefully change,please login");
                             address="login.jsp";
                         }else {
                             request.setAttribute("invalid","Change passsword is invalid");
                             address="/WEB-INF/pages/changePassword.jsp";
                         }
                     }else{
                         request.setAttribute("invalid","New password and confirm password doen't equals");
                         address="/WEB-INF/pages/changePassword.jsp";
                     }
            }
            }

            if (address != null) {
                request.getRequestDispatcher(address).forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


}
