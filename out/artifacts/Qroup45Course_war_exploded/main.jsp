<%@ page import="az.orient.cource.model.LoginUser" %><%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 8/18/2020
  Time: 11:44 AM
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
            $('#newBtnId').hide();
            $('#keywordId').hide();
            $('#searchBtnId').hide();

        })

    </script>
</head>
<body>

<%
    LoginUser loginUser = (LoginUser) request.getSession(false).getAttribute("login");
    if(loginUser == null){
        response.sendRedirect("login.jsp");
}
%>

<%--<%--%>
<%--    LoginUser loginUser2 = (LoginUser) request.getSession(false).getAttribute("login");--%>
<%--    if(loginUser2 != null){--%>
<%--        response.sendRedirect("index.jsp");--%>
<%--    }--%>
<%--%>--%>
<jsp:include page="/static/header.jsp"></jsp:include>
<jsp:include page="/static/menu.jsp"></jsp:include>
<jsp:include page="/static/footer.jsp"></jsp:include>
<jsp:include page="/static/information.jsp"></jsp:include>
<div class="ui-layout-center">
</div>



</body>
</html>
