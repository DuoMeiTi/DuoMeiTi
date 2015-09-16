<%@ include file="/jsp/base/taglib.jsp"%>

<layout:override name="main_content">
	<div class="mycontent">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3 classbuilding">
				<span>教学楼管理</span>
			</div>
		</div>
		<hr>
		<style type="text/css">
tr, th, td {
	text-align: center;
}
</style>
		<div class="modal fade" id="createBuilding">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h2 id="detailTitle">添加新教学楼</h2>
					</div>
					<div class="modal-body">
						<div class="row-fluid">
							<!-- block -->
							<div class="block">
								<div class="block-content collapse in">
									<form class="form-inline" action="createBuildingSave"
										method="POST" id="createBuilding_form">
										<center>
											<div class="form-group">

												<label for="username">教学楼名</label> <input type="text"
													class="form-control" name="build_name"
													value="<s:property value="build_name"/>" placeholder="">
											</div>
											<button type="button" class="btn btn-default"
												onclick="submitCreateBuilding()">确认</button>
										</center>
									</form>
								</div>
							</div>
							<!-- /block -->
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<div class="modal fade" id="detailModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h2 id="detailTitle">详细信息</h2>
					</div>
					<div class="modal-body">
						<div class="row-fluid">
							<!-- block -->
							<div class="block">
								<div class="navbar navbar-inner block-header">
									<div class="muted pull-left">
										<b>综一351</b> 中控 详细信息
									</div>
								</div>
								<div class="block-content collapse in">
									<div class="span12">

										<table cellpadding="0" cellspacing="0" border="0"
											class="table table-striped table-bordered" id="zhongkong">
											<thead>
												<tr>
													<th>资产编号</th>
													<th>型号</th>
													<th>出厂号</th>
													<th>出厂日期</th>
													<th>审批日期</th>
												</tr>
											</thead>
											<tbody>
												<tr class="odd gradeX">
													<td>100067</td>
													<td>TEST</td>
													<td>123400</td>
													<td>2013/07/22</td>
													<td>2013/09/01</td>
												</tr>
											</tbody>

										</table>
										<center></center>
									</div>
								</div>
							</div>
							<div class="block">
								<div class="navbar navbar-inner block-header">
									<div class="muted pull-left">
										<b>综一351</b> 功放 详细信息
									</div>
								</div>
								<div class="block-content collapse in">
									<div class="span12">

										<table cellpadding="0" cellspacing="0" border="0"
											class="table table-striped table-bordered" id="gongfang">
											<thead>
												<tr>
													<th>资产编号</th>
													<th>型号</th>
													<th>出厂号</th>
													<th>出厂日期</th>
													<th>审批日期</th>
												</tr>
											</thead>
											<tbody>
												<tr class="odd gradeX">
													<td>100067</td>
													<td>TEST</td>
													<td>123401</td>
													<td>2013/07/22</td>
													<td>2013/09/01</td>
												</tr>
											</tbody>

										</table>
										<center></center>
									</div>
								</div>
							</div>
							<div class="block">
								<div class="navbar navbar-inner block-header">
									<div class="muted pull-left">
										<b>综一351</b> 数字处理机 详细信息
									</div>
								</div>
								<div class="block-content collapse in">
									<div class="span12">

										<table cellpadding="0" cellspacing="0" border="0"
											class="table table-striped table-bordered" id="shuzichuliji">
											<thead>
												<tr>
													<th>资产编号</th>
													<th>型号</th>
													<th>出厂号</th>
													<th>出厂日期</th>
													<th>审批日期</th>
												</tr>
											</thead>
											<tbody>
												<tr class="odd gradeX">
													<td>100067</td>
													<td>TEST</td>
													<td>123300</td>
													<td>2013/07/22</td>
													<td>2013/09/01</td>
												</tr>
											</tbody>

										</table>
										<center></center>
									</div>
								</div>
							</div>
							<!-- /block -->
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<div class="modal fade" id="buildingModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h2 id="detailTitle">教学楼信息</h2>
					</div>
					<div class="modal-body">
						<div class="row-fluid">
							<table class="table table-bordered" id="builds_table">

								<tr class="active">
									<th>教学楼编号</th>
									<th>教学楼名</th>
									<th>删除</th>
								</tr>


								<s:iterator value="builds" var="i" status="index">
									<tr class="info">
										<td><s:property value="#i.build_id" /></td>
										<td><s:property value="#i.build_name" /></td>
										<td><a class="btn btn-default btn-danger" type="button"
											onclick="deleteBuilding('<s:property value="#i.build_id"/>')">删除</a></td>
									</tr>
								</s:iterator>




							</table>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<center>
			<div class="alert alert-info" role="alert" id="alert_add_info"
				style="display: none">
				<br />

			</div>
		</center>
		<div class="row-fluid">
			<!-- block -->
			<div class="block">
				<div>
					<table cellpadding="0" cellspacing="0" border="0"
						class="table table-striped table-bordered" id="classrooms">
						<thead>
							<tr>
								<th>教学楼</th>
								<th>教室</th>
								<th>中控</th>
								<th>功放</th>
								<th>数字处理器</th>
								<th>投影机</th>
								<th>计算机</th>
								<th>显示器</th>
								<th>机柜</th>
								<th>幕布</th>
								<th>麦克</th>
								<th>外围</th>
								<th>详细</th>
								<th>修改</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<tr class="odd gradeX">
								<td>综一</td>
								<td>351</td>
								<td>100067</td>
								<td>200231</td>
								<td>300126</td>
								<td>400061</td>
								<td>500177</td>
								<td>600481</td>
								<td>700693</td>
								<td>800176</td>
								<td>900190</td>
								<td>100326</td>
								<td><center>
										<a class="btn btn-default btn-info"
											onclick="changeDetailShow(0)" data-toggle="modal"
											data-target="#detailModal" id="rtInsert" type="button">详细</a>
									</center></td>
								<td><center>
										<a class="btn btn-default btn-warning" type="button">修改</a>
									</center></td>
								<td><center>
										<a class="btn btn-default btn-danger" type="button">删除</a>
									</center></td>
							</tr>
							<tr class="odd gradeX">
								<td>综一</td>
								<td>352</td>
								<td>100027</td>
								<td>200191</td>
								<td>300026</td>
								<td>400311</td>
								<td>500117</td>
								<td>600881</td>
								<td>700693</td>
								<td>800176</td>
								<td>900400</td>
								<td>100036</td>
								<td><center>
										<a class="btn btn-default btn-info"
											onclick="changeDetailShow(1)" data-toggle="modal"
											data-target="#detailModal" id="rtInsert" type="button">详细</a>
									</center></td>
								<td><center>
										<a class="btn btn-default btn-warning" type="button">修改</a>
									</center></td>
								<td><center>
										<a class="btn btn-default btn-danger" type="button">删除</a>
									</center></td>
							<tr class="odd gradeX">
								<td>综一</td>
								<td>353</td>
								<td>100327</td>
								<td>200196</td>
								<td>300226</td>
								<td>400611</td>
								<td>500317</td>
								<td>600281</td>
								<td>700493</td>
								<td>800376</td>
								<td>900420</td>
								<td>100436</td>
								<td><center>
										<a class="btn btn-default btn-info"
											onclick="changeDetailShow(2)" data-toggle="modal"
											data-target="#detailModal" id="rtInsert" type="button">详细</a>
									</center></td>
								<td><center>
										<a class="btn btn-default btn-warning" type="button">修改</a>
									</center></td>
								<td><center>
										<a class="btn btn-default btn-danger" type="button">删除</a>
									</center></td>
							</tr>
						</tbody>
					</table>
					<center>

						<a class="btn btn-default btn-success" type="button"
							data-toggle="modal" data-target="#createBuilding">添加教学楼</a> <a
							class="btn btn-default btn-success" type="button"
							onclick="animatedShow('添加教室成功')">添加教室</a> <a
							class="btn btn-default btn-danger" type="button"
							data-toggle="modal" data-target="#buildingModal">删除教学楼</a>
					</center>
				</div>
			</div>
			<!-- /block -->
		</div>
	</div>
	<script>
		function changeDetailShow(num) {
			var line = $(document).find("#zhongkong tr:eq(1)").children();

			$(line).eq(0).text(num);
			$(line).eq(1).text(num);

		}
		function submitCreateBuilding() {
			var params = $('#createBuilding_form').serialize(); //利用jquery将表单序列化

			$.ajax({
				url : 'createBuildingSave',
				type : 'post',
				dataType : 'json',
				data : params,
				success : createBuildingCallback
			});
		}
		function deleteBuilding(num){
			var params=$.param({build_id:num});
			$.ajax({
				url:'deleteBuilding',
				type:'post',
				dataType:'json',
				data:params,
				success:deleteBuildingCallback
			});
		}
		function deleteBuildingCallback(data) {
			$("#buildingModal").modal('hide');
			if (data.status == "0") {
				alert("删除教学楼成功");
			} else if (data.status == "1") {
				alert("教学楼名字为空");
			} else if (data.status == "2") {
				alert("无此教学楼");
			} else {
				alert("error with status" + data.status);
			}
			location.reload();
		}
		function createBuildingCallback(data) {
			$("#createBuilding").modal('hide');

			if (data.status == "0") {
				alert("添加教学楼成功");
			} else if (data.status == "1") {
				alert("教学楼名字为空");
			} else if (data.status == "2") {
				alert("教学楼名字重复");
			} else {
				alert("error with status" + data.status);
			}
			location.reload();
		}
		function animatedShow(text) {
			$("#alert_add_info").hide(200);
			$("#alert_add_info").text(text);
			$("#alert_add_info").show(500);
		}
	</script>
</layout:override>


<%@ include file="base.jsp"%>