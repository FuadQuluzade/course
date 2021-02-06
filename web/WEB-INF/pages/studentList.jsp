<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 21.08.2020
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course</title>
    <jsp:include page="/static/import.jsp"></jsp:include>
    <script type="text/javascript">
        $(function () {
         $('#studentTableId').DataTable({
             'searching':false
         });

        });
    </script>
</head>
<body>
<jsp:include page="/static/header.jsp"></jsp:include>
<jsp:include page="/static/menu.jsp"></jsp:include>
<jsp:include page="/static/footer.jsp"></jsp:include>
<jsp:include page="/static/information.jsp"></jsp:include>
<div class="ui-layout-center">
    <div id="studentDataId">
        <table id="studentTableId" class="display">
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Address</th>
                <th>Date of Birth</th>
                <th>Phone</th>
<c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
                <th>Edit</th>
                <th>Delete</th>
</c:if>

            </tr>
            </thead>
            <tbody>

            <c:forEach items="${studentList}" var="sl">
                <tr>
                    <td>${sl.id}</td>
                    <td>${sl.name}</td>
                    <td>${sl.surname}</td>
                    <td>${sl.address}</td>
                    <td>${sl.dob}</td>
                    <td>${sl.phone}</td>
                    <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
                        <td><a href="javascript: editStudent('${sl.id}');">Edit</a></td>
                        <td><a href="javascript: deleteStudent('${sl.id}', '${sl.name} ${sl.surname}');">Delete</a></td>
                    </c:if>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

</div>

<div id="newStudentDialogId"></div>
<div id="editStudentDialogId"></div>
<script type="text/javascript" src="js/student.js"></script>

</body>
</html>

