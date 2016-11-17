<%@ include file="/jsp/base/taglib.jsp"%>


<div class="row">
	<div class="col-lg-6">
			<ul>
			<s:iterator value="execute_devices" var="device" >				
				<label class="control-label device-type-label"><s:property value="#device.rtType" />&nbsp;</label> 
				<span>
					<button type="button" class="btn btn-primary btn-sm repairRecordInput"
							deviceId='<s:property value="#device.rtId" />'
							deviceType='<s:property value="#device.rtType" />'
							deviceNumber='<s:property value="#device.rtNumber" />'
							>
						维修记录
					</button>
				</span>
				<table class="table  table-bordered">
					<thead>
						<tr>
							<td>资产编号</td>
							<td>型号</td>
							<td>出厂号</td>
							<td>出厂日期</td>
							<td>审批日期</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><s:property value="#device.rtNumber" /></td>
							<td><s:property value="#device.rtVersion" /></td>
							<td><s:property value="#device.rtFactorynum" /></td>
							<td><s:property value="@util.Util@formatTimestampToOnlyDate(#device.rtProdDate)"/></td>
							<td><s:property value="@util.Util@formatTimestampToOnlyDate(#device.rtApprDate)"/></td>
						</tr>
					</tbody>
				</table>
			</s:iterator>
		</ul>
	</div>
	
	<div class="col-lg-6">
	
	
		<s:if test="!execute_repairRecords.isEmpty()">
		
			<label class="control-label">维修记录：</label>
		
			<table class="table  table-bordered"  >
				<thead>
					<tr>
						<td>维修人</td>
						<td>维修设备</td>
						<td>维修情况</td>
						<td>维修时间</td>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="execute_repairRecords" var="repairRecord" status="i">
						<tr>
							<td><s:property value="#repairRecord.repairmanFullName" /> </td>
							<td><s:property value="#repairRecord.deviceType" /></td>
							<td><s:property value="#repairRecord.repairdetail" /></td>
							<td><s:property value="@util.Util@formatTimestamp(#repairRecord.repairdate)" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
				
		</s:if>
		
	</div>


</div>



