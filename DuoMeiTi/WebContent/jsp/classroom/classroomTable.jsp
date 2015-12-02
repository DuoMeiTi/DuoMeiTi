
<%@ include file="/jsp/base/taglib.jsp" %>


<table class="table table-bordered table-striped" id="classroom_table">
	<s:iterator  begin="0"  end="classroom_list.size()-1" var="i" step="5">
	
		<tr class=""  >
			 <s:iterator var="j" begin="0" end="@@min(classroom_list.size()-#i-1,4)" step="1">
			 	<td
				 	studentId='<s:property value="classroom_list.get(#i+#j).principal.id"/>' 
				 	studentFullName='<s:property value="classroom_list.get(#i+#j).principal.user.fullName"/>'
				 	studentNumber='<s:property value="classroom_list.get(#i+#j).principal.studentId"/>'
				 	classroomNum='<s:property value="classroom_list.get(#i+#j).classroom_num"/>'
				 	classroomId='<s:property value="classroom_list.get(#i+#j).id"/>' 
				 	nowrap="nowrap"
			 	>
<%-- 			 	<s:if test="classroom_list.get(#i+#j).classroom_num!=null"> --%>
			 		
			 		<a href="classroom_detail?classroomId=<s:property value="classroom_list.get(#i+#j).id"/>" class="btn btn-success btn-sm">
			 		
<!-- 			 		教室号： -->
<!-- 			 		<h5> -->
			 			<s:property value="classroom_list.get(#i+#j).classroom_num"/>
<!-- 			 		</h5> -->
			 		</a>
<%-- 			 	</s:if> --%>
				<s:if test="makeUrl().contains(@util.Const@AdminRole)">
					<button class="btn btn-info  btn-xs update ">编辑</button>
            	</s:if>
			
				<s:if test="makeUrl().contains(@util.Const@AdminRole)">
					<button class="btn btn-info  btn-xs delete">删除</button>
				</s:if>
				</td>
			</s:iterator>
		</tr>		
	</s:iterator>
</table>
