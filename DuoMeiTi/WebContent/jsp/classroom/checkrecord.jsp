<%@ include file="/jsp/base/taglib.jsp"%>

<label class="control-label">周检查记录：</label>
<table class="table device-table-bordered" id="checkrecord_table">
	<thead>
		<tr>
			<td>检查人</td>
			<td>教室状况</td>
			<td>检查时间</td>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="checkrecords" var="checkrecord" status="i">
			<tr>
				<td width="20%"><s:property value="#checkrecord.checkman.fullName" /></td>
				<td width="20%"><s:property value="#checkrecord.checkdetail" /></td>
				<td width="20%">
				

					<s:property value="@util.Util@formatTimestamp(#checkrecord.checkdate)" />
					

				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>