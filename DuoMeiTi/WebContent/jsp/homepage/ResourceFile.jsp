<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">

<h2> 资源文件</h2>
<div id="ResourceFileTableDiv">
	<%@ include file="/jsp/admin/HomepageModify/ResourceFileTable.jsp" %>
</div>



<div id="paginationDiv">
	<%@ include file="/jsp/base/widgets/pagination.jsp" %>
</div>



<script type='text/javascript' src="/js/base/pagination.js"></script>




<script> 

function requestPageNumCallback(data) 
{
	$("#ResourceFileTableDiv").html(data.file_path_html);

	
	
}


</script>




</layout:override>










<%@ include file="/jsp/homepage/new_home_base.jsp" %>

