<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="main_content">

<link rel="stylesheet" type="text/css" media="screen" href="/css/student/chooseclass.css"/> 

<div class="mycontent">
	
	<div class="alert alert-success" role="alert">
		注意：方格中的数字表示当前剩余的选班量，红色格子表示自己选中的时间段
	</div>
	
	
	

	<h3>选择值班时间地点</h3>
	<div class="teachbuilding-droplist">
	
		<select class="form-control buildingSelect">
			<option value=-1>请选择选班地点</option>
			<s:iterator value="dutyPlaceList" var="i" status="index" >
  				<option value=<s:property value="#i.id"/>><s:property value="#i.placeName"/></option>
  			</s:iterator>
  		</select>
	</div>
	
	



	<div class="time-table hide" iid=<s:property value="#session.student_id"/> 
	
		studentId=<s:property value="#session.student_id"/>>
		
		<h3>选班时间段列表</h3>
		<table class="table table-bordered">
			<thead>
				<tr class="row">
					<th class="col-md-2">值班时间</th>
					<s:iterator value="{'一','二','三','四','五','六','日'}" var='week'>
						<th class="col-md-1.5"><s:property value='week'/></th>
					</s:iterator>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="{'7:40 ~~ 9:20','9:50 ~~ 11:30','13:15 ~~ 14:50','15:20 ~~ 17:00','17:45 ~~ 19:20'}" 
					var="time" status="i">
					
					<tr class="row" row='<s:property value="#i.index+1"/>'   >
					
						<td class="col-md-2"><s:property value="time"/> </td>
						
						<s:iterator value="{'0','0','0','0','0','0','0'}" var="num" status="j">
							<td class="col-md-1.5" 							
								dutyPieceTime='<s:property value="(#i.index) * 7 + #j.index  "/>' >
							</td>
						</s:iterator>
					</tr>
				</s:iterator>
			</tbody>
		</table>





	</div>
</div>

<!-- <script type='text/javascript' src="/js/student/chooseclass.js"></script> -->
<script>

$(document).on("change",".buildingSelect",function(){
	
	
	var id=parseInt($(this).val());	
	var timetable=$(".time-table");
	if(id < 0) 
	{
		timetable.addClass("hide");
		return ;
	}
	timetable.removeClass("hide");
	$("[dutyPieceTime]").each(function(i) {		
		$(this).html("");
		$(this).css("background-color", "white");
		
		$(this).attr("isChoosed", false);
	});
	
	var studentId = $(timetable).attr("studentId");

	
// 	alert(id);
	$.ajax({
		url:"/student/dutyManage_obtainDutyTable",
		type : 'post',
		dataType : 'json',
		data : 
			{"obtainDutyTable_dutyPlaceId":id,
			 "obtainDutyTable_studentId":studentId,
			},
		success : 
			function obtainDutyTableCallBack(data)
			{
// 				alert("GG");
				var dutyPieceList = data.obtainDutyTable_dutyPieceList;
				for(var i = 0; i < dutyPieceList.length; ++ i)
				{		
					var d = dutyPieceList[i];
					$("[dutyPieceTime="+d.time+"]").attr("numberOfDuty", d.numberOfDuty);
					$("[dutyPieceTime="+d.time+"]").attr("dutyPieceId", d.id);
					$("[dutyPieceTime="+d.time+"]").attr("dutyLeft", d.dutyLeft);
					
					$("[dutyPieceTime="+d.time+"]").html(d.dutyLeft
							);
				}
				
				
				
				var choosedDuty = data.obtainDutyTable_dutyChoosedPieceList;
				for(var i = 0; i < choosedDuty.length; ++ i)
				{
					var d = choosedDuty[i];
					$("[dutyPieceTime="+d.time+"]").css("background-color", "red");
					$("[dutyPieceTime="+d.time+"]").attr("isChoosed", true);
				}
				
				
			}		
	})

	
	
})

$(document).on("click","[dutyPieceTime]",function() {
	
	var timetable=$(".time-table");
	var studentId = $(timetable).attr("studentId");
	var cntDutyPiece = $(this);
	
	
// 	alert("GGG");
	if($(this).attr("isChoosed") =="true") 
	{
// 		alert("GGG");
		if(!window.confirm("确定要删除此时间段值班？")) return ;
		
// 		alert("HEHE");
		$.ajax({
			url:"/student/dutyManage_deleteSchedule",
			type : 'post',
			dataType : 'json',
			data : 
				{"deleteSchedule_dutyPieceId" : $(this).attr("dutyPieceId"),
				 "deleteSchedule_studentId": studentId,
				},
			success : 
				function deleteScheduleTableCallBack(data)
				{
// 					alert("GGGfff");
					var dutyLeft = data.deleteSchedule_dutyLeft;
					var status = data.status;
					var cmd = status.substr(0, 1);
					var info = status.substr(1);
					
					alert(info);
					if(cmd != "0") return ;
					$(cntDutyPiece).attr("dutyLeft", dutyLeft);
					$(cntDutyPiece).attr("isChoosed", false);
					$(cntDutyPiece).css("background-color", "white");
					$(cntDutyPiece).text(dutyLeft);
					
					
				}		
		})

		
		
	}
	else 
	{
		if(!window.confirm("确定要选择此时间段值班？")) return ;
		
// 		alert("GGG");
		$.ajax({
			url:"/student/dutyManage_addSchedule",
			type : 'post',
			dataType : 'json',
			data : 
				{"addSchedule_dutyPieceId" : $(this).attr("dutyPieceId"),
				 "addSchedule_studentId": studentId,
				},
			success : 
				function addScheduleTableCallBack(data)
				{
					var dutyLeft = data.addSchedule_dutyLeft;
					var status = data.status;
					var cmd = status.substr(0, 1);
					var info = status.substr(1);
					
					alert(info);
					if(cmd != "0") return ;
					$(cntDutyPiece).attr("dutyLeft", dutyLeft);
					$(cntDutyPiece).attr("isChoosed", true);
					
					$(cntDutyPiece).css("background-color", "red");
					$(cntDutyPiece).text(dutyLeft);
					
					
				}		
		})
		
	}
// 	alert("GG");
	
	
	
	
	
// 	alert("FFF");
	
	
})



















</script>

</layout:override>

<%@ include file="/jsp/student/base.jsp" %>

