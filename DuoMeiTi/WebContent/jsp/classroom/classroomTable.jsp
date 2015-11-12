
<%@ include file="/jsp/base/taglib.jsp" %>


<table class="table table-bordered table-striped" id="classroom_table">
	
	<tr class="success" id="search_infor">
		<th>教室号</th>
		<th>负责人</th>
		<th>编辑</th>
		<th>管理教室</th>
	</tr>
	
	<s:iterator value="classroom_list" var="i" status="index">
	
		<tr class="success">
			<td><s:property value="#i.classroom_num"/></td>
			<td> <s:property value="#i.principal.user.fullName"/>    
			</td>
			<td>
				<a class="btn btn-info">编辑</a>
			<td>
			   <a href="classroom_detail?classroomId=<s:property value="#i.id"/>" class="btn btn-info">详细</a>
			</td>
		</tr>		
	</s:iterator>
</table>
