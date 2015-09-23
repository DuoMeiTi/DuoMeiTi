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
				name="radio-select" value=0
				onclick="return showdiv('#personCondition')" checked> 按负责人
			</label> <label class="radio-inline" style="margin-left:5%" > <input type="radio"
				name="radio-select" value=1
				onclick="return showdiv('#buildingCondition')"> 按教学楼
			</label> <label class="radio-inline" style="margin-left:5%" > <input type="radio"
				name="radio-select" value=2
				onclick="return showdiv('#equipmentCondition')"> 按设备
			</label> <label class="radio-inline" style="margin-left:5%" > <input type="radio"
				name="radio-select" value=3
				onclick="return showdiv('#timeCondition')"> 按时间
			</label>
			<button type="button" class="btn btn-default" style="float:right;margin-right:5%" onclick="search()">检索维修记录</button>
			<button type="button" class="btn btn-default" style="float:right;margin-right:5%">导出检索记录</button>
		</div>
		<br />
		<div class="searchCondition" id="conditions">
			<div class="searchCondition-person" id="personCondition">
				<select id="principal" class="form-control" style="width: 10%; float: left">
					<option value="0">姓名</option>
					<option value="1">学号</option>
				</select>
				<input type="text" class="form-control" id="personVal" style="width: 30%">
			</div>
			
			<div class="searchCondition-building" id="buildingCondition">
				<select id="building" class="form-control" style="width: 20%; float: left">
					<option value="0">一馆</option>
					<option value="1">综合教学一号楼</option>
				</select>
				<select id="classroom" class="form-control" style="width: 20%">
					<option value="0">101</option>
					<option value="1">205</option>
				</select>
			</div>
			
			<div class="searchCondition-equipment" id="equipmentCondition">
				<select id="device" class="form-control" style="width: 30%">
					<option value="0">计算机</option>
					<option value="1">投影</option>
					<option value="2">中央控制器</option>
					<option value="3">外围设备</option>
				</select>

			</div>
			
			<div class="searchCondition-time" id="timeCondition">
				<select id="time" class="form-control" style="width: 30%">
					<option value="0">2015年9月</option>
					<option value="1">2015年8月</option>
					<option value="2">2015年7月</option>
					<option value="3">2015年6月</option>
				</select>
			</div>
		</div>
		
		<br />
		<p>Hello<p/>
		<p>
			 <!-- <script language="javascript" type="text/javascript">
				//var items=$(":radio:checked"); //获取选中的项 

				//alert(items.val()); //拿到选中项的值 
			</script> -->
		<p/>
		<%-- <form class="form-horizontal" action="classroom_search"--%>

		<div id="maintainRecords_table">
			<iframe name="myFrame" frameborder="0" scrolling="no" width="800px" height="200px" src="/jsp/admin/record_query.jsp"></iframe>
		</div>
	</div>

	<script language="javascript" type="text/javascript">
		
	</script>




	<script>
		function sc_principal() {
			
			var options = $("#principal option:selected");
			alert("this is 0: " + options.val());
		}
		
		function sc_building() {
			/* var options = $("#building option:selected");
			var options1 = $("#classroom option:selected");
			alert("this is 1: " + options.val() + " - " + options1.val()); */
			alert("There's no need!");
		}
		
		function sc_device() {
			var options = $("#device option:selected");
			alert("this is 2: " + options.val());
		}
		
		function sc_time() {
			var options = $("#time option:selected");
			alert("this is 3: " + options.val());
		}
		
		function search() {
			var items=$(":radio:checked"); //获取选中的项;
			switch (parseInt(items.val())) {
				case 0:
					sc_principal();
					break;
				case 1:
					sc_building();
					break;
				case 2:
					sc_device();
					break;
				default:
					sc_time();
			} 
		}
	
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