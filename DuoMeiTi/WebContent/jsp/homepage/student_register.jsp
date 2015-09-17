<%@ include file="/jsp/base/taglib.jsp" %>





<layout:override name="main_content">
<br/>

	<form class="form-horizontal" action="student_register" method="POST" id="student_register_form">
	 
	  <div class="row container-fluid">
		  <div class="form-group">
		    <label for="username" class="col-lg-2 control-label" >用户名</label>
		    <div class="input-group col-lg-6">    
		    <input type="text" class="form-control" name="username" value="<s:property value="username"/>" placeholder="">
		  	</div>
		  </div>
	   	  <div class="form-group">
		  	<label for="studentId" class="col-lg-2 control-label">学&nbsp;&nbsp;&nbsp;&nbsp;号</label>
		  	<div class="input-group col-lg-6">
		  	<input type="text" class="form-control" name="studentId" value="<s:property value="studentId"/>" placeholder="">
		  	</div>
		  </div>
	  
		  <div class="form-group">
		    <label for="password" class="col-lg-2 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
		    <div class="input-group col-lg-6">
		    <input type="password" class="form-control" name="password" value="<s:property value="password"/>" placeholder="">
		  	</div>
		  </div>
		  
		  <div class="form-group">
		    <label for="sex" class="col-lg-2 control-label">性&nbsp;&nbsp;&nbsp;&nbsp;别</label>
		    <div class="input-group col-lg-6">
		    	<s:select list="sexSelect"></s:select>
		  	</div>
		  </div>
		  
		  <div class="form-group">
		  	<label for="profilePhotoPath" class="col-lg-2 control-label">上传头像</label>
		  	<div class="input-group col-lg-6">
		  	
		  	</div>
		  </div>
	  
		  <div class="form-group">
		    <label for="idCard" class="col-lg-2 control-label">身份证号</label>
		    <div class="input-group col-lg-6">
		    <input type="text" class="form-control" name="idCard" value="<s:property value="idCard"/>" placeholder="">
		  	</div>
		  </div>
	  	
	  	
		  <div class="form-group">
		  	<label for="bankCard" class="col-lg-2 control-label">银行卡号</label>
		  	<div class="input-group col-lg-6">
		  	<input type="text" class="form-control" name="bankCard" value="<s:property value="bankCard"/>" placeholder="">
		  	</div>
		  </div>
	  
		  <div class="form-group">
		    <label for="college" class="col-lg-2 control-label">院系信息</label>
		    <div class="input-group col-lg-6">
		    	<s:select list="collegeSelect"></s:select>
		  	</div>
		  </div>
		  
		  <div class="form-group">
		  	<label for="phoneNumber" class="col-lg-2 control-label">联系方式</label>
		  	<div class="input-group col-lg-6">
		  	<input type="text" class="form-control" name="phoneNumber" value="<s:property value="phoneNumber"/>" placeholder="">
		  	</div>
		  </div>
		  
		  <div class="form-group">
		  	<label for="status" class="col-lg-2 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;态</label>
		  	<div class="input-group col-lg-6">
		  		<s:select list="statusSelect"></s:select>
		  	</div>
		  </div>
		  
		  <div class="form-group">
		  	<label for="remark" class="col-lg-2 control-label">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
		  	<div class="input-group col-lg-6">
		  	<input type="text" class="form-group" name="remark" value="<s:property value="remark"/>" placeholder="">
		  	</div>
		  </div>
 		</div>
	 <button type="button" class="btn btn-default register">注册</button>
	</form>	
	
	<br/>
	
	
	<!-- <div class="alert alert-info" role="alert" id="alert_register_info"  style="display:none">
	<br/>
	</div>	 -->
	
	<%-- <table class="table table-bordered" id="user_table">
		<tr class="active">
			<th> Username </th>
			<th> Password </th>
			<th> 删除</th>
		</tr>
		<s:iterator value="user_list" var="i" status="index" >  
			<tr class="success" user_id=<s:property value="#i.id"/> >
				<td>   <s:property value="#i.username"/>    </td>
				<td>   <s:property value="#i.password"/>   </td>
				<td> <button type="button" class="btn btn-danger delete">删除</button> 
				 </td>
			</tr>
		</s:iterator>  
	</table> --%>
	
	
	
	
	

<script>

    $(document).on("click", "button", function (){
        var params = $('#student_register_form').serialize(); //利用jquery将表单序列化

        $.ajax({
          url: 'student_register_save',
          type: 'post',
          dataType: 'json',
          data: params,
          success: studentRegisterCallback
        });

    });
    
    function animatedShow(text)
    {
    	$("#alert_register_info").hide();
		$("#alert_register_info").text(text);
		$("#alert_register_info").show(500);
    }
    function studentRegisterCallback(data)
    {    	
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
</script>

</layout:override>










<%@ include file="/jsp/homepage/base.jsp" %>

