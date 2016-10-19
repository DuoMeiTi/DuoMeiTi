<%@ include file="/jsp/base/taglib.jsp"%>

<label class="control-label">维修记录：</label>
<table class="table device-table-bordered" id="repairrecord_table">
	<thead>
		<tr>
			<td>维修人</td>
			<td>维修设备</td>
			<td>维修情况</td>
			<td>维修时间</td>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="repairrecords" var="repairrecord" status="i">
			<tr>
				<td><s:property value="#repairrecord.repairmanFullName" /> </td>
				<td><s:property value="#repairrecord.deviceType" /></td>
				<td><s:property value="#repairrecord.repairdetail" /></td>
				<td><s:property value="@util.Util@formatTimestamp(#repairrecord.repairdate)" /></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
