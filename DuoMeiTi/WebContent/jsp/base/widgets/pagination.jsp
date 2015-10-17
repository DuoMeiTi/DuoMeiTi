<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<nav>
  <ul class="pagination pagination-lg">
  
  	<s:if test="currentPageNum - 2 > 1"> 
		<li>
	      <a href="#" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
  	</s:if>
  	<s:iterator  var="i" begin="@@max(currentPageNum - 2, 1)" end="currentPageNum - 1" step = "1" >
  			
			<li requestPageNum=<s:property value="i"/> > 
				<a href="#"><s:property value="i"/> </a>
			</li>
	</s:iterator>
	<li  class="active"> <a> <s:property value="currentPageNum"/>  </a></li>
	
	<s:iterator var="i"  begin="currentPageNum + 1" end="@@min(currentPageNum + 2, totalPageNum)" step = "1" >
			<li requestPageNum=<s:property value="i"/> > 
				<a href="#"> <s:property value="i"/> </a>  
			</li>
	</s:iterator>
	
	
 	<s:if test="currentPageNum + 2 < totalPageNum"> 
		<li>
	      <a href="#" aria-label="Previous">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
  	</s:if>
	
	
	
  </ul>
</nav>



