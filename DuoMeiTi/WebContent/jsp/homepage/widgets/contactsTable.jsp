<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<center>
		<table class="table table-bordered table-hover" id="contacts_table" style="width:1000px;">
			<tr>
				<th class="col-lg-3"> 姓名 </th>
				<th class="col-lg-4"> 学号</th>
				<th class="col-lg-5"> 电话 </th>
			</tr>
					
			<s:iterator value="contacts_list" var="i" status="index" >  
				<s:if test="#i.isPassed == @model.StudentProfile@Passed && #i.isUpgradePrivilege!=@model.StudentProfile@DepartureStudent">							
					<tr id=<s:property value="#i.id"/>>
						<td class="col-lg-3">   <s:property value="#i.user.fullName"/>    </td>
						<td class="col-lg-4">   <s:property value="#i.studentId"/>   </td>
						<td class="col-lg-5">   <s:property value="#i.user.phoneNumber"/> </td>
					</tr>
				</s:if>
			</s:iterator>
		</table>
</center>