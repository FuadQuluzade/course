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
        $('#birthdayTxtId').datepicker({
            changeMonth: true,
            changeYear: true

        });
    })
</script>

<div class="lblDesign">Name: </div> <input type="text" id="nameTxtId" placeholder="Employee name" /> <br>
<div class="lblDesign">Surname: </div> <input type="text" id="surnameTxtId" placeholder="Employee Surname" /> <br>
<div class="lblDesign">Address: </div> <input type="text" id="addressTxtId" placeholder="Address" /> <br>
<div class="lblDesign">Birthday: </div> <input type="text" id="birthdayTxtId" placeholder="Date" /> <br>
<div class="lblDesign">Phone: </div> <input type="text" id="phoneTxtId" placeholder="Phone" /> <br>
