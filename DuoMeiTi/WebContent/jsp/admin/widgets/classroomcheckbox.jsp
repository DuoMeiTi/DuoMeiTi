<%@ include file="/jsp/base/taglib.jsp" %>


	
<%-- 	<s:iterator value="classroom_list" var="i" status="index">	 --%>
<%-- 			<input type="checkbox">	<s:property value="#i.classroom_num"/>		 --%>
			
<%-- 	</s:iterator> --%>
	<table class="table table-bordered table-striped" id="classroom_table" >
		<s:if test="classroom_list.size() != 0">
			<label style="margin:3px 5px;"><input type="checkbox" id="checkAll">全选</label>
		</s:if>
		<s:iterator begin="0" end="classroom_list.size()-1" var="i" step="6">
			<tr>
				<s:iterator  var="j" begin="0" end="@@min(classroom_list.size()-#i-1,5)" step="1">
					<td>
						<input type="checkbox" name="checkOne" id="<s:property value="classroom_list.get(#i+#j).id"/>"><span><s:property value="classroom_list.get(#i+#j).classroom_num"/></span>
					</td>
				</s:iterator>
			</tr>
		</s:iterator>
	</table>
