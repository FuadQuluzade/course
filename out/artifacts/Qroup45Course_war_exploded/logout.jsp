<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 22.09.2020
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
session.removeAttribute("login");
session.invalidate();
response.sendRedirect("login.jsp");
%>
