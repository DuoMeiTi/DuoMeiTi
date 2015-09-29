<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<ul class="message-list-group list-group">
	<s:if test="newMes.size>0">
		<s:iterator value="newMes" var="i" status="index" >
			<li class="list-group-item" fid=<s:property value="#i.fromId"/> fname="<s:property value="#i.fromUsername"/>">
				【来自 <s:property value="#i.fromUsername"/>】 <s:property value="#i.content"/>
			</li>
		</s:iterator>
	</s:if>
	<s:else>
		<li class="list-group-item">没有新消息</li>
	</s:else>
</ul>