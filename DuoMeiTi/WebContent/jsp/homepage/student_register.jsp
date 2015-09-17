<%@ include file="/jsp/base/taglib.jsp" %>





<layout:override name="main_content">
<br/>

	<form class="form-horizontal" action="student_register" method="POST" id="student_register_form">
	 
<!-- 	  <div class="row container-fluid"> -->
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">用户名</span>
	  			<input type="text" class="form-control" name="username" value="<s:property value="username"/>" placeholder="">	
	  		</div>
	  	</div>
		  
	   	 <div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span>
	  			<input type="text" class="form-control" name="studentId" value="<s:property value="studentId"/>" placeholder="">	
	  		</div>
	  	 </div>
	  </div>
	   	 
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;码</span>
	  			<input type="password" class="form-control" name="password" value="<s:property value="password"/>" placeholder="">	
	  		</div>
	  	 </div>
	  	 <div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">确认密码</span>
	  			<input type="password" class="form-control" name="passwordAgain" value="<s:property value="passwordAgain"/>" placeholder="">	
	  		</div>
	  	 </div>
	  </div>
	  
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">性&nbsp;&nbsp;&nbsp;&nbsp;别</span>
	  			<s:select list="sexSelect" class="form-control" id='sex'></s:select>
	  		</div>
	  	</div>
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">上传头像</span>
	  			<input type="text" class="form-control">
	  		</div>
	  	</div>
	  </div>
	  
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">身份证号</span>
	  			<input type="text" class="form-control" name="idCard" value="<s:property value="idCard"/>" placeholder="">
	  		</div>
	  	</div>
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">银行卡号</span>
	  			<input type="text" class="form-control" name="bankCard" value="<s:property value="bankCard"/>" placeholder="">
	  		</div>
	  	</div>
	  </div>
	  
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">院系信息</span>
	  			<s:select list="collegeSelect" class="form-control" id="college"></s:select>
	  		</div>
	  	</div>
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">联系方式</span>
	  			<input type="text" class="form-control" name="phoneNumber" value="<s:property value="phoneNumber"/>" placeholder="">
	  		</div>
	  	</div>
	  </div>
	  
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">状&nbsp;&nbsp;&nbsp;&nbsp;态</span>
	  			<select class="form-control">
	  				<option value="0">在岗</option>
	  				<option value="1">离职</option>s
	  			</select>
	  		</div>
	  	</div>
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">负责教室</span>
	  			<input type="text" class="form-control" name="classrooms" value="<s:property value="classrooms"/>" placeholder="">
	  		</div>
	  	</div>
	  </div>
	  
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon" data-date-format="yyyy-mm-dd">入职时间</span>
	  			<input type="date" class="form-control" name="entryTime" value="<s:property value="entryTime"/>" placeholder="">
	  		</div>
	  	</div>
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon" >备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</span>
	  			<input type="text" class="form-control" name="remark" value="<s:property value="remark"/>" placeholder="">
	  		</div>
	  	</div>
	  </div>
	 <br/>
	 <br/> 
	 <button type="button" class="btn btn-default col-lg-offset-5" >注册</button>
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
		var sex=$('#sex option:selected').text();
        var college=$('#college option:selected').text();
        var status=$('#status option:selected').text();
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

