<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="mycontent">

	<div class="mycontent">
		<div id="classlist-content">
			<table class="classlist table table-bordered table-striped" id="classlist">
				<tr class="row">
					<th class="col-lg-1.5">姓名</th>
					<th class="col-lg-0.5">性别</th>
					<th class="col-lg-1.5">学号</th>
					<th class="col-lg-2.5">身份证号</th>
					<th class="col-lg-2">院系信息</th>
					<th class="col-lg-2">联系方式</th>
					<th class="col-lg-2">审核状态</th>
				</tr>
				<tr class="row">
					<td class="col-lg-1.5">dorothy</td>
					<td class="col-lg-0.5">女</td>
					<td class="col-lg-1.5">21524009</td>
					<td class="col-lg-2.5">210245658945632568</td>
					<td class="col-lg-2">创新创业学院</td>
					<td class="col-lg-2">18045695368</td>
					<td class="col-lg-2">审核通过</td>
					
				</tr>
			</table>
		</div>
	</div>

</layout:override>







<%@ include file="/jsp/admin/users_request.jsp" %>