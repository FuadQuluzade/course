<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 27.08.2020
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#teacherTableId').DataTable({
            "searching" : false
        });
    });

</script>
<table id="teacherTableId" class="display">

    <thead>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Date of Birth</th>
        <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${teacherList}" var="tl">
        <tr>
            <td>${tl.id}</td>
            <td>${tl.name}</td>
            <td>${tl.surname}</td>
            <td>${tl.address}</td>
            <td>${tl.phone}</td>
            <td>${tl.dob}</td>
            <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <td><a href="javascript: editTeacher('${tl.id}');">Edit</a></td>
            <td><a href="javascript: deleteTeacher('${tl.id}');">Delete</a></td>
            </c:if>
        </tr>
    </c:forEach>

    </tbody>


</table>
