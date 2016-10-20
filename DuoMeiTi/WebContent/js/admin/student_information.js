


//获取所有在职学生信息
$(document).on("click", "#obtainWorkingStudent", function() {
	$.ajax({
        url: 'student_information_obtainWorkingStudent',
        type: 'post',
        dataType: 'json',
        data : {},
        success: function(data)
        {
        	$("#studentTableDiv").html(data.studenttable_jsp)

        }
        
      });
})

//获取所有离职学生信息
$(document).on("click", "#obtainDepartureStudent", function() {
	
	
	$.ajax({
        url: 'student_information_obtainDepartureStudent',
        type: 'post',
        dataType: 'json',
        data : {},
        success: function(data)
        {
//        	alert("FFF");
        	$("#studentTableDiv").html(data.studenttable_jsp)
//        	studenttable_jsp
        }
        
      });
	

})





//search
var name_id;
$(document).on("click", "#student_search", function() {
	name_id = $("#name_id").val();
	search_select = $("#search_select").val();
/*	alert("检索2:" + name_id + search_select);*/
	var params = {
			"search_select" : search_select,
			"name_id" : name_id,
		};
	
	$.ajax({
        url: 'student_information_search',
        type: 'post',
        dataType: 'json',
        data : params,
        success: searchCallback
      });
})

//var search_student;
function searchCallback(data) 
{
	
	$("#student_information_table").html(data.studenttable_jsp);

}


//delete
var delete_Id;
$(document).on("click", ".delete", function() {
	var temp = confirm("删除不可恢复，而且将会删除与此学生相关联的所有数据！");
	
	if (temp == true) 
	{
		delete_Id = $(this).parents("tr").attr("id");// attr所选元素属性值
		
//		alert(delete_Id);
		$.ajax({
			url : 'student_information_delete',
			type : 'post',
			dataType : 'json',
			data : {"delete_studentDatabaseId" : delete_Id,},// {"后台",""}
			success : deleteCallback
		});

	}
})

function deleteCallback(data) {
	
	alert(data.delete_status);
	location.reload() 
}



//edit
var edit_Id;
$(document).on("click", ".edit", function() {

	edit_Id = $(this).parents("tr").attr("id");// attr所选元素属性值
//	if(edit_Id == "search_information"){
////		alert("编辑：" + search_student);
//		$.ajax({
//			url : 'get_student_information',
//			type : 'post',
//			dataType : 'json',
//			data : {"studentDatabaseId" : search_student,},// {"后台",""}
//			success : getInformationCallback
//		});
//	}
//	
//	else
	{
//		alert("id：" + edit_Id);
		$.ajax({
			url : 'student_information_obtain',
			type : 'post',
			dataType : 'json',
			data : {"studentDatabaseId" : edit_Id,},// {"后台",""}
			success : getInformationCallback
		});
	}
})



function getInformationCallback(data) {

	/*alert(data.fullName);
	alert("getInformationCallback");*/
	$("#username").val(""+data.username);
	$("#password").val(""+data.password);
	
	$("#fullName").val(""+data.fullName);
	$("#sex").val(""+data.sex);
	$("#studentId").val(""+data.studentId);
	$("#college").val(""+data.college);
	$("#phoneNumber").val(""+data.phoneNumber);
	$("#isUpgradePrivilege").val(""+data.isUpgradePrivilege);
	$("#bankCard").val(""+data.bankCard);
	$("#idCard").val(""+data.idCard);
}




//save
$(document).on("click", "#editSave", function() {
	/*alert("save");*/
	var params = $('#edit_student_form').serializeArray(); //利用jquery将表单序列化

	var data = {"studentDatabaseId" : edit_Id };
	
	
	for(var i = 0; i < params.length; ++ i)
	{
		var cnt = params[i];
		data[cnt.name] = cnt.value || '';
	}

//	data = $.extend({}, data, {"studentDatabaseId" : edit_Id })

//	alert(JSON.stringify(data));
//	return ;
	/*alert(params);*/
	$.ajax({
        url: 'student_information_save',
        type: 'post',
        dataType: 'json',
        data: data,
        success: studentInformationSaveCallback
      });
	
})

function studentInformationSaveCallback(data) {
	$('#student_edit').modal('hide');
	alert(data.isRepeat);
	
//	if(data.isRepeat=="1"){
//		alert("修改失败，学号已存在！");
//	}else{
//		alert("修改成功！");
//	}
	location.reload() 
		
}



$(document).on("click", ".watchScore", function() {
	$('#watchScoreModal').modal('show');
	var studentDatabaseId = $(this).parents("tr").attr("id");// attr所选元素属性值
	data = {"studentDatabaseId": studentDatabaseId}
	$.ajax({
        url: 'student_information_watchScore',
        type: 'post',
        dataType: 'json',
        data: data,
        success: function(data)
        {
        	$("#watchScoreTableDiv").html(data.studentScoreJsp);
        }
	
	
      });
})
	
	
	
	

