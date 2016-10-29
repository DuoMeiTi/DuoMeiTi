<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="mycontent">
<div class="mycontent">

<form method="post" action="" enctype="multipart/form-data">
		<br/>
		<div class="alert alert-danger" role="alert"  >
			<p>选择一个教学楼之后才能上传课表，课表名称中应该包含所对应的教室号的子串; 课表上传后将会自动覆盖已有的课表</p>			
			<p>有按钮的教室表示此教室含有课程表，点击可以下载其课程表； 否则表示此教室不含有课程表</p>
		</div>	

		<output id="list"></output>

		
		<div id="uploadedFileNumber" class="alert alert-success" role="alert"
			style="display:none;"
		 >
		</div>
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
			<br/>
				
				
	 <div class="row">
	 	<div class="col-md-4">
			<select
				 class="form-control" 
				id="selectTeachBuilding"    >			
				<option value=-1> 所有教学楼 </option>
							
				<s:set name="teachBuildingList" value="@util.Util@getAllTeachBuildingList()" >  </s:set>
				
				<s:iterator var = "i" begin="0" end="#teachBuildingList.size() - 1" step="1">	
					<option  value= '<s:property value = "#teachBuildingList.get(#i).build_id"/>'
						<s:if test="execute_SelectTeachBuilding == #teachBuildingList.get(#i).build_id">
								selected="selected"
						</s:if> >					
						
						<s:property value = "#teachBuildingList.get(#i).build_name"/>
					</option>
				</s:iterator>
			</select>
		</div>
		
		<s:if test="execute_SelectTeachBuilding != -1">
			<div class="col-md-4">
<!-- 				<p > -->
					本教学楼一共有<s:property value="executeClassroomList.size()"/>个教室 
<!-- 				</p> -->
			</div>
		</s:if>
		
	 </div>
		
		
		
		
		
		<br/>
		
		<div class="row">
			
			<div class="col-lg-12" id="classrooms">
					<table class="table table-bordered table-striped" id="classroom_table" >
						<s:iterator begin="0" end="executeClassroomList.size()-1" var="i" step="6">
							<tr>
								<s:iterator  var="j" begin="0" end="@@min(executeClassroomList.size()-#i-1,5)" step="1">
									<td  >
										
										<s:if test="executeClassroomList.get(#i+#j).class_schedule_path != null">
											<a href='<s:property value="executeClassroomList.get(#i+#j).class_schedule_path" />'
											   class="btn btn-info"
												>
												<s:property value="executeClassroomList.get(#i+#j).classroom_num"/>
											</a>
										</s:if>
										<s:else>
											<span class="label label-default">
												<s:property value="executeClassroomList.get(#i+#j).classroom_num"/>
											</span>
										</s:else>

										<span class="label label-success" 
											  classroomNumber='<s:property value="executeClassroomList.get(#i+#j).classroom_num"/>'
											  style="display: none;"
											  >
											上传成功
										</span>										
									</td>
								</s:iterator>
							</tr>
						</s:iterator>
					</table>
			</div>
		</div>
		
		
</form>	
	
<script>	

	$(document).on("change", "#selectTeachBuilding", function(){
		selectTeachBuilding = $("#selectTeachBuilding").val();
		
		window.location.href = "classScheduleImport?execute_SelectTeachBuilding=" + selectTeachBuilding;
		
		
		
	})

	





	var file_list;
	var cntFileNumber;
	var uploadSuccessNumber = 0;

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
	    
	    
	    $("#uploadedFileNumber").hide();
	    $("#uploadedFileNumber").html("选中了" + file_list.length + "个课程表");
	    
	    $("#uploadedFileNumber").show(500);
	    
		
	})

	
	$(document).on("click", "#button", function(){
		file_list = document.getElementById('file_upload').files;
		
		selectTeachBuilding = $("#selectTeachBuilding").val();
		if(selectTeachBuilding == -1)
		{
			alert("您还没有选中教学楼，不能上传");
			return ;
		}
		
		if(file_list == null || file_list.length == 0)
		{
			alert("您还没有选择课程表文件，不能上传");
			return ;
		}
		
		
		$("[classroomNumber]").hide();		
		cntFileNumber = 0;
		
		uploadSuccessNumber = 0;
		sendFile();
	});
	
	function sendFile() {
		var fd = new FormData();
		fd.append("file", file_list[cntFileNumber]);
		
		selectTeachBuilding = $("#selectTeachBuilding").val();
		fd.append("upload_SelectTeachBuilding", selectTeachBuilding);
	    $.ajax({  
	          url: "classScheduleImport_upload" ,  
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
		var classroomNumber = data.upload_classroomNumber;
		var status = data.upload_status;
		var cmd = status.charAt(0);
		var classroomNumberSpan = "[classroomNumber='"+classroomNumber + "']";
		
		$(classroomNumberSpan).show();		
		if(cmd == 0) uploadSuccessNumber ++;
		cntFileNumber ++;
		if(cntFileNumber >= file_list.length)
		{		
			alert("成功上传了 " + uploadSuccessNumber + "张课程表")
			return ;
		}
		sendFile();
	}
	
	
	

</script>



		
		
		
		
		
</div>
</layout:override>



<%@ include file="/jsp/admin/batchImport.jsp" %>