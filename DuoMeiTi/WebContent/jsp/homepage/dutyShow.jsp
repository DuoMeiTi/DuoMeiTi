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
					
						<td 
						
						<s:if test="(#i *dutyPlaceList.size() + #j) % 2 == 0">
	            			style="background-color:yellow;"
	            		</s:if>
	            		<s:else  >
            				style="background-color:#66CDAA;"
            			</s:else>
						 
						>	
						
								<b>
									<s:property value="@util.Util@convertToMultiLine(dutyPlaceList.get(#j).placeName, 3)" 
										escape="false"/>
								</b>
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
				
					
					
					
<%-- 				<s:if test="#session.role.equals(@util.Const@AdminRole)"> --%>
<!--             		(您是管理员) -->
<%--             	</s:if> --%>
<%--             	<s:elseif test="#session.role.equals(@util.Const@StudentRole)"> --%>
<!--             		(您是在职学生) -->
<%--             	</s:elseif> --%>
<%--             	<s:else> --%>
<!--             		(您是有管理员权限的在职学生) -->
<%--             	</s:else> --%>
					
					
					<td
					
						<s:if test="(#j *dutyPlaceList.size() + #place) % 2 == 0">
	            			style="background-color:yellow;"
	            		</s:if>
	            		<s:else  >
            				style="background-color:#66CDAA;"
            			</s:else>
					
					
					>							
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
									"
								class="studentInfo"	
								
									>
								
								<s:property value="@util.Util@convertToMultiLine(
												dutyShowList.get(#place).get(#i * 7 + #j).get(#k).student.user.fullName, 3)" 
												escape="false"/>
												
								<div   style="display:none;">
									
									<div>									
										<img src="<s:property value='dutyShowList.get(#place).get(#i * 7 + #j).get(#k).student.user.profilePhotoPath'/>"
											height="120px"
											width="100px"
										>
									</div>

									<div>									
										姓名：<s:property value="dutyShowList.get(#place).get(#i * 7 + #j).get(#k).student.user.fullName"/>
									</div>
									<div>									
										学号：<s:property value="dutyShowList.get(#place).get(#i * 7 + #j).get(#k).student.studentId"/>
									</div>
									
									<div>									
										电话：<s:property value="dutyShowList.get(#place).get(#i * 7 + #j).get(#k).student.user.phoneNumber"/>
									</div>

									
									
								</div>
								
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





<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">
        		学生信息
        </h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
      </div>
    </div>
  </div>
</div>



<script>



$(document).on( "click",".studentInfo", function(){
	
	$('#myModal').modal('show')
	$(".modal-body").html($(this).find("div").html());
})

// $('#myModal').on('show.bs.modal', function (e) {
	
	
// 	alert("SB");
// 	  // do something...
// 	})


</script>





</layout:override>










<%@ include file="/jsp/homepage/new_home_base.jsp" %>

