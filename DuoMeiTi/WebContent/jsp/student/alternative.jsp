<%@ include file="/jsp/base/taglib.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta name="renderer" content="webkit"/>
        <title>多媒体管理系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" />
		<link rel="stylesheet" type="text/css" media="screen" href="/css/base/table.css" >		
		<link rel="stylesheet" type="text/css" media="screen" href="/css/base/base.css"/> 
		<link rel="stylesheet" type="text/css" media="screen" href="/css/admin/classroom_manage.css"/>
		<link rel="stylesheet" type="text/css" media="screen" href="/css/admin/repertory.css"/>
		<link href="/datepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
        <script type='text/javascript' src="/js/base/jquery-2.1.4.min.js"></script>
        <script type='text/javascript' src="/datepicker/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="/datepicker/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
		<style>
				td,tr,th{
				text-align:center;
				vertical-align:middle;
				}
		</style>
    </head>   

<body>
	<div class="mycontent">
		<div id="alterlist_table">
			<table class="table table-bordered table-striped"
				id="alter_table">
				<thead>
					<tr>
						<th style="width:10%;">资产编号</th>
						<th style="width:10%;">型号</th>
						<th style="width:10%;">名称</th>
						<th style="width:10%;">出厂号</th>
						<th style="width:10%;">出厂日期</th>
						<th style="width:10%;">使用时常</th>
						<th style="width:10%;"></th>
						<!-- <th style="width:10%;"></th> -->
					</tr>
				</thead>
				
				<s:iterator value="#session.alter_list" var="item">
					<tr class="success" id='<s:property value="#item.id"/>'>
							<td><s:property value="#item.rtNumber"/></td>
							<td><s:property value="#item.rtVersion"/></td>
							<td><s:property value="#item.rtType"/></td>
							<td><s:property value="#item.rtFactorynum"/></td>
							<td><s:property value="#item.rtProdDate"/></td>
							<td><s:property value="#item.rtReplacePeriod"/></td>
							<td><button type="button" class="btn btn-primary btn-sm" onclick="refresh(<s:property value="#item.rtId"/>)" 
									>加入教室</button></td>
							<%-- td><a href="<%=path%>/admin/classroomDevice/delalter_action?rtid=<s:property value="#item.rtId"/>" id="delalter" type="button" class="btn btn-primary btn-sm"  
									>删除设备</a></td> --%>
					</tr>
				</s:iterator>


			</table>
		</div>
	</div>
			<%-- <div >
				<ul>
					<s:iterator value="reper" var="device" status="i">
						<li id="device-<s:property value="#i.index"/>" />
							<div style="margin-bottom:5px">
								<label class="control-label device-type-label"><s:property value="#device.rtType"/>&nbsp;</label>
								<span>
									<button type="button" class="btn btn-primary btn-sm" 
									onclick="openRepairMoadl(<s:property value="#i.index"/>)">维修记录</button>
									<button type="button" class="btn btn-primary btn-sm"  
									onclick="openRepairMoadl(<s:property value="#i.index"/>)">移入备用</button>
								</span>
								<span style="visibility:hidden" class="device-id-span"><s:property value="#device.rtId"/></span>
								<span style="visibility:hidden" class="device-num-span"><s:property value="#device.rtNumber"/></span>
							</div>
							<table class="table device-table-bordered">
								<thead>
									<tr><td>资产编号</td><td>型号</td><td>名称</td><td>出厂号</td><td>出厂日期</td><td>审批日期</td></tr>
								</thead>
								<tbody>
									<tr><td><s:property value="#device.rtNumber"/></td>
										<td><s:property value="#device.rtVersion"/></td>
										<td><s:property value="#device.rtType"/></td>
										<td><s:property value="#device.rtFactorynum"/></td>
										<td><s:property value="#device.rtProdDate"/></td>
										<td><s:property value="#device.rtApprDate"/></td>
									</tr>
								</tbody>
							</table>
						</li>
					</s:iterator>
				</ul>
			</div> --%>
<script>	
	function GetRequest() {
		//url例子：XXX.aspx?ID=" + ID + "&Name=" + Name；  
		var url = location.search; //获取url中"?"符以及其后的字串  
		var theRequest = new Object();
		if (url.indexOf("?") != -1)//url中存在问号，也就说有参数。  
		{
			var str = url.substr(1);
			strs = str.split("&");
			for (var i = 0; i < strs.length; i++) {
				theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
			}
		}
		return theRequest;
	}
	function getId() {
		Request = GetRequest();
		var classid = Request['classroomid'];
		return classid;
	}
	
	$(window.parent.document).find("#main").load(function(){
		var main = $(window.parent.document).find("#main");
		var thisheight = $(document).height()+30;
		main.height(thisheight);
	});
	
	function refresh(rtid) {
		var url = "<%=path%>/student/classroomDevice/addalter_action?rtid=" + rtid;
		url = url+"&classroomid="+getId();
		parent.location.reload(); 
		window.open(url);
		window.opener.location.reload();
		window.opener.closeWindow();
	}
	</script>
</body>
</html>