function checkAmorPm()
{
	var am = document.getElementById("AM").checked;
	var pm = document.getElementById("PM").checked;
	if(am|pm)
	{
		if(!(am&pm))
		{
			return true
		}
	}
	return false;
}

function checktime()
{
	if(!checkAmorPm()) 
	{
		return false;
	}
	var am = document.getElementById("AM").checked;
	var pm = document.getElementById("PM").checked;
	var starthour = document.getElementById("starthour").value;
	var startmin = document.getElementById("startminute").value;
	var endhour = document.getElementById("endhour").value;
	var endmin = document.getElementById("endminute").value;
	alert("starthour "+starthour+" startmin "+startmin+" endhour "+endhour+" endmin "+endmin);
	if(am)
	{
		if(starthour>0&&starthour<=12)
		{
			if(startmin>=0&&startmin<=59)
				return true;
		}
	}
	else
	{
		if(starthour>12&&starthour<=24)
		{
			if(startmin>=0&&startmin<=59)
				return true;
		}
	}
	return false;
}

$(document).on("click", "#addbutton", function(){
	var starttime = $("#startTime").val();
	var endtime = $("#endTime").val();
	var data = {"currentPageNum":1 , "isAjaxTransmission":true,"startTime":starttime,"endTime":endtime,"query":true};
	if(checkrecordtime(starttime,endtime))
	{
		$.ajax({
	        url: '/admincheckin/checkinrecordquery',
	        type: 'post',
	        dataType: 'json',
	        data: data,
			success:refreshtable
	      });
	}
	else
		{
		alert("查询时间错误");
		}

	
})




function checkrecordtime(starttime,endtime)
{
	var nowdate = new Date();
	var start = new Date(starttime.replace(/-/,"/"));
	var end = new Date(endtime.replace(/-/,"/"));
	if(endtime>nowdate)return false;
	if(start>end)return false;
	return true;
}
function refreshtable(data)
{
	document.getElementById("queryrecordstable").innerHTML=data.newtablestring;
}

$(document).on("click",'#savechange',function(){
	if(!checktime())
	{
		alert("时间设置错误");
	}
	else
	{
		var params = $('#change_time_form').serialize(); //利用jquery将表单序列化 
		alert(params);
		$.ajax({
	        url: '/admincheckin/setcheckinrule',
	        type: 'post',
	        dataType: 'json',
	        data: params,
	        success: setcheckincallback,
	        error:setcheckinerror
	      });
	}
})

function setcheckinerror()
{
	$('#changetime').modal('hide');
	alert("error");
}
function setcheckincallback(data)
{
	$('#changetime').modal('hide');
	alert(data.result);
}