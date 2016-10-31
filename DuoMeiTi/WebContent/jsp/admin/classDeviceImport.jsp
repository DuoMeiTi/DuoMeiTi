<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="mycontent">
<div class="mycontent">

<form method="post" action="" enctype="multipart/form-data">
		<br/>
		<div class="alert alert-danger" role="alert"  >

		</div>	

		<output id="list"></output>

		

		<span class="btn btn-success btn-lg btn-file">
		    浏览文件 <input  type="file" id="file_upload" >
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
						<s:if test="execute_selectTeachBuilding == #teachBuildingList.get(#i).build_id">
								selected="selected"
						</s:if> >					
						
						<s:property value = "#teachBuildingList.get(#i).build_name"/>
					</option>
				</s:iterator>
			</select>
		</div>
		
		<s:if test="execute_selectTeachBuilding != -1">
			<div class="col-md-4">

				本教学楼一共有<s:property value="execute_classroomDeviceMap.size()"/>个教室
			</div>
		</s:if>
		
	 </div>
		
		
		
		
		
		<br/>
		
	<div class="row">
		
		<div class="col-lg-12" id="classrooms">
			
		<table class="table table-bordered  ">
			<s:iterator value="execute_classroomDeviceMap">
				<tr>
					<td>
						<button class="btn btn-success">
							<s:property value="key"/>
						</button>
					</td>
					<td>
					<s:iterator value="value" var="device">
					
						<span class="label label-success">							
							<s:property value="#device.rtType"/>
							
						</span>					
						<span class="label label-info">
							<s:property value="#device.rtNumber"/>
						</span>
							&nbsp;
					</s:iterator>
					</td>
					
				</tr>
					
			</s:iterator>
		</table>
		</div>
	</div>
		
		
</form>	
	
<script>	

	$(document).on("change", "#selectTeachBuilding", function(){
		selectTeachBuilding = $("#selectTeachBuilding").val();
		
		window.location.href = "classDeviceImport?execute_selectTeachBuilding=" + selectTeachBuilding;
		
		
		
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
	})

	
	$(document).on("click", "#button", function(){
		file_list = document.getElementById('file_upload').files;
		
		selectTeachBuilding = $("#selectTeachBuilding").val();


		if(file_list == null || file_list.length == 0)
		{
			alert("您还没有选择教室设备文件，不能上传");
			return ;
		}
		
		
		
		var fd = new FormData();
		fd.append("file", file_list[0]);		
// 		selectTeachBuilding = $("#selectTeachBuilding").val();
// 		fd.append("upload_SelectTeachBuilding", selectTeachBuilding);
	    $.ajax({  
	          url: "classDeviceImport_importExcel" ,  
	          type: "POST",  
	          data: fd,  
	          async: true,
	          cache: false,
	          contentType: false,
	          processData: false,
	          success: function(data) {
	        	  
	          },
	     })	

		
		
	});
	
	

</script>



		
		
		
		
		
</div>
</layout:override>



<%@ include file="/jsp/admin/batchImport.jsp" %>