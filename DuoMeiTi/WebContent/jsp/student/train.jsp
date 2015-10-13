<%@ include file="/jsp/base/taglib.jsp" %>
<link rel="stylesheet" type="text/css" media="screen" href="/css/student/train.css"/>
<layout:override name="main_content">
	<div class="mycontent">
		<h2 class="trTitle">培训通知</h2>
		<div class="trBody well" id="trainBody">
			<s:property escape="false" value="trContent"/>		
		</div>
		
		<script></script>
	</div>
</layout:override>

<%@ include file="base.jsp" %>