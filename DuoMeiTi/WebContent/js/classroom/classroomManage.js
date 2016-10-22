

$(document).on("click", ".delete", function(){
	deleteID = $(this).parents("[classroomId]").attr("classroomId");
	/*alert("delete:" + deleteID);*/
	alert("删除后教室内设备将变为备用状态！");
	
	$.ajax({
		url : 'classroomManageNew_classroomDelete',
		type : 'post',
		dataType : 'json',
		data : {
			"deleteID" : deleteID
		},
		success : classroomDeleteCallback
		
	})
})

function classroomDeleteCallback(data){
/*	alert("callBack:" + data.status);*/
	location.reload(); 
	/*alert("删除成功！");*/
}

function getParam() {
	//url例子：XXX.aspx?ID=" + ID + "&Name=" + Name；  
	var url = location.search; //获取url中"?"符以及其后的字串  
	var theRequest = new Object();
	if (url.indexOf("?") != -1)//url中存在问号，也就说有参数。  
	{
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}


function getRoot(cnt) {
	return $(cnt).parents(".classroomManage");
	
}
var urlParam = getParam();
var build_id = urlParam['build_id'];
var build_name = urlParam['build_name'];
var searchType, searchParam;
var root;

$(document).on("click", ".all", function (){
	root = getRoot(this);
	searchType = "all";
	classroomSearch()
})
	

$(document).on("click", ".search",  function (){
	root = getRoot(this);
	searchType = $(root).find(".searchType").val()
	searchParam = $(root).find(".searchParam").val();
	classroomSearch()
})

function  classroomSearch() {
	
	
	var params = {
			"searchType" : searchType,
			"searchParam" :searchParam,
			"build_id" : build_id,
			"build_name" : build_name
		};
		
	$.ajax({
		url : 'classroomManageNew_search',
		type : 'post',
		dataType : 'json',
		data : params,
		success : classroomSearchCallback
	});
	
}

function classroomSearchCallback(data){
//	alert(data.classroomHtml);
	$("#classroom_table").html(data.classroomHtml);
	
}



$(document).on("click", ".add", function (){
	root = getRoot(this);
//	student_id
	$("#classroom_modal").modal('show');

})

$(document).on("click", "#queryStu", function (){
	
	var input_principal_student_number = $("#input_principal_student_number").val();
	if(input_principal_student_number == "" || !/^\d+$/.test(input_principal_student_number)) {
		$("#input_principal_student_name").text("请输入数字!");
		return;
	}
	
	$.ajax({
		url : 'classroomManageNew_queryStu',
		type : 'post',
		dataType : 'json',
		data : {
			"studentNumber" : input_principal_student_number
		},
		success : queryStuNameCallback
	});

})


function queryStuNameCallback(data) {
	if(data.status==null || data.status == "") {
		$("#input_principal_student_full_name").text("无此学号!");
		$("#addClassroom").attr("disabled", "disabled");
	}
	else {
		$("#input_principal_student_full_name").text(data.status);
		$("#addClassroom").removeAttr("disabled");
	}
}

function clearModal() {
	$("#input_principal_student_number").val("");
	$("#input_principal_student_full_name").text("");
	$("#input_classroom_num").val("");
}

var submit_type;
var classroomId;
$(document).on("click", ".update", function (){
	clearModal();
	$(".modal-title").text("编辑教室");	
	submit_type = "update";
	classroomId = $(this).parents("[classroomId]").attr('classroomId');
	//alert(classroomId);
	$("#input_principal_student_number").val($(this).parents("[studentNumber]").attr('studentNumber'));
	$("#input_principal_student_full_name").text($(this).parents("[studentFullName]").attr('studentFullName'))
	$("#input_classroom_num").val($(this).parents("[classroomNum]").attr('classroomNum'));
	
	$("#exist").text("");
	$("#addClassroomNotice").hide();
	$('#classroom_modal').modal('show');
})

$(document).on("click", ".add", function (){
	clearModal();
	$(".modal-title").text("添加教室");	
	
	submit_type = "add";
	classroomId = "";
	
	$("#exist").text("");	
	$("#input_principal_student_number").val("")
	$("#input_principal_student_full_name").text("")
	$("#input_classroom_num").val("");
	$("#addClassroomNotice").show();
	$('#classroom_modal').modal('show');
	
})

$(document).on("click", "#addClassroom", function (){
	
	
	
	
	
//	alert(submit_type);
	
	studentNumber = $("#input_principal_student_number").val();
	classroom_num = $("#input_classroom_num").val();
	
	if(classroom_num=="")
	{
		$("#exist").text("教室号为空，添加失败");
		return ;
	}
	
//	alert(classroom_num)
//	studentId = 

	$.ajax({
		url : 'classroomManageNew_addClassroom',
		type : 'post',
		dataType : 'json',
		data : {
			"studentNumber" : studentNumber,
			"add_classroom_num" : classroom_num,
			"build_id" : build_id,
			"submit_type" : submit_type,
			"classroomId":classroomId
		},
		success : addClassroomCallback
	});
})


function addClassroomCallback(data) {
	
	var status = data.addClassroom_status;
	
	if(status != "")
	{
		alert(status);
	}
	else 
	{
		window.location.reload(); 
	}
	
	
//	if(data.status == "exist") {
//		$("#exist").text("教室号已存在");
//	}
//	else if(data.status == "ok") {
//		
////		alert("GGGG");
//		$('#classroom_modal').modal('hide');
//		$(".classroomTableDiv").html(data.classroomHtml);
//	}
//	else if(data.status == "no_principal")
//	{
//		$("#exist").text("负责人学号不正确，添加失败");
//	}
//	else
//	{
//		alert("发生严重错误，不知名原因");
//	}
	
	
}






