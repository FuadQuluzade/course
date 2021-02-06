var globStudentId=0;

$(function () {

    $('#newStudentDialogId').dialog({
        autoOpen: false,
        title: 'New Student',
        height: 400,
        width: 400,
        buttons: {
            'Save': function () {
                addStudent();
                $(this).dialog('close');
            },
            'Close': function () {
                $(this).dialog('close');
            }
        }

    });
    $('#editStudentDialogId').dialog({
        autoOpen: false,
        title: 'Edit Student',
        height: 400,
        width: 400,
        buttons: {
            'Update': function () {
                updateStudent();
                $(this).dialog('close');
            },
            'Close': function () {
                $(this).dialog('close');
            }
        }

    });



    $('#newBtnId').click(function () {
        $('#newStudentDialogId').load('views/newStudent.jsp', function () {
            $(this).dialog('open');
        });

    });
    $('#searchBtnId').click(function () {
        searchStudentData();
    });


});

function addStudent() {
    var name = $('#nameTxtId').val();
    var surname = $('#surnameTxtId').val();
    var address = $('#addressTxtId').val();
    var phone = $('#phoneTxtId').val();
    var dob = $('#birthdayTxtId').val();

    var arr = {
        name: name,
        surname: surname,
        address: address,
        phone: phone,
        dob: dob
    };
    $.ajax({
        url :'cs?action=addStudent',
        type: "POST",
        data: arr,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Student has been successfully added");
                getStudentList();
            } else {
                alert("Problem!Student has not been succesfully added");
            }
        }
    });


}

function getStudentList() {
    $.ajax({
        url : 'cs?action=getStudentData',
        type: 'GET',
        dataType: 'html',
        success : function (response) {
            $('.ui-layout-center').html(response);

        }
    })

}





function editStudent(studentId) {
    globStudentId=studentId;
    $.ajax({
        url : 'cs?action=editStudent',
        type: 'GET',
        data : 'studentId='+studentId,
        dataType : 'html',
        success: function (response) {
            $('#editStudentDialogId').html(response);
            $('#editStudentDialogId').dialog('open');
        }
    });
}

function updateStudent() {
    var name = $('#nameTxtIdU').val();
    var surname = $('#surnameTxtIdU').val();
    var address = $('#addressTxtIdU').val();
    var phone = $('#phoneTxtIdU').val();
    var dob = $('#birthdayTxtIdU').val();

    var arr = {
        name: name,
        surname: surname,
        address: address,
        phone: phone,
        dob: dob,
        studentId : globStudentId

    };
    $.ajax({
        url :'cs?action=updateStudent',
        type: "POST",
        data: arr,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Student has been successfully update");
                getStudentList();
            } else {
                alert("Problem!Student has not been succesfully update");
            }
        }
    });

}

function deleteStudent(studentId,fullname) {
    var isDelete = confirm("are you sure to delete " + fullname);
    if (isDelete) {

        $.ajax({
            url: 'cs?action=deleteStudent',
            type: 'POST',
            data: 'studentId=' + studentId,
            dataType: 'text',
            success: function (response) {
                if (response == 'success') {
                    alert("Student has been successfully deleted");
                    getStudentList();
                } else {
                    alert("Problem!Student has not been succesfully deleted");
                }

            }

        });
    }
}

function searchStudentData() {
    var keyword=$('#keywordId').val();
    $.ajax({
       url:"cs?action=searchStudentData",
        type:'GET',
        data:'keyword='+keyword,
        dataType:'html',
        success: function (response) {
          $('#studentDataId').html(response);
        },
        error: function () {
          alert('error')
        }
    });

}