<%@ include file="/jsp/base/taglib.jsp" %>
<layout:override name="main_content">
<div class="mycontent">
	<div >
	<h3>查看学生签到记录</h3>
	</div>
	<div class="form-group" >
	    			<label for="startTime">开始时间</label>
					<input id="startTime" type="date"></input>
					<label for="endTime">结束时间</label>
					<input id="endTime" type="date"></input>
					<label for="stuName">学生姓名：</label>
					<input id="stuName" style="width:100px" placeholder="输入学生姓名"/>
					<button type="button" class="btn btn-default"  id="addbutton" >查询</button>
<!-- 					<label for="changecheckinrule">&nbsp;&nbsp;修改签到时间</label> -->
<!-- 					<button type="button" class="btn btn-default" data-toggle="modal" data-target="#changetime" id="addbutton">修&nbsp;&nbsp;改</button> -->
					<button id="exportButton" type="button" class="btn btn-default">导&nbsp;&nbsp;出</button>
					<br/>
<!-- 					<table> -->
<!-- 					<tr> -->
<%-- 					<td><label>上午签到时间&nbsp;&nbsp;</label></td><td ><input id="amCheckIn" disabled="true" class="form-control" value='<s:property value='amCheckIn'/>'></input></td> --%>
<!-- 					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
<%-- 					<td><label>下午签到时间&nbsp;&nbsp;</label></td><td ><input id="pmCheckIn" disabled="true" class="form-control" value='<s:property value='pmCheckIn'/>'></input></td> --%>
<!-- 					</tr> -->
<!-- 					</table> -->
	</div>
	<div id="checkinRecordTableDiv">




			<%@ include file="/jsp/admin/widgets/checkinTable.jsp" %>

	</div>
	<!-- 模态框（Modal） -->
	
<!-- 			<div class="modal fade" id="changetime" tabindex="-1" role="dialog"  -->
<!-- 			   aria-labelledby="myModalLabel" aria-hidden="true"> -->
<!-- 			   <div class="modal-dialog"> -->
<!-- 			      <div class="modal-content"> -->
<!-- 			       <div class="modal-header"> -->
<!-- 					            <button type="button" class="close"  -->
<!-- 					               data-dismiss="modal" aria-hidden="true"> -->
<!-- 					                  &times; -->
<!-- 					            </button> -->
<!-- 						            <h4 class="modal-title" id="myModalLabel"> -->
<!-- 						               		修改签到时间 -->
<!-- 						            </h4> -->
<!-- 			         		</div> -->
<!-- 					        <form class="bs-example bs-example-form" id="change_time_form" role="form"> -->
<!-- 					         <div class="input-group"> -->
<!-- 						         <span class="input-group-addon">开始时间</span> -->
<!-- 						          <input type="number" name="starthour" class="form-control" placeholder="小时&nbsp;&nbsp;1-24" id="starthour"> -->
<!-- 						      </div> -->
<!-- 						      <br> -->
						      
<!-- 						      <div class="input-group"> -->
<!-- 						         <span class="input-group-addon">开始时间</span> -->
<!-- 						          <input type="number" name="startminute" class="form-control" placeholder="分钟&nbsp;&nbsp;0-59" id="startminute"> -->
<!-- 						      </div> -->
<!-- 						      <br> -->
						      
<!-- 						      <div class="input-group"> -->
<!-- 						         <span class="input-group-addon">结束时间</span> -->
<!-- 						         <input type="number" name="endhour" class="form-control" placeholder="小时&nbsp;&nbsp;1-24" id="endhour"> -->
<!-- 						      </div> -->
<!-- 						      <br> -->
<!-- 						       <div class="input-group"> -->
<!-- 						         <span class="input-group-addon">结束时间</span> -->
<!-- 						          <input type="number" name="endminute" class="form-control" placeholder="分钟&nbsp;&nbsp;0-59" id="endminute"> -->
<!-- 						      </div> -->
<!-- 						      <br> -->
<!-- 						        <div class="checkbox"> -->
<!-- 							          <label> -->
<!-- 							            <input type="checkbox" name="AM" id="AM" value="true"> 上午 -->
<!-- 							          </label> -->
<!-- 							          <label> -->
<!-- 							          	<input type="checkbox" name="PM" id="PM" value="true"> 下午 -->
<!-- 							          </label> -->
<!-- 					        	</div> -->
<!-- 						   </form> -->
<!-- 			         <div class="modal-footer"> -->
<!-- 			            <button type="button" class="btn btn-default"  -->
<!-- 			               data-dismiss="modal">关闭 -->
<!-- 			            </button> -->
<!-- 			            <button type="button" class="btn btn-primary" id="savechange"> -->
<!-- 			              		 保存 -->
<!-- 			            </button> -->
<!-- 			         </div> -->
<!-- 			      </div>/.modal-content -->
<!-- 			</div>/.modal -->
<!-- 			</div> -->



</div>

<script type='text/javascript' src="/js/admin/checkinrecord.js"></script>
<script>
window.onload=function(){
		var nowdate = new Date();
		var predate = new Date();
		predate.setDate(nowdate.getDate()-7);
		var nowday = nowdate.getDate()<10? "0" + nowdate.getDate():nowdate.getDate();
		var preday = predate.getDate()<10? "0" +predate.getDate():predate.getDate();
		var nowmonth = nowdate.getMonth()+1<10? "0"+(nowdate.getMonth()+1):nowdate.getMonth()+1;
		var premonth = predate.getMonth()+1<10? "0"+(predate.getMonth()+1):predate.getMonth()+1;
		document.getElementById("endTime").value = nowdate.getFullYear()+"-"+nowmonth+"-"+nowday;
		document.getElementById("startTime").value = predate.getFullYear()+"-"+premonth+"-"+preday;
		//document.getElementById("amCheckIn").innerHTML = "hehehe";
		//document.getElementById("pmCheckIn").innerHTML = "hehehe";
	}
// 	function sendRequestPage(currentPageNum) {
// 		var starttime = $("#startTime").val();
// 		var endtime = $("#endTime").val();
// 		var data = {"currentPageNum": currentPageNum , "isAjaxTransmission":true,"startTime":starttime,"endTime":endtime};
		
// 		if(typeof(pageAddtionalData)!="undefined")
// 			data = $.extend({}, data, pageAddtionalData);
// 	    $.ajax({
// 	        url: '',
// 	        type: 'post',
// 	        dataType: 'json',
// 	        data: data,
// 	        success: _requestPageCallback,
// 	        error: requesterror
// 	      });	
// 	}
// // 你可以定义pageAddtionalData变量，这个变量应该是json变量，这个变量可以直接通过ajax 在选择页码的时候传到后台
// // 你应该重写下面这个函数，使其在回调的时候可以做你自己做的事情
// function requestPageCallback(data){
// 	document.getElementById("recordstable").innerHTML=data.newtablestring;
// }
</script>
</layout:override>

<%@ include file="/jsp/admin/base.jsp" %>