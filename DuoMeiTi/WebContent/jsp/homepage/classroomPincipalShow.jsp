<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">


<div   class="table-responsive"> 

	
<h3>教室负责人总表</h3>

	<s:iterator value="classroomMap" >
		
		<h4>
			<s:property value="key"/>
		</h4>
		<table class="table table-bordered table-condensed">
			
			<tbody>
			<s:iterator var="i" begin="0" end="value.size() - 1" step="10">
				
				<tr>
					
					<s:iterator var="j" begin="0" end="@@min(9, value.size() - 1 - #i + 1 - 1)" step="1">
						<td> 
							<s:property value="value.get(#i+#j).classroom_num"/>
							<s:if test="value.get(#i+#j).principal != null">
								<span class="label label-success">
									<s:property value="value.get(#i+#j).principal.user.fullName"/>
								</span>
							</s:if>
							
							
						</td>
					</s:iterator>
					
				</tr>
				
<%-- 				 --%>
					
<%-- 				</s:iterator> --%>
						
			
<!-- 				<tr> -->
<!-- 					<td> -->
<!-- 					</td> -->
<!-- 				</tr> -->
			</s:iterator>
			</tbody>
		</table>
		
	
	</s:iterator>


<!-- <table class="table table-bordered table-condensed" > -->
<!-- </table> -->



	
<!-- <table class="table table-bordered table-condensed" 	 -->
<!-- style="table-layout:fixed;"> -->

<!-- 	<thead> -->
<!-- 		<tr> -->
<!-- 			<th style="width:80px!important;"> -->
<!-- 				负责人 -->
<!-- 			</th>							 -->
<!-- 			<th> -->
<!-- 				教室 -->
<!-- 			</th> -->
			
<!-- 			<th style="width:80px!important;"> -->
<!-- 				负责人 -->
<!-- 			</th>							 -->
<!-- 			<th> -->
<!-- 				教室 -->
<!-- 			</th> -->
			

<!-- 		</tr> -->
		
<!-- 	</thead> -->
	
	
<!-- 	<tbody> -->
		
<%-- 		<s:iterator var="i" begin="0" end = "classroomByPrinicpalList.size() - 1" step="2"> --%>
		
<!-- 			<tr> -->
<!-- 				<td style="background-color:yellow;">  -->
<%-- 					<s:property value="classroomByPrinicpalList.get(#i).get(0).principal.user.fullName"/> --%>
<!-- 				</td> -->
			
<!-- 				<td> -->
<!-- 					<table class="table table-bordered table-condensed" style="border-collapse: collapse;font-size:15px!important;">						 -->
<!-- 						<tbody>								 -->
<%-- 							<s:iterator var="j" begin="0" end="classroomByPrinicpalList.get(#i).size() - 1" step="4"> --%>
<!-- 								<tr> -->
<%-- 								 	<s:iterator var="k" begin="0" end="@@min(3, classroomByPrinicpalList.get(#i).size() - 1 - #j + 1 - 1)" step="1"> --%>
								 		
<!-- 										<td > -->
<%-- 											<s:property value="classroomByPrinicpalList.get(#i).get(#j + #k).teachbuilding.build_name"/> --%>
<%-- 											<s:property value="classroomByPrinicpalList.get(#i).get(#j + #k).classroom_num"/> --%>
<!-- 										</td> -->
<%-- 									</s:iterator> --%>
<!-- 								 </tr> -->
<%-- 							</s:iterator> --%>
<!-- 						</tbody> -->
<!-- 					</table> -->
<!-- 				</td> -->
				
				
				
				
<%-- 				<s:if test="#i+1<classroomByPrinicpalList.size()" > --%>
<!-- 					<td style="background-color:yellow;"> -->
<%-- 						<s:property value="classroomByPrinicpalList.get(#i + 1).get(0).principal.user.fullName"/> --%>
<!-- 					</td> -->
				
				
<!-- 					<td > -->
<!-- 						<table class="table table-bordered table-condensed" >						 -->
<!-- 							<tbody>								 -->
<%-- 								<s:iterator var="j" begin="0" end="classroomByPrinicpalList.get(#i + 1).size() - 1" step="4"> --%>
<!-- 									<tr> -->
<%-- 								 	<s:iterator var="k" begin="0" end="@@min(3, classroomByPrinicpalList.get(#i + 1).size() - 1 - #j + 1 - 1)" step="1">	 --%>
<!-- 											<td style="font-size:15px!important;"> -->

<%-- 												<s:property value="classroomByPrinicpalList.get(#i + 1).get(#j + #k).teachbuilding.build_name"/> --%>
<%-- 												<s:property value="classroomByPrinicpalList.get(#i + 1).get(#j + #k).classroom_num"/> --%>
<!-- 											</td> -->
										
<%-- 									</s:iterator> --%>
<!-- 									 </tr> -->
<%-- 								</s:iterator> --%>
								
<!-- 							</tbody> -->
<!-- 						</table> -->
<!-- 					</td> -->

					
				
				
				
				
				
				
				
				
<%-- 				</s:if> --%>
			



<!-- 			</tr> -->
			
<%-- 		</s:iterator> --%>
			
			
<!-- 		</tbody> -->
<!-- </table> -->





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

