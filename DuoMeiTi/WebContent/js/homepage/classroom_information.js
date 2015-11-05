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

	var searchselect = $("#searchselect  option:selected").attr("value");
	var query_condition = $("#query_condition").val();
	
	if(query_condition.length == 0){
		alert("输入不能为空!!!");
	}
	else{
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
				url : '/homepage/classroom_json/classroom_search2',
				type : 'post',
				dataType : 'json',
				data : params,
				success : ClassroomSearchCallback
			});
	}
}

var search_id
function ClassroomSearchCallback(data) {
	if (data.status == "0") {
		var classrooms = data.classrooms;
		search_id = data.search_classroom_id;
		if(classrooms.length == 0){
			alert("未找到对应教室");
		}

		else{
			var tb = document.getElementById('classroom_table');
			var rowNum=tb.rows.length;
			for (i=2; i<rowNum; i++)
		    {
		        tb.deleteRow(i);
		        rowNum=rowNum-1;
		        i=i-1;
		    }
			document.getElementById('search_classroom').style.display='';
			search_class_num.innerText = classrooms[0].classroom_num; 
			search_principal_name.innerText = classrooms[0].principal_name;
		}
	} else if (data.status == "1") {
		animatedShow("查询关键字为空");
	} 
}

$(document).on("click", "#detail-button", function() {
	location.href = 'classroom_detail?classroomId='+ search_id;	
})



function queryAll() {
	location.reload();
	
	/*$("#pagediv").css("display","block");
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
	});*/
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