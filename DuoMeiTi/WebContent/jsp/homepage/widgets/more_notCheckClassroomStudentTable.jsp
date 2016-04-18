<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>



<s:iterator value="notCheckClassroomStudentList" var="i" status="index" >
	         	 	<div class="usoft-listvie	w-basic">
						<ul>
						   <li>
						      <span class="usoft-listview-item-date">
<%-- 						      		<s:property value="#i.rtDeadlineData.toString()"/> --%>
						      </span>
						      <span>
						      	<a href="#" >
						      		<s:property value="#i.principal.user.fullName"/>
						      		未检查
						      		<s:property value="#i.teachbuilding.build_name"/>
						      		<s:property value="#i.classroom_num"/>
						      		
						      		
						      	</a>
						      </span>
						    </li>
						  </ul>	
					</div>
</s:iterator>