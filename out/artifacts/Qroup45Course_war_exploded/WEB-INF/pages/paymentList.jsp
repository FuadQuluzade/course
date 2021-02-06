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
            $('#paymentTableId').DataTable({
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
    <div id="accordion">
        <h3>Advanced Search</h3>
        <div>
            <select class="cmdDesign" id="advLessonComboId">
                <option value="0">Select Lesson</option>
                <c:forEach items="${lessonList}" var="ll">
                    <option value="${ll.id}">${ll.lessonName}</option>
                </c:forEach>
            </select> &nbsp;
            <select class="cmdDesign" id="advTeacherComboId">
                <option value="0">Select Teacher</option>
                <c:forEach items="${teacherList}" var="tl">
                    <option value="${tl.id}">${tl.name} ${tl.surname}</option>
                </c:forEach>
            </select>
            <br>
            <input type="number" id="advMinAmountId" placeholder="Min Amount"/> &nbsp;
            <input type="number" id="advMaxAmountId" placeholder="Max Amount"/> <br>
            <input type="text" id="advBeginDateId" class="datepicker" placeholder="Begin Date"/> &nbsp;
            <input type="text" id="advEndDateId" class="datepicker" placeholder="End Date"/> &nbsp;
            <input type="button" value="Search" id="advSearchBtnId"/>



        </div>
    <div id="paymentDataId">
        <table id="paymentTableId" class="display">
            <thead>
            <tr>
                <th>#</th>
                <th>Student FullName</th>
                <th>Lesson Name</th>
                <th>Teacher FullName</th>
                <th>Amount</th>
                <th>Payment Date</th>
                <th>Edit</th>
                <th>Delete</th>


            </tr>
            </thead>
            <tbody>

            <c:forEach items="${paymentList}" var="pl">
                <tr>
                    <td>${pl.id}</td>
                    <td>${pl.student.name} ${pl.student.surname}</td>
                    <td>${pl.lesson.lessonName}</td>
                    <td>${pl.teacher.name} ${pl.teacher.surname}</td>
                    <td>${pl.amount}</td>
                    <td>${pl.payDate}</td>
                    <td><a href="javascript: editPayment('${pl.id}'); ">Edit</a></td>
                    <td><a href="javascript: deletePayment('${pl.id}'); ">Delete</a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

</div>
</div>
<div id="newPaymentDialogId"></div>
<div id="editPaymentDialogId"></div>
<script type="text/javascript" src="js/payment.js"></script>

</body>
</html>

