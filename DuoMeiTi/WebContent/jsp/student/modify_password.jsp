<%@ include file="/jsp/base/taglib.jsp" %>
<layout:override name="main_content">
<link rel="stylesheet" type="text/css" media="screen" href="/css/student/normalize.css"/> 
<link rel="stylesheet" type="text/css" media="screen" href="/css/student/default.css"/> 
<link rel="stylesheet" type="text/css" media="screen" href="/css/student/styles.css"/> 
<style>
#alert_register_info{
	width:250px;
	position:relative;
	top:-90px;
	left:700px;
	
}

</style>
<div class="mycontent">
	<!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
	<article class="htmleaf-container">
		<header class="htmleaf-header">
			<div class="htmleaf-links">
				<a class="htmleaf-icon icon-htmleaf-home-outline" href="http://www.htmleaf.com/" title="jQuery之家" target="_blank">
				<a class="htmleaf-icon icon-htmleaf-arrow-forward-outline" href="http://www.htmleaf.com/css3/ui-design/201510162674.html" title="返回下载页" target="_blank"><span> 返回下载页</span></a>
			</div>
		</header>
		<div class="panel-lite">
		  <div class="thumbur">
		    <div class="icon-lock"></div>
		  </div>
		  <h4>修改密码</h4>
		  <div class="form-group">
		    <input type="password" required="required" class="form-control sty" id="oldPsw"/>
		    <label class="form-label">原  密  码</label>
		  </div>
		  <div class="form-group">
		    <input type="password" required="required" class="form-control sty" id="newPsw"/>
		    <label class="form-label">新  密  码</label>
		  </div>
		  <div class="form-group">
		    <input type="password" required="required" class="form-control sty" id="rePsw"/> 
		    <label class="form-label">确认新密码</label>
		  </div>
		  <br />
		  <br />
		  <br />
		  <br />		  
		  <button class="floating-btn" id="update_btn" onclick="psw_update()"><i class="icon-arrow"></i></button>
	</article>
	<div class="alert alert-info" role="alert" id="alert_register_info"
		style="display: none">
	 </div>

</div>
<script type="text/javascript" src="/js/student/modify_password.js"></script>
</layout:override>
<%@ include file="/jsp/student/base.jsp" %>
