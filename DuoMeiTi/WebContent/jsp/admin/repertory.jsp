<%@ include file="/jsp/base/taglib.jsp"%>

<layout:override name="main_content">
	<div class="mycontent">

		<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">添加设备信息</button>
		<div class="modal fade" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h2 class="modal-title" id="modal-title">添加设备信息</h2>
					</div>
					<form class="form-inline well" id="repertory_form" action="repertory">
						<div class="modal-body">
						
							<div class="row">
							<div class="col-lg-6">
								<div class="input-device">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default">设备类型</button>
									</span>
									<select class="form-control" name="type" placeholder="请选择">
										<option value="<s:property value="type"/>"></option>
										<option value="<s:property value="type"/>">计算机</option>
										<option value="<s:property value="type"/>">投影仪</option>
									</select>
								</div>
								<div id="alert-bar" style="color: red"></div>
							</div>
							<div class="col-lg-6">
								<div class="input-device">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default">资产编号</button>
									</span>
									<input type="text" class="form-control" name="number" value="<s:property value="number"/>">
								</div>
								<div id="alert-bar" style="color: red"></div>
							</div>
							</div>
						
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>设备类型</th>
					<th>资产编号</th>
					<th>型号</th>
					<th>出厂日期</th>
					<th>审批日期</th>
					<th>出厂号</th>
					<th>使用状态</th>
					<th>编辑</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>11</td>
					<td>11</td>
					<td>11</td>
					<td>11</td>
					<td>11</td>
					<td>11</td>
					<td>11</td>
					<td>11</td>
					<td>11</td>
				</tr>
			</tbody>
		</table>
	</div>
</layout:override>

<%@ include file="/jsp/admin/base.jsp"%>