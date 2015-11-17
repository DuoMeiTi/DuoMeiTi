<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>



	<table class="table table-bordered" id="admin_information_table">
			
			<tr class="row" >
				<th >姓名</th>
				<th>学号</th>
				<th>签到时间</th>
			</tr>
			<s:iterator value="recordlist" var="i"  status="index"> 
    			<tr class="row" id=<s:property value="#i.id"/>>
					<td class="col-lg-1.5"> <s:property value="#i.student.user.fullName"/> </td>
					<td class="col-lg-1.5"><s:property value="#i.student.studentId"/></td>
					<td class="col-lg-1.5"> <s:property value="#i.recordtime"/> </td>
					<td></td>
				</tr>
			</s:iterator>
		</table>