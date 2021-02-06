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
    <title>Forget password</title>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script type="text/javascript" src="js/login.js"></script>
</head>
<body>

<div class="login-page">
    <div class="form">
        <form  action="ls?action=sendEmail" method="post" class="login-form">
            <input type="email" placeholder="E-mail" name="email"/>
            <c:if test="${not empty success}">
                <label style="color: green ">${success}</label>
            </c:if>
            <c:if test="${not empty invalid}">
                <label style="color: red ">${invalid}</label>
            </c:if>
            <button>Forget</button>
        </form>
    </div>
</div>



</body>
</html>

