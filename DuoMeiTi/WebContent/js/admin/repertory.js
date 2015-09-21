$(document).on("click", "#rtInsert", function() {
	$("[name=rtDevice]").val("");
	$("[name=rtType]").val("");
	$("[name=rtNumber]").val("");
	$("[name=rtVersion]").val("");
	$("[name=rtFactorynum]").val("");
})

$(document).find("[list=device]").change(function() {
	var selectvalue = $("#rtDevice option:selected").attr("value");
	if (selectvalue == "主要设备") {
		$("[list=mainDevice]").show();
	} else if (selectvalue == "耗材设备") {
		$("[list=costDevice]").show();
	}
	
})

$(document).on("click", "#rtSave", function() {
	if ($("#rtDevice").val() == "" || $("#rtType").val() == "") {
		alert("输入不能为空！");
		return;
	}
	var params = $("#repertory_form").serialize();// 序列化表单值→ Json；
	// ajax方法通过HTTP请求加载远程数据；
	$.ajax({
		url : 'repertory_insert',
		type : 'post',
		dataType : 'json',
		data : params,
		success : repertoryCallback
	});

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
		$(cnt).attr("rt_id", data.rtId);
		$('#rtModal').modal('hide');
		alert("保存成功！ ");

	}
}

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

$(document).find("#rtDevice").change(function() {
	var selectvalue = $("#rtDevice option:selected").attr("value");
	if (selectvalue == "all") {
		$("#main").hide();
		selectDevice();
	} else if (selectvalue == "main") {
		$("#main").show();
		//alert($(document).find("#rtMainDevice").html());
		$(document).find("#rtMainDevice option:first").attr("selected","selected");
	}
	
})
				   
$(document).find("#rtMainDevice").change(function() {
	selectDevice();
})
function selectDevice() {
	var keyword = $("#repertory_search").serialize();
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

/*$(document).find("#repertory_table tr:not").dblclick()*/