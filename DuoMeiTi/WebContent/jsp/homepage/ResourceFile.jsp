<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">

<h2> 资源文件</h2>



<div id="resourceFileTableDiv">
	<table class="table table-bordered" id="file_list_table">

	<tr class="active">
		<th colspan="2"> 文件列表 </th>
	</tr>
	
	
	
	<s:iterator value="file_path_list" var="i" status="index" >  


		<tr class="success" >
			<td filePath='<s:property value="@util.Util@getFileNameFromPath(#i.filePath)"/>' style="text-align:left;" class="col-lg-10">  
				 <a href="<s:property value="#i.filePath"/> "> 
<%-- 						<s:property value="#i.filePath.split('/')"/> --%>
					<s:property value="@util.Util@getFileNameFromPath(#i.filePath)"/>
				 </a>  
			</td>
			<%-- <td class="col-lg-2">
				<button type="button" class="btn btn-danger delete" filepath="<s:property value="#i.filePath"/>">删除</button>
					
				</button>
			</td> --%>

		</tr>
	</s:iterator>  
	</table>
</div>





<!-- 为了使用分页，include如下jsp -->
<%-- <%@ include file="/jsp/base/widgets/pagination.jsp" %> --%>

<script>

// 你可以定义pageAddtionalData变量，这个变量应该是json变量，这个变量可以直接通过ajax 在选择页码的时候传到后台
// 你应该重写下面这个函数，使其在回调的时候可以做你自己做的事情
// function requestPageCallback(data){
	
// 	$("#resourceFileTableDiv").html(data.file_path_html);
// }
</script>






</layout:override>










<%@ include file="/jsp/homepage/new_home_base.jsp" %>

