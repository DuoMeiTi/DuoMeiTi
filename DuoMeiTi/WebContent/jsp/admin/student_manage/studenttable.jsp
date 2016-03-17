<%@ include file="/jsp/base/taglib.jsp"%>

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