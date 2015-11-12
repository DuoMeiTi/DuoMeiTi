<%@ include file="/jsp/base/taglib.jsp" %>



<layout:override name="mycontent">



	<div class="mycontent">
	
	
	
	<br>
	<br>
	<form class="form-horizontal" id="student_search_form">
	<div class="form-horizontal">
        <div class="form-group">
        
            <label class="col-lg-2 control-label" style="margin:2px">学生查询：</label>
            <div class="col-lg-2">
                <select class="col-lg-2 form-control" style="margin:3px;"name="search_select" id="search_select">
                <option value="1">姓名</option>
                <option value="2">学号</option>
            </select>
            </div>
            
			<div class="col-lg-2">
				<input type="text" class="col-lg-3 form-control" style="margin:3px;height:34px;" aria-describedby="basic-addon1" name="name_id" id="name_id">
			</div>

			<div class="col-lg-1">
                <button type="button" class="btn btn-primary" style="margin:2px;" id="student_search" >查&nbsp;&nbsp;询</button>
            </div>
        </div>
    </div>
    </form>
	
	
	<div class="student_table">
	
		<table class="table table-bordered table-hover" id="student_information_table">
			
			<tr class="row" id="search_infor">
				<th>姓名</th>
				<th>性别</th>
				<th>学号</th>
				<th>学院</th>
				<th>电话</th>
				<th>银行卡号</th>
				<th>身份证号</th>
				<th>权限</th>
				<th>分数</th>
				<th>编辑</th>
				<th>删除</th>
			</tr>
		
			<tr class="row" id="search_information" style="display: none;">
				<td id="search_name"></td>
				<td id="search_sex"></td>
				<td id="search_studentid"></td>
				<td id="search_college"></td>
				<td id="search_number"></td>
				<td id="search_bankCard"></td>
				<td id="search_idCard"></td>
				<td id="search_isUpgradePrivilege"></td>
				
				<td>
					<button type="button" class="btn btn-primary btn-sm edit" data-toggle="modal" data-target="#student_edit" id="edit-button" name="edit-button" >编辑</button>
				</td>
				<td id="search_score"></td>
				<td>
					<button type="button" class="btn btn-primary btn-sm delete" id="delede-button">删除</button>	
				</td>
			</tr>
			
			
			<s:iterator var="i" begin="0" end="student_list.size()-1" step="1" status="index">
				<tr class="row" id=<s:property value="student_list.get(#i).id"/>>
					<td> <s:property value="student_list.get(#i).user.fullName"/> </td>
					<td> <s:property value="student_list.get(#i).user.sex"/>  </td>
					<td> <s:property value="student_list.get(#i).studentId"/> </td>
					<td> <s:property value="student_list.get(#i).college "/> </td>
					<td> <s:property value="student_list.get(#i).user.phoneNumber"/> </td>
					<td> <s:property value="student_list.get(#i).bankCard"/> </td>
					<td> <s:property value="student_list.get(#i).idCard"/> </td>
					<td> 
						<s:if test="student_list.get(#i).isUpgradePrivilege == 0">在职学生</s:if>
						<s:else>管理教师</s:else>
					</td>
					<td>
						<s:if test="score_list.get(#i).score==-1">
							未提交
						</s:if>
						<s:else>
							<s:property value="score_list.get(#i).score"/>
						</s:else>
					</td>
					<td>
						<button type="button" class="btn btn-primary btn-sm edit" data-toggle="modal" data-target="#student_edit" id="edit-button" name="edit-button" >编辑</button>
					</td>
					<td>
						<button type="button" class="btn btn-primary btn-sm delete" id="delede-button">删除</button>	
					</td>
				</tr>
					
			</s:iterator>
		</table>
		
	</div>	
		
		<div class="modal fade" id="student_edit">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h2 class="modal-title" id="modal-title">编辑学生信息</h2>
									</div>
									<div class="modal-body">
										<form class="form-inline well" id="edit_student_form" method="post">
											<div class="modal-body">
												<div class="row">
													<div class="col-lg-6">
														<div class="input-group">
															<span class="input-group-btn">
																<button type="button" class="btn btn-default">姓名</button>
															</span>
															<input type="text" class="form-control" id="fullName"  name="fullName" value="">
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
															</span>
															<input type="text" class="form-control" id="studentId"  name="studentId" value="">
														</div>
													</div>
												
													<div class="col-lg-6">
														<div class="input-group">
															<span class="input-group-btn">
																<button type="button" class="btn btn-default">电话</button>
															</span>
															<input type="text" class="form-control" id="phoneNumber"  name="phoneNumber" value="">
														</div>
													</div>
													
													<div class="col-lg-6">
														<div class="input-group">
															<span class="input-group-btn">
																<button type="button" class="btn btn-default">学院</button>
															</span>							
															<s:select list="collegeSelect" class="form-control" name="college"></s:select>
														</div>
													</div>
													
													<div class="col-lg-6">
														<div class="input-group">
															<span class="input-group-btn">
																<button type="button" class="btn btn-default">权限</button>
															</span>
															<!-- <input type="text" class="form-control" id="isUpgradePrivilege"  name="isUpgradePrivilege" value=""> -->
															<s:select list="#{'0':'在职学生','1':'管理教师','2':'离职学生'}" class="form-control" name="isUpgradePrivilege" id="isUpgradePrivilege"></s:select>
														</div>
													</div>
													
													<div class="col-lg-6">
														<div class="input-group">
															<span class="input-group-btn">
																<button type="button" class="btn btn-default">银行卡号</button>
															</span>
															<input type="text" class="form-control" id="bankCard"  name="bankCard" value="">
														</div>
													</div>							
													
													<div class="col-lg-6">
														<div class="input-group">
															<span class="input-group-btn">
																<button type="button" class="btn btn-default">身份证号</button>
															</span>
															<input type="text" class="form-control" id="idCard"  name="idCard" value="">
														</div>
													</div>
									
												</div>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary" id="editSave">保存</button>
										<button type="button" class="btn btn-default" data-dismiss="modal" id="editClose">关闭</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
		
		<script type='text/javascript' src="/js/admin/student_information.js"></script>
		
		
		
	</div>
	
	
</layout:override>


<%@ include file="base.jsp" %>