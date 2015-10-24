$(document).on("click",".buildingSelect",function(){
	var id=parseInt($(this).val());
	var timetable=$(".time-table");
	if(id>0){
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
})

function getDutyTableCallBack(data){
	
}