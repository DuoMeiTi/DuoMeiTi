<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<table class="table table-bordered" id="exam_table">
	<tr class="active">
		<th style="display:none;">编号</th>
		<th>题目</th>
		<th>选项</th>
		<th>操作</th>
	</tr>
	
	<s:iterator var="i"  begin="0" end="qtitle.size()-1" step="1">
		<tr class="danger" titleId=<s:property value="qtitle.get(#i).emId" />>
			<td style="display:none;"><s:property value="qtitle.get(#i).emId" /></td>
			
			<td class="titleContent"><s:property value="qtitle.get(#i).emTitle" escape="false"/></td>
			
			<td class="optionList">
				<s:iterator var="j" begin="0" end="qoption.get(#i).size() - 1" step="1">
					<div isRight='<s:property value="qoption.get(#i).get(#j).emCheck" />'
					     optionText='<s:property value="qoption.get(#i).get(#j).emOption" />' >
					
					
						<s:if test="qoption.get(#i).get(#j).emCheck.equals('true')" >
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
						</s:if>
						<s:else>
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						</s:else>
					
						<s:property value="qoption.get(#i).get(#j).emOption" />
					</div>
				</s:iterator>
			</td>
			
			<td>
				<button type="button" class="btn btn-success edit">编辑</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-danger delete">删除</button>
			</td>
		</tr>
	</s:iterator>
  	
</table>