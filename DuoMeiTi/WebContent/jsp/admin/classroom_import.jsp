<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="mycontent">
<div class="mycontent">


<form method="post" action="" enctype="multipart/form-data">
		
		
		
		
		<div class="alert alert-danger" role="alert"  style="margin-top:20px;">
			<p>选择相应的教室，进行批量上传, 以文件名称作为对上传照片的描述</p>
			
		</div>	
		<br/>
		
		<div>
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
		</div>
		
		<div class="row">
			<div class="col-lg-4">
				<div class="input-group">					
					<span class="input-group-addon">教学楼</span>
					<select class="form-control" id="selectTeachBuilding"   >			
						<option value=-1>	
							所有教学楼
						</option>
									
						<s:set name="teachBuildingList" value="@util.Util@getAllTeachBuildingList()" > 
						</s:set>
				
						<s:iterator var = "i" begin="0" end="#teachBuildingList.size() - 1" step="1">	
							<option  value= '<s:property value = "#teachBuildingList.get(#i).build_id"/>' >					
								<s:property value = "#teachBuildingList.get(#i).build_name"/>
							</option> 				
						</s:iterator>				
					</select>
				</div>
			</div>
			<div class="col-lg-8" id="classrooms">
				<%@ include file="/jsp/admin/widgets/classroomcheckbox.jsp" %>
			</div>
		</div>
		
		
		
</form>	
	
<script>

$(document).on("change","#selectTeachBuilding",function(){
	var selectTeachBuilding = $("#selectTeachBuilding").val();
	
	selectTeachBuilding = parseInt(selectTeachBuilding);
	
// 	selectTeachBuilding
	$.ajax({  
        url:'/admin/batchImport/batchImport_changeBuilding' ,  
        type: "POST",  
        data: {
        	"selectTeachBuilding":selectTeachBuilding,
        },           
        success: function(data) {
        	$("#classrooms").html(data.classroomcheckbox);
        }
   });  
});

//全选
$(document).on("click","#checkAll",function(){
	$("input[name='checkOne']").each(function(){
		$(this).prop("checked",$("#checkAll").prop("checked"));
	})
});

//单选
$(document).on("click","input[name='checkOne']",function(){
	var checkOnes = $("input[name='checkOne']");
	var count = 0;
	for(var i=0;i<checkOnes.size();i++){
		if($(checkOnes[i]).prop("checked") == false){
			$("#checkAll").prop("checked",false);
			break;
		}
		else{
			count++;
		}
	}
	if(count == checkOnes.size()){
		$("#checkAll").prop("checked",true);
	}
});













	
	var file_list;
	$(document).on("change", "#file_upload", function(){
		file_list = document.getElementById('file_upload').files;
		var output = [];
	    for (var i = 0, f; i < file_list.length; i++) 
	    {
	    	f = file_list[i];
	      	output.push('<li><strong>', f.name, '</strong> (', f.type || 'n/a', ') - ',
	                  f.size, ' bytes');
	    }
	    document.getElementById('list').innerHTML = '<ul>' + output.join('') + '</ul>';

		
	})

	
	
	var file_list;
	var classrooms = [];
	var cntFileNumber;
	
// 	var remark_list = [];
	
	$(document).on("click", "#button", function(){
		classrooms = [];
		var checkOnes = $("input[name='checkOne']");
		for(var i=0;i<checkOnes.size();i++){
			if($(checkOnes[i]).prop("checked") == true){
 				classrooms.push(parseInt($(checkOnes[i]).attr("id")));
// 				classrooms += $(checkOnes[i]).attr("id") + "-";
			}
		}
		console.log("classrooms:"+classrooms);
		file_list = document.getElementById('file_upload').files;		
		if(file_list.length == 0) 
		{
			$("#alert").hide();
			$("#alert").html("您还没有选中文件啊");
			$("#alert").show(500);
			return ;
		}
		if(classrooms.length == 0){
			$("#alert").hide();
			$("#alert").html("您还没有选择教室啊");
			$("#alert").show(500);
			return ;
		}
		
		cntFileNumber = 0;
		sendFile();
	});
	
	function sendFile() {		
		var fd = new FormData();		
		fd.append("file", file_list[cntFileNumber]);
		fd.append("classrooms",classrooms);
	    $.ajax({  
	          url: "batchImport_classroomUpload" ,  
	          type: "POST", 
	          dataType: 'json',
	          traditional:true,
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
			
			$("#alert").html("成功上传前" +(cntFileNumber-1) +"个文件， "+"第" + cntFileNumber + "个文件上传失败:" + data.message);
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



<%@ include file="/jsp/admin/batchImport.jsp" %>