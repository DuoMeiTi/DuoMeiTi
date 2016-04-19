<%@ include file="/jsp/base/taglib.jsp" %>


<!-- 	<div class="mycontent"> -->
	
		<div class="form-group col-lg-offset-1">
		
		    <label for="username" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传课表:</label>
		    <br/>
		    <br/>
		    <div class="col-lg-1">
		 		<input type="file" name="schedulePath" id="schedule_upload"  placeholder="图片地址">
		 	</div> 
			<br/>
			<br/>
		
			
		 	<div class="col-lg-1">
		 	<button type="button" class="btn btn-success col-lg-offset-4" id="schedule_up">上传</button> 
		 	</div>
		</div>
		<hr>
		
		
		<table class="table table-bordered" id="file_list_table">

			<tr class="active">
				<th> 课程表 </th>
			</tr>		
			<tr class="success" >
				<td style="text-align:left;">  
					<a href="<s:property value="schedulePath"/>"> 
					<s:property value="@util.Util@getFileNameFromPath(schedulePath)"/>
					</a>  
				</td>
			</tr>
		</table>
		
		
		<script>
		$(document).on('click', "#schedule_up", function(){
			var f_id = $("#schedule_upload").val();
			var fd = new FormData();
			if(f_id.length != 0){
				var temp = confirm("上传后将覆盖原有文件！");
				if(temp == true){
					fd.append("file", document.getElementById('schedule_upload').files[0]);
		        	fd.append("remark",$("#remark").val());
		        	
		        	fd.append("classroomId",$("#classroomid").text());
		        	
			        $.ajax({
			          url: 'schedule_upload',
			          type: 'post',
			          dataType: 'json',
			          data: fd,
			          async: true,  
			          contentType: false,  //
			          processData: false,  
			          success: pictureUpCallback 
			        }); 
				}
			}
			else{
				alert("请选择文件！");
			}
		})
		
		function scheduleUpCallBack(data){
			alert("上传成功！");
			location.reload();
		}
		
		
		
		
		
		</script>

			
			
			
		
	

	
	