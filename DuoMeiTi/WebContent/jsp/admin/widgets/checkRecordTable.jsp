<%@ include file="/jsp/base/taglib.jsp" %>


<table class="table table-bordered table-striped" id="Records_table">
	<thead>
		<tr>
			<th style="width:10%;">学号</th>
			<th style="width:10%;">姓名</th>
			<th style="width:10%;">设备</th>
			<th style="width:40%;">维修情况</th>
			<th style="width:10%;">日期</th>
			<th style="width:10%;">教室</th>
			<th style="width:10%;">教学楼</th>
		</tr>
	</thead>
	
	<s:iterator value="#session.maintainRecords_list" var="item">
		<tr class="success" id='<s:property value="#item.id"/>'>
			<td><s:property value="#item.studentId" /></td>
			<td><s:property value="#item.fullName" /></td>
			<td><s:property value="#item.rtType" /></td>
			<td><s:property value="#item.repairDetail" /></td>
			<td><s:property value="#item.repairDate" /></td>
			<td><s:property value="#item.classRoom" /></td>
			<td><s:property value="#item.teachBuilding" /></td>
		</tr>
	</s:iterator>


</table>