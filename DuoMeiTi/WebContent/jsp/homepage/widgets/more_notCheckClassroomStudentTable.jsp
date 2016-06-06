<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>


<s:iterator  var="i"  begin="0" end = "allNotCheckStudentList.size()-1" >

<div class="usoft-listvie	w-basic">
	<ul>
	   <li>
	      <span class="usoft-listview-item-date"> </span>
	      
	      <span>
	      	<strong>第<s:property value="allNotCheckStudentList.size() - #i"/>周未检查教室的学生: </strong>
<!-- 	      	[  -->
	      		<s:iterator  var="j"  begin="0" end = "allNotCheckStudentList.get(#i).size()-1" >
	      			<s:if test="#j > 0">
	      			,
	      			</s:if>
	      			<s:property value="allNotCheckStudentList.get(#i).get(#j).user.fullName"/>
      			</s:iterator>
<!--       			] -->
<%--  	      		<s:property value="#i.principal.user.fullName"/> --%>
<!-- 	      		未检查 -->
<%-- 	      		<s:property value="#i.teachbuilding.build_name"/> --%>
<%-- 	      		<s:property value="#i.classroom_num"/> --%>
	      		
	      </span>
	    </li>
	  </ul>	
</div>
</s:iterator>














