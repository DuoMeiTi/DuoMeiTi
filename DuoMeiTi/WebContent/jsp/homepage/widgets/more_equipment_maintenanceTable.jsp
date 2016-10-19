<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<s:iterator value="repair_list" var="i" status="index" >
  	 <div class="usoft-listview-basic">
		<ul>
		   <li>
		      <span class="usoft-listview-item-date">
		      		<s:property value="@util.Util@formatTimestamp(#i.repairdate.toString())"/>
		      </span>
		      <span>

		      		<s:property value="#i.repairmanFullName"/>
		      		维修		      		
		      		<strong>
			      		<s:property value="#i.teachingBuildingName"/>
			      		<s:property value="#i.classroomName"/>
		      		</strong>
		      		
		      		<s:property value="#i.deviceType"/>
		      		:
		      		<s:property value="#i.repairdetail"/>

		      </span>
		    </li>
		  </ul>	
	</div>
</s:iterator>