/**
 * 
 */

$(document).on("click", "#editAllTeachBuilding", function(){
	
	if($(this).attr("show") == "true")
	{
		$(this).text("显示所有教学楼");
		$(this).attr("show", "false")

		$(this).removeClass("btn-danger");
		$(this).addClass("btn-warning");

		$(".showTeachBuilding").hide();
		$(".deleteTeachBuilding").show();
	}
	else 
	{
		$(this).text("编辑所有教学楼");
		$(this).attr("show", "true");		
		$(this).addClass("btn-danger");
		$(this).removeClass("btn-warning");
		
		$(".showTeachBuilding").show();
		$(".deleteTeachBuilding").hide();
	}
	

	
//	alert("test")
})


$(".delete").click(function(){
	var temp = confirm("删除不可恢复！");
	if (temp == true) {
		var id = $(this).closest("td").attr("build_id");
		/* var isPass=$('#judge').find("option:selected").val(); */
		$.ajax({
			url: 'build_delete',
	        type: 'post',
	        dataType: 'json',
	        data:{buildId:id},
	        success: deleteCallBack
		});
	}
})

function deleteCallBack(data){
	if(data.status == 0){
		/* var td = $("td[build_id='"+data.buildId+"']");
		td.html(""); */
		location.reload();
		alert("删除成功！");
	}
	else alert("无法删除！");
}

function add() {
	$('#teachBuildingModal').modal('show');
	
	$('#teachBuildingModal').on('hidden.bs.modal', function (e) {
		$("#exist").text("");
	});
}

function addTeachBuilding() {
	var input_buildName_num = $("#input_buildName_num").val();
	$.ajax({
		url : '/admin/classroom_json/addTeachBuilding',
		type : 'post',
		dataType : 'json',
		data : {
			"build_name" : input_buildName_num
		},
		success : addTeachBuildingCallback
	});
}

function addTeachBuildingCallback(data) {
	if(data.add_status == "exist") {
		$("#exist").text("教室号已存在");
	}
	else if(data.add_status == "ok") {
		$('#teachBuildingModal').modal('hide');
		window.location.href=window.location.href;  
		window.location.reload;
	}
}