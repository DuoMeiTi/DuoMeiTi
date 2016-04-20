<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>
	<table class="table table-bordered table-hover" id="repertory_table">
		<tr class="active">
 			<th>设备类型</th>
 			
			<th>资产编号</th>
 			<th>型号</th>
 			
 			
 			<th>出厂日期</th>
 			<th>审批日期</th>
 			<th>出厂号</th>
 			
 			<th>使用状态</th>
			<th>查看状态历史</th>
 			<th>删除</th>
		</tr>
		<s:iterator value="repertory_list" var="i" status="index">
			<tr class="warning" rt_id="<s:property value="#i.rtId"/>">
			
				<td class="click_me"> <s:property value="#i.rtType"/> </td>
				
				<td class="click_me"> <s:property value="#i.rtNumber"/> </td>
				<td class="click_me"> <s:property value="#i.rtVersion"/> </td>
							
				<td class="click_me"> <s:property value="@util.Util@formatTimestampToOnlyDate(#i.rtProdDate)"/> </td>
				<td class="click_me"> <s:property value="@util.Util@formatTimestampToOnlyDate(#i.rtApprDate)"/> </td>
				<td class="click_me"> <s:property value="#i.rtFactorynum"/> </td>					
				<td class="click_me"> <s:property value="#i.rtDeviceStatus"/> </td>
				<td > 
					<button type="button" class="btn btn-info watchDeviceStatusHistory" >状态历史</button> 
				</td>
				<td> 
					<button type="button" class="btn btn-danger delete" >删除</button> 
				</td>
			</tr>
		</s:iterator>
	</table>