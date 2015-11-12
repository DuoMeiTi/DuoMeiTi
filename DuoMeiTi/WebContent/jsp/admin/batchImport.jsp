<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="main_content">
<div class="mycontent">






<form method="post" action="" enctype="multipart/form-data">

		<div class="alert alert-danger" role="alert">
			课表名称的格式为：教学楼名称-教室号
		</div>	
		<br/>
		<div id="alert" class="alert alert-success" role="alert" style="display:none;">...</div>
		<span class="btn btn-success btn-lg btn-file">
		    浏览文件 <input  type="file" id="file_upload" multiple>
		</span>
		&nbsp;
		&nbsp;
		&nbsp;
		&nbsp;
		&nbsp;
		<button class="btn btn-primary btn-lg" type="button" id="button" > 上传</button>
		
		<br/>
		<output id="list"></output>
		<br/>
		<br/>
		
		
		
</form>	
	
<script>

	
// 	var file_list = document.getElementById('file_upload').files;
	var file_list;
	$(document).on("change", "#file_upload", function(){
	
// 		alert("DOUBI");
		file_list = document.getElementById('file_upload').files;
		var output = [];
	    for (var i = 0, f; i < file_list.length; i++) 
	    {
// 	    	alert("YYY");
	    	f = file_list[i];
	      	output.push('<li><strong>', f.name, '</strong> (', f.type || 'n/a', ') - ',
	                  f.size, ' bytes');
	    }
	    document.getElementById('list').innerHTML = '<ul>' + output.join('') + '</ul>';

		
	})

	
	
	var file_list;
	var cntFileNumber;
	
	$(document).on("click", "#button", function(){
// 		alert("enter");
		file_list = document.getElementById('file_upload').files;		
		if(file_list.length == 0) 
		{
			$("#alert").hide();
			$("#alert").html("您还没有选中文件啊");
			$("#alert").show(500);
			return ;
		}
		
		cntFileNumber = 0;
		sendFile();
	});
	
	function sendFile() {		
		var fd = new FormData();		
		fd.append("file", file_list[cntFileNumber]);
	    $.ajax({  
	          url: "batchImport_upload" ,  
	          type: "POST",  
	          data: fd,  
	          async: true,  
	          cache: false,  
	          contentType: false,  
	          processData: false,  
	          success: sendFileCallback,
	     })	
	}
	
	function sendFileCallback(data)
	{
		cntFileNumber ++;
// 		alert(data.status);
// 		alert(data.message);
		if(data.status == "1") {
			
			$("#alert").html("成功上传" +(cntFileNumber-1) +"个文件"+"第" + cntFileNumber + "个文件上传失败:" + data.message);
			$("#alert").show();
			return ;
		}
		

		$("#alert").html("已经成功上传了" + cntFileNumber + "个文件");
		$("#alert").show();
		if(cntFileNumber >= file_list.length)
		{
			return ;
		}
		
		sendFile();
		
		
	}
	
	
	

</script>



		
		
		
		
		
</div>
</layout:override>



<%@ include file="/jsp/admin/base.jsp" %>