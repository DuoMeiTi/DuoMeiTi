function animateShow(text)
		    {
		    	//alert(text+"animatedShow");
		    	$("#alert_register_info").hide();
				$("#alert_register_info").text(text);
				$("#alert_register_info").show(500);
		    }
function animateHide()
			{
				$("#alert_register_info").hide(500);
			}
			
			$(document).on('click','.mycontent',function(){
				$("#alert_register_info").hide();
			})

function psw_update(){
    		var oldPsw = $("#oldPsw").val();
    		var newPsw = $("#newPsw").val();
    		var rePsw = $("#rePsw").val();
//    		alert(oldPsw);
    		$("#update_btn").popover('show');
    		event.stopPropagation();
    		if(newPsw == "")
        	{
        		animateShow("新密码必须非空");
        		return false;
        	}
    		
    		if(newPsw == oldPsw)
        	{
        		animateShow("新密码与原密码相同，请重新输入！");
        		return false;
        	}
    		
    		if(newPsw != rePsw)
    		{
    			animateShow("两次新密码输入不一致");
        		return false;
    		}
    		$.ajax({
    			url : '/student/password_update',
    			type : 'post',
    			dataType : 'json',
    			data : {//发送到服务器的数据
    				"oldPsw" : oldPsw,
    				"newPsw" : newPsw
    			},
    			success : pswUpdateCallback
    		});

    		function pswUpdateCallback(data){
    			animateShow(data.status);
    		}
    	}