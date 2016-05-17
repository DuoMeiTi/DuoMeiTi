<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>


<s:iterator value="notice_list" var="i" status="index" >
	         	 	<div class="usoft-listview-basic">
						<ul>
						   <li>
						      <span class="usoft-listview-item-date">
						      		<s:property value="#i.time.toString()"/>
						      </span>
						      <span>
<!-- 						      	<a >						      	 -->

						      		<s:property value="#i.content" escape="false"/>
<!-- 						      	</a> -->
						      	
						      	<hr/>

						      </span>
						    </li>
						  </ul>	
					</div>
</s:iterator>



	
	
	

