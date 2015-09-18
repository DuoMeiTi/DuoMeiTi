<%@ include file="/jsp/base/taglib.jsp" %>





<layout:override name="main_content">

	<br/><br/>
	<a href="/login" class="btn btn-primary">跳到登录</a>
	
	<br/><br/>
	<a href="/login_success" class="btn btn-default">跳到登录成功界面，可以在这个界面可以退出登录</a>
	
	
	<br/><br/>
	<a href="/register" class="btn btn-default">跳到注册</a>
	<br/><br/>
	<a href="/student_register" class="btn btn-default">跳到学生注册</a>
	<br/><br/>
	<a href="/admin/" class="btn btn-default">跳到系统管理员</a>
	
	<br/><br/>
	<a href="/admin_eg" class="btn btn-default">跳到系统管理员带上边框的形式</a>
	
	
	<br/><br/>
	<form method="post" action="" enctype="multipart/form-data">
		<input type="file" id="f2">
		<input type="submit" name="submit">
	</form>

</layout:override>














<%@ include file="/jsp/homepage/base.jsp" %>

