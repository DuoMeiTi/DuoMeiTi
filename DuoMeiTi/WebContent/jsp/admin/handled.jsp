<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="mycontent">

	<div class="mycontent">
		<div id="classlist-content">
			<table class="classlist table table-bordered table-striped" id="classlist">
				<tr class="row">
					<th class="col-lg-2">姓名</th>
					<th class="col-lg-2">学号</th>
					<th class="col-lg-2">班级</th>
					<th class="col-lg-4">电话</th>
					<th class="col-lg-2">审核状态</th>
				</tr>
				<tr class="row">
					<td class="col-lg-2">dorothy</td>
					<td class="col-lg-2">20150001</td>
					<td class="col-lg-2">计算机三班</td>
					<td class="col-lg-4">12345678901</td>
					<td class="col-lg-2">审核通过</td>
					
				</tr>
			</table>
		</div>
	</div>

</layout:override>







<%@ include file="/jsp/admin/users_request.jsp" %>