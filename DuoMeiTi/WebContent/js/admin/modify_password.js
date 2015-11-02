	
    function psw_change_ensure()
    {
    	
    	//验证两次新密码输入是否一致
    	if($("#newPsw").val()!=$("#rePsw").val())
    	{
    		alert("两次密码输入不一致，请重新输入！");
    		return false;
    	}
    	
    	if($("#newPsw").val()==$("#oldPsw").val())
    	{
    		alert("新密码与原密码相同，请重新输入！");
    		return false;
    	}
    	
    	if($("#newPsw").val()=="")
    	{
    		alert("新密码必须非空");
    		return false;
    	}
    	
    	//if($("#oldPsw").val()==)
    	//将数据传入后台
    	var oldPsw = $("#oldPsw").val();
    	var newPsw = $("#newPsw").val();
    	var params={
    			"oldPsw":oldPsw,
    			"newPsw":newPsw,
    	}
    	$.ajax({
    		url : '/admin/modify_password',
    		type : 'post',
    		dataType : 'json',
    		data : params, 
    		success : checkOldPsw_callback
    	})
    	
    	
    }
    
    function checkOldPsw_callback(data){
    	alert(data.cmpResult);
    }
    //重置三个密码输入框  
    function psw_change_reset()
    {
    	$("#oldPsw").val("");
    	$("#newPsw").val("");
    	$("#rePsw").val("");
    	
    }   

   