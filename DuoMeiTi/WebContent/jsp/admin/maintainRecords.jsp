<%-- <%@page import="java.util.ArrayList,model.Classroom"%> --%>
<%-- <%@ include file="/jsp/base/taglib.jsp"%> --%>
<%-- <% --%>
// String path = request.getContextPath();
// String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
<%-- %> --%>
<%-- <layout:override name="main_content"> --%>
<!-- <style> -->
/* td,tr,th{ */
/* 	text-align:center; */
/* 	vertical-align:middle; */
/* } */
<!-- </style> -->

<!-- 	<div class="mycontent"> -->
		
<!-- 		<hr> -->

<!-- 		<div class="radios"> -->
<!-- 			<label class="radio-inline" style="margin-left:5%" > <input type="radio" -->
<!-- 				name="radio-select" value="0" -->
<!-- 				onclick="return showdiv('#buildingCondition')" checked> 按教学楼 -->
<!-- 			</label> -->
			
					
<!-- 			<label class="radio-inline" style="margin-left:5%" > <input type="radio" -->
<!-- 				name="radio-select" value="2" -->
<!-- 				onclick="return showdiv('#equipmentCondition')"> 按设备 -->
<!-- 			</label> <label class="radio-inline" style="margin-left:5%" > <input type="radio" -->
<!-- 				name="radio-select" value="3" -->
<!-- 				onclick="return showdiv('#timeCondition')"> 按时间 -->
<!-- 			</label> -->
<!-- 			<a id="commitSearch" type="button" target="myFrame" class="btn btn-default" style="float:right;margin-right:5%" onclick="startSearch()">检索维修记录</a> -->
<!-- 		</div> -->
<!-- 		<br /> -->
<!-- 		<div class="searchCondition" id="conditions"> -->
		
<!-- 			<div class="searchCondition-building" id="buildingCondition"> -->
<!-- 				<select id="building" class="form-control" style="width: 20%;"> -->
<%-- 				<s:iterator value="builds" var="build" status="i"> --%>
<%-- 					<option value="<s:property value="#build.build_id"/>"><s:property value="#build.build_name"/></option> --%>
<%-- 				</s:iterator> --%>
<!-- 				</select> -->
<!-- 			</div> -->
			

			
<!-- 			<div class="searchCondition-equipment" id="equipmentCondition" style="width:40%;"> -->
<!-- 				<div style="width:auto;float:left;"> -->
<%-- 				<s:select list="device" class="form-control" onchange="show_or_hide()" name="main_type" id="main_type"></s:select> --%>
<!-- 				</div> -->
<!-- 				<div class="searchCondition-equipment" id="sub_type" style="margin-left:10px;width:auto;float:left;"> -->
<!-- 					<div id="sub_type_main" style="width:100%"> -->
<%-- 						<s:select list="mainDevice" class="form-control" name="deviceid" id="main_device"></s:select> --%>
<!-- 					</div> -->
<!-- 					<div id="sub_type_cost" style="width:100%"> -->
<%-- 						<s:select list="costDevice" class="form-control" name="deviceid" id="cost_device"></s:select> --%>
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<script> -->
				
// 				$(function(){
// 					$('#cost_device').hide();
// 					$('#main_device').hide();
// 				})
// 				function show_or_hide()
// 				{
// 					var type=$('#main_type option:selected').val();
// 					if(type=="主要设备")
// 					{
// 						$('#cost_device').hide();
// 						$('#main_device').show();
						
// 					}
// 					else if (type=="耗材设备")
// 					{
// 						$('#cost_device').show();
// 						$('#main_device').hide();
// 					}
// 					else
// 					{
// 						$('#cost_device').hide();
// 						$('#main_device').hide();
// 					}
// 				}
<!-- 				</script>	 -->
<!-- 			</div> -->
		
<!-- 			<div class="searchCondition-time" id="timeCondition"> -->

<!-- 				<div class="form-group" style="margin-bottom:-0.5%;"> -->
<!-- 	    			<label for="startTime">开始时间</label> -->
<!-- <!-- 					<input id="startTime" type="date" value="2015-09-30"> --> -->
<!-- 					<input id="startTime" type="date" value=""> -->
					
<!-- 					<label for="endTime">结束时间</label>					 -->
<!-- <!-- 					<input id="endTime" type="date" value="2015-10-01"> --> -->
<!-- 					<input id="endTime" type="date" value=""> -->
					

					
					
<!-- 				</div> -->

<!-- 			</div> -->
<!-- 		</div> -->
		
<!-- 		<br /> -->
<!-- 		<p> -->

<!-- 		<p/> -->


<!-- 		<div id="maintainRecords_table"> -->
		
		
<!-- 			<iframe name="myFrame" frameborder="0" style="width:100%;height:1000px;" src="/jsp/admin/record_query.jsp"></iframe> -->
<!-- 		</div> -->
<!-- 	</div> -->

<!-- 	<script language="javascript" type="text/javascript"> -->
		
<!-- 	</script> -->




<!-- 	<script> -->
	
// 		function sc_condition(type,val)
// 		{
<%-- 			var href="<%=path%>/admin/classroomDevice/query_action"; --%>
			
// 			href=href+"?Type="+type+"&Value="+val;
// 			//alert(href);
// 			document.getElementById("commitSearch").href=href;
// 			/* alert(type + ":" +val); */
// 		}
// 		function sc_building() {
			
// 			var optionval = $("#building option:selected").val();
// 			sc_condition('1',optionval);
// 		}
		
		
// 		function sc_device() {
// 			if($('#main_device').is(':visible'))
// 			{
// 				var optionval=$('#main_device option:selected').val();
// 			}
// 			else if($('#cost_device').is(':visible'))
// 			{
// 				var optionval=$('#cost_device option:selected').val();
// 			}
// 			else
// 			{
// 				var optionval=null;
// 			}
// 			//alert(optionval);
			
			
// 			sc_condition('2',optionval);
// 		}
		
// 		function sc_time() {
// 			/* var optionval = $("#time option:selected").val() */
// 			var str = $("#startTime").val();
// 			var end = $("#endTime").val();
// 			optionval = str +":"+ end;
// 			sc_condition('3',optionval);
// 		}
		
// 		function startSearch() {
// 			var items=$(":radio:checked"); //获取选中的项;
// 			switch (parseInt(items.val())) {
// 				case 0:
// 					sc_building();
// 					break;
// 				case 2:
// 					sc_device();
// 					break;
// 				case 3:
// 					sc_time();
// 					break;
// 				default:
// 					alert("ERROR:Wrong val.");
// 					return false;
// 			} 
// 			return true;
// 		}
	
// 		function showdiv(objId) {
// 			$('#buildingCondition').hide();
// 			$('#equipmentCondition').hide();
// 			$('#timeCondition').hide();
// 			$(objId).show();
			
			
// // 			if("timeCondition" == $(objId).attr("id"))
// // 			{
// // 				var now = new Date();
// // 				var now_s = now.getFullYear().toString() + "-" + now.getMonth().toString() + "-" + now.getDate().toString();
// // 				alert(now_s);
// // 				$("#startTime").val(now_s);
				
// // 			}
			
// 			return true;
// 		}
// 		function hidediv(objId) {
// 			document.getElementById(objId).style.display = "none";
// 		}
// 		$(window).load(function() {
// 			$('#buildingCondition').show();
// 			//$('#buildingCondition').hide();
// 			$('#equipmentCondition').hide();
// 			$('#timeCondition').hide();
// 		});

// 		function ClassroomSearchCallback(data) {
// 			if (data.status == "0") {

// 				$("#classroom_search_table").html(data.classroominfo.html);
// 				animatedShow("查询成功");
// 			} else if (data.status == "1") {
// 				animatedShow("查询关键字为空");
// 			} else {
// 				alert("error with status" + data.status);
// 			}
// 			//$("#add_new_courseplan").attr("disabled",false);

// 			//     	alert($(document).find("#added_user_tr").html());
// 			//     	alert(data.added_user_html);
// 			//     	alert(data.username);
// 		}
<!-- 	</script> -->
<%-- </layout:override> --%>

<%-- <%@ include file="/jsp/admin/base.jsp"%> --%>