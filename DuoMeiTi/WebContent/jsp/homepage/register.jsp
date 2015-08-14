<%@ include file="/jsp/base/taglib.jsp" %>





<layout:override name="main_content">
<br/>

	<form class="form-inline" action="register" method="POST">
	  <div class="form-group">
	    <label for="username">Name</label>	    
	    <input type="text" class="form-control" name="username" value="<s:property value="username"/>" placeholder="">
	  </div>
	  
	  
	  <div class="form-group">
	    <label for="password">Password</label>
	    <input type="password" class="form-control" name="password" value="<s:property value="password"/>" placeholder="">
	  </div>
	  
	  <button type="submit" class="btn btn-default">注册</button>
	  
	  
	</form>



</layout:override>










<%@ include file="/jsp/homepage/base.jsp" %>

