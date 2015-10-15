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
				dutyleft.html(leftnum-1);
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
	alert("hello");
})
