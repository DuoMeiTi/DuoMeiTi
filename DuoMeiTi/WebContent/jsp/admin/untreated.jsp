<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="mycontent">

	<div class="mycontent">
		<div id="classlist-content">
			<table class="classlist table table-bordered table-striped" id="classlist">
				<tr class="row">
					<th class="col-lg-2">姓名</th>
					<th class="col-lg-2">学号</th>
					<th class="col-lg-2">班级</th>
					<th class="col-lg-2">电话</th>
					<th class="col-lg-2">待审核</th>
					<th class="col-lg-2"></th>
				</tr>
				
				<tr class="row">
					<td class="col-lg-2">dorothy</td>
					<td class="col-lg-2">20150001</td>
					<td class="col-lg-2">计算机三班</td>
					<td class="col-lg-2">12345678901</td>
					<td class="col-lg-2">
						<select id="judge" name="judge">
							<option value="1">通过</option>
							<option value="0">不通过</option>
						</select>
					</td>
					<td>
						<button type="button" class="btn btn-primary btn-sm">确定</button>
					</td>
				</tr>
			
			</table>
		</div>
	</div>
	
	
    

</layout:override>







<%@ include file="/jsp/admin/users_request.jsp" %>