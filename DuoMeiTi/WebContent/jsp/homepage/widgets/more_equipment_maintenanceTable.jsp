<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<s:iterator value="repair_list" var="i" status="index" >
	         	 	<div class="usoft-listview-basic">
						<ul>
						   <li>
						      <span class="usoft-listview-item-date">
						      		<s:property value="#i.repairdate.toString()"/>
						      </span>
						      <span>
						      	<a href="#" >
						      		<s:property value="#i.repairman.fullName"/>
						      		维修
						      		<s:property value="#i.device.classroom.teachbuilding.build_name"/>
						      		教室
						      		<s:property value="#i.device.classroom.classroom_num"/>
						      		<s:property value="#i.device.rtType"/>
						      		:
						      		<s:property value="#i.repairdetail"/>
						      	</a>
						      </span>
						    </li>
						  </ul>	
					</div>
		        </s:iterator>