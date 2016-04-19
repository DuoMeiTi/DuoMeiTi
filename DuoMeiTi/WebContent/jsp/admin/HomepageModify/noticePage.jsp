<%@ include file="/jsp/base/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>




 <layout:override name="mycontent">  
<div class="mycontent">



<div id="resourceFileTableDiv">
	<%@ include file="/jsp/admin/HomepageModify/notice.jsp" %>
</div> 

</div>

<script>

// // // 你可以定义pageAddtionalData变量，这个变量应该是json变量，这个变量可以直接通过ajax 在选择页码的时候传到后台 
// // // 你应该重写下面这个函数，使其在回调的时候可以做你自己做的事情 -->
//   function requestPageCallback(data){  
// 	$("#resourceFileTableDiv").html(data.file_path_html);  
//  } 
</script>






 </layout:override>  









 <%@ include file="/jsp/admin/HomepageModify/Base.jsp"%>


