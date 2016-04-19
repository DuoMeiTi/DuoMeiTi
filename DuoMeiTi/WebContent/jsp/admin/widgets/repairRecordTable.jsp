<%@ include file="/jsp/base/taglib.jsp" %>


<table class="table table-bordered table-hover" id="repairRecordTable">
	<tr class="active">
			<th>设备</th>
			<th>维修人</th>
			<th>教学楼</th>
			<th>教室</th>
			<th>维修内容</th>
			<th>维修时间</th>			
	</tr>
	
	<s:iterator value="repairRecordList" var="i" status="index">
	
		<tr class="warning" repairRecordId="<s:property value="#i.id"/>">
				
			<td  > <s:property value="#i.device.rtType"/> </td>
			<td  > <s:property value="#i.repairman.fullName"/> </td>
			<td  > <s:property value="#i.classroom.teachbuilding.build_name"/> </td>
			<td  > <s:property value="#i.classroom.classroom_num"/> </td>
			<td  > <s:property value="#i.repairdetail"/> </td>
			<td  > <s:property value="@util.Util@formatTimestamp(#i.repairdate)"/> </td>

		</tr>
	</s:iterator>
</table>