

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

var search_student;
function searchCallback(data) {
	if(data.isEmpty == "1"){
		search_student = data.student_profile_id;
		$("#student_information_table").html(data.studenttable_jsp);
	}	
	else{
		alert("查找的学生不存在！");
		location.reload();
	}
}


//delete
var delete_Id;
$(document).on("click", ".delete", function() {
	var temp = confirm("删除不可恢复！");
	if (temp == true) {
		delete_Id = $(this).parents("tr").attr("id");// attr所选元素属性值
		/*alert(delete_Id);*/
		if(delete_Id == "search_information"){
			alert(search_student);
			$.ajax({
				url : 'student_manage_delete',
				type : 'post',
				dataType : 'json',
				data : {"rtID" : search_student,},// {"后台",""}
				success : deleteCallback
			});
		}
		else{
			$.ajax({
				url : 'student_manage_delete',
				type : 'post',
				dataType : 'json',
				data : {"rtID" : delete_Id,},// {"后台",""}
				success : deleteCallback
			});
		}
	}
})

function deleteCallback(data) {
	if(data.isException=="1"){
		alert("学生存在关联数据，无法删除！")
	}else{
		alert("删除成功！");
	}
	location.reload() 
}



//edit
var edit_Id;
$(document).on("click", ".edit", function() {

	edit_Id = $(this).parents("tr").attr("id");// attr所选元素属性值
	if(edit_Id == "search_information"){
//		alert("编辑：" + search_student);
		$.ajax({
			url : 'get_student_information',
			type : 'post',
			dataType : 'json',
			data : {"rtID" : search_student,},// {"后台",""}
			success : getInformationCallback
		});
	}
	
	else{
//		alert("id：" + edit_Id);
		$.ajax({
			url : 'get_student_information',
			type : 'post',
			dataType : 'json',
			data : {"rtID" : edit_Id,},// {"后台",""}
			success : getInformationCallback
		});
	}
})



function getInformationCallback(data) {

	/*alert(data.fullName);
	alert("getInformationCallback");*/
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
	var params = $('#edit_student_form').serialize(); //利用jquery将表单序列化 
	/*alert(params);*/
	$.ajax({
        url: 'student_information_save',
        type: 'post',
        dataType: 'json',
        data: params,
        success: studentInformationSaveCallback
      });
	
})

function studentInformationSaveCallback() {
	$('#student_edit').modal('hide');
	location.reload() 
	alert("修改成功！");
	
}
