<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="mycontent">

<link rel="stylesheet" type="text/css" media="screen" href="/css/admin/dutyManage.css"/> 

<div class="dutyManage">


	<h3>选班开关</h3>
	<s:if test="chooseClassSwitch==false">
		<button type="button" class="btn btn-primary" id="chooseClassSwitch">选班功能已关闭</button>
	</s:if>
	<s:else>
		<button type="button" class="btn btn-warning" id="chooseClassSwitch">选班功能已打开</button>
	</s:else>
	
	
	<h3> 值班地点管理</h3>	
		<form class="form-inline" method="POST">
			<input type="text" class="form-control" style="width:200px;" 
			id="newDutyPlace"> 
			<button type="button" class="btn btn-default" id="addDutyPlace"> 添加</button>
		</form>
		
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
	<div class="teachbuilding-droplist">
	
		<select class="form-control buildingSelect" id="selectDutyPlace">
			<option value=-1>请选择选班地点</option>
			<s:iterator value="dutyPlaceList" var="i" status="index" >
	  			<option  value=<s:property value="#i.id"/> ><s:property value="#i.placeName"/></option>
	  		</s:iterator>
	  	</select>

		<button type="button" class="btn btn-primary adjust-btn" statu=0>打开调整</button>
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
<%-- 				<s:iterator value="{'7:40 ~~ 9:20','9:50 ~~ 11:30','13:15 ~~ 14:50','15:20 ~~ 17:00','17:45 ~~ 19:20'}" var="time" status="row"> --%>
<!-- 					<tr class="row" row=<s:property value="#row.index+1"/>   > -->
<%-- 						<td class="col-md-2"><s:property value="time"/></td> --%>
<%-- 						<s:iterator value="{'','','','','','',''}" var="num" status="col"> --%>
<%-- 							<td class="col-md-1.5 students" col=<s:property value="#col.index+1"/>><s:property value="num"/></td> --%>
<%-- 						</s:iterator> --%>
<!-- 					</tr> -->
<%-- 				</s:iterator> --%>


<s:iterator value="{'7:40 ~~ 9:20','9:50 ~~ 11:30','13:15 ~~ 14:50','15:20 ~~ 17:00','17:45 ~~ 19:20'}"
	 		var="period" status="i" 	>

		<tr class="row"   >
		
			<td class="col-md-2"> <s:property value="period"/> </td>
			
			<s:iterator value="{'','','','','','',''}" var="value"  status="j"  >
			
				<td class="col-md-1.5 students"  dutyTime='<s:property value="(#i.index) * 7 + #j.index  "/>'  >
				
<%-- 					<s:property value="(#i.index) * 7 + #j.index  "/> --%>
				
				</td>
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
		        <h4 class="modal-title" id="myModalLabel">添加学生</h4>
		      </div>
		      <div class="modal-body">
		      	<form class="form-inline">
  					<div class="form-group">
    					<label for="studentName">姓名</label>
    					<input type="text" class="form-control" id="studentName">
  					</div>
  					<div class="form-group">
    					<label for="studentId">学号</label> 
    					<input type="text" class="form-control" id="studentId">
  					</div>
  					<button type="button" class="btn btn-default" id="searchButton">检索</button>
				</form>
				<hr>
				<p class="log"></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" id="dutyAdd" data-dismiss="modal">添加</button>
		      </div>
	    	</div>
	    </div>
	</div>
	
</div>


<script>

$(document).on("click","#addDutyPlace",function() {

	$.ajax({
		url:"/admin/student_manage/duty_manage_addDutyPlace",
		type : 'post',
		dataType : 'json',
		data : {"newDutyPlace": $("#newDutyPlace").val() },
		success : addDutyPlaceCallBack
	})


});
function addDutyPlaceCallBack(data){
	window.location.reload();
}





$(document).on("click","#deleteDutyPlace",function() {
	
	
	
	var deletedDutyPlaceId = $(this).attr("deletedDutyPlaceId");
	
	alert(deletedDutyPlaceId);
	
	$.ajax({
		url:"/admin/student_manage/duty_manage_deleteDutyPlace",
		type : 'post',
		dataType : 'json',
		data : {"deletedDutyPlaceId": deletedDutyPlaceId },
		success : deleteDutyPlaceCallBack
	})


});
function deleteDutyPlaceCallBack(data){
	window.location.reload();
// 	window.location.reload();
}









$(document).on("change",".buildingSelect",function(){

	
			
	var id=parseInt($(this).val());
	alert(id);
	if(id <0) return ;
	
// 	return ;
	
	var timetable=$(".time-table");
// 	alert(id);

	
	$("td").each(function(i)
	{
		if($(this).attr("col")>0)
			$(this).html("");
	});
	
	timetable.removeClass("hide");
	adjustBtnClose();
	$.ajax({
		url:"/admin/student_manage/duty_manage_obtainDutyTable",
		type : 'post',
		dataType : 'json',
		data : {"obtainDutyTable_dutyPlaceId":id},
		success : obtainDutyTableCallBack
	})

});





function obtainDutyTableCallBack(data) {
	
	alert("GGGG");	
	
	var list =  data.obtainDutyTable_dutyScheduleList;
	
	alert(list[0].id);
	
	
	
	
}
// 	var dutySchedule=data.dutySchedule;
// 	$(dutySchedule).each(function(i){
// 		var id=dutySchedule[i].studentId;
// 		var name=dutySchedule[i].studentName;
// 		var time=dutySchedule[i].time;
// 		var row = parseInt(time/10);
// 		var col = parseInt(time%10);
// 		$("td").each(function(j){
// 			var coll=$(this).attr("col");
// 			var roww=$(this).closest("tr").attr("row");
// 			if(coll==col&&roww==row){
// 				$(this).append("<span  class ='student-name' iid="+id+">"+name+"</span>");
// 			}
// 		});
// 	});
// }




</script>




<script type='text/javascript' src="/js/admin/dutyManage.js"></script>
</layout:override>

<%@ include file="base.jsp" %>