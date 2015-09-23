//insert
$(document).on("click", "#rtInsert", function() {
	$("[name=rtDevice]").val("");
	$("#rtType1").hide();
	$("#rtType2").hide();
	$("[name=rtType]").val("");
	$("[name=rtNumber]").val("");
	$("[name=rtVersion]").val("");
	$("[name=rtFactorynum]").val("");
	$("#rtSave").attr("mark","insert");
})

$(document).find("#rtDevice").change(function() {
	var selectvalue = $("#rtDevice option:selected").attr("value");
	if(selectvalue == ""){
		$("#rtType1").hide();
		$("#rtType2").hide();
	} else if (selectvalue == "主要设备") {
		$("#rtType1").show();
		$("#rtType2").hide();
	} else if (selectvalue == "耗材设备") {
		$("#rtType2").show();
		$("#rtType1").hide();
	}
	
})


//update
var rtId;
//$(document).find("#repertory_table").on("click"," tr:not(:first) .click_me", function() {
$(document).on("click",".click_me", function() {
	rtId = $(this).parent().attr("rt_id");
//	$(this).attr("data-toggle","modal");
//	$(this).attr("data-target","#rtModal");
	$("#rtSave").attr("mark","update");
	
	$(document).find('#rtModal').modal('toggle');
	$.ajax({
		url : 'repertory_fetch',
		type : 'post',
		dataType : 'json',
		data : {"rtId" : rtId,},// {"后台",""}
		success : fetchCallback
	});
})
function fetchCallback(data) {
	//alert(data.status + "," + data.rtSearch_list[0].rtType);
	if(data.status == "0") {
		alert("error");
		return;
	}
	else if(data.status == "1") {
		var temp = data.rtSearch_list[0];
		$(document).find("#rtDevice").val(temp.rtDevice);
		if(temp.rtDevice == "主要设备") {
			$(document).find("#rtType1").val(temp.rtType);
			$("#rtType1").show();
			$("#rtType2").hide();
		}
		else if(temp.rtDevice == "耗材设备") {
			$(document).find("#rtType2").val(temp.rtType);
			$("#rtType1").hide();
			$("#rtType2").show();
		}
		$(document).find("#rtNumber").val(temp.rtNumber);
		$(document).find("#rtVersion").val(temp.rtVersion);
		$(document).find("#rtFactorynum").val(temp.rtFactorynum);
	}
	
}
//insert update
$(document).on("click", "#rtSave", function() {

	var d_type = $("#rtDevice").val();
	var d_name;
	if (d_type == "") {
		alert("输入不能为空！");
		return;
	} 
	else if(d_type == "主要设备") {
		d_name = $("#rtType1").val();				
	}
	else{
		d_name = $("#rtType2").val();
	}
	if(d_name == "") {
		alert("输入不能为空！");
		return;
	}
	
	var params = $("#repertory_form").serialize();// 序列化表单值→ Json；
	//alert(decodeURIComponent(params,true));
	var fd = new FormData();
	var cnt = $("#repertory_form");
 	fd.append("rtDevice", $(cnt).find("#rtDevice").val());
 	fd.append("rtType",d_name);
 	fd.append("rtNumber", $(cnt).find("[name=rtNumber]").val());
 	fd.append("rtVersion", $(cnt).find("[name=rtVersion]").val());
 	fd.append("rtFactorynum", $(cnt).find("[name=rtFactorynum]").val());
	
	if($(this).attr("mark") == "insert"){
		
		
		// ajax方法通过HTTP请求加载远程数据；
	    $.ajax({  
	        url:'/admin/repertory_insert' ,  
	        type: "POST",  
	        data: fd,  
	        async: true,  
	        cache: false,  
	        contentType: false,  
	        processData: false,  
	        success: repertoryCallback,

	   });  
	}
	else if($(this).attr("mark") == "update") {
//		$.ajax({
//			url : 'repertory_update',
//			type : 'post',
//			dataType : 'json',
//			data : keyword,
//			success : updateCallback
//		});
		
		//alert("===="+ rtId);
		fd.append("rtId", rtId);
		$.ajax({  
	        url:'/admin/repertory_update' ,  
	        type: "POST",  
	        data: fd,  
	        async: true,  
	        cache: false,  
	        contentType: false,  
	        processData: false,  
	        success: updateCallback,

	   });  
		
	}

})

function repertoryCallback(data) {
	if (data.status == "1") {
		$("#repertory_table tr:first").after(data.add_repertory_html);

		var cnt = $(document).find("#repertory_table tr:eq(1)");
		$(cnt).children().eq(0).text(data.rtType);
		$(cnt).children().eq(1).text(data.rtNumber);
		$(cnt).children().eq(2).text(data.rtVersion);
		$(cnt).children().eq(3).text(data.rtFactorynum);
		// alert(data.rtId+", "+data.rtType+", "+data.rtNumber);
		//$(cnt).attr("rt_id", data.rtId);
		$('#rtModal').modal('hide');
		alert("保存成功！ ");

	}
}

function updateCallback(data) {
	if(data.status == "1") {
		var rtId = data.rtId;
		$.ajax({
			url : 'repertory_fetch',
			type : 'post',
			dataType : 'json',
			data : {"rtId" : rtId,},// {"后台",""}
			success : fetchupCallback
		});
	}
}

function fetchupCallback(data) {
	if(data.status == "1") {
		alert("@#$%$");
		var row = data.rtSearch_list[0];
		var line = $(document).find("#repertory_table tr[rt_id = " + row.rtId +"]");
		//alert($(line).html());
		$(line).children().eq(0).text(row.rtType);
		$(line).children().eq(1).text(row.rtNumber);
		$(line).children().eq(2).text(row.rtVersion);
		$(line).children().eq(3).text(row.rtFactorynum);
		// alert(data.rtId+", "+data.rtType+", "+data.rtNumber);
		$('#rtModal').modal('hide');
		alert("修改成功！ ");
	}
	
}

//delete
var delete_rtId;
$(document).on("click", ".delete", function() {
	var temp = confirm("删除不可恢复！");
	if (temp == true) {
		delete_rtId = $(this).parents("tr").attr("rt_id");// attr所选元素属性值
		$.ajax({
			url : 'repertory_delete',
			type : 'post',
			dataType : 'json',
			data : {"rtId" : delete_rtId,},// {"后台",""}
			success : deleteCallback
		});
	}
})

function deleteCallback(data) {
	if (data.status == "0") {
		alert("删除数据不存在！ ");
	} else if (data.status == "1") {
		$(document).find("tr[rt_id=" + delete_rtId + "]").remove();
	}
}

//search
$(document).find("#sDevice").change(function() {
	var selectvalue = $("#sDevice option:selected").attr("value");
	if (selectvalue == "") {
		$("#main").hide();
		$("#cost").hide();
		selectDevice();
	} else if (selectvalue == "主要设备") {
		$("#main").show();
		$("#cost").hide();
		$(document).find("#sMainDevice option:first").attr("selected","selected");
		selectDevice();
	} else if(selectvalue == "耗材设备") {
		$("#main").hide();
		$("#cost").show();	
		$(document).find("#sCostDevice option:first").attr("selected","selected");
		selectDevice();
	}
	
})
				   
$(document).find("#sMainDevice").change(function() {
	selectDevice();
})
$(document).find("#sCostDevice").change(function() {
	selectDevice();
})
function selectDevice() {
	var keyword = $("#repertory_search").serialize();
	//alert(decodeURIComponent(keyword,true));
	$.ajax({
		url : 'repertory_search',
		type : 'post',
		dataType : 'json',
		data : keyword,
		success : searchCallback
	});
}
/*$(document).on("click", "#rtSearch", function() {
	// alert($("#rtDevice option:selected").attr("value"));
	var keyword = $("#repertory_search").serialize();
	// alert(keyword);
	$.ajax({
		url : 'repertory_search',
		type : 'post',
		dataType : 'json',
		data : keyword,
		success : searchCallback
	});
})*/

function searchCallback(data) {
	if (data.status == "1") {
		$("#repertory_table tr:not(:first)").remove();
		$(document).find("#noResult").text("");
		// $("#repertory_table tr:first").after(data.add_repertory_html);
		var everylist = data.rtSearch_list;
		$(everylist).each(function(i) {
			$("#repertory_table").append(data.add_repertory_html);
			var row = $(document).find("#repertory_table tr:eq(" + (i + 1) + ")");
			$(row).find("td:eq(0)").text(everylist[i].rtType);
			$(row).find("td:eq(1)").text(everylist[i].rtNumber);
			$(row).find("td:eq(2)").text(everylist[i].rtVersion);
			$(row).find("td:eq(3)").text(everylist[i].rtFactorynum);
			$(row).attr("rt_id", everylist[i].rtId);
		})

	} else if (data.status == "0") {
		$(document).find("#repertory_table tr:not(:first)").remove();
//		$("#repertory_table tr:not(:first)").remove();
		$(document).find("#noResult").text("无查询结果");
	}
}

