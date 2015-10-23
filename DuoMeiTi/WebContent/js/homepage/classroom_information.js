/**
 * 
 */
function GetRequest() {
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

//jquery发送ajax请求
function queryclassrooms() {
	//var params = $('#classroom_search_form').serialize(); //利用jquery将表单序列化
	//params = decodeURIComponent(params,true);
	/*$("#pagediv").css("display","none");*/
	var searchselect = $("#searchselect  option:selected").attr("value");
	var query_condition = $("#query_condition").val();
	var Request = new Object();
	//获取url中的参数  
	Request = GetRequest();
	var build_id = Request['build_id'];
	var build_name = Request['build_name'];
	var params = {
		"searchselect" : searchselect,
		"query_condition" : query_condition,
		"build_id" : build_id,
		"build_name" : build_name
	};
	
	$.ajax({
		url : '/homepage/classroom_json/classroom_search',
		type : 'post',
		dataType : 'json',
		data : params,
		success : ClassroomSearchCallback
	});
}

function ClassroomSearchCallback(data) {
	if (data.status == "0") {
		$("#classroom_table tr:not(:first)").remove();
		$("#classroom_table tr:first").after(data.classroominfo_html);
		var classrooms = data.classrooms;
		var table = $("#classroom_search_table");
		var row;
		$(classrooms).each(function(i) {
			row = $(table).find("tr:eq(" + (i + 1) + ")");
			$(row).find("td:eq(0)").text(classrooms[i].classroom_num);
			$(row).find("td:eq(1)").text(classrooms[i].repertorys);
//			$(row).find("td:eq(2)").text(classrooms[i].capacity);
//			$(row).find("td:eq(2)").text(classrooms[i].principal_name);
//			$(row).find("td:eq(3)").children().eq(0).attr("onclick", "edit_classroom("+ i +")");
			$(row).find("td:eq(4)").children().eq(0).attr("href", "classroom_detail?classroom_id=" + classrooms[i].id +"&classroom_num=" +classrooms[i].classroom_num);
		});
	} else if (data.status == "1") {
		animatedShow("查询关键字为空");
	} 
}

function queryAll() {
	$("#pagediv").css("display","block");
	$("#query_condition").val("");
	//获取url中的参数  
	Request = GetRequest();
	var build_id = Request['build_id'];
	var build_name = Request['build_name'];
	var params = {
		"searchselect" : "",
		"query_condition" : "",
		"build_id" : build_id,
		"build_name" : build_name
	};
	
	$.ajax({
		url : '/homepage/classroom_json/classroom_search',
		type : 'post',
		dataType : 'json',
		data : params,
		success : ClassroomSearchCallback
	});
}


/* function mypost(count) {
	var xmlobj; //定义XMLHttpRequest对象 
	//如果当前浏览器支持Active Xobject，则创建ActiveXObject对象  
	if (window.ActiveXObject) {
		//xmlobj = new ActiveXObject("Microsoft.XMLHTTP");  
		try {
			xmlobj = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlobj = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (E) {
				xmlobj = false;
			}
		}
	}
	//如果当前浏览器支持XMLHttp Request，则创建XMLHttpRequest对象  
	else if (window.XMLHttpRequest) {
		xmlobj = new XMLHttpRequest();
	}
	
	var pageSize = $("#pageSize").text();
	Request = GetRequest();
	var currPage = Request['currPage'];
	if(currPage == null) currPage = 1;
	var index = (currPage - 1) * pageSize + count;
	//return index;
	// alert(index);
	var build_name = $("#build_name").text();
	var param = "classroomselectIndex=" + index + "&build_name=" + build_name;
	
	xmlobj.open("POST", "/admin/classroom/classroom_detail", true); //调用classroom_detail.action     
	xmlobj.setRequestHeader("cache-control", "no-cache");
	xmlobj.setRequestHeader("contentType", "text/html;charset=uft-8"); //指定发送的编码  
	xmlobj.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded;"); //设置请求头信息  

	xmlobj.send(param); //设置为发送给服务器数据 
	window.location.href = "/admin/classroom/classroom_detail?classroomselectIndex=" + index + "&build_name=" + build_name;
} */