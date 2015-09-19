<%@ include file="/jsp/base/taglib.jsp"%>
<style>
<!--
.STYLE1 {
	font-size: 12px;
	color: #FF0000;
}
-->
</style>




<layout:override name="main_content">
	<br />

	<form class="form-inline" action="student_register" method="POST"
		id="admin_register_form">

		<div class="form-group col-lg-offset-1">
			<label for="fullName">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
			<input type="text" class="form-control" name="fullName"
				id="fullNameId" onblur=checkFullname()
				value="<s:property value="fullName"/>" placeholder="">
		</div>



		<div class="form-group">
			<span style="color: red" id=fullname_msg>*</span>
		</div>



		<div class="form-group col-lg-offset-1">
			<label for="username">用户名</label> <input type="text"
				class="form-control" name="username" id="usernameId"
				onblur=checkUsername() value="<s:property value="username"/>"
				placeholder="">
		</div>

		<div class="form-group">
			<span style="color: red" id=username_msg>*</span>
		</div>
		<br> <br> <br>

		<div class="form-group col-lg-offset-1">
			<label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
			<input type="password" class="form-control" name="password"
				value="<s:property value="password"/>" placeholder="">
		</div>
		<div class="form-group">
			<span style="color: red">*</span>
		</div>
		<div class="form-group col-lg-offset-1">
			<label for="passwordAgain">确认密码</label> <input type="password"
				class="form-control" name="passwordAgain"
				value="<s:property value="passwordAgain"/>" placeholder="">
		</div>
		<div class="form-group">
			<span style="color: red">*</span>
		</div>
		<br> <br> <br>


		<div class="form-group col-lg-offset-1">
			<label for="unitInfo">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
			<input type="password" class="form-control" name="unitInfo"
				value="<s:property value="unitInfo"/>" placeholder="">
		</div>
		<div class="form-group">
			<span style="color: red">*</span>
		</div>
		<div class="form-group col-lg-offset-1">
			<label for="profilePhotoPath">上传头像</label> <input type="password"
				class="form-control" name="profilePhotoPath"
				value="<s:property value="profilePhotoPath"/>" placeholder="">
		</div>
		<div class="form-group">
			<span style="color: red">*</span>
		</div>
		<br> <br> <br>




		<div class="form-group col-lg-offset-1">
			<label for="unitInfo">工作单位</label> <input type="password"
				class="form-control" name="unitInfo"
				value="<s:property value="unitInfo"/>" placeholder="">
		</div>
		<div class="form-group">
			<span style="color: red">*</span>
		</div>

		<div class="form-group col-lg-offset-1">
			<label for="phoneNumber">联系方式</label> <input type="password"
				class="form-control" name="phoneNumber"
				value="<s:property value="phoneNumber"/>" placeholder="">
		</div>
		<div class="form-group">
			<span style="color: red">*</span>
		</div>
		<br> <br> <br>

		<div class="form-group col-lg-offset-1">
			<label for="remark">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
			<input type="password" class="form-control" name="remark"
				value="<s:property value="remark"/>" placeholder="">
		</div>
		<div class="form-group">
			<span style="color: red">*</span>
		</div>


		<br /> <br />
		<button type="button" class="btn btn-default col-lg-offset-5">注&nbsp;&nbsp;&nbsp;&nbsp;册</button>
	</form>

	<br />


	<div class="alert alert-info" role="alert" id="alert_register_info"
		style="display: none">
		<br />
	</div>







	<script>

    $(document).on("click", "button", function (){
        var params = $('#admin_register_form').serialize(); //利用jquery将表单序列化
        $.ajax({
          url: 'admin_register_save',
          type: 'post',
          dataType: 'json',
          data: params,
          success: adminRegisterCallback
        });

    });
    
    function animatedShow(text)
    {
    	//alert(text+"animatedShow");
    	$("#alert_register_info").hide();
		$("#alert_register_info").text(text);
		$("#alert_register_info").show(500);
    }
    function adminRegisterCallback(data)
    {    	
    	//alert(data.register_status);
    	
    	if(data.register_status == "0")
    	{    		
        	/* $("#user_table tr:first").after(data.added_user_html);
        	
        	
        	var cnt = $(document).find("#user_table tr:eq(1)");
        	$(cnt).children().eq(0).text(data.username);
        	$(cnt).children().eq(1).text(data.password);
        	c
//         	alert(data.user_id);
        	
			
        	$(cnt).attr("user_id", data.user_id);
 */
    		alert("yeah");
    	}
    	else if(data.register_status == "1")
    	{
    		animatedShow("注册用户名或者密码为空");
    	}
    	else if(data.register_status == "2")
   		{
    		animatedShow("注册用户名重复");
   		}
    	else 
   		{
    		alert("error with status" + data.status);
   		}

		
    }
    
    //输入验证
    function checkUsername(){
    	var val = $("#usernameId").val(); 
    	 if(val=="")
    		$("#username_msg").text("非空"); 
    		alert(val);
    }
</script>

</layout:override>










<%@ include file="/jsp/homepage/base.jsp"%>

