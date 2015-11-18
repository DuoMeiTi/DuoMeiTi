

<%@ include file="/jsp/base/taglib.jsp" %>

<!-- Modal -->
<div class="modal fade" id="classroom_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h2 class="modal-title" id="myModalLabel">添加教室</h2>
		</div>
		
		<div class="modal-body">
			<form class="form-horizontal">
<!-- 				<input style="visibility:hidden" id="submit_type" value="add" /> -->
				<div class="form-group">
					<label class="control-label col-sm-3" for="input_classroom_num">教室号</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="input_classroom_num">
					</div>
					<div style="text-align:center" class="col-sm-4 control-label">
						<span id="exist"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="input_principal_student_number">负责人学号</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="input_principal_student_number">
					</div>
					<div style="text-align:center" class="col-sm-4 control-label">
						<strong>负责人姓名:</strong>  
						<span id="input_principal_student_full_name">
						</span>
					</div>

				</div>
			</form>
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
				<div class="col-lg-2">
				  	<button class="btn btn-primary all" style="margin:2px;">所有教室</button>
				</div>
				
				
				<s:if test="url.contains(@util.Const@AdminRole)">
				
					<div class="col-lg-1">
					  	<button class="btn btn-danger add" style="margin:2px;" >新增教室</button>
					</div>
					
				</s:if>
	      </div>
	   </div>
	 </form>
	
	
	
	    
	
	<div class="classroomTableDiv">
		<%@ include file="classroomTable.jsp" %>		
	</div>	
		
		
		
		
		
</div>
		
		
		
		
		
		
		
		
		
		
		
		
		