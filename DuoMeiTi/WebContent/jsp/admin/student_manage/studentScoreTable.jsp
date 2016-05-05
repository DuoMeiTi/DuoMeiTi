<%@ include file="/jsp/base/taglib.jsp"%>

<table class="table table-bordered table-hover" id="studentScoreTable">

			<tr>
				<th>提交次数</th>
				<th>学生姓名</th>				
				<th>本次提交的分数（也就是答对的题目数量）</th>
			</tr>
	
			<s:iterator var="i" begin="0" end="studentScoreList.size()-1" step="1"  >
				<tr >
				
					<td> 
						<s:property value="#i +1"/>
					</td>
					<td>
						<s:property value="studentScoreList.get(#i).stuPro.user.fullName"/> 
					</td>
					<td> 
						<s:property value="studentScoreList.get(#i).score"/>
					</td>
					

				</tr>
					
			</s:iterator>
			
			
</table>