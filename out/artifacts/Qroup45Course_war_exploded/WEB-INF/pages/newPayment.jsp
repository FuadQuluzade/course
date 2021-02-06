<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 06.09.2020
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="lblDesign">Student :</div>
<select class="cmdDesign" id="studentComboId">
    <option value="0" selected>Select Student</option>
    <c:forEach items="${studentList}" var="sl">
        <option value="${sl.id}" >${sl.name} ${sl.surname}</option>
    </c:forEach>
</select>
<br>
<div class="lblDesign">Teacher :</div>
<select  class="cmdDesign" id="teacherComboId">
    <option value="0" selected>Select Teacher</option>
    <c:forEach items="${teacherList}" var="tl">
        <option value="${tl.id}" >${tl.name} ${tl.surname}</option>
    </c:forEach>
</select>
<br>
<div class="lblDesign">Lesson :</div>
<select class="cmdDesign" id="lessonComboId">
 <br>
    <option value="0" selected>Select Lesson</option>
    <c:forEach items="${lessonList}" var="ll">
        <option value="${ll.id}" >${ll.lessonName}</option>
    </c:forEach>
</select>
<br>
<div class="lblDesign">Amount :</div>
<input type="number" id="amountId" placeholder="Amount">