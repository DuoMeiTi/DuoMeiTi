/**
 * 
 */

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