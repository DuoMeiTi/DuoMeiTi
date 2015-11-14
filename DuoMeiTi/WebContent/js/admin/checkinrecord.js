function checkAmorPm()
{
	var isam = document.getElementById("isAM").checked;
	var ispm = document.getElementById("isPM").checked;
	alert("isam "+isam+"  ispm"+ispm);
	if(isam|ispm)
	{
		if(!(isam&ispm))
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
	var isam = document.getElementById("isAM").checked;
	var ispm = document.getElementById("isPM").checked;
	var starthour = document.getElementById("starthour").value;
	var startmin = document.getElementById("startminute").value;
	var endhour = document.getElementById("endhour").value;
	var endmin = document.getElementById("ednminute").value;
	alert("starthour "+starthour+" startmin "+startmin+" endhour "+endhour+" endmin "+endmin);
	if(isam)
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

function query()
{
	var starttime = $("#startTime").val();
	var endtime = $("#endTime").val();
	if(checkrecordtime(starttime,endtime))
	{
		$.ajax({
	        url: '/admincheckin/checkinrecord_date',
	        type: 'post',
	        dataType: 'json',
	        data: {"startTime" : starttime,"endTime":endtime},
			success:refreshtable
	      });
	}
	else
		{
		alert("查询时间错误");
		}
	
}

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
	var newtable =data.newtablestring;
	document.getElementById("recordstable").innerHTML=newtable;
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