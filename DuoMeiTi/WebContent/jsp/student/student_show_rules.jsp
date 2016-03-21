<%@ include file="/jsp/base/taglib.jsp"%>



<layout:override name="main_content">
<div class="mycontent">
	
	
	<div class="mycontent">
		<h2 class="trTitle">规章制度</h2>
		<div class="trBody well" id="trainBody">
			
			
<%-- 			<s:if test="textShow == null || textShow.equals('')"> --%>
            		
<%--            	</s:if> --%>
<%--            	<s:elseif test="#session.role.equals(@util.Const@StudentRole)"> --%>
<!--            		(您是在职学生) -->
<%--            	</s:elseif> --%>
<%--            	<s:else> --%>
<!--            		(您是有管理员权限的在职学生) -->
<%--            	</s:else> --%>
<%-- 			<s:if  --%>
			<s:property escape="false" value="textShow"/>		
		</div>
		
		<script></script>
	</div>
	
	
	
<!-- 	<br> -->
<!-- 	<br> -->

<!-- 	<form  class="form-horizontal" method="post"> -->
<!-- 		<p style="font-size: 18px">  -->
<%-- 			<s:property escape="false" value="textShow"/> --%>
		
<!-- 		</p> -->
<!-- 	<br> -->
	
	
	
	
	
<!-- 	</center> -->
<!-- 	<br> -->
<!-- 	<br> -->
	
	
	
</div>
</layout:override>

<%@ include file="base.jsp"%>