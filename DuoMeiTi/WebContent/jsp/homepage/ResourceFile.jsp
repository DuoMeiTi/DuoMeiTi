<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">

<h2> 资源文件</h2>



<div id="resourceFileTableDiv">
	<%@ include file="/jsp/admin/HomepageModify/ResourceFileTable.jsp" %>
</div>





<%@ include file="/jsp/base/widgets/pagination.jsp" %>

<script>

function requestPageNumCallback(data){
	
	$("#resourceFileTableDiv").html(data.file_path_html);
}
</script>






</layout:override>










<%@ include file="/jsp/homepage/new_home_base.jsp" %>

