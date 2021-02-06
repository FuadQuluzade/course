<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="az.orient.cource.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="az.orient.cource.model.Teacher" %><%--
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
            $('#teacherTableId').DataTable({
                "searching" : false
            });


        });
    </script>
</head>
<body>
<%List<Teacher> teacherList= (List<Teacher>) request.getAttribute("teacherList");%>
<jsp:include page="/static/header.jsp"></jsp:include>
<jsp:include page="/static/menu.jsp"></jsp:include>
<jsp:include page="/static/footer.jsp"></jsp:include>
<jsp:include page="/static/information.jsp"></jsp:include>
<div class="ui-layout-center">
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
           <td><a href="javascript: deleteTeacher('${tl.id}', '${tl.name} ${tl.surname}');">Delete</a></td>
           </c:if>
       </tr>
   </c:forEach>
      </tbody>

    </table>
</div>
<div id="newTeacherDialogId">

</div>
<div id="editTeacherDialogId">

</div>
<script type="text/javascript" src="js/teacher.js"></script>
</body>
</html>

