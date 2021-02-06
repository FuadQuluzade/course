var  globTeacherId=0;
$(function () {
    $('#newTeacherDialogId').dialog({
        autoOpen: false,
        title: 'New Teacher',
        height: 400,
        width: 400,
        buttons: {
            'Save': function () {
                addTeacher();
                $(this).dialog('close');
            },
            'Close': function () {
                $(this).dialog('close');
            }
        }

    });



    $('#editTeacherDialogId').dialog({
        autoOpen: false,
        title: 'Edit Teacher',
        height: 400,
        width: 400,
        buttons: {
            'Update': function () {
                updateTeacher();
                $(this).dialog('close');
            },
            'Close': function () {
                $(this).dialog('close');
            }
        }

    });

    $('#newBtnId').click(function () {
        $('#newTeacherDialogId').load('views/newTeacher.jsp', function () {
            $(this).dialog('open');
        });
    });

    });

function addTeacher() {
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
        url :'cs?action=addTeacher',
        type: "POST",
        data: arr,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Teacher has been successfully added");
                getTeacherList();
            } else {
                alert("Problem!Teacher has not been succesfully added");
            }
        }
    });


}
function getTeacherList() {
    $.ajax({
        url : 'cs?action=getTeacherData',
        type: 'GET',
        dataType: 'html',
        success : function (response) {
            $('.ui-layout-center').html(response);

        }
    });

}



function editTeacher(teacherId) {
    globTeacherId=teacherId;
    $.ajax({
        url : 'cs?action=editTeacher',
        type: 'GET',
        data : 'teacherId='+teacherId,
        dataType : 'html',
        success: function (response) {
            $('#editTeacherDialogId').html(response);
            $('#editTeacherDialogId').dialog('open');
        }
    });
}
function updateTeacher() {
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
        teacherId : globTeacherId

    };
    $.ajax({
        url :'cs?action=updateTeacher',
        type: "POST",
        data: arr,
        dataType: 'text',
        success: function (response) {
            if (response == 'success') {
                alert("Teacher has been successfully update");
                getTeacherList();
            } else {
                alert("Problem!Teacher has not been succesfully update");
            }
        }
    });

}

function deleteTeacher(teacherId,fullname) {
    var isDelete = confirm("are you sure to delete " + fullname);
    if (isDelete) {

        $.ajax({
            url: 'cs?action=deleteTeacher',
            type: 'POST',
            data: 'teacherId=' + teacherId,
            dataType: 'text',
            success: function (response) {
                if (response == 'success') {
                    alert("Teacher has been successfully deleted");
                    getTeacherList();
                } else {
                    alert("Problem!Teacher has not been succesfully deleted");
                }

            }

        });
    }
}