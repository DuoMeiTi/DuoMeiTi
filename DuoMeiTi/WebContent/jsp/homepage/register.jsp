<%@ include file="/jsp/base/taglib.jsp" %>





<layout:override name="main_content">
<br/>

	<form class="form-inline" action="register" method="POST" id="register_form">
	  <div class="form-group">
	    <label for="username">Name</label>	    
	    <input type="text" class="form-control" name="username" value="<s:property value="username"/>" placeholder="">
	  </div>
	  
	  
	  <div class="form-group">
	    <label for="password">Password</label>
	    <input type="password" class="form-control" name="password" value="<s:property value="password"/>" placeholder="">
	  </div>
	  
	  <button type="button" class="btn btn-default">注册</button>
	</form>	
	<br/>
	
	
	<div class="alert alert-info" role="alert" id="alert_register_info"  style="display:none">
	<br/>
	</div>	
	
	
	
	
	<table class="table table-bordered" id="user_table">
	
		<tr class="active">
			<th> Username </th>
			<th> Password </th>
		</tr>
		
		
		<s:iterator value="user_list" var="i" status="index" >  
			<tr class="success">
				<td>   <s:property value="#i.username"/>    </td>
				<td>   <s:property value="#i.password"/>   </td>
			</tr>
		</s:iterator>  
		
		
		

	</table>
	
	<s:debug> </s:debug>
	
	
	
	

<script>
    
//     alert("SB");
    
    $(document).on("click", "button", function (){
        var params = $('#register_form').serialize(); //利用jquery将表单序列化
//         alert(params);
        //jquery发送ajax请求

        $.ajax({
          url: 'registerSave',
          type: 'post',
          dataType: 'json',
          data: params,
          success: registerCallback
        });

    });
    
    function animatedShow(text)
    {
    	$("#alert_register_info").hide();
		$("#alert_register_info").text(text);
		$("#alert_register_info").show(500);
    }
    function registerCallback(data)
    {
    	
//     	alert($(document).find("#added_user_tr").html());
//     	alert(data.added_user_html);
//     	alert(data.username);
    	if(data.status == "0")
    	{    		
        	$("#user_table tr:first").after(data.added_user_html);
        	
        	
        	var cnt = $(document).find("#user_table tr:eq(1)").children();
        	$(cnt).eq(0).text(data.username);
        	$(cnt).eq(1).text(data.password);

    		animatedShow("注册成功");
    	}
    	else if(data.status == "1")
    	{
    		animatedShow("注册用户名或者密码为空");
    	}
    	else if(data.status == "2")
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

