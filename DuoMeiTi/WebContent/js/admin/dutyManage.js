
$(document).on("click","#dutyChooseSwitchIsOpen",function(){
	$.ajax({
		url : "/admin/student_manage/duty_manage_switchDutyChoose",
		type : 'post',
		dataType : 'json',
		data : {},
		success : 

			function switchStatuChangeCallBack(data){
// 			alert("JJJJ");
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





















$('#newDutyPlace').bind('keypress',function(event){
//	alert("SS");
    if(event.keyCode == "13")
	{
//    	alert("22");
    	$("#addDutyPlace").click();
	}
//    alert("33");
})

$(document).on("click","#addDutyPlace",function() {

	$.ajax({
		url:"/admin/student_manage/duty_manage_addDutyPlace",
		type : 'post',
		dataType : 'json',
		data : {"addDutyPlace_placeName": $("#newDutyPlace").val() },
		success : function (data)
		{
			status = data.addDutyPlace_status;
			if(status == "")
			{
				window.location.reload();
			}
			else 
			{
				alert(status);
			}
			
			
		}
	})


});
//function addDutyPlaceCallBack(data){
//	window.location.reload();
//}





$(document).on("click","#deleteDutyPlace",function() {
	
	
	
	var deletedDutyPlaceId = $(this).attr("deletedDutyPlaceId");
	
// 	alert(deletedDutyPlaceId);
	
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
// 	alert("SBSB");
	$.ajax({
		url:"/admin/student_manage/duty_manage_obtainDutyTable",
		type : 'post',
		dataType : 'json',
		data : {"obtainDutyTable_dutyPlaceId":id},
		success : obtainDutyTableCallBack
	})

});





function obtainDutyTableCallBack(data) {
// 	alert("FFFF");
// 	alert(ds_list);
	
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

	
	
	$("#selectAddStudentType").val("studentFullName");
	$("#studentFullNameOrStudentIdListString").val("");
	$("#myModal").modal();
});









// 添加addDutySchedule
$(document).on("click","#addDutySchedule",function() {
	
	var selectAddStudentType = $("#selectAddStudentType").val();
	var studentFullNameOrStudentIdListString = $("#studentFullNameOrStudentIdListString").val();
//	alert(selectAddStudentType);
//	alert(studentFullNameOrIdListString);
//	if($(".log").text() != "可以添加")
//	{
//		alert("没有合法学生信息，无法添加请继续搜索");
//		return ;
//	}
	
//	return ;
	$.ajax({
		url:"/admin/student_manage/duty_manage_addDutySchedule",
		type : 'post',
		dataType : 'json',
		data : {
				"addDutySchedule_selectAddStudentType":selectAddStudentType,
				"addDutySchedule_studentFullNameOrStudentIdListString":studentFullNameOrStudentIdListString,
				
				"addDutySchedule_dutyPlaceId":cntDutyPlaceId,				
				"addDutySchedule_dutyPieceTime":cntDutyPieceTime,
				},
		success : 
			
			function dutyAddCallBack(data) {
				
				var status = data.addDutySchedule_status;
				var addedDutyScheduleList = data.addDutySchedule_addedDutyScheduleList;
				
//				alert(addedDutyScheduleList);
				
				for(var i = 0; i < addedDutyScheduleList.length; ++ i)
				{
					var dutySchedule = addedDutyScheduleList[i];
					var fullName = dutySchedule.student.user.fullName;
					var dutyScheduleId = dutySchedule.id;
					
					$(cntAddDutyPieceButton).before(
							"<span  dutyScheduleId=" + dutyScheduleId +">"
							+ fullName +
							"</span>");

				}
				
				
				
				
				if(status != "")
				{
					alert(status);					
				}
				
				
				
//				var cmd = data.status.substr(0, 1);
//				var info = data.status.substr(1);
				
//				alert(info);
//				if(cmd != "0")
//				{
//					return ;	
//				}
	
	// 			$("[dutyPieceTime="+ cntDutyPieceTime + "]").pre
	
//				var fullName=$("#studentName").val();	
//				var dutyScheduleId = data.addDutySchedule_addeddutyScheduleId;
//				
//				$(cntAddDutyPieceButton).before(
//						"<span  dutyScheduleId=" + dutyScheduleId +">"
//						+ fullName +
//						"</span>");
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
