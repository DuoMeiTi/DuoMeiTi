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
	
	

<script>
    
//     alert("SB");
    
    $(document).on("click", "button", function (){
        var params = $('#register_form').serialize(); //利用jquery将表单序列化
        //jquery发送ajax请求

        $.ajax({
          url: 'registerSave',
          type: 'post',
          dataType: 'json',
          data: params,
          success: registerCallback
        });

    });
    
    function registerCallback(data)
    {
    	$("#user_table tr:first").after(data.added_user_html);
    	
    	var cnt = $(document).find("#added_user_tr");
    	$(cnt).children().eq(0).text(data.username);
    	$(cnt).children().eq(1).text(data.password);
    	
//     	alert($(document).find("#added_user_tr").html());
//     	alert(data.added_user_html);
//     	alert(data.username);
    	if(data.status == "0")
    	{
    		alert("注册成功");
    	}
    	else if(data.status == "1")
    	{
    		alert("注册用户名为空");
    	}
    	else if(data.status == "2")
   		{
    		alert("注册用户名重复");
   		}
    	else 
   		{
    		alert("error with status" + data.status);
   		}

		
    }
</script>

</layout:override>










<%@ include file="/jsp/homepage/base.jsp" %>

