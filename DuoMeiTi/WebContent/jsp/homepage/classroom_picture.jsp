<%@ include file="/jsp/base/taglib.jsp" %>


<!-- 	<div class="mycontent"> -->
<table>
	<s:iterator begin="0" end="picture_list.size()-1" var="i" step="3">
		<tr class="">
			<s:iterator  var="j" begin="0" end="@@min(picture_list.size()-#i-1,2)" step="1">
			<td width="300">
				<div  class="text" style=" text-align:center;">
					<label>&nbsp;<s:property value="picture_list.get(#i+#j).remark"/>&nbsp;</label>
				</div>
				<div>
					<img src="<s:property value="picture_list.get(#i+#j).path"/>" height="350px" width="270px" >  
				</div>
			</td>
			</s:iterator>
		</tr>
	</s:iterator>
</table>	
	
	
		
			
			
			
		
		

	
	
	