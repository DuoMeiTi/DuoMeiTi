<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">


<div   class="table-responsive"> 

	
	<h3>值班表</h3>
	<table class="table table-bordered" 
	
	style="table-layout:fixed;"
	>
	
	
	
		<thead>
			<tr >
				<th colspan=1 
				style="width:100px!important;"
				>
				值班时间
				</th>
				<s:iterator var="i" begin = "0" end="@util.Util@dutyWeekList.size() - 1" step="1">
				
					<th 
					colspan="<s:property value='dutyPlaceList.size()'/>" 
					
					style="width:<s:property value='dutyPlaceList.size()*60'/>;">
					
					周<s:property value='@util.Util@dutyWeekList.get(#i)'/>
					</th>
				</s:iterator>
			</tr>
			
		</thead>
		<tbody>
		
			<tr  >
				<td >					
				</td>
				<s:iterator var="i" begin = "0" end="7 - 1" step="1"> 
				
					<s:iterator var="j" begin="0" end= "dutyPlaceList.size() - 1" step="1">
					
						<td >	
						
							<s:property value="@util.Util@convertToMultiLine(dutyPlaceList.get(#j).placeName, 3)" 
										escape="false"/>
						</td>
					
					</s:iterator>
					
				</s:iterator>
			</tr>
			
			
		
		<s:iterator var="i" begin = "0" end="@util.Util@dutyPeriodList.size() - 1" step="1">
				
		<tr>					
			<td >
<!-- 							<nobr> -->
					<s:property value="@util.Util@dutyPeriodList.get(#i)"/>
<!-- 							</nobr>  -->
			</td>
			
			<s:iterator var="j" begin = "0" end="7 - 1" step="1">
				
				<s:iterator var="place" begin="0" end= "dutyPlaceList.size() - 1" step="1">
			
					<td >
							
						<s:iterator var="k" begin="0" end="dutyShowList.get(#place).get(#i * 7 + #j).size() - 1" step="1">
			
							<span
								style="display:block;
									text-align:center;
									background-color:#ddd;
									margin:2px 0;
									cursor:pointer;
									min-height:27px;
									line-height:27px;
									font-size:85%;
									">
								
								<s:property value="@util.Util@convertToMultiLine(
												dutyShowList.get(#place).get(#i * 7 + #j).get(#k).student.user.fullName, 3)
												" 
												escape="false"/>
							</span>
					
						</s:iterator>		

						
					</td>
			
				</s:iterator>
			</s:iterator>
			

			
			
		</tr>
				
				
		</s:iterator>
		
		
		
	</tbody>
</table>





</div>




<script>

	var a = 199;
	
// 	$("th").css("width", a + "px");
// 	$(".multiSpan").attr("colspan", 3);


</script>





</layout:override>










<%@ include file="/jsp/homepage/new_home_base.jsp" %>

