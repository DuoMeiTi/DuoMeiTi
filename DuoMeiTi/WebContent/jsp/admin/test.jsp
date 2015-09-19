<%@ include file="/jsp/base/taglib.jsp" %>





<layout:override name="main_content">


<br/>
	<form class="form-inline" action="student_register" method="POST" id="student_register_form">
	  <div class="form-group">
	  	<label for="username">用户名</label>
	  	<input type="text" class="form-control" name="username" value="<s:property value="username"/>" placeholder="">	
	  </div>
	  <div class="form-group">
	  	<label for="password">密码</label>
	  	<input type="password" class="form-control" name="password" value="<s:property value="password"/>" placeholder="">
	  </div>
	  <div class="form-group">
	  	<span>jk</span>
	  </div>
	   	 
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">姓&nbsp;&nbsp;&nbsp;&nbsp;名</span>
	  			<input type="text" class="form-control" name="fullName" value="<s:property value="fullName"/>" placeholder="">	
	  		</div>
	  	 </div>
	  	 <div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">联系方式</span>
	  			<input type="text" class="form-control" name="phoneNumber" value="<s:property value="phoneNumber"/>" placeholder="">	
	  		</div>
	  	 </div>
	  </div>
	  
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;码</span>
	  			<input type="password" class="form-control" name="password" value="<s:property value="password"/>" placeholder="">
	  		</div>
	  	</div>
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">确认密码</span>
	  			<input type="password" class="form-control" name="passwordAgain" value="<s:property value="passwordAgain"/>" placeholder="">
	  		</div>
	  	</div>
	  </div>
	  
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">学&nbsp;&nbsp;&nbsp;&nbsp;号</span>
	  			<input type="text" class="form-control" name="studentId" value="<s:property value="studentId"/>" placeholder="">
	  		</div>
	  	</div>
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">银行卡号</span>
	  			<input type="text" class="form-control" name="bankCard" value="<s:property value="bankCard"/>" placeholder="">
	  		</div>
	  	</div>
	  </div>
	  
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">性&nbsp;&nbsp;&nbsp;&nbsp;别</span>
	  			<s:select list="sexSelect" class="form-control" name="sex"></s:select>
	  		</div>
	  	</div>
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">身份证号</span>
	  			<input type="text" class="form-control" name="idCard" id="idCard" value="<s:property value="idCard"/>" placeholder="">
	  		</div>
	  	</div>
	  	<div class="col-lg-2">
	  		<span id="idCardInfo"></span>
	  	</div>
	  </div>
	  
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">负责教室</span>
	  			<input type="text" class="form-control" name="classrooms" value="<s:property value="classrooms"/>" placeholder="">
	  		</div>
	  	</div>
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon data-date-format="yyyy-mm-dd"" >入职时间</span>
	  			<input type="date" class="form-control" name="entryTime" value="<s:property value="entryTime"/>" placeholder="">
	  		</div>
	  	</div>	
	  </div>
	  	
	  <div class="row">
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<span class="input-group-addon">上传头像</span>
	  			<input type="text" class="form-control">
	  		</div>
	  	</div>
	  	
	  	<div class="col-lg-4 col-lg-offset-1">
	  		<div class="input-group">
	  			<button type=button" class="btn btn-default">注册</button>
	  		</div>
	  	</div>
	  </div>
	</form>	
</div>
	


</layout:override>










<%@ include file="/jsp/homepage/base.jsp" %>

