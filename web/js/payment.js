var globPaymentId=0;

$(function () {
    $( "#accordion" ).accordion({
        collapsible: true
    });

    $('.datepicker').datepicker({
        changeMonth: true,
        changeYear: true
    });

    $('#newPaymentDialogId').dialog({
        title: "New Payment",
        autoOpen: false,
        height: 400,
        weight: 400,
        modal: true,
        buttons: {
            "Save": function () {
                addPayment();
                $(this).dialog('close');
            },
            "Clear": function () {
                $(this).dialog('close');

            }
        }
    });
    $('#editPaymentDialogId').dialog({
        title: "Update Payment",
        autoOpen: false,
        height: 400,
        weight: 400,
        modal: true,
        buttons: {
            "Update": function () {
                updatePayment();
                $(this).dialog('close');
            },
            "Clear": function () {
                $(this).dialog('close');

            }
        }
    });

    $('#newBtnId').click(function () {
        $('#newPaymentDialogId').load('cs?action=newPayment', function () {
            $(this).dialog('open');
        });
    });

    $('#advLesssonComboId').change(function () {
          getTeacherComboListByLessonId($(this).val());
    });
    $('#advSearchBtnId').click(function () {
          advancedSearchPayment();
    });

});

function addPayment() {
    var studentId = $('#studentComboId').val();
    var teacherId = $('#teacherComboId').val();
    var lessonId = $('#lessonComboId').val();
    var amount = $('#amountId').val();

    var data = {
        studentId: studentId,
        teacherId: teacherId,
        lessonId: lessonId,
        amount: amount
    };
    $.ajax({
        url: 'cs?action=addPayment',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response) {
                alert('payment has been succesfully created');
                getPaymentList();
            } else {
                alert('Problem!payment has not been succesfully created')
            }
        }
    });
}

function getPaymentList() {
     $.ajax({
      url: 'cs?action=getPaymentData',
         type: 'GET',
         dataType: 'text',
         success: function (response) {
             $('#paymentDataId').html(response);
         }
     });
}

function editPayment(paymentId) {
    globPaymentId=paymentId;
 $.ajax({
     url:'cs?action=editPayment',
     type:'GET',
     data:'paymentId='+paymentId,
     dataType:'html',
     success:function (response) {
         $('#editPaymentDialogId').html(response);
         $('#editPaymentDialogId').dialog('open');

     }
 });
}


function   updatePayment() {
    var studentId = $('#studentComboIdU').val();
    var teacherId = $('#teacherComboIdU').val();
    var lessonId = $('#lessonComboIdU').val();
    var amount = $('#amountIdU').val();

    var data = {
        studentId: studentId,
        teacherId: teacherId,
        lessonId: lessonId,
        amount: amount,
        paymentId:globPaymentId
    };
    $.ajax({
        url: 'cs?action=updatePayment',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (response) {
            if (response) {
                alert('payment has been succesfully update');
                getPaymentList();
            } else {
                alert('Problem!payment has not been succesfully update')
            }
        }
    });
}

function deletePayment(paymentId) {
   $.ajax({
       url:'cs?action=deletePayment',
       type: 'POST',
       data: 'paymentId=' + paymentId,
       dataType: 'text',
       success: function (response) {
           if (response == 'success') {
               alert("Payment has been successfully deleted");
               getPaymentList();
           } else {
               alert("Problem!Payment has not been succesfully deleted");
           }

       }
   });
}

function getTeacherComboListByLessonId(lessonId){
    $.ajax({
       url :'cs?action=getTeacherComboListByLessonId' ,
        type:'GET',
        data:'lessonId='+lessonId,
        dataType:'html',
        success: function (response) {
           $('#advTeacherComboId').html(response);

        }
    });

}

function  advancedSearchPayment() {
   var lessonId=  $('#advLessonComboId').val();
   var teacherId= $('#advTeacherComboId').val();
   var minAmount= $('#advMinAmountId').val();
   var maxAmount= $('#advMaxAmountId').val();
   var beginDate= $('#advBeginDateId').val();
   var endDate=   $('#advEndDateId').val();


   var data={
       lessonId:lessonId,
       teacherId:teacherId,
       minAmount:minAmount,
       maxAmount:maxAmount,
       beginDate:beginDate,
       endDate:endDate
   };
   $.ajax({
    url:'cs?action=advancedSearchPayment',
       type:'GET',
       data: data,
       dataType:'html',
       success:function (response) {
        $('#paymentDataId').html(response);

       }
   });

}