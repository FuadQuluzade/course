<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 7/30/2020
  Time: 4:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
 <jsp:include page="/static/import.jsp"></jsp:include>
</head>
<body>
<div id="container">
    <jsp:include page="/static/header.jsp"></jsp:include>
    <jsp:include page="/static/menu.jsp"></jsp:include>
    <div id="content">
        <form action="/cs?action=register" method="post">
            <div class="lblDesign">Name: </div> <input type="text" name="nameTxt" placeholder="Employee name" required/> <br>
            <div class="lblDesign">Surname: </div> <input type="text" name="surnameTxt" placeholder="Employee Surname" required/> <br>
            <div class="lblDesign">Adress: </div> <input type="text" name="adressTxt" placeholder="Adress" required/> <br>
            <div class="lblDesign">Gender: </div> <input type="radio" name="gender" value="male" /> Male&nbsp;
            <input type="radio" name="gender" value="female" />Female <br>
            <div class="lblDesign">Email: </div> <input type="email" name="emailTxt" placeholder="Email" required/> <br>
            <div class="lblDesign">Password: </div> <input type="password" name="password" placeholder="Password" required/> <br>
            <div class="lblDesign">Birthday: </div> <input type="date" name="birthday" placeholder="Date" required/> <br>
            <div class="lblDesign">Education : </div>
            <select name="combobox">
                <option value="0">Education</option>
                <option value="Orta">Orta</option>
                <option value="Bakalavr">Bakalavr</option>
                <option value="Magistr">Magistr</option>
            </select><br>
            <div class="lblDesign">Language : </div><input type="checkbox" name="langs" value="eng"/>English <br>
            <div class="lblDesign"></div><input type="checkbox" name="langs" value="turk"/>Turkish <br>
            <div class="lblDesign"></div><input type="checkbox" name="langs" value="rus"/>Russian <br>
            <div class="lblDesign"></div><input type="checkbox" name="langs" value="ger"/>German <br>
            <div class="lblDesign">Not : </div>
            <textarea name="Not" cols="20" rows="3">

    </textarea><br>
            <input type="submit" value="Save"/> &nbsp; <input type="reset" value="Clear"/>

        </form>
    </div>
<jsp:include page="/static/footer.jsp"></jsp:include>
</div>

</body>
</html>
