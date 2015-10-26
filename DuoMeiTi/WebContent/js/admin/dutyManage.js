$(document).on("click","#chooseClassSwitch",function(){
	var btn=$(this);
	if(btn.hasClass("btn-primary")){
		btn.removeClass("btn-primary");
		btn.addClass("btn-warning");
		btn.html("选班功能已打开");
	}else{
		btn.removeClass("btn-warning");
		btn.addClass("btn-primary");
		btn.html("选班功能已关闭");
	}
});

$(document).on("click",".buildingSelect",function(){
	var id=parseInt($(this).val());
	var timetable=$(".time-table");
	if(id>0){
		$("td").each(function(i){
			if($(this).attr("col")>0)$(this).html("");
		});
		timetable.removeClass("hide");
		$.ajax({
			url:"/admin/student_manage/getDutyTable",
			type : 'post',
			dataType : 'json',
			data : {"teachBuildingId":id},
			success : getDutyTableCallBack
		})
	}
	else{
		timetable.addClass("hide");
	}
});

function getDutyTableCallBack(data){
	var dutySchedule=data.dutySchedule;
	$(dutySchedule).each(function(i){
		var id=dutySchedule[i].studentId;
		var name=dutySchedule[i].studentName;
		var time=dutySchedule[i].time;
		var row = parseInt(time/10);
		var col = parseInt(time%10);
		console.log(time);
		$("td").each(function(j){
			var coll=$(this).attr("col");
			var roww=$(this).closest("tr").attr("row");
			if(coll==col&&roww==row){
				$(this).append("<span>"+name+"</span>");
			}
		});
	});
}