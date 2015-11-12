/**
 * 
 */


$(document).on('click','.move2class',function(){	
	rtID = $(this).parents("[rtID]").attr("rtID");// attr所选元素属性值
	classroom_id = $("#classroomid").text();
	/*alert(rtID+classroom_id);*/
	var params = {
			"classroomid" : classroom_id,
			"rtID" : rtID
	}
	
	$.ajax({
        url: '/admin/classroom_json/move2class',
        type: 'post',
        dataType: 'json',
        data : params,
        success: move2classCallback
      });

})

function move2classCallback(data){
	$("#device_jsp").html(data.device_jsp);
	$("#alterdevice_jsp").html(data.alterdevice_jsp);
	/*alert("callback");*/
}



/*备用设备*/
function alter_device() {
/*	alert("alter");*/
	var classroomid = $("#classroomid").text();
	/*alert(classroomid);*/
	var params = {
			"classroomid" : classroomid,
		};
	$.ajax({
        url: '/admin/classroom_json/alterdevice',
        type: 'post',
        dataType: 'json',
        data : params,
        success: alterdeviceCallback
      });
}

function alterdeviceCallback(data){
	$("#alterdevice_jsp").html(data.alterdevice_jsp);
	document.getElementById('alterdevice_jsp').style.display='';
}



/*移入维修*/
$(document).on('click','#move2repair',function(){
	/*alert("move2repair!");*/
	class_Id = $("#classroomid").text();
	move_device_id = $(this).parents("[device_id]").attr("device_id");// attr所选元素属性值
	/*alert(class_Id+"  "+move_device_id);*/
	var params = {
			"move_class_id" : class_Id,
			"move_device_id" : move_device_id,
			"opt" : 1,
		};

	$.ajax({
        url: '/admin/classroom_json/move2repair',
        type: 'post',
        dataType: 'json',
        data : params,
        success: moveCallback
      });
})


function moveCallback(data){
	/*alert("callback");*/
	$("#device_jsp").html(data.device_jsp);
}

$(document).on('click','#move2bad',function(){
	/*alert("move2bad!");*/
	class_Id = $("#classroomid").text();
	move_device_id = $(this).parents("[device_id]").attr("device_id");// attr所选元素属性值
	/*alert(class_Id+"  "+move_device_id);*/
	var params = {
			"move_class_id" : class_Id,
			"move_device_id" : move_device_id,
			"opt" : 1,
		};
	/*alert($("#device_jsp").html());*/
	$.ajax({
        url: '/admin/classroom_json/move2bad',
        type: 'post',
        dataType: 'json',
        data : params,
        success: moveCallback
      });
})


function checkrecord_submit() {
	var checkrecord_detail = $("#checkdetail").val();
	var classroomid = $("#classroomid").text();
	var checkrecord_detail = $("#checkdetail").val();
//	alert(checkrecord_detail);
	var params = {
		"checkdetail" : checkrecord_detail,
		"classroomid": classroomid
	};
	
	$.ajax({
		url : '/admin/classroom_json/checkrecord_save',
		type : 'post',
		dataType : 'json',
		data : params,
		success : checkrecord_save_callback
	});
}

function checkrecord_save_callback(data) {
	if(data.savestatus == "fail") 
		alert("fail!");
	else if(data.savestatus == "success") {
		$("#checkrecord_jsp").html(data.checkrecord_jsp);
	}
	$('#check-record-modal').modal('hide');
}



var selectDeviceId;
var selectIndex;

function openRepairMoadl(index) {
	selectDeviceId = $("#device-" + index + " .device-id-span:first").text();
	selectIndex = index;
	var num = $("#device-" + index + " .device-num-span:first").text();
	var type = $("#device-" + index + " .device-type-label:first").text();
	
	alert(selectDeviceId+" "+num+" "+type);
	$('#repair-record-modal').modal('show');
	$('#repair-record-modal').on('shown.bs.modal', function () {
		  $("#selectType").text(type);
		  $("#selectNum").text(num);
	})
}

function repairrecord_submit() {
	var repairdetail = $("#repairdetail").val();
	var params = {
			"repairdetail" : repairdetail,
			"deviceId" : selectDeviceId
		};
		
		$.ajax({
			url : '/admin/classroom_json/repairrecord_save',
			type : 'post',
			dataType : 'json',
			data : params,
			success : repairrecord_save_callback
		});
}

function repairrecord_save_callback(data) {
	if(data.savestatus == "fail") 
		alert("fail!");
	else if(data.savestatus == "success") {
		$("#repairrecord_jsp").html(data.repairrecord_jsp);
	}
	$('#repair-record-modal').modal('hide');
}


//function checkrecord_save_callback(data) {
//if(data.savestatus == "fail") 
//	alert("fail!");
//else if(data.savestatus == "success") {
//	var login_username = $("#login_user_name").text();
//	var checkrecord_detail = $("#checkdetail").val();
//	var today = new Date().format("yy-MM-dd");
//	var table = $("#checkrecord_table");
//	var rowcount = $(table).find("tr").length;
////	alert(rowcount);
//	$(table).find("tr:first").after("<tr><td width=\"20%\"></td><td></td><td></td></tr>");
//	var row = $(table).find("tr:first").next();
//	
//	$(row).find("td:eq(0)").text(login_username);
//	$(row).find("td:eq(1)").text(checkrecord_detail);
//	$(row).find("td:eq(2)").text(today);
//	if(rowcount >= 6) {
//		$(table).find("tr:last").remove();
////		var v = $(table).find("tr:eq(1)").find("td:eq(1)").text();
////		var last = $(table).find("tr:last").find("td:eq(1)").text();
////		alert(v+" "+last);
//	}
////	alert("success!");
//}
//$('#check-record-modal').modal('hide');
//}

//function repairrecord_save_callback(data) {
//	if(data.savestatus == "fail") 
//		alert("fail!");
//	else if(data.savestatus == "success") {
//		var login_username = $("#login_user_name").text();
//		var type = $("#device-" + selectIndex + " .device-type-label:first").text();
//		var repairdetail = $("#repairdetail").val();
//		var today = new Date().format("yy-MM-dd");
//		var table = $("#repairrecord_table");
//		var rowcount = $(table).find("tr").length;
////		alert(rowcount);
//		$(table).find("tr:last").after("<tr><td width=\"20%\"></td><td></td><td></td><td></td></tr>");
//		var row = $(table).find("tr:last");
//		$(row).find("td:eq(0)").text(login_username);
//		$(row).find("td:eq(1)").text(type);
//		$(row).find("td:eq(2)").text(repairdetail);
//		$(row).find("td:eq(3)").text(today);
//		if(rowcount >= 6) {
//			$(table).find("tr:eq(1)").remove();
////			var v = $(table).find("tr:eq(1)").find("td:eq(1)").text();
////			var last = $(table).find("tr:last").find("td:eq(1)").text();
////			alert(v+" "+last);
//		}
////		alert("success!");
//	}
//	$('#repair-record-modal').modal('hide');
//}




