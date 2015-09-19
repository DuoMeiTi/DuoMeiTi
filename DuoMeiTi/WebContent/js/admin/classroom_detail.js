/**
 * 
 */
Date.prototype.format = function (fmt) {
	var o = {
		"M+": this.getMonth()+1, //月份 
		"d+": this.getDate(), //日 
		"h+": this.getHours(), //小时 
		"m+": this.getMinutes(), //分 
		"s+": this.getSeconds(), //秒 
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
		"S": this.getMilliseconds() //毫秒 
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}


function checkrecord_submit() {
	var checkrecord_detail = $("#checkdetail").val();
//	alert(checkrecord_detail);
	var params = {
		"checkdetail" : checkrecord_detail
	};
	
	$.ajax({
		url : '/admin/classroom_json/checkrecord_save',
		type : 'post',
		dataType : 'json',
		data : params,
		success : checkrecord_save_callback
	});
}

function checkrecord_save_callback(data) {
	if(data.savestatus == "fail") 
		alert("fail!");
	else if(data.savestatus == "success") {
		var login_username = $("#login_user_name").text();
		var checkrecord_detail = $("#checkdetail").val();
		var today = new Date().format("yy-MM-dd");
		var table = $("#checkrecord_table");
		var rowcount = $(table).find("tr").length;
		if(rowcount >= 5) {
			$(table).find("tr:eq(1)").remove();
		}
		$(table).find("tr:first").after("<td width=\"25%\"></td><td></td><td></td>");
		var row = $(table).find("tr:last");
		$(row).find("td:eq(0)").text(login_username);
		$(row).find("td:eq(1)").text(checkrecord_detail);
		$(row).find("td:eq(2)").text(today);
		alert("success!");
	}
	$('#check-record-modal').modal('hide');
}