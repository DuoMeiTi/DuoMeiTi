
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
var root;
$(document).on("click", ".search", function() {
	
	root = getRoot(this);
	
	
//	alert($(root).find(".searchType").val());
	
	var build_id = urlParam['build_id'];
	var build_name = urlParam['build_name'];
	var params = {
			"searchType" : $(root).find(".searchType").val(),
			"searchParam" : $(root).find(".searchParam").val(),
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
	
})

function classroomSearchCallback(data){
	$(root).find('.classroomTableDiv').html(data.classroomHtml);
	
}


