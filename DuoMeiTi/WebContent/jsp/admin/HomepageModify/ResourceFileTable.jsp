<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/base/taglib.jsp" %>





<table class="table table-bordered" id="file_list_table">

	<tr class="active">
		<th colspan="2"> 文件列表 </th>
	</tr>
	
	
	
	<s:iterator value="file_path_list" var="i" status="index" >  


		<tr class="success" >
			<td filePath='<s:property value="@util.Util@getFileNameFromPath(#i.filePath)"/>' style="text-align:left;" class="col-lg-10">  
				 <a href="<s:property value="#i.filePath"/> "> 
					<s:property value="@util.Util@getFileNameFromPath(#i.filePath)"/>
				 </a>
<%-- 				 <video width="320" src="<s:property value="#i.filePath"/>"  controls="controls"> --%>
				 
<!-- 				 </video> -->
				   
			</td>
			<td class="col-lg-2">
				<button type="button" class="btn btn-danger delete" filepath="<s:property value="#i.filePath"/>">删除</button>
					
				</button>
			</td>

		</tr>
	</s:iterator>  
	<script>
		$(".delete").click(function(){
			var temp = confirm("删除不可恢复！");
			if(temp == true){
				var filePath = $(this).attr("filePath");
				//alert(filePath);
				$.ajax({
					url:"HomepageModify/FileUploadDelete",
					type: "POST",  
			        data: {'filePath':filePath},
			        dataType : 'json',
			        /* async: true,  
			        cache: false,  
			        contentType: false,  
			        processData: false, */  
			        success: deleteCallBack,
				});
			}
		})
		
		function deleteCallBack(data){
			//alert("ddddd");
			location.reload();
		}
		
	</script>
	
	

</table>
