
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
	$(root).find('.classroomTableDiv').html(data.classroomHtml);
	
}



$(document).on("click", ".add", function (){
	root = getRoot(this);
//	student_id
	$("#classroom_modal").modal('show');

})

$(document).on("click", "#queryStu", function (){
	
	var input_principal_student_id = $("#input_principal_student_id").val();
	if(input_principal_student_id == "" || !/^\d+$/.test(input_principal_student_id)) {
		$("#input_principal_student_name").text("请输入数字!");
		return;
	}
	
	$.ajax({
		url : 'classroomManageNew_queryStu',
		type : 'post',
		dataType : 'json',
		data : {
			"studentID" : input_principal_student_id
		},
		success : queryStuNameCallback
	});

})


function queryStuNameCallback(data) {
	if(data.classroomHtml==null || data.classroomHtml == "") {
		$("#input_principal_student_name").text("无此学号!");
		$("#add_classroom_btn").attr("disabled", "disabled");
	}
	else {
		$("#input_principal_student_name").text(data.classroomHtml);
		$("#add_classroom_btn").removeAttr("disabled");
	}
}

function clearModal() {
	$("#input_principal_student_id").val("");
	$("#input_principal_student_name").text("");
	$("#input_classroom_num").val("");
}


$(document).on("click", ".update", function (){
	clearModal();
	$(".modal-title").text("编辑教室");
	$('#classroom_modal').modal('show');
	var submit_type = "update";
	
})

$(document).on("click", ".add", function (){
	clearModal()
	$(".modal-title").text("添加教室");
	var tr = $(this).parents("[student_id]").attr(student_id)
})
$(document).on("click", "#addClassroom", function (){
	
	
	
	
	var submit_type = "add";
	alert(submit_type);
	var stuId = $("#input_principal_student_id").val();
	var classroom_num = $("#input_classroom_num").val();
		

	$.ajax({
		url : 'classroomManageNew_addClassroom',
		type : 'post',
		dataType : 'json',
		data : {
			"studentID" : stuId,
			"add_classroom_num" : classroom_num,
			"build_id" : build_id,
			"submit_type" : submit_type
		},
		success : addClassroomCallback
	});
})


function addClassroomCallback(data) {
	if(data.classroomHtml == "exist") {
		$("#exist").text("教室号已存在");
	}
	else if(data.classroomHtml == "ok") {
		$('#classroom_modal').modal('hide');
		window.location.href=window.location.href;  
		window.location.reload;
	}
}






