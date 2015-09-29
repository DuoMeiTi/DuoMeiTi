function psw_change_ensure(){
	var oldPsw = $("#oldPsw").val();
//	alert(oldPsw);
	var newPsw = $("#newPsw").val();
	var rePsw = $("#rePsw").val();
	
	$.ajax({
		url : '/admin/modify_password',
		type : 'post',
		dataType : 'json',
		data : params,
		success : checkOldPsw_callback
	});
}