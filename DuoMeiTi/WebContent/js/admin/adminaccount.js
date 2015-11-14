/**
 * 管理员账号管理
 */



//delete
$(document).on("click", ".delete", function() {
	var temp = confirm("删除不可恢复！");
	if (temp == true) {
		var delete_Id = $(this).parents("tr").attr("id");// attr所选元素属性值
			$.ajax({
				url : 'deleteAdminProfile',
				type : 'post',
				dataType : 'json',
				data : {"rmID" : delete_Id},// {"后台",""}
				success : deleteCallback
			});
	}
})

function deleteCallback(data) {
	alert(data.result);
	location.reload();
}

function getInformationCallback(data) {
	/*alert(data.fullName);
	alert("getInformationCallback");*/
	$("#fullName").val(""+data.fullName);
	$("#sex").val(""+data.sex);
	$("#phoneNumber").val(""+data.phoneNumber);
	$("#unitinfo").val(""+data.unitinfo);
	$("#remark").val(""+data.remark);
}

function  checkPassword()
{
	if(document.getElementById("usernameInput")=="")
	{
		alert("用户名不能为空");
		return false;
	}
	if(document.getElementById("passwordInput1")==""){
		alert("不能为空");
		return false;
	}
	if(document.getElementById("passwordInput1").value!=document.getElementById("passwordInput2").value)
	{
		alert("输入密码不一致");
		document.getElementById("passwordInput1").value="";
		document.getElementById("passwordInput2").value="";
		return false;
	}
	return true;
}

//save
$(document).on("click", "#editSave", function() {
	var params = $('#edit_admin_form').serialize(); //利用jquery将表单序列化 
	alert(params);
	$.ajax({
        url: 'adminaccountupdate',
        type: 'post',
        dataType: 'json',
        data: params,
        success: adminaccountCallback
      });
})

$(document).on("click",'#addaccount',function(){
	if(checkPassword())
	{
		var params = $('#add_admin_form').serialize();
		$.ajax({
	        url: 'addNewAdminProfile',
	        type: 'post',
	        dataType: 'json',
	        data: params,
	        success: adminaccountCallback,
	        error:errorfuction
	      });
	}
	else
		{
		alert("ajax error");
		}
})

function errorfuction(){
	alert("error "+arguments[1]);
}

function adminaccountCallback(data) {
	$('#admin_add').modal('hide');
	if(data.result=="success")
	{
		alert(data.result);
	}
	else
		{
			alert(data.result);
		}
	location.reload();
}
