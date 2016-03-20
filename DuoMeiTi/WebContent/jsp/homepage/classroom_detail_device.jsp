<%@ include file="/jsp/base/taglib.jsp"%>



	<div class="container-fluid">

		<br>
		<a class="btn btn-primary btn-lg" href="<s:property value="class_schedule_path"/>">课表
		</a>
		<table class=" table table-bordered table-striped ">
			<thead>
				<tr>
					<th>名称</th>
					<th>资产编号</th>
					<th>型号</th>
					<th>出厂号</th>
					<th>出厂日期</th>
					<th>更换日期</th>
				</tr>
			</thead>


			<s:iterator value="rtClass" var="device" status="i">
<!-- 				<div style="margin-bottom: 5px"> -->

<%-- 					<span style="visibility: hidden" class="device-id-span"><s:property --%>
<%-- 							value="#device.rtId" /></span> <span style="visibility: hidden" --%>
<%-- 						class="device-num-span"><s:property --%>
<%-- 							value="#device.rtNumber" /></span> --%>
<!-- 				</div> -->


				<tbody>
					<tr class="success">
						<td><s:property value="#device.rtType" /></td>
						<td><s:property value="#device.rtNumber" /></td>
						<td><s:property value="#device.rtVersion" /></td>
						<td><s:property value="#device.rtFactorynum" /></td>
						<td><s:property value="#device.rtProdDate" /></td>
						<td><s:property value="#device.rtDeadlineData" /></td>
					</tr>
				</tbody>


			</s:iterator>
		</table>

		<br> <br> <br>


	</div>



