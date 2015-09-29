<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<s:iterator value="historyMes" var="i" status="index" >
	<s:if test="#i.from.id==another">
		<div class="message clearfix fr">
	   		<span class="triangle right"></span>
	    	<div class="article"><s:property value="#i.content"/></div>
		</div>
	</s:if>
	<s:else>
		<div class="message clearfix">
			<span class="triangle"></span>
	    	<div class="article"><s:property value="#i.content"/></div>
		</div>
	</s:else>
</s:iterator>

