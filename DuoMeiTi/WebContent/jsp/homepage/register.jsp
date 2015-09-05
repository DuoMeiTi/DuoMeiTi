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

<script>
    
    alert("SB");
    
    $(document).on("click", "button", function (){
        var params = $('#register_form').serialize(); //利用jquery将表单序列化
        //jquery发送ajax请求
        alert("GOGO");
        $.ajax({
          url: 'registerSave',
          type: 'post',
          dataType: 'json',
          data: params,
          success: registerCallback
        });
        alert("GOGO");
    });
    
    function registerCallback(data){

		alert(data.status);
    }
</script>

</layout:override>










<%@ include file="/jsp/homepage/base.jsp" %>

