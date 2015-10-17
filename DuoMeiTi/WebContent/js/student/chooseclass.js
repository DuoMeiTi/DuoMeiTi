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
	if(id>0){
		timetable.removeClass("hide");
		$.ajax({
			url:"/student/getdutytime",
			type : 'post',
			dataType : 'json',
			data : {"teachBuildingId":id},
			success : getDutyTimeCallBack
		})
	}
	else{
		timetable.addClass("hide");
	}
})

function getDutyTimeCallBack(data){
	var dutyTime=data.duties;
	$(dutyTime).each(function(i){
		$(".dutyleft").each(function(j){
			$(this).html(dutyTime[i].dutyLeft);
		})
	})
}

$("#duty-choose-submit").click(function(){
	var chosen = new Array();
	$(".chosen").each(function(i){
		var row=parseInt($(this).closest("tr").attr("row"));
		var col=parseInt($(this).attr("col"));
		chosen[i]=row*10+col;
	});
	$.ajax({
		url:"/student/sendChoice",
		type : 'post',
		dataType : 'json',
		data : {"chosen":chosen},
		traditional:true,
		success : sendChoiceCallBack
	});
});

function sendChoiceCallBack(){
	
}