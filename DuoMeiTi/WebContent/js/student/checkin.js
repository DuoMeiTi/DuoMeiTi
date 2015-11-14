function checkin(){
	$.ajax({
		url: "/studentcheckin/checkinbyusername",
		success: result,
		error: result
	});
}

function result(data){
	alert(data.result);
	location.reload();
}