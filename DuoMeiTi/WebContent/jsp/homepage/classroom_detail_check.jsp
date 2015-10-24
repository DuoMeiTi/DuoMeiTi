<%@ include file="/jsp/base/taglib.jsp"%>



<div class="container-fluid">







	<br>

	<table class=" table table-bordered table-striped ">
		<thead>
			<tr>
				<th>检查人</th>
				<th>教室状况</th>
				<th>检查时间</th>

			</tr>
		</thead>


		<s:iterator value="checkrecords" var="checkrecord" status="i">
			<%-- <li id="device-<s:property value="#i.index"/>" /> --%>
			<div style="margin-bottom: 5px">
				<%-- <label class="control-label device-type-label"><s:property value="#device.rtType"/>&nbsp;</label> --%>


				<%-- <span style="visibility: hidden" class="device-id-span"><s:property
						value="#device.rtId" /></span> <span style="visibility: hidden"
					class="device-num-span"><s:property value="#device.rtNumber" /></span> --%>
			</div>


			<tbody>
				<tr class="success">
					<td width="20%"><s:property
							value="#checkrecord.checkman.username" /></td>
					<td><s:property value="#checkrecord.checkdetail" /></td>
					<td><s:property value="#checkrecord.checkdate" /></td>
				</tr>
			</tbody>


		</s:iterator>
	</table>

	<br> <br> <br>


</div>



