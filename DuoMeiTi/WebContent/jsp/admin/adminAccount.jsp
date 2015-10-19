<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">

<div class="mycontent">
	
	<div class="student_table">
		<table class="table table-bordered" id="student_information_table">
			
			<tr>
				<th >姓名</th>
				<th >性别</th>
				<th >电话</th>
				<th >头像</th>
				<th >unitInfo</th>
				<th >备注</th>
				<th >编辑</th>
				<th >删除</th>
			</tr>
    		<tr >					
				<td>
					GGG
				</td>
				<td>
					JLKJLKJ	
				</td>
			</tr>
			
    		<tr >					
				<td>
					GGG
				</td>
				<td>
					JLKJLKJ	
				</td>
			</tr>
			
			<s:iterator value="allAdminProfilelist" var="i"  status="index"> 
    			<tr >					
					<td >
						ggg
					</td>
					<td >
						SBSB
					</td>
				</tr>
			</s:iterator>
			
			
		</table>
		
		
	</div>	
		
		
		


</div>

	
	
	
</layout:override>


<%@ include file="/jsp/admin/base.jsp" %>