<%@ include file="/jsp/base/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<layout:override name="main_content">
	<link href="/css/admin/classroom_detail.css" rel="stylesheet" />
	<script type='text/javascript' src="/js/student/classroom_detail.js"></script>



<div class="mycontent">

	<div class="row">
		<div class="col-lg-6 col-lg-offset-3 classbuilding">
			<span><s:property value="building.build_name"/></span>&nbsp;&nbsp;<span><s:property value="classroom.classroom_num"/></span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span>负责人:</span>
			<span id="classroomid" style="visibility:hidden"><s:property value="classroom.id"/></span>
			<span class="director-span"><s:property value="classroom.principal.user.username"/></span>
		</div>
	</div>
	<br>
		
		
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
			</div>
			<div class="col-lg-2">
				<button type="button" class="btn btn-primary btn-sm"
					data-toggle="modal" data-target="#check-record-modal">填写周检查记录</button>
			</div>
		</div>
	</div>
	
	<div >
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
		<!-- 备用设备 -->
		<div id="alterdevice_jsp" style="display: none;">
			<%@ include file="/jsp/classroom/alterdevice.jsp" %>>
		</div>

	</div>
	
	
	<!-- Modal -->
	
	<div>
		<%@ include file="/jsp/classroom/modal.jsp" %>
	</div>
	
	
</div>



</layout:override>



<%@ include file="/jsp/student/base.jsp" %>