<%@ include file="/jsp/base/taglib.jsp" %>

<layout:override name="mycontent">
	
	
	<form method="post" action="" enctype="multipart/form-data">	
		<br/>
<!-- 		<div class="alert alert-danger" role="alert"> -->
<!-- 			<p>具有rar、zip、tar、7z、jar后缀的压缩文件不能上传！</p> -->
<!-- 		</div> -->
		<input type="file" id="file_upload">
		
		<br/>
		<button class="btn btn-primary" id="file_upload_button" type="button" > 上传</button>
	</form>
	
	<div id="ResourceFileTableDiv">
		<%@ include file="/jsp/admin/HomepageModify/ResourceFileTable.jsp" %>
	</div>
	

<script> 
	
	
	$(document).on("click", "#file_upload_button", function() {
		
		var fd = new FormData();
		var file_list = document.getElementById('file_upload').files;
		var file_name,file_suffix;
// 		var suffix = "rarziptar7zjar";
		if(file_list.length != 0)
		{
// 			file_name = file_list[0].name.split(".");
// 			file_suffix = file_name[file_name.length-1].toLowerCase();
// 			if(suffix.indexOf(file_suffix)>=0){
// 				alert("不允许上传压缩文件！");
// 				$("#file_upload").val("");
// 			}
// 			else
			{
				fd.append("file", file_list[0]);
				
				$.ajax({  
			          url: "HomepageModify/FileUploadInsert" ,  
			          type: "POST",  
			          data: fd,  
			          async: true,  
			          cache: false,  
			          contentType: false,  
			          processData: false,  
			          success: insertCallBack,
//		 	          {
//		 	        	  alert("GOOD");
//		 	        	  window.location.reload() 
//		 	          },  
//		 	          error: function (returndata) {  
//		 	              alert(returndata);  
//		 	          }  
			     });
			}
		}	
			
		
 		
	      

		
		
// 		xmlhttp=new XMLHttpRequest();
// 		alert("DOUBI");
// 		xmlhttp.open("POST","",true);
// 		var fd = new FormData();

//         fd.append("image", document.getElementById('file_upload').files[0]); 
// 		xmlhttp.send(fd);

		
	})
	
	function insertCallBack(data)
	{
		$("#file_upload").val("");
		$(document).find("#ResourceFileTableDiv").html(data.classroom_file_table);
		
// 		window.location.reload() 

	}
	


</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</layout:override>

<%@ include file="/jsp/admin/HomepageModify/Base.jsp" %>
