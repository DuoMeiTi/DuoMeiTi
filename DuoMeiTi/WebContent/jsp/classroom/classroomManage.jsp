

<%@ include file="/jsp/base/taglib.jsp" %>



<div class="classroomManage">
	<form class="form-horizontal" >
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
					<input type="text" class="col-lg-3 form-control searchParam" style="margin:3px;height:34px;" aria-describedby="basic-addon1">
				</div>
				
				<div class="col-lg-1">
				    <button type="button" class="btn btn-primary search" style="margin:2px;">查询</button>
				</div>
				<div class="col-lg-2">
				  	<button type="button" class="btn btn-primary all" style="margin:2px;">所有教室</button>
				</div>
				<div class="col-lg-1">
				  	<button type="button" class="btn btn-danger add" style="margin:2px;" >新增教室</button>
				</div>
	      </div>
	   </div>
	 </form>
	
	
	
	    
	
	<div class="classroomTableDiv">
		<%@ include file="classroomTable.jsp" %>		
	</div>	
		
		
		
		
		
</div>
		
		
		
		
		
		
		
		
		
		
		
		
		