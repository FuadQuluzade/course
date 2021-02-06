<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 12.09.2020
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<option value="0">Select Teacher</option>
<c:forEach items="${teacherList}" var="tl">
    <option value="${tl.id}">${tl.name} ${tl.surname}</option>
</c:forEach>

