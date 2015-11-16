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
						      	<a href="#" >
						      		<s:property value="#i.title"/>
						      		:
						      		<s:property value="#i.content"/>
						      	</a>
						      </span>
						    </li>
						  </ul>	
					</div>
</s:iterator>



	
	
	

