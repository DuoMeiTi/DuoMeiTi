

<%@ include file="/jsp/base/taglib.jsp" %>

<!-- Modal -->
<div class="modal fade" id="classroom_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h2 class="modal-title" id="myModalLabel">添加教室</h2>
		</div>
		
		<div class="modal-body">
			<form class="form-horizontal">
				
				<div class="alert alert-success" role="alert" id="addClassroomNotice">
					可以一次添加多个教室号，每个教室号使用空格分割
				</div>
				
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="input_classroom_num">教室号</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_classroom_num">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="input_principal_student_number">负责人学号</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="input_principal_student_number">
					</div>
				</div>
				
			</form>
			
			<div class="well">
			
				<div  class=" row ">
				
					    <div class="   col-md-2   ">
      						<label class="control-label " for ="input_principal_student_full_name">负责人姓名:</label>
    					</div>
    					
					    <div class=" col-md-5   " >
      						<span  id="input_principal_student_full_name"> </span> 
    					</div>
				</div>
				 
	
				<div  class="row">
					<span class="  col-md-5" id="exist"></span>
				</div>
			
			 </div>
			 
			 
		</div>
	      
		<div class="modal-footer">
			<button class="btn btn-success" id="queryStu">查询负责人姓名</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			<button type="button" class="btn btn-primary" id="addClassroom" >确定</button>
		</div>
	</div>
	</div>
</div>






<div class="classroomManage">
	<form class="form-horizontal" onsubmit="return false">
	   <div class="form-horizontal">
	       <div class="form-group">
	       
				<label class="col-lg-2 control-label" style="margin:2px">教室查询：</label>
				
				
				<div class="col-lg-2">
				    <select class="col-lg-2 form-control searchType" style="margin:3px;">
			            <option value="classroomNum">教室号</option>
			            <option value="principal">负责人</option>
				    </select>
				 </div>
				        
				<div class="col-lg-2">
					<input type="text" class="col-lg-3 form-control searchParam" style="margin:3px;height:34px;" 
					>
				</div>
				
				<div class="col-lg-1">
				    <button  class="btn btn-primary search" style="margin:2px;">查询</button>
				</div>
				<div class="col-lg-3">
				  	<button class="btn btn-info all" style="margin:2px;">
				  		本教学楼共有<s:property value="classroom_list.size()"/>个教室
					</button>
				</div>
				
<!-- 		  	  <div class="col-lg-2"> -->
  	  
<!-- 		  	  		<button type="button" class="btn btn-default "   style="margin:2px;" -->
<!-- 						 	 > -->
<%-- 				 	 	共有<s:property value="classroom_list.size()"/>个教室 --%>
<!-- 					</button> -->
			
<!-- 	  			</div> -->
				
				
				<s:if test="makeUrl().contains(@util.Const@AdminRole)">
				
					<div class="col-lg-1">
					  	<button class="btn btn-danger add" style="margin:2px;" >新增教室</button>
					</div>
					
				</s:if>
	      </div>
	   </div>
	 </form>
	
	
	
	    
	
	<div>
		<%@ include file="classroomTable.jsp" %>		
	</div>	
		
		
		
		
		
</div>
		
		
		
		
		
		
		
		
		
		
		
		
		