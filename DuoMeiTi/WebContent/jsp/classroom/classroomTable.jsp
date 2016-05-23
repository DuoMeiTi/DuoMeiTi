
<%@ include file="/jsp/base/taglib.jsp" %>


<table class="table table-bordered table-striped" id="classroom_table">
	<s:iterator  begin="0"  end="classroom_list.size()-1" var="i" step="5">
	
		<tr class=""  >
			 <s:iterator var="j" begin="0" end="@@min(classroom_list.size()-#i-1,4)" step="1">
			 	<td
				 	classroomNum='<s:property value="classroom_list.get(#i+#j).classroom_num"/>'
				 	classroomId='<s:property value="classroom_list.get(#i+#j).id"/>'
				 	
				 	studentNumber = '<s:property value="classroom_list.get(#i+#j).principal.studentId"/>'
				 	studentFullName = '<s:property value="classroom_list.get(#i+#j).principal.user.fullName"/>'
				 	
				 	nowrap="nowrap"
			 	>
			 		
			 		<a 
			 			href="classroom_detail?classroomId=<s:property value="classroom_list.get(#i+#j).id"/>"
<%-- 			 			href="classroom_detail?classroomId=<s:property value="classroom_list.get(#i+#j).id"/>" --%>
			 			
			 		 	class="btn btn-success btn-sm"
			 		 >
			 		
			 			<s:property value="classroom_list.get(#i+#j).classroom_num"/>

			 		</a>
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
