<%@ include file="/jsp/base/taglib.jsp" %>
<%
// String path = request.getContextPath();
// String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<layout:override name="main_content">
<!-- 	<link href="/css/admin/classroom_detail.css" rel="stylesheet" /> -->
<!-- 	<script type='text/javascript' src="/js/student/classroom_detail.js"></script> -->



<div class="mycontent">

	<div class="row">
		<div class="col-lg-6 col-lg-offset-3 classbuilding">
			<span><s:property value="classroom.teachbuilding.build_name"/></span>&nbsp;&nbsp;
			<span><s:property value="classroom.classroom_num"/></span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span>负责人:</span>
			<span id="classroomid" style="visibility:hidden"><s:property value="classroom.id"/></span>
			<span class="director-span"><s:property value="classroom.principal.user.username"/></span>
		</div>
	</div>
	
	<br>
	
	
	<div>
	
		<%@ include file="/jsp/classroom/classroomInformation.jsp" %>
		
	</div>		
</div>



</layout:override>



<%@ include file="/jsp/student/base.jsp" %>