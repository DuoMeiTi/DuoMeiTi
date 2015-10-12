<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">

<div class="mycontent">
	<h1>选择值班时间地点</h1>
	<div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    		选择教学楼
    		<span class="caret"></span>
  		</button>
  		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
  			<s:iterator value="teahBuildings" var="i" status="index" >
  				<li iid=<s:property value="#i.buildingId"/>><a href="#"><s:property value="#i.buildingName"/></a></li>
  			</s:iterator>
		</ul>
	</div>
</div>

</layout:override>

<%@ include file="/jsp/student/base.jsp" %>
