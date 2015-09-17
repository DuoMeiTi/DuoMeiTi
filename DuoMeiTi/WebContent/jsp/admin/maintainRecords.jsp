<%@page import="java.util.ArrayList,model.Classroom"%>
<%@ include file="/jsp/base/taglib.jsp"%>

<layout:override name="main_content">
<style>
td,tr,th{
	text-align:center;
	vertical-align:middle;
}
</style>

	<div class="mycontent">
		<div class="row">
			<a type="button" class="btn btn-primary"
				style="width: 49%; float: left" href="/admin/classroomDevice/maintainRecords">设备维修记录</a>
			<a type="button" class="btn btn-primary"
				style="width: 49%; float: right" href="/admin/classroomDevice/equipmentQueryAndEdit">设备信息查询及批量修改</a>
		</div>
		<hr>

		<div class="radios">
			<label class="radio-inline" style="margin-left:5%" > <input type="radio"
				name="radio-select" id="person" value="person"
				onclick="return showdiv('#personCondition')" checked> 按负责人
			</label> <label class="radio-inline" style="margin-left:5%" > <input type="radio"
				name="radio-select" id="building" value="building"
				onclick="return showdiv('#buildingCondition')"> 按教学楼
			</label> <label class="radio-inline" style="margin-left:5%" > <input type="radio"
				name="radio-select" id="equipment" value="equipment"
				onclick="return showdiv('#equipmentCondition')"> 按设备
			</label> <label class="radio-inline" style="margin-left:5%" > <input type="radio"
				name="radio-select" id="time" value="time"
				onclick="return showdiv('#timeCondition')"> 按时间
			</label>
			<button type="button" class="btn btn-default" style="float:right;margin-right:5%">检索维修记录</button>
			<button type="button" class="btn btn-default" style="float:right;margin-right:5%">导出检索记录</button>
		</div>
		<br />
		<div class="searchCondition" id="conditions">
			<div class="searchCondition-person" id="personCondition">
				<select class="form-control" style="width: 10%; float: left">
					<option>姓名</option>
					<option>学号</option>
				</select> <input type="text" class="form-control" id="personVal"
					style="width: 30%">
			</div>
			<div class="searchCondition-building" id="buildingCondition">
				<select class="form-control" style="width: 20%; float: left">
					<option>一馆</option>
					<option>综合教学一号楼</option>
				</select> <select class="form-control" style="width: 20%">
					<option>101</option>
					<option>205</option>
				</select>
			</div>
			<div class="searchCondition-equipment" id="equipmentCondition">
				<select class="form-control" style="width: 30%">
					<option>计算机</option>
					<option>投影</option>
					<option>中央控制器</option>
					<option>外围设备</option>
				</select>

			</div>
			<div class="searchCondition-time" id="timeCondition">
				<select class="form-control" style="width: 30%">
					<option>2015年9月</option>
					<option>2015年8月</option>
					<option>2015年7月</option>
					<option>2015年6月</option>
				</select>
			</div>
		</div>
		
		<br />

		<%-- <form class="form-horizontal" action="classroom_search"--%>

		<div id="maintainRecords_table">
			<table class="table table-bordered table-striped"
				id="Records_table">
				<thead>
					<tr>
						<th>维修人</th>
						<th>教室</th>
						<th>时间</th>
						<th>设备名称</th>
						<th>维修情况</th>
					</tr>
				</thead>
				
				<s:iterator value="maintainRecords_list" var="item">
					<tr class="success" id='<s:property value="#item.id"/>'>
						<td><s:property value="#item.person" /></td>
						<td><s:property value="#item.classroom" /></td>
						<td><s:property value="#item.time" /></td>
						<td><s:property value="#item.equipmentName" /></td>
						<td><s:property value="#item.detail" /></td>
						</td>
					</tr>
				</s:iterator>


			</table>
		</div>
	</div>

	<script language="javascript" type="text/javascript">
		
	</script>




	<script>
		function showdiv(objId) {
			$('#personCondition').hide();
			$('#buildingCondition').hide();
			$('#equipmentCondition').hide();
			$('#timeCondition').hide();
			$(objId).show();
			return true;
		}
		function hidediv(objId) {
			document.getElementById(objId).style.display = "none";
		}
		$(window).load(function() {
			$('#personCondition').show();
			$('#buildingCondition').hide();
			$('#equipmentCondition').hide();
			$('#timeCondition').hide();
		});

		$("#sc_button").Click(function() {
			var params = $('#classroom_search_form').serialize(); //利用jquery将表单序列化
			params = decodeURIComponent(params, true);
			$.ajax({
				url : 'classroom_search',
				type : 'post',
				dataType : 'json',
				data : params,
				success : ClassroomSearchCallback
			});
		});

		function ClassroomSearchCallback(data) {
			if (data.status == "0") {

				$("#classroom_search_table").html(data.classroominfo.html);
				animatedShow("查询成功");
			} else if (data.status == "1") {
				animatedShow("查询关键字为空");
			} else {
				alert("error with status" + data.status);
			}
			//$("#add_new_courseplan").attr("disabled",false);

			//     	alert($(document).find("#added_user_tr").html());
			//     	alert(data.added_user_html);
			//     	alert(data.username);
		}
	</script>
</layout:override>

<%@ include file="/jsp/admin/base.jsp"%>