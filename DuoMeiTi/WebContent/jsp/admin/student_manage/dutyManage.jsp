<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="mycontent">

<link rel="stylesheet" type="text/css" media="screen" href="/css/admin/dutyManage.css"/> 

<div class="dutyManage">


	<h3>选班开关</h3>
	<s:if test="dutyChooseSwitchIsOpen==false">
		<button type="button" class="btn btn-primary" id="dutyChooseSwitchIsOpen">选班功能已关闭</button>
	</s:if>
	<s:else>
		<button type="button" class="btn btn-warning" id="dutyChooseSwitchIsOpen">选班功能已打开</button>
	</s:else>	
	
  	
	<h3> 值班地点管理 </h3>	
 	
	<div class="form-inline" >
		
		
		<div class="form-group">

				<input type="text" class="form-control" style="width:200px;" id="newDutyPlace"> 
 		</div>
		
		
		<button type="button" class="btn btn-default" id="addDutyPlace"> 添加</button>
		
		
	</div>
	
	<br/>
		
	<table class="table table-bordered">		  
		<tr>		  
			<s:iterator value="dutyPlaceList" var="i" >	
				<td class="active"  >					
					<s:property value="#i.placeName"/>
					<button type="button" class="btn btn-primary btn-sm" id="deleteDutyPlace" 
						deletedDutyPlaceId='<s:property value="#i.id"/>'>
						删除
					</button>
				</td>
			</s:iterator>			  
		</tr>  
	</table>
		
	
	
	<h3>值班表</h3>
	<div >
	
		<select class="form-control buildingSelect" id="selectDutyPlace"
				style="width:200px;">		
			<option value=-1>请选择选班地点</option>
			<s:iterator value="dutyPlaceList" var="i" status="index" >
	  			<option  value=<s:property value="#i.id"/> ><s:property value="#i.placeName"/></option>
	  		</s:iterator>
	  	</select>

		<button type="button" class="btn btn-primary adjustDutyNumber" status=0>打开值班容量调整</button>
		<button type="button" class="btn btn-primary adjust-btn" statu=0>打开值班人员调整</button>
	</div>
	
	
	
	<br/>
	<div class="time-table hide">
		<table class="table table-bordered">
			<thead>
				<tr class="row">
					<th class="col-md-2">值班时间</th>
					<s:iterator value="{'一','二','三','四','五','六','日'}" var='week'>
						<th class="col-md-1.5"><span><s:property value='week'/></span></th>
					</s:iterator>
				</tr>
			</thead>
			<tbody>

				<s:iterator value="{'7:40 ~~ 9:20','9:50 ~~ 11:30','13:15 ~~ 14:50','15:20 ~~ 17:00','17:45 ~~ 19:20'}"
					 		var="period" status="i" 	>
				
						<tr class="row"   >
						
							<td class="col-md-2"> <s:property value="period"/> </td>
							
							<s:iterator value="{'','','','','','',''}" var="value"  status="j"  >
							
								<td class="col-md-1.5 "  
									dutyPieceTime='<s:property value="(#i.index) * 7 + #j.index  "/>' 
									>				
								
								</td>
							</s:iterator>
						</tr>
				</s:iterator>	
				
			</tbody>
		</table>
	</div>
	
	
	
	
	
	<!-- Modal 添加学生modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">添加学生</h4>
		      </div>
		      <div class="modal-body">
		      	
		      	<div class="alert alert-success" role="alert">
		      		可以一次输入一个或多个学生姓名，学生学号，按照空格分隔； <br/>
		      		若有所输入的学生姓名或者学生学号不存在，将跳过；<br/>
		      		所输入的学生姓名重名，也将跳过，这种情况下应该按照学号输入；<br/>
				</div>

		      	
		      	<form >		      	
			      	<div class="form-group">
				      	<select class="form-control" id="selectAddStudentType">
				      		<option value="studentFullName">按照学生姓名</option>		
							<option value="studentStudentId">按照学生学号</option>
			  			</select>
		  			</div>		  				
		  			<div class="form-group">
				      	<input type="text" class="form-control" id="studentFullNameOrStudentIdListString">
		  			</div> 					
  					
				</form>
				<hr>
				<p class="log"></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" id="addDutySchedule" data-dismiss="modal">添加</button>
		      </div>
	    	</div>
	    </div>
	</div>
	
	
	
	
	
<!-- Modal    调整值班容量modal-->
	<div class="modal fade" id="updateDutyNumberModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">更新值班容量</h4>
		      </div>
		      <div class="modal-body">
		      	<form class="form-inline">
  					<div class="form-group">
    					<label for="inputDutyNumber">新的容量值</label>
    					<input type="text" class="form-control" id="inputDutyNumber">
  					</div>
				</form>
				<hr>

		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" id="updateDutyNumberButton" data-dismiss="modal">确定</button>
		      </div>
	    	</div>
	    </div>
	</div>
	
	
	
	<!-- 	表单 -->
	
	
	
	
	
</div>


<script>






</script>




<script type='text/javascript' src="/js/admin/dutyManage.js"></script>


</layout:override>

<%@ include file="base.jsp" %>
