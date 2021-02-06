<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 21.08.2020
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="ui-layout-west">

<%--    <input type="button" value="Student data" id="studentDataBtn" class="btnDesign" onclick="printNumbers(1)"><br>--%>
<%--    <input type="button" value="Teacher data" id="teacherDataBtn" class="btnDesign" onclick="printreversenums(10);"><br>--%>
    <c:choose>
        <c:when test="${login.role.roleName eq 'ROLE_STUDENT'}">
            <a href="cs?action=getTeacherList" ><input type="button" value="Teacher data" class="btnDesign"></a>

        </c:when>

        <c:when test="${login.role.roleName eq 'ROLE_TEACHER'}">
            <a href="cs?action=getStudentList" ><input type="button" value="Student data"  class="btnDesign"></a>

        </c:when>
        <c:otherwise>
            <a href="cs?action=getStudentList" ><input type="button" value="Student data"  class="btnDesign"></a>
            <a href="cs?action=getTeacherList" ><input type="button" value="Teacher data" class="btnDesign"></a>
            <a href="cs?action=getPaymentList" ><input type="button" value="Payment data" class="btnDesign"></a>
        </c:otherwise>

    </c:choose>


</div>
