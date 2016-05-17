<%@ include file="/jsp/base/taglib.jsp"%>



<layout:override name="mycontent">

	<div class="mycontent">
		<br> <br>
		<form class="form-horizontal" id="student_search_form">
			<div class="form-horizontal">
				<div class="form-group">

					<label class="col-lg-2 control-label" style="margin: 2px">学生查询：</label>
					<div class="col-lg-2">
						<select class="col-lg-2 form-control" style="margin: 3px;"
							name="search_select" id="search_select">
							<option value="1">姓名</option>
							<option value="2">学号</option>
						</select>
					</div>

					<div class="col-lg-2">
						<input type="text" class="col-lg-3 form-control"
							style="margin: 3px; height: 34px;"
							aria-describedby="basic-addon1" name="name_id" id="name_id">
					</div>

					<div class="col-lg-1">
						<button type="button" class="btn btn-primary" style="margin: 2px;"
							id="student_search">查&nbsp;&nbsp;询</button>
					</div>
					
					<div class="col-lg-1">
						<button type="button" class="btn btn-danger" style="margin: 2px;" 
							id = "obtainDepartureStudent">
							所有离职学生
						</button>
					</div>
				</div>
			</div>
		</form>


		<div class="student_table" id="studentTableDiv">
			<%@ include file="studenttable.jsp"%>
		</div>

		<div class="modal fade" id="student_edit">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h2 class="modal-title" id="modal-title">编辑学生信息</h2>
					</div>
					<div class="modal-body">
						<form class="form-inline well" id="edit_student_form"
							method="post">
							<div class="modal-body">
								<div class="row">
									<div class="col-lg-6">
										<div class="input-group">
											<span class="input-group-btn">
												<button type="button" class="btn btn-default">姓名</button>
											</span> <input type="text" class="form-control" id="fullName"
												name="fullName" value="">
										</div>
									</div>

									<div class="col-lg-6">
										<div class="input-group">
											<span class="input-group-btn">
												<button type="button" class="btn btn-default">性别</button>
											</span>
											<s:select list="sexSelect" class="form-control" name="sex"></s:select>
										</div>
									</div>

									<div class="col-lg-6">
										<div class="input-group">
											<span class="input-group-btn">
												<button type="button" class="btn btn-default">学号</button>
											</span> <input type="text" class="form-control" id="studentId"
												name="studentId" value="">
										</div>
									</div>

									<div class="col-lg-6">
										<div class="input-group">
											<span class="input-group-btn">
												<button type="button" class="btn btn-default">电话</button>
											</span> <input type="text" class="form-control" id="phoneNumber"
												name="phoneNumber" value="">
										</div>
									</div>

									<div class="col-lg-6">
										<div class="input-group">
											<span class="input-group-btn">
												<button type="button" class="btn btn-default">学院</button>
											</span>
											<s:select list="collegeSelect" class="form-control"
												name="college"></s:select>
										</div>
									</div>

									<div class="col-lg-6">
										<div class="input-group">
											<span class="input-group-btn">
												<button type="button" class="btn btn-default">权限</button>
											</span>
											<!-- <input type="text" class="form-control" id="isUpgradePrivilege"  name="isUpgradePrivilege" value=""> -->
											<s:select list="#{'0':'在职学生','1':'管理教师','2':'离职学生'}"
												class="form-control" name="isUpgradePrivilege"
												id="isUpgradePrivilege"></s:select>
										</div>
									</div>

									<div class="col-lg-6">
										<div class="input-group">
											<span class="input-group-btn">
												<button type="button" class="btn btn-default">银行卡号</button>
											</span> <input type="text" class="form-control" id="bankCard"
												name="bankCard" value="">
										</div>
									</div>

									<div class="col-lg-6">
										<div class="input-group">
											<span class="input-group-btn">
												<button type="button" class="btn btn-default">身份证号</button>
											</span> <input type="text" class="form-control" id="idCard"
												name="idCard" value="">
										</div>
									</div>

								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="editSave">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="editClose">关闭</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		
		
		
		
		
		
		
		<div class="modal fade" id="watchScoreModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h2 class="modal-title" id="modal-title">查看学生历史分数</h2>
					</div>
					<div class="modal-body">
						<div id = "watchScoreTableDiv">
						</div>
					</div>
					<div class="modal-footer">
<!-- 						<button type="button" class="btn btn-primary"  data-dismiss="modal">保存</button> -->
						<button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>

		<script type='text/javascript' src="/js/admin/student_information.js"></script>



	</div>


</layout:override>


<%@ include file="base.jsp"%>