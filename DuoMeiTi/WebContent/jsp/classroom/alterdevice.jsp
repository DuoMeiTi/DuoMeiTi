<%@ include file="/jsp/base/taglib.jsp"%>

<label class="control-label">备用设备：</label>
	<table class="table device-table-bordered" id="alter_table">
		<thead>
			<tr>
				<th style="width: 10%;">资产编号lalaal</th>
				<th style="width: 10%;">型号</th>
				<th style="width: 10%;">名称</th>
				<th style="width: 10%;">出厂号</th>
				<th style="width: 10%;">出厂日期</th>
				<th style="width: 10%;">使用时常</th>
				<th style="width: 10%;"></th>
			</tr>
		</thead>
		<s:iterator value="repertory_list" var="device" status="i">
			<tr rtID=<s:property value="#device.rtId"/> >
				<td><s:property value="#device.rtNumber"/></td>
				<td><s:property value="#device.rtVersion"/></td>
				<td><s:property value="#device.rtType"/></td>
				<td><s:property value="#device.rtFactorynum"/></td>
				<td><s:property value="#device.rtProdDate"/></td>
				<td><s:property value="#device.rtReplacePeriod"/></td>
				<td><button type="button" class="btn btn-primary btn-sm move2class">加入教室</button></td>
			</tr>
		</s:iterator>
	</table>
