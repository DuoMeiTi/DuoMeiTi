<%@ include file="/jsp/base/taglib.jsp" %>


<!-- 	<div class="mycontent"> -->
	
	
	
		<div class="form-group col-lg-offset-1">
		
		    <label for="username" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上传照片:</label>
		    <br/>
		    <br/>
		    <div class="col-lg-1">
		 		<input type="file" name="profilePhotoPath" id="picture_upload"  placeholder="图片地址">
		 	</div> 
			<br/>
			<br/>
		
			<label class="col-lg-2 control-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:</label>
			<div class="col-lg-2">
		 		<input type="text" class="form-control" id="remark" name="remark" >
		 	</div>
		 	<div class="col-lg-1">
		 	<button type="button" class="btn btn-success col-lg-offset-4" id="picture_up">上传</button> 
		 	</div>
		</div>
		<hr>
		<br/>
		<br/>
		<br/>
		<br/>

		<table>
			<s:iterator begin="0" end="picture_list.size()-1" var="i" step="3">
				<tr class="">
					<s:iterator  var="j" begin="0" end="@@min(picture_list.size()-#i-1,2)" step="1">
					<td height="480" width="400">
						<div  class="text" style=" text-align:center;">
							<label>&nbsp;<s:property value="picture_list.get(#i+#j).remark"/>&nbsp;</label>
						</div>
						<div>
							<img src="<s:property value="picture_list.get(#i+#j).path"/>" height="360px" width="300px" >  
						</div>
						<div id ="<s:property value="picture_list.get(#i+#j).id"/>">
							<button type="button" class="btn btn-primary btn-sm delete col-lg-offset-4" id="delede-button">删除</button>
						</div>
					</td>
					</s:iterator>
				</tr>
			</s:iterator>
		
		</table>




			
	
		<script>
		$(document).on('click',"#picture_up",function(){
			
	        var f_id = $("#picture_upload").val();
	        var fd = new FormData();
	        if(f_id.length != 0){
	        	/* alert("not empty"); */
	        	fd.append("file", document.getElementById('picture_upload').files[0]);
	        	fd.append("remark",$("#remark").val());
	        	
	        	fd.append("classroomId",$("#classroomid").text());
	        	
		       
		        $.ajax({
		          url: 'picture_upload',
		          type: 'post',
		          dataType: 'json',
		          data: fd,
		          async: true,  
		          contentType: false,  //
		          processData: false,  
		          success: pictureUpCallback 
		        }); 
	        }
	        else{
	        	alert("请选择照片！");
	        }
			
			
		})
		
		function pictureUpCallback(data)
	    {    	
			alert("上传成功");
			location.reload();

	    }	
		
		//delete
		var delete_Id;
		$(document).on("click", ".delete", function() {
			var temp = confirm("删除不可恢复！");
			if (temp == true) {
				delete_Id = $(this).parents("div").attr("id");// attr所选元素属性值 
// 				alert(delete_Id); 
				$.ajax({
					url : 'picture_delete',
					type : 'post',
					dataType : 'json',
					data : {"picID" : delete_Id,},// {"后台",""}
					success : deleteCallback
				});
			}
		})

		function deleteCallback(data) {
			location.reload() 
			alert("删除成功！");
		}

		
		
		</script>

	
	
	