<%@ include file="/jsp/base/taglib.jsp"%>

<label class="control-label"></label>
	<table class="table device-table-bordered" id="alter_table">
		<thead>
			<tr>
				<th >名称</th>
				<th  >资产编号</th>
				<th >型号</th>
				
				<th  >出厂号</th>
				<th  >出厂日期</th>
				<th  >审批日期</th>
				<th ></th>
			</tr>
		</thead>
		<s:iterator value="repertory_list" var="device" status="i">
			<tr rtID=<s:property value="#device.rtId"/> >
			
				<td><s:property value="#device.rtType"/></td>
				<td><s:property value="#device.rtNumber"/></td>
				<td><s:property value="#device.rtVersion"/></td>
				
				<td><s:property value="#device.rtFactorynum"/></td>
				<td><s:property value="@util.Util@formatTimestampToOnlyDate(#device.rtProdDate)"/></td>
				<td><s:property value="@util.Util@formatTimestampToOnlyDate(#device.rtApprDate)"/></td>
				<td><button type="button" class="btn btn-primary btn-sm move2class">加入教室</button></td>
			</tr>
		</s:iterator>
	</table>
