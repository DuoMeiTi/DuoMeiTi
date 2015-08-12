<%@ include file="/jsp/base/taglib.jsp" %>





<layout:override name="main_content">
<br/>

	<form class="form-inline" action="register">
	  <div class="form-group">
	    <label for="userName">Name</label>   
	    
	    <input type="text" class="form-control" name="userName" value="<s:property value="userName"/>" placeholder="Sam">
<%-- 	    <s:property value="userName"/> --%>
<!-- 	    asdfs -->
	    
	  </div>
	  <div class="form-group">
	    <label for="exampleInputEmail2">Password</label>
	    <input type="pwd" class="form-control" name="user_pwd" placeholder="******">
	  </div>
	  <button type="submit" class="btn btn-default">注册</button>
	</form>



</layout:override>










<%@ include file="/jsp/homepage/base.jsp" %>

