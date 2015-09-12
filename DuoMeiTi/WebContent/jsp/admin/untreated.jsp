<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="mycontent">

	<div class="mycontent">
		<div id="classlist-content">
			<table class="classlist table table-bordered table-striped" id="student_messeage">
				<tr class="row">
					<th class="col-lg-1.5">姓名</th>
					<th class="col-lg-0.5">性别</th>
					<th class="col-lg-1.5">学号</th>
					<th class="col-lg-2.5">身份证号</th>
					<th class="col-lg-2.5">院系信息</th>
					<th class="col-lg-1.5">联系方式</th>
					<th class="col-lg-1">待审核</th>
					<th class="col-lg-1"></th>
				</tr>
				
				<tr class="row">
					<td class="col-lg-1.5">dorothy</td>
					<td class="col-lg-0.5">女</td>
					<td class="col-lg-1.5">21524009</td>
					<td class="col-lg-2.5">210256196536925864</td>
					<td class="col-lg-2.5">创新创业学院</td>
					<td class="col-lg-1.5">18042563456</td>
					<td class="col-lg-1">
						<select id="judge" name="judge">
							<option value="1">通过</option>
							<option value="0">不通过</option>
						</select>
					</td>
					<td class="col-lg-1">
						<button type="button" class="btn btn-primary btn-sm">确定</button>
					</td>
				</tr>
			
			</table>
		</div>
	</div>
	
	<script>
	$(document).on("click", "button", function (){
		var strValue=$("#select_id").val(); //获取Select选择的Value
//		alert(strValue);
		$.ajax({
			url: 'requestSave',
	        type: 'post',
	        dataType: 'json',
	        data:strValue,
	        success:judgeCallBack
		});
	});
	</script>
	
	
    

</layout:override>







<%@ include file="/jsp/admin/users_request.jsp" %>