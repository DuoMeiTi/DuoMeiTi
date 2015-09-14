<%@ include file="/jsp/base/taglib.jsp" %>





<layout:override name="main_content">
<br/>

	<form class="form-inline" action="" method="POST">
	
	  <div class="form-group">
	    <label for="username">用户名</label>	    
	    <input type="text" class="form-control" name="username" value="<s:property value="username"/>" placeholder="">
	  </div>	  
	  
	  <div class="form-group">
	    <label for="password">密码</label>
	    <input type="password" class="form-control" name="password" value="<s:property value="password"/>" placeholder="">
	  </div>
	  
	  <button type="submit" class="btn btn-default">登录</button>
	</form>



</layout:override>



<%@ include file="/jsp/homepage/base.jsp" %>
