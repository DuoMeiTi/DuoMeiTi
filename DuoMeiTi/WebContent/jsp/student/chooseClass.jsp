<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">

<div class="mycontent">
	<h3>选择值班时间地点</h3>
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
	<h3>时间列表</h3>
	<div class="time-table">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th></th>
					<s:iterator value="{'一'，'二'，'三'，'四'，'五'，'六'，'日'}" var="name">
						<th>周<s:property value="name"/></th>
					</s:iterator>
					<!-- <th>周一</th>
					<th>周二</th>
					<th>周三</th>
					<th>周四</th>
					<th>周五</th>
					<th>周六</th>
					<th>周日</th> -->
				</tr>
			</thead>
			<tbody>
				<tr>
					
				</tr>
			</tbody>
		</table>
	</div>
	
</div>

</layout:override>

<%@ include file="/jsp/student/base.jsp" %>
