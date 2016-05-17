<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>



<s:iterator value="deviceReplaceList" var="i" status="index" >
	         	 	<div class="usoft-listview-basic">
						<ul>
						   <li>
						      <span class="usoft-listview-item-date">
<%-- 						      		<s:property value="#i.rtDeadlineData.toString()"/> --%>
						      </span>
						      <span>
<!-- 						      	<a href="#" > -->
						      		<s:property value="#i.rtClassroom.teachbuilding.build_name"/>
						      		<s:property value="#i.rtClassroom.classroom_num"/>
						      		
						      		<s:property value="#i.rtType"/>
<!-- 						      	</a> -->
						      </span>
						    </li>
						  </ul>	
					</div>
</s:iterator>