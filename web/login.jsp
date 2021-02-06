<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 22.09.2020
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script type="text/javascript" src="js/login.js"></script>
</head>
<body>

<div class="login-page">
    <div class="form">
        <form  class="register-form">
            <input type="text" placeholder="name"/>
            <input type="password" placeholder="password"/>
            <input type="text" placeholder="email address"/>
            <button>create</button>
            <p class="message">Already registered? <a href="#">Sign In</a></p>
        </form>
        <form  action="ls?action=login" method="post" class="login-form">
            <input type="text" placeholder="Username" name="username"/>
            <input type="password" placeholder="Password" name="password"/>
           <c:if test="${not empty invalid}">
               <label style="color: red ">${invalid}</label>
            </c:if>
            <button>Login</button>
            <p class="message"><a href="ls?action=forget">Forget password</a></p>
        </form>
    </div>
</div>

<%--<div style="text-align: center; border: 1px solid" >--%>
<%--    <form action="ls" method="post">--%>
<%--        <div class="lblDesign" >Username : </div><input type="text" name="username" placeholder="Username" required/> <br>--%>
<%--        <div class="lblDesign" >Password : </div><input type="password" name="password" placeholder="Password" required/> <br>--%>
<%--        <c:if test="${not empty invalid}">--%>
<%--           <label style="color: red ">${invalid}</label>--%>
<%--        </c:if>--%>
<%--        <br>--%>
<%--        <input type="submit" value="Log in" /> &nbsp; <input type="reset" value="Clear">--%>

<%--    </form>--%>
<%--</div>--%>

</body>
</html>
