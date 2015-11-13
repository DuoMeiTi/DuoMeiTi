<%@ include file="/jsp/base/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
	<link href="/css/admin/classroom_detail.css" rel="stylesheet" />
	<script type='text/javascript' src="/js/admin/classroom_detail.js"></script>

	
	
	
	
	
	
	<!-- 添加设备 -->
	<div class="container-fluid">
		<div class="row col-lg-12">
			<div class="col-lg-7">
				<!-- <form action="#" method="post" id="addclassroomdevice"> -->
				<div class="form-group">
					<label for="zichanhao" style="float: left; vertical-align: middle;">添加设备:</label>
					<input type="text" class="form-control" id="zichanhao"
						style="width: 30%; float: left; margin-left: 5%;"
						placeholder="请输入资产编号"></input>
					<!-- <button type="submit" class="btn btn-info btn-sm" style="margin-left:5%">查询</button> -->
					<button type="button" class="btn btn-primary btn-sm"
						style="margin-left: 5%" onclick="add_classroomrt()">添加</button>
					<button id="alterSearch" class="btn btn-primary btn-sm"
						style="margin-left: 5%" onclick="alter_device()">备用设备</button>
				</div>
				<!-- </form> -->
	
			</div>
			<div class="col-lg-2">
				<!-- <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#schedule-modal">查看课表</button> -->
				<%-- <a type="button" href="<%=path%>/schedule/校部第一教学馆1-101(10301).pdf" target="_black" class="btn btn-primary btn-sm" onclick="checkschedule()">查看课表</a> --%>
			</div>
			<div class="col-lg-2">
				<button type="button" class="btn btn-primary btn-sm"
					data-toggle="modal" data-target="#check-record-modal">填写周检查记录</button>
			</div>
		</div>
	</div>


	<div class="mycontent">
		<!-- 设备列表 -->
		<div class="device">
			<div class="detail-div" id="device_jsp">
				<%@ include file="/jsp/classroom/device.jsp"%>
			</div>
		</div>
		
		<!-- 检查记录与维修记录 -->
		<div class="record">
			<ul>
				<li id="checkrecord_jsp"><%@ include
						file="/jsp/classroom/checkrecord.jsp"%></li>
				<li id="repairrecord_jsp"><%@ include
						file="/jsp/classroom/repairrecord.jsp"%></li>
			</ul>
		</div>
	</div>
	
	<!-- 备用设备 -->
	<div class="mycontent">
		<div id="alterdevice_jsp" style="display: none;">
			<%@ include file="/jsp/classroom/alterdevice.jsp" %>>
		</div>
	</div>
	
	<!-- Modal -->
	
	<div>
		<%@ include file="/jsp/classroom/modal.jsp" %>>
	</div>
	
	
	
	

<%-- 	<div class="row">
			<div class="col-lg-6 col-lg-offset-3 classbuilding">
				<span><s:property value="building.build_name"/>&nbsp;&nbsp;<s:property value="classroom.classroom_num"/></span>&nbsp;&nbsp;&nbsp;&nbsp;
				<span>负责人:</span>
				<span id="classroomid" style="visibility:hidden" value="<s:property value="classroom.id"/>"><s:property value="classroom.id"/></span>
				<span class="director-span"><s:property value="classroom.principal.user.username"/></span>
			</div>
			</div>
		<hr> --%>

	
	<!-- <div class="form-group">
							<label for="dtp_input2" class="col-md-2 control-label">选择日期</label>
							<div style="float:left;position:relative;" class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
								<input class="form-control" size="16" type="text" value="" readonly> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-remove"></span>
								</span>
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
							<input type="hidden" id="dtp_input2" value="" />
							<button type="button" style="float:right" class="btn btn-primary">查询</button>
							<br /><br /><br />
						</div> -->
						<!-- <table class="table table-bordered">
							<thead>
								<tr><td>1-2节</td><td>3-4节</td><td>5-6节</td><td>7-8节</td><td>9-10节</td></tr>
							</thead>
							<tbody>
								<tr><td>C语言</td><td>Java语言</td><td>PHP语言</td><td>C#语言</td><td>C++语言</td></tr>
							</tbody>
						</table> -->
	
	
	<%-- <div class="record">
				<ul>
					<li>
						<label class="control-label">周检查记录：</label>
						<table class="table device-table-bordered" id="checkrecord_table">
							<thead>
								<tr><td>检查人</td><td>教室状况</td><td>检查时间</td></tr>
							</thead>
							<tbody>
								<s:iterator value="checkrecords" var="checkrecord" status="i">
									<tr>
										<td width="20%"><s:property value="#checkrecord.checkman.fullName"/></td>
										<td><s:property value="#checkrecord.checkdetail"/></td>
										<td><s:property value="#checkrecord.checkdate"/></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</li>
					<li>
					<label class="control-label">维修记录：</label>
						<table class="table device-table-bordered" id="repairrecord_table">
							<thead>
								<tr><td>维修人</td><td>维修设备</td><td>维修情况</td><td>维修时间</td></tr>
							</thead>
							<tbody>
								<s:iterator value="repairrecords" var="repairrecord" status="i">
									<tr>
										<td width="20%"><s:property value="#repairrecord.repairman.fullName"/></td>
										<td><s:property value="#repairrecord.device.rtType"/></td>
										<td><s:property value="#repairrecord.repairdetail"/></td>
										<td><s:property value="#repairrecord.repairdate"/></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</li>
				</ul>
			</div>
		</div> --%>




<div id="alter_table">
			<!-- <button id="alterSearch" class="btn btn-primary btn-sm" style="margin-left:5%" onclick="alter_device()">备用设备</button> -->
			<!-- <a id="alterSearch" target="myFrame1" class="btn btn-primary btn-sm" style="margin-left:5%" onclick="alter_device()">备用设备</a> -->

			<iframe name="myFrame1" id="main" frameborder="0" scrolling="no" style="width:100%;" height="" ></iframe>
		</div>
		<script>
			<%-- function alter_device() {
				var classroomid = $("#classroomid").text();
				var href="<%=path%>/admin/classroomDevice/alter_action?classroomid="+classroomid;
				//document.getElementById("alterSearch").href=href;
				window.open(href,'myFrame1');
			}
			function closeWindow() {
				window.opener=null;
				window.open('','_self');
				window.close();
			} --%>
		</script>