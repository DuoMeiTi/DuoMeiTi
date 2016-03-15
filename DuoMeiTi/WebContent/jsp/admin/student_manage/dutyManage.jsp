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
  	
		<form class="form-inline" method="POST">
			
			
			<div class="form-group">

					<input type="text" class="form-control" style="width:200px;" id="newDutyPlace"> 
  			</div>
			
			
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
	<div 
	>
	
		<select class="form-control buildingSelect" id="selectDutyPlace"
				style="width:200px;"
		>
		
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
  					
<!--   					<button type="button" class="btn btn-default" id="searchButton">检索</button> -->
				</form>
				<hr>
<!-- 				<p class="log"></p> -->
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


$(document).on("click","#dutyChooseSwitchIsOpen",function(){
	$.ajax({
		url : "/admin/student_manage/duty_manage_switchDutyChoose",
		type : 'post',
		dataType : 'json',
		data : {},
		success : 

			function switchStatuChangeCallBack(data){
			var btn=$("#dutyChooseSwitchIsOpen");
				
			if(btn.hasClass("btn-primary"))
			{
				btn.removeClass("btn-primary");
				btn.addClass("btn-warning");
				btn.html("选班功能已打开");
			}
			else
			{
				btn.removeClass("btn-warning");
				btn.addClass("btn-primary");
				btn.html("选班功能已关闭");
			}
			
		}

		
		
		
		
		
	});
});























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
}








// 选择值班地点

$(document).on("change",".buildingSelect",function(){

	
			
	var id=parseInt($(this).val());
	
	
	var timetable=$(".time-table");
	if(id <0) 
	{
		timetable.addClass("hide");
		return ;
	}
	


	timetable.removeClass("hide");
	$("[dutyPieceTime]").each(function(i) {
		
		$(this).html("");

	});
	
	
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
	
	
	var ds_list =  data.obtainDutyTable_dutyScheduleList;
	
	for(var i = 0; i < ds_list.length; ++ i)
	{
		var time = ds_list[i].dutyPiece.time;
		var fullName = ds_list[i].student.user.fullName;
		var dutyScheduleId = ds_list[i].id;
		var dutyPieceId = ds_list[i].dutyPiece.id;		
		$("[dutyPieceTime="+time+"]").append(
				"<span  dutyScheduleId=" + dutyScheduleId +">"
				+ fullName +
				"</span>"
				
		);
	}
	


	
	var dutyPieceList = data.obtainDutyTable_dutyPieceList;
	for(var i = 0; i < dutyPieceList.length; ++ i)
	{		
		var d = dutyPieceList[i];
		$("[dutyPieceTime="+d.time+"]").attr("numberOfDuty", d.numberOfDuty);
		$("[dutyPieceTime="+d.time+"]").attr("dutyPieceId", d.id);
		$("[dutyPieceTime="+d.time+"]").attr("dutyLeft", d.dutyLeft);
		
		
// 		$("[dutyPieceTime="+d.time+"]").prepend(
				
// 				"<span  class= 'showDutyNumberAndLeft'>" + 
// 				d.numberOfDuty + 
// 				"," +
// 				d.dutyLeft + 
				
				
// 				"</span>"
				
// 		);
	}
		

	
}



var cntDutySchedule;
$(document).on("click","[dutyScheduleId]",function() {
	
	
	if(!window.confirm("您确定要删除此学生的选班时间段吗？")) return ;

	cntDutySchedule = $(this);
	var deleteDutySchedule_id = $(this).attr("dutyScheduleId");
	
	$.ajax({
		url:"/admin/student_manage/duty_manage_deleteDutySchedule",
		type : 'post',
		dataType : 'json',
		data : {"deleteDutySchedule_id": deleteDutySchedule_id },
		success : deleteDutyScheduleCallBack
	})

	
})
function deleteDutyScheduleCallBack(data)
{
	$(cntDutySchedule).remove();	
}


// 控制 调整在职学生的按钮
$(document).on("click",".adjust-btn",function()
{
	if($(".buildingSelect").val()<0)
	{
		alert("请选择教学楼");
		return;
	}
	if($(this).attr("statu")==0)
	{
		adjustBtnOpen();
	}
	else
	{
		adjustBtnClose();
	}
});

function adjustBtnClose(){
	var btn=$(".adjust-btn");
	$(".addBtn").each(function(i){
		$(this).remove();
	});
	btn.attr("statu",0);
	btn.html("打开值班人员调整");
	btn.removeClass("btn-warning");
	btn.addClass("btn-primary");
}

function adjustBtnOpen(){
	var btn=$(".adjust-btn");
	$("[dutyPieceTime]").each(function(i){
		$(this).append("<span class='addBtn'>+</span>");
	});
	btn.attr("statu",1);
	btn.html("关闭值班人员调整");
	btn.removeClass("btn-primary");
	btn.addClass("btn-warning");
}


//添加学生的按钮

var cntDutyPlaceId;
var cntDutyPieceTime;

var cntAddDutyPieceButton;
$(document).on("click",".addBtn",function() {
	
	cntDutyPlaceId = $(".buildingSelect").val();
	cntDutyPieceTime = $(this).parent().attr("dutyPieceTime");
	
	cntAddDutyPieceButton = $(this);

	
	
	$("#studentName").val("");
	$("#studentId").val("");
	$(".log").html("");
	$("#myModal").modal();
});





// 检索学生
$(document).on("click","#searchButton",function(){
	var name=$("#studentName").val();
	var id=$("#studentId").val();
	var logdiv=$(".log");
	if(name==""&&id=="")logdiv.html("请至少填写一个字段");
	else{
		$.ajax({
			url:"/admin/student_manage/searchStudent",
			type : 'post',
			dataType : 'json',
			data : {"studentName":name,"studentId":id},
			success : SearchStudentCallBack
		});
	}
})


var cntStudentId;
function SearchStudentCallBack(data){
	var students=data.searchResult;
	var logdiv=$(".log");
	var nameInput=$("#studentName");
	var idInput=$("#studentId");

	
	if(students.length==0)logdiv.html("查无此人");
	if(students.length==1)
	{
		if(nameInput.val()=="") nameInput.val(students[0].studentName);
		if(idInput.val()=="")idInput.val(students[0].studentId);
		cntStudentId = students[0].id;

		logdiv.html("可以添加");
	}
	if(students.length>1)logdiv.html("有多名学生同名，请输入学号重新检索");
}



// 添加addDutySchedule
$(document).on("click","#addDutySchedule",function() {
	
	
	if($(".log").text() != "可以添加")
	{
		alert("没有合法学生信息，无法添加请继续搜索");
		return ;
	}
	
	



	$.ajax({
		url:"/admin/student_manage/duty_manage_addDutySchedule",
		type : 'post',
		dataType : 'json',
		data : {"addDutySchedule_dutyPlaceId":cntDutyPlaceId,
				"addDutySchedule_studentId":cntStudentId,
				"addDutySchedule_dutyPieceTime":cntDutyPieceTime,
				},
		success : 
			
		function dutyAddCallBack(data) {
			
			
			var cmd = data.status.substr(0, 1);
			var info = data.status.substr(1);
			
			alert(info);
			if(cmd != "0")
			{
				return ;	
			}

// 			$("[dutyPieceTime="+ cntDutyPieceTime + "]").pre

			var fullName=$("#studentName").val();	
			var dutyScheduleId = data.addDutySchedule_addeddutyScheduleId;
			
			$(cntAddDutyPieceButton).before(
					"<span  dutyScheduleId=" + dutyScheduleId +">"
					+ fullName +
					"</span>");
		}
	});
})












// adjustDutyNumber

// 控制 调整值班容量

$(document).on("click",".adjustDutyNumber",function()
{
	if($(".buildingSelect").val()<0)
	{
		alert("请选择教学楼");
		return;
	}
	if($(this).attr("status")==0) // 关闭状态
	{
		
	 	$("[dutyPieceTime]").each(function(i){
			$(this).append(
					"<span> </span>"								
			);			
			$(this).children(":last").addClass("updateDutyNumber");
			
			
			var numberOfDuty = $(this).attr("numberOfDuty");
			$(this).children(":last").html("更新(" + numberOfDuty + ")");
			
			
			
			
// 			$("[dutyPieceTime="+d.time+"]").attr("numberOfDuty", d.numberOfDuty);
// 			$("[dutyPieceTime="+d.time+"]").attr("dutyPieceId", d.id);
// 			$("[dutyPieceTime="+d.time+"]").attr("dutyLeft", d.dutyLeft);
		});
	 	
	 	
	 	
	 	$(this).attr("status", 1);
	 	$(this).html("关闭值班容量调整");
	 	$(this).removeClass("btn-primary");
	 	$(this).addClass("btn-warning");

	}
	else
	{
		$(".updateDutyNumber").each(function(i){
			$(this).remove();
		});
		$(this).attr("status", 0);
		$(this).html("打开值班容量调整");
		$(this).removeClass("btn-warning");
		$(this).addClass("btn-primary");

	}
	
});



var cntDutyPieceId;
$(document).on("click",".updateDutyNumber",function() {
	

	$("#inputDutyNumber").val("");
	
	$("#updateDutyNumberModal").modal();
	
	cntDutyPieceId = $(this).parent().attr("dutyPieceId");

});

$(document).on("click","#updateDutyNumberButton",function() {
	
	var dutyNumber = $("#inputDutyNumber").val();
	dutyNumber = parseInt(dutyNumber);

	
	if(isNaN(dutyNumber))
	{
		alert("输入的是非法的数字"); return;
	}
	
	if(dutyNumber < 0)
	{
		alert("输入的数字是负数，请重新输入"); return;
	}
	

	
	
	$.ajax({
		url:"/admin/student_manage/duty_manage_updateDutyNumber",
		type : 'post',
		dataType : 'json',
		data : {"updateDutyNumber_dutyNumber":dutyNumber,
				"updateDutyNumber_dutyPieceId":cntDutyPieceId,
				},
		success : 
			
		function updateDutyNumberCallBack(data) {
			
			var cmd = data.status.substr(0, 1);
			var info = data.status.substr(1);
			alert(info);
			if(cmd != "0")
			{	
				return ;
			}
			
			
			var cntOccupiedDuty = $("[dutyPieceId="+ cntDutyPieceId  + "]").attr("numberOfDuty") 
								- $("[dutyPieceId="+ cntDutyPieceId  + "]").attr("dutyLeft");
			
			$("[dutyPieceId="+ cntDutyPieceId  + "]").attr("numberOfDuty", dutyNumber);			
			
			$("[dutyPieceId="+ cntDutyPieceId  + "]").attr("dutyLeft", dutyNumber - cntOccupiedDuty);
			
// 			var text = dutyNumber + ", " + (dutyNumber - cntOccupiedDuty);
			
// 			$("[dutyPieceId="+ cntDutyPieceId  + "]").find(".showDutyNumberAndLeft").text(text);

			$("[dutyPieceId="+ cntDutyPieceId  + "]").find(".updateDutyNumber").text("更新(" + dutyNumber + ")");

			

		}
				
				
				
	});

	
	
})





</script>




<script type='text/javascript' src="/js/admin/dutyManage.js"></script>
</layout:override>

<%@ include file="base.jsp" %>
