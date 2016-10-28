<%@ include file="/jsp/base/taglib.jsp" %>





<layout:override name="main_content">
<br/>
<form class="form-inline" action="student_register" method="POST" id="student_register_form">
	  <div class="form-group col-lg-offset-4">
	  	<label for="username">用&nbsp;&nbsp;户&nbsp;&nbsp;名</label>
	  	<input type="text" class="form-control" name="user.username" id="username"
	  		onblur=checkUsername()  placeholder="用作登录的用户名">	
	  </div>
	  <div class="form-group">
	  	<span style="color:red" id="username_msg">*</span>
	  </div>
	  
	  <br><br>
	  
	  <div class="form-group col-lg-offset-4">
	  	<label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
	  	<input type="password" class="form-control" name="user.password" id="password" onblur="checkPassword()"  >
	  </div>
	  <div class="form-group">
	  	<span style="color:red" id="password_msg">*</span>
	  </div>
	  
	  <br><br>
	  
	  <div class="form-group col-lg-offset-4">
	  	<label for="passwordAgain">确认密码</label>
	  	<input type="password" class="form-control" name="passwordAgain"  id="passwordAgain" onblur="confirmPassword()"    >
	  </div>
	  <div class="form-group">
	  	<span style="color:red" id="confirm_msg">*</span>
	  </div>
	  
	  <br><br>
	  
	  <div class="form-group col-lg-offset-4">
	  	<label for="fullName">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
	  	<input type="text" class="form-control" name="user.fullName" id="fullName" onblur="checkFullName()" placeholder="您的真实姓名">	
	  </div>
	  <div class="form-group">
	  	<span style="color:red" id="fullName_msg">*</span>
	  </div>
	  
	  <br><br>
	  
	  <div class="form-group col-lg-offset-4">
	  	<label for="sex">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
	  	<s:select list="@util.Const@sexSelect" class="form-control" name="user.sex" id="sex" onblur="checkSex()"></s:select>
	  </div>
	  <div class="form-group">
	  	<span style="color:red" id="sex_msg">*</span>
	  </div>
	  
	  <br><br>
	  
	  <div class="form-group col-lg-offset-4">
		<label for="profilePhotoPath">上传头像</label> 
	    <input type="file" class ="pull-right "name="user.profilePhotoPath" id="file_upload"  placeholder="图片地址"> 
	   <br>
<%-- 			 <s:fielderror/> --%>
	  </div>
	  
	  <br><br>
	  
	  
	  <div class="form-group col-lg-offset-4">
	  	<label for="studentId">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
	  	<input type="text" class="form-control" name="studentId"  id="studentId" onblur="checkStudentId()" >
	  </div>
	  <div class="form-group">
	  	<span style="color:red" id="studentId_msg">*</span>
	  </div>
	  
	  <br><br>
	  
	  <div class="form-group col-lg-offset-4">
	  	<label for="college">院系信息</label>
	  	<s:select list="@util.Const@collegeSelect" class="form-control" name="college" id="college"></s:select>
	  </div>
	  
	  <br><br>
	   
	  <div class="form-group col-lg-offset-4">
	  	<label for="phoneNumber">联系方式</label>
	  	<input type="text" class="form-control" name="user.phoneNumber" id="user.phoneNumber" >	
	  </div>
	  
	  <br><br>
	  
	  <div class="form-group col-lg-offset-4">
	  	<label for="bankCard">银行卡号</label>
	  	<input type="text" class="form-control" name="bankCard"  id="bankCard"  >
	  </div>
	  
	  <br><br>
	  
	  <div class="form-group col-lg-offset-4">
	  	<label for="idCard">身&nbsp;&nbsp;份&nbsp;&nbsp;证</label>
	  	<input type="text" class="form-control" name="idCard"  id="idCard"  >
	  </div>
	  <br><br>
	  

	  <br> <br>
	 
	  <div class="form-group col-lg-offset-5">
	  	<button type="button" class="btn btn-primary">注册</button>
	  </div>
	  <div class="form-group col-lg-offset-1">
	  	<span style="color:red">(*为必填项)</span>
	  </div>
</form>
	
<script>

    $(document).on("click", "button", function (){
        //var params = $('#student_register_form').serialize(); //利用jquery将表单序列化
        var fd = new FormData();
        if(document.getElementById('file_upload').files.length != 0)
    		fd.append("file", document.getElementById('file_upload').files[0]);
        
        
        var params = $('#student_register_form').serializeArray();
        
        for(var i = 0; i < params.length; ++ i)
        {
        	var cnt = params[i];
        	if(cnt.name == "passwordAgain")
        	{
        		cnt.name = "save_"+cnt.name;
        	}
        	else
        	{
        		cnt.name = "save_newStudentProfile."+cnt.name;
        	}
        	fd.append(cnt.name, cnt.value);
        }
        
        $.ajax({
          url: 'student_register_save',
          type: 'post',
          dataType: 'json',
          data: fd,
          async:true,
	      contentType:false,
	      processData:false,
          success: studentRegisterCallback
        });

    });
    
    function studentRegisterCallback(data)
    {    	
    	if(data.save_status == "")
    	{    		
    		alert("注册成功, 您现在可以登录了");
    		window.location.href = "/login";
    	}
    	else 
    	{
    		alert(data.save_status);
    	}
    	

		
    }

    
    function checkUsername(){
    	var username = $("#username").val();
    	if(username.indexOf(' ') != -1)
   		{
    		$("#username_msg").text("用户名中不可以含有空格");
   		}
    	else if(username=="")
    		$("#username_msg").text("不能为空"); 
   	 	else
    		$("#username_msg").text("");
    }
    
    function checkFullName(){
    	var fullName=$('#fullName').val();
    	
    	if(fullName.indexOf(' ') != -1)
    	{
    		$('#fullName_msg').text("真实姓名中不可以含有空格");
    	}
    	else if(fullName=="")
    		$('#fullName_msg').text("不能为空");
    	else
    		$('#fullName_msg').text("");
    }
    
    function checkPassword(){
    	var password=$('#password').val();
    	if(password=="")
    		$('#password_msg').text("不能为空");
    	else
    		$('#password_msg').text("");
    }
    
    function confirmPassword(){
    	var password=$('#password').val();
    	var passwordAgain=$('#passwordAgain').val();
    	if(!(password==passwordAgain))
    		$('#confirm_msg').text("两次密码不一致");
    	else
    		$('#confirm_msg').text("");
    }
    
    function checkStudentId(){
    	var studentId=$('#studentId').val();
    	
    	if(username.indexOf(' ') != -1)
   		{
    		$("#username_msg").text("学号中不可以含有空格");
   		}
    	else if(studentId=="")
    		$('#studentId_msg').text("不能为空");
    	else
    		$('#studentId_msg').text("");
    }
    
    function checkSex(){
    	var studentId=$('#sex').val();
    	if(studentId=="")
    		$('#sex_msg').text("不能为空");
    	else
    		$('#sex_msg').text("");
    }
    
</script>

</layout:override>










<%@ include file="/jsp/homepage/new_home_base.jsp" %>

