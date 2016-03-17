//$(document).on("click","#chooseClassSwitch",function(){
//	$.ajax({
//		url : "/admin/student_manage/switchStatuChange",
//		type : 'post',
//		dataType : 'json',
//		data : {},
//		success : switchStatuChangeCallBack
//	});
//});
//
//function switchStatuChangeCallBack(data){
//	var btn=$("#chooseClassSwitch");
//	var log=data.log;
//	if(log=="success"){
//		if(btn.hasClass("btn-primary")){
//			btn.removeClass("btn-primary");
//			btn.addClass("btn-warning");
//			btn.html("选班功能已打开");
//		}else{
//			btn.removeClass("btn-warning");
//			btn.addClass("btn-primary");
//			btn.html("选班功能已关闭");
//		}
//		alert("状态修改成功");
//	}else{
//		alert("额..好像出问题了..");
//	}
//	
//}






//$(document).on("click",".buildingSelect",function(){
//	var id=parseInt($(this).val());
//	
//	var timetable=$(".time-table");
//	if(id>0){
//		$("td").each(function(i){
//			if($(this).attr("col")>0)$(this).html("");
//		});
//		timetable.removeClass("hide");
//		adjustBtnClose();
//		$.ajax({
//			url:"/admin/student_manage/getDutyTable",
//			type : 'post',
//			dataType : 'json',
//			data : {"teachBuildingId":id},
//			success : getDutyTableCallBack
//		})
//	}
//	else{
//		timetable.addClass("hide");
//	}
//});





//function getDutyTableCallBack(data){
//	var dutySchedule=data.dutySchedule;
//	$(dutySchedule).each(function(i){
//		var id=dutySchedule[i].studentId;
//		var name=dutySchedule[i].studentName;
//		var time=dutySchedule[i].time;
//		var row = parseInt(time/10);
//		var col = parseInt(time%10);
//		$("td").each(function(j){
//			var coll=$(this).attr("col");
//			var roww=$(this).closest("tr").attr("row");
//			if(coll==col&&roww==row){
//				$(this).append("<span  class ='student-name' iid="+id+">"+name+"</span>");
//			}
//		});
//	});
//}















//$(document).on("click",".adjust-btn",function(){
//	if($(".buildingSelect").val()==0){
//		alert("请选择教学楼");
//		return;
//	}
//	if($(this).attr("statu")==0){
//		adjustBtnOpen();
//	}else{
//		adjustBtnClose();
//	}
//});
//
//function adjustBtnClose(){
//	var btn=$(".adjust-btn");
//	$(".addBtn").each(function(i){
//		$(this).remove();
//	});
//	btn.attr("statu",0);
//	btn.html("打开调整");
//	btn.removeClass("btn-warning");
//	btn.addClass("btn-primary");
//}
//
//function adjustBtnOpen(){
//	var btn=$(".adjust-btn");
//	$(".students").each(function(i){
//		$(this).append("<span class='addBtn'>+</span>");
//	});
//	btn.attr("statu",1);
//	btn.html("关闭调整");
//	btn.removeClass("btn-primary");
//	btn.addClass("btn-warning");
//}

//
//$(document).on("click",".student-name",function(){
//	var cur=$(this);
//	var name=$(this).html();
//	var sid=$(this).attr("iid");
//	var col=$(this).closest("td").attr("col");
//	var row=$(this).closest("tr").attr("row");
//	var time=parseInt(row)*10+parseInt(col);
//	var tid=$(".buildingSelect").val();
//	if($(".adjust-btn").attr("statu")==1){
//		if(confirm("确定删除"+name+"同学选的这个值班吗")){
//			$.ajax({
//				url:"/admin/student_manage/deleteDuty",
//				type : 'post',
//				dataType : 'json',
//				data : {"teachBuildingId":tid,"student_Id":sid,"dtime":time},
//				success : function deleteDutyCallBack(data){
//					var log=data.log;
//					if(log=="success"){
//						alert("删除成功");
//						cur.remove();
//					}
//					else alert("好像出了什么问题..!");
//				}
//			});
//		}
//	}
//});




//function deleteDutyCallBack(data){
//	
//}

//$(document).on("click",".addBtn",function(){
//	var col=$(this).closest("td").attr("col");
//	var row=$(this).closest("tr").attr("row");
//	var time=parseInt(row)*10+parseInt(col);
//	var tid=$(".buildingSelect").val();
//	$("#studentName").val("");
//	$("#studentId").val("");
//	$(".log").html("");
//	$(".modal-body").attr("time",time);
//	$(".modal-body").attr("tid",tid);
//	$("#myModal").modal();
//});

//$(document).on("click","#searchButton",function(){
//	var name=$("#studentName").val();
//	var id=$("#studentId").val();
//	var logdiv=$(".log");
//	if(name==""&&id=="")logdiv.html("请至少填写一个字段");
//	else{
//		$.ajax({
//			url:"/admin/student_manage/searchStudent",
//			type : 'post',
//			dataType : 'json',
//			data : {"studentName":name,"studentId":id},
//			success : SearchStudentCallBack
//		});
//	}
//})
//
//function SearchStudentCallBack(data){
//	var students=data.searchResult;
//	var logdiv=$(".log");
//	var nameInput=$("#studentName");
//	var idInput=$("#studentId");
//	var dutyAdd=$("#dutyAdd");
//	if(students.length==0)logdiv.html("查无此人");
//	if(students.length==1){
//		if(nameInput.val()=="")nameInput.val(students[0].studentName);
//		if(idInput.val()=="")idInput.val(students[0].studentId);
//		dutyAdd.attr("sid",students[0].id);
//		logdiv.html("可以添加");
//	}
//	if(students.length>1)logdiv.html("有多名学生同名，请输入学号重新检索");
//}

//$(document).on("click","#dutyAdd",function(){
//	var sid=parseInt($(this).attr("sid"));
//	var time=$(".modal-body").attr("time");
//	var tid=$(".modal-body").attr("tid");
//	$.ajax({
//		url:"/admin/student_manage/dutyAdd",
//		type : 'post',
//		dataType : 'json',
//		data : {"student_Id":sid,"dtime":time,"teachBuildingId":tid},
//		success : function dutyAddCallBack(data){
//			var log=data.log;
//			if(log=="fail0"){
//				alert("人数已满，不能再加");
//			}else if(log=="fail1"){
//				alert("数据库好像出问题了！");
//			}else{
//				alert("添加成功");
//			}
//			var name=$("#studentName").val();
//			var row = parseInt(time/10);
//			var col = time%10;
//			$("td").each(function(i){
//				var rrow=$(this).closest("tr").attr("row");
//				var ccol=$(this).attr("col");
//				if(ccol==col&&rrow==row){
//					$(this).prepend("<span  class ='student-name' iid="+sid+">"+name+"</span>");
//				}
//			});
//		}
//	});
//})



