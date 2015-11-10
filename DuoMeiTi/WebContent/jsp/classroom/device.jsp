<%@ include file="/jsp/base/taglib.jsp"%>

<ul>
	<s:iterator value="rtClass" var="device" status="i">
		
		<div style="margin-bottom: 5px" class_id=<s:property value="classroom.id"/> device_id=<s:property value="#device.rtId"/> >
			<label class="control-label device-type-label"><s:property value="#device.rtType" />&nbsp;</label> <span>
				<button type="button" class="btn btn-primary btn-sm"
					onclick="openRepairMoadl(<s:property value="#i.index"/>)">维修记录</button>
				
				<button type="button" class="btn btn-primary " id="move2repair">移入维修</button>
				<button type="button" class="btn btn-primary move2" id="move2bad">移入报废</button>
				
				<%-- <a
				href="<%=path%>/admin/classroomDevice/move2alter_action?m2alter=<s:property value="#device.rtId"/>&classroomId=<s:property value="classroom.id"/>&opt=0"
				id="mtoalter" type="button" class="btn btn-primary btn-sm">移入维修</a>
				<a
				href="<%=path%>/admin/classroomDevice/move2alter_action?m2alter=<s:property value="#device.rtId"/>&classroomId=<s:property value="classroom.id"/>&opt=1"
				id="mtoalter" type="button" class="btn btn-primary btn-sm">移入报废</a> --%>
				
			</span> <span style="visibility: hidden" class="device-id-span"><s:property
					value="#device.rtId" /></span> <span style="visibility: hidden"
				class="device-num-span"><s:property value="#device.rtNumber" /></span>
		</div>
		<table class="table device-table-bordered">
			<thead>
				<tr>
					<td>资产编号</td>
					<td>型号</td>
					<td>名称</td>
					<td>出厂号</td>
					<td>出厂日期</td>
					<td>更换日期</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><s:property value="#device.rtNumber" /></td>
					<td><s:property value="#device.rtVersion" /></td>
					<td><s:property value="#device.rtType" /></td>
					<td><s:property value="#device.rtFactorynum" /></td>
					<td><s:property value="#device.rtProdDate" /></td>
					<td><s:property value="#device.rtDeadlineData" /></td>
				</tr>
			</tbody>
		</table>
	</s:iterator>
</ul>