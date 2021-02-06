<%--
  Created by IntelliJ IDEA.
  User: Fuad
  Date: 27.08.2020
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#birthdayTxtIdU').datepicker({
            changeMonth: true,
            changeYear: true

        });
    })
</script>

<div class="lblDesign">Name: </div> <input type="text" id="nameTxtIdU" value="${teacher.name}" /> <br>
<div class="lblDesign">Surname: </div> <input type="text" id="surnameTxtIdU" value="${teacher.surname}" /> <br>
<div class="lblDesign">Address: </div> <input type="text" id="addressTxtIdU" value="${teacher.address}" /> <br>
<div class="lblDesign">Birthday: </div> <input type="text" id="birthdayTxtIdU" value="${teacher.dob}" /> <br>
<div class="lblDesign">Phone: </div> <input type="text" id="phoneTxtIdU" value="${teacher.phone}" /> <br>
