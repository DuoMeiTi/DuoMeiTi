<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>

<nav >
  <ul class="pagination pagination-lg ">
  	
  	<li class="previous" id="firstPage"><a href="#">首页</a></li>
  
  	<s:if test="currentPageNum - 2 > 1"> 
		<li>
	      <a href="#" aria-label="Previous">
	        <span aria-hidden="true">...</span>
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
	        ...
	      </a>
	    </li>
  	</s:if>
  	
    
    <li class="next" id="lastPage" totalPageNum=<s:property value="totalPageNum"/>><a href="#">尾页 </a></li>
	

	
	
  </ul>
</nav>








