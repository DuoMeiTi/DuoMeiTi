<%@ include file="/jsp/base/taglib.jsp"%>
<layout:override name="main_content">
	<div class="row">
		<div class="col-md-offset-3">
			   <h1>
				该用户不在本系统中，如有需要使用请注册。
				<hr/>
			</h1>
		</div>
		<div class="col-md-offset-3">
			<br />
		</div>
		<div class="col-md-offset-3">
			<br />
		</div>
	</div>
	<script>
    $(document).ready(function (){
    	alert("该用户不在本系统中，如有需要使用请注册。");
    	window.location.href="https://sso.dlut.edu.cn/cas/logout?service=http://202.118.73.42";
    });
    </script>
</layout:override>
<%@ include file="/jsp/homepage/new_home_base.jsp"%>