<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<table class="table table-bordered" id="watchDeviceStatusHistory">
	<tr class="active">

		<th>设备状态</th>
		<th>状态更改人</th>
		<th>状态更改时间</th>
	</tr>
	
	<s:iterator var="i"  begin="0" end="deviceStatusHistoryList.size() - 1" step="1">
		<tr class="danger"  >	 
			
			<td >
				<s:property value="deviceStatusHistoryList.get(#i).status"  />
			</td>
			
			<td >
				<s:property value="deviceStatusHistoryList.get(#i).user.fullName"  />

			</td>
			
			<td>
				<s:property value="@util.Util@formatTimestamp(deviceStatusHistoryList.get(#i).date)" escape="false"/>
			</td>
		</tr>
	</s:iterator>
  	
</table>