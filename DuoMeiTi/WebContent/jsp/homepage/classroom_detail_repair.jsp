<%@ include file="/jsp/base/taglib.jsp"%>



<div class="container-fluid">







	<br>

	<table class=" table table-bordered table-striped ">
		<thead>
			<tr>
				<th>维修人</th>
				<th>维修设备</th>
				<th>维修情况</th>
				<th>维修时间</th>

			</tr>
		</thead>


		<s:iterator value="repairrecords" var="repairrecord" status="i">
			

			<tbody>
				<tr class="success">
					
				<td width="20%"><s:property value="#repairrecord.repairmanFullName"/></td>
				<td><s:property value="#repairrecord.deviceType"/></td>
				<td><s:property value="#repairrecord.repairdetail"/></td>
				<td>
					<s:property value="@util.Util@formatTimestamp(#repairrecord.repairdate)"/>
					
<%-- 					<s:property value="#repairrecord.repairdate.toString()"/> --%>
				</td>
								
				</tr>
			</tbody>


		</s:iterator>
	</table>

	<br> <br> <br>


</div>



