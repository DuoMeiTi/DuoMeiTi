<%@ include file="/jsp/base/taglib.jsp" %>





<layout:override name="main_content">
<br/>
<form class="form-inline" action="student_register" method="POST" id="student_register_form">
	  <div class="form-group col-lg-offset-1">
	  	<label for="username">用户名</label>
	  	<input type="text" class="form-control" name="username" value="<s:property value="username"/>" placeholder="">	
	  </div>
	  <div class="form-group">
	  	<span style="color:red">*</span>
	  </div>
	  <div class="form-group col-lg-offset-1">
	  	<label for="college">院系信息</label>
	  	<s:select list="collegeSelect" class="form-control" name="college"></s:select>
	  </div>
	  <div class="form-group">
	  	<span style="color:red">*</span>
	  </div>
	  
	  <br>
	  <br>
	  <br>
	  
	  <div class="form-group col-lg-offset-1">
	  	<label for="fullName">姓&nbsp;&nbsp;&nbsp;&nbsp;名</label>
	  	<input type="text" class="form-control" name="fullName" value="<s:property value="fullName"/>" placeholder="">	
	  </div>
	  <div class="form-group">
	  	<span style="color:red">*</span>
	  </div>
	  <div class="form-group col-lg-offset-1">
	  	<label for="phoneNumber">联系方式</label>
	  	<input type="text" class="form-control" name="phoneNumber" value="<s:property value="phoneNumber"/>" placeholder="">	
	  </div>
	  
	  <br>
	  <br>
	  <br>
	  
	  <div class="form-group col-lg-offset-1">
	  	<label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
	  	<input type="password" class="form-control" name="password" value="<s:property value="password"/>" placeholder="">
	  </div>
	  <div class="form-group">
	  	<span style="color:red">*</span>
	  </div>
	  <div class="form-group col-lg-offset-1">
	  	<label for="passwordAgain">确认密码</label>
	  	<input type="password" class="form-control" name="passwordAgain" value="<s:property value="passwordAgain"/>" placeholder="">
	  </div>
	  <div class="form-group">
	  	<span style="color:red">*</span>
	  </div>
	  
	  <br>
	  <br>
	  <br>
	  
	  <div class="form-group col-lg-offset-1">
	  	<label for="studentId">学&nbsp;&nbsp;&nbsp;&nbsp;号</label>
	  	<input type="text" class="form-control" name="studentId" value="<s:property value="studentId"/>" placeholder="">
	  </div>
	  <div class="form-group">
	  	<span style="color:red">*</span>
	  </div>
	  <div class="form-group col-lg-offset-1">
	  	<label for="bankCard">银行卡号</label>
	  	<input type="text" class="form-control" name="bankCard" value="<s:property value="bankCard"/>" placeholder="">
	  </div>
	  
	  <br>
	  <br>
	  <br>
	  
	  <div class="form-group col-lg-offset-1">
	  	<label for="idCard">身份证</label>
	  	<input type="text" class="form-control" name="idCard" id="idCard" value="<s:property value="idCard"/>" placeholder="">
	  </div>
	  <div class="form-group">
	  	<span style="color:red">*</span>
	  </div>
	  <div class="form-group col-lg-offset-1">
	  	<label for="entryTime">入职时间</label>
	  	<input type="date" class="form-control" name="entryTime" value="<s:property value="entryTime"/>" placeholder="">
	  </div>
	  <div class="form-group">
	  	<span style="color:red">*</span>
	  </div>
	  
	  <br>
	  <br>
	  <br>
	  
	  <div class="form-group col-lg-offset-1">
	  	<label for="classrooms">负责教室</label>
	  	<input type="text" class="form-control" name="classrooms" value="<s:property value="classrooms"/>" placeholder="">
	  </div>
	  <div class="form-group col-lg-offset-1">
	  	<label for="sex">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
	  	<s:select list="sexSelect" class="form-control" name="sex"></s:select>
	  </div>
	  <div class="form-group">
	  	<span style="color:red">*</span>
	  </div>
	  
	  <br>
	  <br>
	  <br>
	 
	  <div class="form-group col-lg-offset-5">
	  	<button type="button" class="btn btn-primary">注册</button>
	  </div>
	  <div class="form-group col-lg-offset-1">
	  	<span style="color:red">(*为必填项)</span>
	  </div>
</form>
	
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
    
  /*   var eok=false;
    $(function(){
    	$('#idCard').focusout(function(){
    		$.ajax({
    			url:'register_check',
    			type:'post',
    			data:{'idCard':$('#idCard').val()},
    			success:function(data){
    				if($('#idCard').val()=="")
    				{
    					$('#idCardInfo').html('<font color="red">不能为空</font>');
    				}
    				else
    				{
    					$('#idCardInfo').html('<font color="green">可用</font>');
    					eok=true;
    				}
    			}
    		});
    	});
    });
    
    function f1()
    {
    	return eok;
    } */
    
    function studentRegisterCallback(data)
    {    	
    	if(data.register_status == "0")
    	{    		
    		alert("注册成功");
    	}
    	else if(data.register_status == "1")
    	{
    		alert("注册用户名或者密码为空");
    	}
    	else if(data.register_status == "2")
   		{
    		alert("注册用户名重复");
   		}
    	else if(data.register_status=="3")
    	{
    		alert("两次密码不一致");
    	}
    	else 
   		{
    		alert("error with status" + data.register_status);
   		}

		
    }
</script>

</layout:override>










<%@ include file="/jsp/homepage/base.jsp" %>

