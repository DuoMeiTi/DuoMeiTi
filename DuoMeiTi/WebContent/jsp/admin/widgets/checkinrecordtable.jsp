<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>

	<table class="table table-bordered table-hover" id="repertory_table">
		<tr class="active">
 		<th>姓名</th>
 		<th>签到时间</th>
		</tr>
		<s:iterator value="recordlist" var="i" status="index">
			<tr class="warning">
				<td class="click_me"> <s:property value="#i.student.user.fullName"/> </td>
				<td class="click_me"> <s:property value="#i.recordtime"/></td>
			</tr>
		</s:iterator>
	</table>