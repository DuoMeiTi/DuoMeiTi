<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<center>
		<div class="classroomTableDiv""> 
			<table class="table table-bordered table-striped" id="contacts_table" >
				<s:iterator begin="0" end="contacts_list.size()-1" var="i" step="5">
					<tr class="">
					
						<s:iterator  var="j" begin="0" end="@@min(contacts_list.size()-#i-1,4)" step="1">
							<td
								fullName='<s:property value="contacts_list.get(#i+#j).user.fullName"/>'
								studentId='<s:property value="contacts_list.get(#i+#j).studentId"/>'
								phoneNumber='<s:property value="contacts_list.get(#i+#j).phoneNumber"/>'
							>
								
									<s:property value="contacts_list.get(#i+#j).user.fullName"/><br>
									<s:property value="contacts_list.get(#i+#j).studentId"/><br>
									<s:property value="contacts_list.get(#i+#j).phoneNumber"/><br>
								
								
								<%-- <s:if test="makeUrl().contains(@util.Const@AdminRole)"> --%>
									<!-- <button class="btn btn-info  btn-primary delete">删除</button> -->
								<%-- </s:if> --%>
							
							</td>
						</s:iterator>
					 </tr>
				</s:iterator>
			</table>
		</div>
		
		<%-- <table class="table table-bordered table-hover" id="contacts_table" style="width:1000px;">
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
		</table> --%>
</center>