//选班JS

/*$(document).on("click",".dutyleft",function(){
	var dutyleft=$(".self-duty-left");
	var leftnum=parseInt(dutyleft.html());
	var leftduty=parseInt($(this).html());
	if($(this).hasClass("chosen")){
		dutyleft.html(leftnum+1);
		$(this).removeClass("chosen");
		$(this).html(leftduty+1);
	}
	else{
		if($(this).html()==0){
			alert("人数已达上限");
		}
		else{
			if(leftnum==0)alert("你已用完所有班次");
			else{
				$(this).addClass("chosen");
				$(this).html(leftduty-1);
				dutayleft.html(leftnum-1);
			}
		}
	}
})*/

$(document).on("click",".dutyleft",function(){
	var leftduty=parseInt($(this).html());
	if($(this).hasClass("chosen")){
		$(this).removeClass("chosen");
		$(this).html(leftduty+1);
	}
	else{
		if($(this).html()==0){
			alert("人数已达上限");
		}
		else{
			$(this).addClass("chosen");
			$(this).html(leftduty-1);
		}
		
	}
})

$(document).on("click",".buildingSelect",function(){
	var id=parseInt($(this).val());
	var timetable=$(".time-table");
	var studentId=parseInt(timetable.attr("iid"));
	if(id>0){
		timetable.removeClass("hide");
		$(".dutyleft").each(function(){
			$(this).removeClass("chosen");
		});
		$.ajax({
			url:"/student/getdutytime",
			type : 'post',
			dataType : 'json',
			data : {"teachBuildingId":id,"studentId":studentId},
			success : getDutyTimeCallBack
		})
	}
	else{
		timetable.addClass("hide");
	}
})

function getDutyTimeCallBack(data){
	var chosen=data.chosen;
	var dutyTime=data.duties;
	var teachBuildingId=data.teachBuildingId;
	var sel=$(".buildingSelect");
	$(".dutyleft").each(function(j){
		$(this).html(dutyTime[j].dutyLeft);
		if(teachBuildingId==sel.val()){
			var cur=$(this);
			var b=10*(parseInt(j/7)+1)+(j%7+1);
			$(chosen).each(function(i){
				if(chosen[i]==b){
					cur.addClass("chosen");
				}
			});
		}
	})
}

$("#duty-choose-submit").click(function(){
	var chosen = new Array();
	$(".chosen").each(function(i){
		var row=parseInt($(this).closest("tr").attr("row"));
		var col=parseInt($(this).attr("col"));
		chosen[i]=row*10+col;
	});
	var timetable=$(".time-table");
	var studentId=parseInt(timetable.attr("iid"));
	var id=parseInt($(".buildingSelect").val());
	$.ajax({
		url:"/student/sendChoice",
		type : 'post',
		dataType : 'json',
		data : {"chosen":chosen,"studentId":studentId,"teachBuildingId":id},
		traditional:true,
		success : sendChoiceCallBack
	});
});

function sendChoiceCallBack(data){
	alert(data.log);
}
