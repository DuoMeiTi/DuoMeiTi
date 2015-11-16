<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">

<h2> 资源文件</h2>



<div id="resourceFileTableDiv">
	<%@ include file="/jsp/admin/HomepageModify/ResourceFileTable.jsp" %>
</div>





<!-- 为了使用分页，include如下jsp -->
<%@ include file="/jsp/base/widgets/pagination.jsp" %>

<script>

// 你可以定义pageAddtionalData变量，这个变量应该是json变量，这个变量可以直接通过ajax 在选择页码的时候传到后台
// 你应该重写下面这个函数，使其在回调的时候可以做你自己做的事情
function requestPageCallback(data){
	
	$("#resourceFileTableDiv").html(data.file_path_html);
}
</script>






</layout:override>










<%@ include file="/jsp/homepage/new_home_base.jsp" %>

